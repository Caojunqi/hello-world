package chessai.controller.searchengine.impl;

import chessai.controller.evaluation.IBoardEvaluator;
import chessai.controller.searchengine.AbstractSearchEngine;
import chessai.model.ChessBoard;
import chessai.model.ChessMove;
import chessai.model.PointState;
import chessai.util.ChessBoardUtils;

/**
 * 负极大值搜索引擎
 */
public class NegamaxSearchEngine extends AbstractSearchEngine {

    public NegamaxSearchEngine(IBoardEvaluator boardEvaluator) {
        this.boardEvaluator = boardEvaluator;
    }

    @Override
    public ChessMove searchBestMove() {
        PointState[][] boardPosition = ChessBoard.getInstance().getCurChessBoard();
        clearPossibleMoves();
        bestMove = null;
        negamax(boardPosition, ChessBoardUtils.SEARCH_DEPTH, ChessBoardUtils.SEARCH_DEPTH);
        return bestMove;
    }

    /**
     * 负极大值搜索函数
     *
     * @param boardPosition 当前面对的棋局局势
     * @param curDepth      当前剩余搜索层数
     * @param maxDepth      本次搜索计划的最大搜索层数
     * @return 当前局面的最优估值
     */
    private int negamax(PointState[][] boardPosition, int curDepth, int maxDepth) {
        int best = -ChessBoardUtils.MAX_SEARCH_VALUE;
        if (isGameOver(boardPosition) || curDepth <= 0) {
            return evaluate(boardPosition, curDepth, maxDepth);
        }
        // 生成合理走法
        createPossibleMoves(boardPosition, curDepth, maxDepth);
        // 遍历所有合理走法
        for (ChessMove move : getPossibleMoves(curDepth)) {
            // 根据走法产生新局面
            PointState oldTargetState = makeMove(boardPosition, move);
            // 递归调用负极大值搜索下一层的节点
            int score = -negamax(boardPosition, curDepth - 1, maxDepth);
            // 恢复当前局面
            unMakeMove(boardPosition, move, oldTargetState);

            if (score > best) {
                // 如果score大于已知的最大值
                best = score;

                // 保存最优走法
                if (curDepth == maxDepth) {
                    bestMove = move;
                }
            }
        }
        return best;
    }

}
