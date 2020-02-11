package chessai.controller.searchengine.impl;

import chessai.controller.ChessMoveManager;
import chessai.controller.searchengine.AbstractSearchEngine;
import chessai.model.ChessMove;
import chessai.model.PointState;
import chessai.util.ChessBoardUtils;
import chessai.util.RandomUtils;

/**
 * 负极大值搜索引擎
 */
public class NegamaxSearchEngine extends AbstractSearchEngine {

    @Override
    public ChessMove searchBestMove(PointState[][] boardPosition) {
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
        int best = -ChessBoardUtils.MAX_EVALUATE_VALUE;
        if (isGameOver(boardPosition) || curDepth <= 0) {
            return evaluate(boardPosition, curDepth, maxDepth);
        }
        // 生成合理走法
        ChessMoveManager.getInstance().createPossibleMoves(boardPosition, curDepth);
        // 遍历所有合理走法
        for (ChessMove move : ChessMoveManager.getInstance().getPossibleMoves(curDepth)) {
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

    /**
     * 估值函数
     *
     * @param boardPosition 待估值棋面
     * @param curDepth      当前剩余搜索层数
     * @param maxDepth      本次搜索计划的最大搜索层数
     * @return 估值结果
     */
    public int evaluate(PointState[][] boardPosition, int curDepth, int maxDepth) {
        int ratio = (maxDepth + curDepth) % 2 == 0 ? -1 : 1;
        if (ChessBoardUtils.AI_CAMP.isWin(boardPosition)) {
            return ratio * ChessBoardUtils.MAX_EVALUATE_VALUE;
        }

        if (ChessBoardUtils.AI_CAMP.isLose(boardPosition)) {
            return -ratio * ChessBoardUtils.MAX_EVALUATE_VALUE;
        }

        // TODO 编写具体的估值函数
        return ratio * RandomUtils.betweenInt(1, 1998, true);
    }
}
