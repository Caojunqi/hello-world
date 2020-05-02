package chessai.controller.searchengine.impl;

import chessai.controller.evaluation.IBoardEvaluator;
import chessai.model.ChessBoard;
import chessai.model.ChessMove;
import chessai.model.PointState;
import chessai.model.historyheuristic.HistoryScoreChessMove;
import chessai.model.transpositiontable.HashItemType;
import chessai.model.transpositiontable.TranspositionTable;
import chessai.util.ChessBoardUtils;

import java.util.List;

/**
 * NegaScout搜索算法结合置换表和历史启发
 * <p>
 * NegaScout算法：PVS算法
 * 置换表：TranspositionTable
 * 历史启发：HistoryHeuristic
 */
public class NegaScoutTranspositionTableHistoryHeuristicSearchEngine extends HistoryHeuristicAlphaBetaSearchEngine {

    public NegaScoutTranspositionTableHistoryHeuristicSearchEngine(IBoardEvaluator boardEvaluator) {
        super(boardEvaluator);
    }

    @Override
    public ChessMove searchBestMove() {
        PointState[][] boardPosition = ChessBoard.getInstance().getCurChessBoard();
        clearPossibleMoves();
        bestMove = null;
        negaScout(boardPosition, ChessBoardUtils.SEARCH_DEPTH, ChessBoardUtils.SEARCH_DEPTH, -ChessBoardUtils.MAX_SEARCH_VALUE, ChessBoardUtils.MAX_SEARCH_VALUE);
        return bestMove;
    }

    private int negaScout(PointState[][] boardPosition, int curDepth, int maxDepth, int alpha, int beta) {
        int current = -ChessBoardUtils.MAX_SEARCH_VALUE;
        // 查询置换表
        TranspositionTable transpositionTable = ChessBoard.getInstance().getTranspositionTable();
        boolean isMaxNode = (maxDepth + curDepth) % 2 == 0;
        Integer score = transpositionTable.lookUpHashTable(alpha, beta, curDepth, isMaxNode);
        if (score != null) {
            // 置换表命中
            return score;
        }
        // 游戏结束
        if (isGameOver(boardPosition) || curDepth <= 0) {
            score = evaluate(boardPosition, curDepth, maxDepth);
            transpositionTable.enterHashTable(HashItemType.Exact, curDepth, score, isMaxNode);
            return score;
        }
        // 记录最佳走法的变量
        // 一个好的走法的定义：1.由其产生的节点引发了剪枝。2.未引发剪枝，但是其兄弟走法中的最佳者。
        ChessMove bestMoveFlag = null;
        // 生成合理走法
        createPossibleMoves(boardPosition, curDepth, maxDepth);
        // 遍历所有合理走法
        List<HistoryScoreChessMove> allPossibleMoves = getPossibleHistoryScoreMoves(curDepth);

        for (int i = 0; i < allPossibleMoves.size(); i++) {
            ChessMove move = allPossibleMoves.get(i);
            // 根据走法产生新局面
            transpositionTable.calcMakeMoveHashKey(move);
            PointState oldState = makeMove(boardPosition, move);
            if (i == 0) {
                // 使用全窗口搜索第一个节点
                score = -negaScout(boardPosition, curDepth - 1, maxDepth, -beta, -alpha);
            } else {
                // 用极小窗口搜索其他节点
                score = -negaScout(boardPosition, curDepth - 1, maxDepth, -alpha - 1, -alpha);
                if (score > alpha && score < beta) {
                    // 用全窗口重新搜索
                    score = -negaScout(boardPosition, curDepth - 1, maxDepth, -beta, -score);
                }
            }
            // 恢复当前局面
            transpositionTable.calcUnMakeMoveHashKey(move, oldState);
            unMakeMove(boardPosition, move, oldState);

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
