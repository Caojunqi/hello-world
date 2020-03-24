package chessai.controller.searchengine.impl;

import chessai.controller.evaluation.IBoardEvaluator;
import chessai.controller.searchengine.AbstractSearchEngine;
import chessai.model.ChessBoard;
import chessai.model.ChessMove;
import chessai.model.PointState;
import chessai.util.ChessBoardUtils;

/**
 * Fail Soft Alpha Beta剪枝技术
 */
public class FailSoftAlphaBetaSearchEngine extends AbstractSearchEngine {
    public FailSoftAlphaBetaSearchEngine(IBoardEvaluator boardEvaluator) {
        this.boardEvaluator = boardEvaluator;
    }

    @Override
    public ChessMove searchBestMove() {
        PointState[][] boardPosition = ChessBoard.getInstance().getCurChessBoard();
        clearPossibleMoves();
        bestMove = null;
        failSoftAlphaBeta(boardPosition, ChessBoardUtils.SEARCH_DEPTH, ChessBoardUtils.SEARCH_DEPTH, -ChessBoardUtils.MAX_SEARCH_VALUE, ChessBoardUtils.MAX_SEARCH_VALUE);
        return bestMove;
    }

    protected int failSoftAlphaBeta(PointState[][] boardPosition, int curDepth, int maxDepth, int alpha, int beta) {
        int current = -ChessBoardUtils.MAX_SEARCH_VALUE;
        if (isGameOver(boardPosition) || curDepth <= 0) {
            return evaluate(boardPosition, curDepth, maxDepth);
        }
        // 生成合理走法
        createPossibleMoves(boardPosition, curDepth, maxDepth);
        // 遍历所有合理走法
        for (ChessMove move : getPossibleMoves(curDepth)) {
            // 根据走法产生新局面
            PointState oldTargetState = makeMove(boardPosition, move);
            // 递归调用Alpha-Beta剪枝搜索下一层的节点
            int score = -failSoftAlphaBeta(boardPosition, curDepth - 1, maxDepth, -beta, -alpha);
            // 恢复当前局面
            unMakeMove(boardPosition, move, oldTargetState);

            if (score > current) {
                current = score;
                if (score >= beta) {
                    break;
                }
                if (score > alpha) {
                    alpha = score;

                    if (curDepth == maxDepth) {
                        bestMove = move;
                    }
                }
            }
        }
        return current;
    }
}
