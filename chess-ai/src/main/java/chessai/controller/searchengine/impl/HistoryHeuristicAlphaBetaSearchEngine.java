package chessai.controller.searchengine.impl;

import chessai.controller.ChessManager;
import chessai.controller.evaluation.IBoardEvaluator;
import chessai.controller.movegenerator.AbstractChessMoveGenerator;
import chessai.controller.searchengine.AbstractSearchEngine;
import chessai.model.*;
import chessai.model.historyheuristic.HistoryHeuristic;
import chessai.model.historyheuristic.HistoryScoreChessMove;
import chessai.util.ChessBoardUtils;

import java.util.*;

/**
 * 结合历史启发和Alpha-Beta fail-soft算法的搜索引擎
 * 历史启发：History Heuristic
 */
public class HistoryHeuristicAlphaBetaSearchEngine extends AbstractSearchEngine {
    /**
     * 当前局面走法集合 <搜索层数, 每层的合法走法>
     */
    private Map<Integer, List<HistoryScoreChessMove>> historyScoreMoves = new HashMap<>();

    /**
     * 历史启发核心类
     */
    protected HistoryHeuristic historyHeuristic = new HistoryHeuristic();

    public HistoryHeuristicAlphaBetaSearchEngine(IBoardEvaluator boardEvaluator) {
        this.boardEvaluator = boardEvaluator;
    }

    @Override
    public ChessMove searchBestMove() {
        // 初始化历史记录表
        historyHeuristic.resetHistoryTable();
        PointState[][] boardPosition = ChessBoard.getInstance().getCurChessBoard();
        clearPossibleMoves();
        bestMove = null;
        alphaBeta(boardPosition, ChessBoardUtils.SEARCH_DEPTH, ChessBoardUtils.SEARCH_DEPTH, -ChessBoardUtils.MAX_SEARCH_VALUE, ChessBoardUtils.MAX_SEARCH_VALUE);
        return bestMove;
    }

    /**
     * 用以产生当前局面中所有可能的走法
     *
     * @param boardPosition 当前局势
     * @param curDepth      当前搜索的层数，同一棋子在每层的走法存在不同的位置
     * @param maxDepth      本次搜索的最大搜索层数
     */
    @Override
    public void createPossibleMoves(PointState[][] boardPosition, int curDepth, int maxDepth) {
        // 在每次生成指定层的所有可能走法时需要先清除掉当前层之前的走法
        clearPossibleMoves(curDepth);
        for (int i = 0; i < ChessBoard.CHESS_BOARD_HEIGHT; i++) {
            for (int j = 0; j < ChessBoard.CHESS_BOARD_LENGTH; j++) {
                PointState pointState = boardPosition[i][j];
                if (pointState == PointState.NO_CHESS) {
                    continue;
                }
                CampType nextCamp = (maxDepth + curDepth) % 2 == 0 ? ChessBoardUtils.AI_CAMP : ChessBoardUtils.AI_CAMP.getEnemyCamp();
                if (!pointState.isSameCamp(nextCamp)) {
                    continue;
                }

                AbstractChessMoveGenerator moveGenerator = ChessManager.getInstance().getChessMoveGenerator(pointState);
                List<Position> positions = moveGenerator.generateValidMove(boardPosition, i, j);
                for (Position position : positions) {
                    int score = historyHeuristic.getHistoryScore(i, j, position.getX(), position.getY());
                    addMove(i, j, position.getX(), position.getY(), curDepth, score);
                }
            }
        }

        // 将本层所有可能走法按照历史得分排序
        Collections.sort(getPossibleHistoryScoreMoves(curDepth));
    }

    /**
     * 清楚指定层数的所有合理走法
     *
     * @param nPly 搜索层数
     */
    @Override
    public void clearPossibleMoves(int nPly) {
        this.historyScoreMoves.remove(nPly);
    }

    /**
     * 获取指定搜索层数的所有合理走法
     *
     * @param nPly 搜索层数
     * @return 该指定搜索层数的所有合理走法
     */
    protected List<HistoryScoreChessMove> getPossibleHistoryScoreMoves(int nPly) {
        List<HistoryScoreChessMove> possibleMoves = historyScoreMoves.get(nPly);
        if (possibleMoves == null) {
            possibleMoves = new ArrayList<>();
        }
        return possibleMoves;
    }

    /**
     * 在走法集合中添加走法 （历史启发搜索引擎专用）
     *
     * @param startX  移动起始点X坐标
     * @param startY  移动起始点Y坐标
     * @param targetX 移动目标点X坐标
     * @param targetY 移动目标点Y坐标
     * @param nPly    搜索层数
     * @param score   该走法的历史得分
     */
    private void addMove(int startX, int startY, int targetX, int targetY, int nPly, int score) {
        this.historyScoreMoves.computeIfAbsent(nPly, n -> new ArrayList<>()).add(HistoryScoreChessMove.valueOf(startX, startY, targetX, targetY, score));
    }

    private int alphaBeta(PointState[][] boardPosition, int curDepth, int maxDepth, int alpha, int beta) {
        int current = -ChessBoardUtils.MAX_SEARCH_VALUE;
        if (isGameOver(boardPosition) || curDepth <= 0) {
            return evaluate(boardPosition, curDepth, maxDepth);
        }
        // 生成合理走法
        createPossibleMoves(boardPosition, curDepth, maxDepth);
        // 记录最佳走法的变量
        // 一个好的走法的定义：1.由其产生的节点引发了剪枝。2.未引发剪枝，但是其兄弟走法中的最佳者。
        ChessMove bestMoveFlag = null;
        // 遍历所有合理走法
        for (ChessMove move : getPossibleHistoryScoreMoves(curDepth)) {
            // 根据走法产生新局面
            PointState oldTargetState = makeMove(boardPosition, move);
            // 递归调用Alpha-Beta剪枝搜索下一层的节点
            int score = -alphaBeta(boardPosition, curDepth - 1, maxDepth, -beta, -alpha);
            // 恢复当前局面
            unMakeMove(boardPosition, move, oldTargetState);

            if (score > current) {
                current = score;
                if (score >= beta) {
                    bestMoveFlag = move;
                    break;
                }
                if (score > alpha) {
                    alpha = score;
                    bestMoveFlag = move;
                    if (curDepth == maxDepth) {
                        bestMove = move;
                    }
                }
            }
        }
        if (bestMoveFlag != null) {
            historyHeuristic.enterHistoryScore(bestMoveFlag, curDepth);
        }
        return current;
    }
}
