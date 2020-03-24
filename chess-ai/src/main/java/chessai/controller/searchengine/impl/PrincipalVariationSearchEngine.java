package chessai.controller.searchengine.impl;

import chessai.controller.evaluation.IBoardEvaluator;
import chessai.controller.searchengine.AbstractSearchEngine;
import chessai.model.ChessBoard;
import chessai.model.ChessMove;
import chessai.model.PointState;
import chessai.util.ChessBoardUtils;

import java.util.List;

/**
 * PVS搜索算法  主要变例搜索（Principal Variation Search）
 * 也叫“极小窗口搜索” （Minimal Window Search）
 */
public class PrincipalVariationSearchEngine extends AbstractSearchEngine {

    public PrincipalVariationSearchEngine(IBoardEvaluator boardEvaluator) {
        this.boardEvaluator = boardEvaluator;
    }

    @Override
    public ChessMove searchBestMove() {
        PointState[][] boardPosition = ChessBoard.getInstance().getCurChessBoard();
        clearPossibleMoves();
        bestMove = null;
        principalVariation(boardPosition, ChessBoardUtils.SEARCH_DEPTH, ChessBoardUtils.SEARCH_DEPTH, -ChessBoardUtils.MAX_SEARCH_VALUE, ChessBoardUtils.MAX_SEARCH_VALUE);
        return bestMove;
    }

    private int principalVariation(PointState[][] boardPosition, int curDepth, int maxDepth, int alpha, int beta) {
        int current = -ChessBoardUtils.MAX_SEARCH_VALUE;
        if (isGameOver(boardPosition) || curDepth <= 0) {
            return evaluate(boardPosition, curDepth, maxDepth);
        }

        // 生成合理走法
        createPossibleMoves(boardPosition, curDepth, maxDepth);
        // 遍历所有合理走法
        List<ChessMove> allPossibleMoves = getPossibleMoves(curDepth);

        for (int i = 0; i < allPossibleMoves.size(); i++) {
            int score = 0;
            ChessMove move = allPossibleMoves.get(i);
            // 根据走法产生新局面
            PointState oldState = makeMove(boardPosition, move);
            if (i == 0) {
                // 使用全窗口搜索第一个节点
                score = -principalVariation(boardPosition, curDepth - 1, maxDepth, -beta, -alpha);
            } else {
                // 用极小窗口搜索其他节点
                score = -principalVariation(boardPosition, curDepth - 1, maxDepth, -alpha - 1, -alpha);
                if (score > alpha && score < beta) {
                    // 用全窗口重新搜索
                    score = -principalVariation(boardPosition, curDepth - 1, maxDepth, -beta, -score);
                }
            }
            // 恢复当前局面
            unMakeMove(boardPosition, move, oldState);

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
