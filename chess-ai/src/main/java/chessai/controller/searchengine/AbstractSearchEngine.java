package chessai.controller.searchengine;

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
     * 针对指定棋局局势搜索一个最佳棋子移动方案
     *
     * @param boardPosition 所面对的棋局局势
     * @return 最佳棋子移动方案
     */
    public abstract ChessMove searchBestMove(PointState[][] boardPosition);

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
            // 黑帅已死
            return true;
        }
        if (!ChessBoardUtils.isRedLive(boardPosition)) {
            // 红将已死
            return true;
        }
        return false;
    }
}
