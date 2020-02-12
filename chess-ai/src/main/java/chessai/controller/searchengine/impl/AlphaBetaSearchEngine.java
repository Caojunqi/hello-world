package chessai.controller.searchengine.impl;

import chessai.controller.ChessMoveManager;
import chessai.controller.searchengine.AbstractSearchEngine;
import chessai.model.ChessMove;
import chessai.model.PointState;
import chessai.util.ChessBoardUtils;

/**
 * Alpha-Beta搜索引擎
 */
public class AlphaBetaSearchEngine extends AbstractSearchEngine {

    @Override
    public ChessMove searchBestMove(PointState[][] boardPosition) {
        alphaBeta(boardPosition, ChessBoardUtils.SEARCH_DEPTH, ChessBoardUtils.SEARCH_DEPTH, -ChessBoardUtils.MAX_EVALUATE_VALUE, ChessBoardUtils.MAX_EVALUATE_VALUE);
        return bestMove;
    }

    private int alphaBeta(PointState[][] boardPosition, int curDepth, int maxDepth, int alpha, int beta) {
        if (isGameOver(boardPosition) || curDepth <= 0) {
            return evaluate(boardPosition, curDepth, maxDepth);
        }
        // 生成合理走法
        ChessMoveManager.getInstance().createPossibleMoves(boardPosition, curDepth);
        // 遍历所有合理走法
        for (ChessMove move : ChessMoveManager.getInstance().getPossibleMoves(curDepth)) {
            // 根据走法产生新局面
            PointState oldTargetState = makeMove(boardPosition, move);
            // 递归调用Alpha-Beta剪枝搜索下一层的节点
            int score = -alphaBeta(boardPosition, curDepth - 1, maxDepth, -beta, -alpha);
            // 恢复当前局面
            unMakeMove(boardPosition, move, oldTargetState);
            if (score >= beta) {
                return beta;
            }
            if (score > alpha) {
                alpha = score;

                if (curDepth == maxDepth) {
                    bestMove = move;
                }
            }
        }
        return alpha;
    }
}
