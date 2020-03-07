package chessai.controller.searchengine;

import chessai.controller.evaluation.IBoardEvaluator;
import chessai.model.CampType;
import chessai.model.ChessMove;
import chessai.model.PointState;
import chessai.util.ChessBoardUtils;

/**
 * 搜索引擎抽象类
 */
public abstract class AbstractSearchEngine {
    /**
     * 当前局面的最优走法
     */
    protected ChessMove bestMove;
    /**
     * 棋局评估器
     */
    protected IBoardEvaluator boardEvaluator;

    /**
     * 针对当前棋局局势搜索一个最佳棋子移动方案
     *
     * @return 最佳棋子移动方案
     */
    public abstract ChessMove searchBestMove();

    /**
     * 在指定棋局中移动一步棋子
     *
     * @param boardPosition 所面对的棋局局势
     * @param move          棋子移动
     * @return 棋子移动前，目标位置的棋点状态
     */
    public PointState makeMove(PointState[][] boardPosition, ChessMove move) {
        // 获取目标位置棋点状态
        PointState oldTargetState = boardPosition[move.getTargetX()][move.getTargetY()];
        // 把棋子移动到目标位置
        boardPosition[move.getTargetX()][move.getTargetY()] = boardPosition[move.getStartX()][move.getStartY()];
        // 将原位置清空
        boardPosition[move.getStartX()][move.getStartY()] = PointState.NO_CHESS;
        // 返回被吃掉的棋子
        return oldTargetState;
    }

    /**
     * 在指定棋局中撤销一步移动
     *
     * @param boardPosition  所面对的棋局局势
     * @param move           所撤销的棋子移动
     * @param oldTargetState 棋子移动前目标位置的棋点状态
     */
    public void unMakeMove(PointState[][] boardPosition, ChessMove move, PointState oldTargetState) {
        // 将目标位置的棋子拷回原位
        boardPosition[move.getStartX()][move.getStartY()] = boardPosition[move.getTargetX()][move.getTargetY()];
        // 恢复目标位置的棋子
        boardPosition[move.getTargetX()][move.getTargetY()] = oldTargetState;
    }

    /**
     * 判断当前棋局是否已分出胜负
     *
     * @param boardPosition 所面对的棋局局势
     * @return true-棋局胜负已分；false-棋局胜负未分。
     */
    public boolean isGameOver(PointState[][] boardPosition) {
        if (!ChessBoardUtils.isBlackLive(boardPosition)) {
            // 黑将已死
            return true;
        }
        if (!ChessBoardUtils.isRedLive(boardPosition)) {
            // 红帅已死
            return true;
        }
        return false;
    }

    /**
     * 估值函数
     *
     * @param boardPosition 待估值棋面
     * @param curDepth      当前剩余搜索层数
     * @param maxDepth      本次搜索计划的最大搜索层数
     * @return 估值结果
     */
    protected int evaluate(PointState[][] boardPosition, int curDepth, int maxDepth) {
        // (maxDepth+curDepth)的值如果是奇数，表明当前局面接下来由敌人走棋，我们需要从当前层的所有局面中挑选最大值
        // 如果是偶数，表明当前局面接下来由AI走棋，我们需要从当前层的所有局面中挑选最小值。
        // 但是无论是采用负极大值搜索还是Alpha-Beta剪枝，最后一层的估值结果都会被先取一个负号，然后再进行大小比较，因此应该再加上一个负号来进行抵消。
        int ratio = (maxDepth + curDepth) % 2 == 0 ? 1 : -1;
        CampType nextCamp = (maxDepth + curDepth) % 2 == 0 ? ChessBoardUtils.AI_CAMP : ChessBoardUtils.AI_CAMP.getEnemyCamp();
        if (ChessBoardUtils.AI_CAMP.isWin(boardPosition)) {
            return ratio * ChessBoardUtils.MAX_EVALUATE_VALUE;
        }

        if (ChessBoardUtils.AI_CAMP.isLose(boardPosition)) {
            return -ratio * ChessBoardUtils.MAX_EVALUATE_VALUE;
        }

        return ratio * boardEvaluator.evaluate(boardPosition, nextCamp);
    }

}
