package chessai.controller.searchengine.impl;

import chessai.controller.ChessMoveManager;
import chessai.controller.evaluation.IBoardEvaluator;
import chessai.controller.searchengine.AbstractSearchEngine;
import chessai.model.ChessBoard;
import chessai.model.ChessMove;
import chessai.model.PointState;
import chessai.util.ChessBoardUtils;

import java.util.List;

/**
 * 结合迭代深化和Alpha-Beta fail-soft算法的搜索引擎
 * 迭代深化：Iterative Deepening
 */
public class IterativeDeepeningAlphaBetaSearchEngine extends AbstractSearchEngine {
    /**
     * 每次搜索的最大耗时（毫秒）
     */
    private static final int MAX_SEARCH_TIME = 5000;
    /**
     * 本次搜索的开始时间戳
     */
    private long startTime;
    /**
     * 每一次迭代深化找出来的最佳着法
     */
    private ChessMove backupBestMove;

    public IterativeDeepeningAlphaBetaSearchEngine(IBoardEvaluator boardEvaluator) {
        this.boardEvaluator = boardEvaluator;
    }

    @Override
    public ChessMove searchBestMove() {
        init();
        int searchDepth = 0;
        while (true) {
            searchDepth++;
            int score = iterativeDeepeningAlphaBeta(searchDepth, searchDepth, -ChessBoardUtils.MAX_SEARCH_VALUE, ChessBoardUtils.MAX_SEARCH_VALUE);
            if (score != -ChessBoardUtils.MAX_SEARCH_VALUE) {
                // 这个结果是实实在在搜索得到的，不是由于时间耗尽而终止
                bestMove = backupBestMove;
            } else {
                // 时间耗尽
                break;
            }
        }
        System.out.println("曹俊棋断点=======搜索到第几层：" + (searchDepth - 1));
        return bestMove;
    }

    /**
     * 在每次开始搜索最佳着法前的初始化工作
     */
    private void init() {
        bestMove = null;
        startTime = System.currentTimeMillis();
        backupBestMove = null;
    }

    private int iterativeDeepeningAlphaBeta(int curDepth, int maxDepth, int alpha, int beta) {
        int current = -ChessBoardUtils.MAX_SEARCH_VALUE;
        PointState[][] boardPosition = ChessBoard.getInstance().getCurChessBoard();
        if (isGameOver(boardPosition) || curDepth <= 0) {
            return evaluate(boardPosition, curDepth, maxDepth);
        }

        // 生成合理走法
        ChessMoveManager.getInstance().createPossibleMoves(boardPosition, curDepth, maxDepth);
        // 遍历所有合理走法
        List<ChessMove> allPossibleMoves = ChessMoveManager.getInstance().getPossibleMoves(curDepth);
        if (curDepth == maxDepth) {
            // 在根节点处，把上一次找到的最佳着法放在第一位
            sortPossibleMoves(allPossibleMoves, backupBestMove);
        }
        for (ChessMove move : allPossibleMoves) {
            if (curDepth == maxDepth) {
                // 在每次走到根节点时，看一下时间是否耗尽
                if (startTime + MAX_SEARCH_TIME <= System.currentTimeMillis()) {
                    return -ChessBoardUtils.MAX_SEARCH_VALUE;
                }
            }

            // 根据走法产生新局面，注，这里必须是先计算HASH值，再执行走法
            PointState oldTargetState = makeMove(boardPosition, move);
            // 递归调用Alpha-Beta剪枝搜索下一层的节点
            int score = -iterativeDeepeningAlphaBeta(curDepth - 1, maxDepth, -beta, -alpha);
            // 恢复当前局面，注，这里必须是先计算HASH值，再撤销走法
            unMakeMove(boardPosition, move, oldTargetState);

            if (score > current) {
                current = score;
                if (score >= beta) {
                    break;
                }
                if (score > alpha) {
                    alpha = score;

                    if (curDepth == maxDepth) {
                        backupBestMove = move;
                    }
                }
            }
        }

        return current;
    }

    /**
     * 把最佳着法放在所有可能走法的第一位
     *
     * @param allPossibleMoves 所有可能着法
     * @param bestMove         上一次搜寻到的最佳着法
     */
    private void sortPossibleMoves(List<ChessMove> allPossibleMoves, ChessMove bestMove) {
        for (int i = 0; i < allPossibleMoves.size(); i++) {
            ChessMove possibleMove = allPossibleMoves.get(i);
            if (possibleMove.equals(bestMove)) {
                ChessMove tempMove = allPossibleMoves.get(0);
                allPossibleMoves.set(0, bestMove);
                allPossibleMoves.set(1, tempMove);
            }
        }
    }
}
