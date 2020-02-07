package chessai.controller.impl;

import chessai.controller.AbstractChessMoveChecker;
import chessai.model.ChessBoard;
import chessai.model.PointState;

/**
 * 棋子移动检测器-双方象移动
 */
public abstract class AbstractMinisterMoveChecker extends AbstractChessMoveChecker {

    /**
     * 判断移动目标点是否过河
     *
     * @param targetX 移动目标点X坐标
     * @param targetY 移动目标点Y坐标
     * @return true-过河了；false-没过河。
     */
    protected abstract boolean isCrossRiver(int targetX, int targetY);

    @Override
    public boolean checkMove(int startX, int startY, int targetX, int targetY) {
        if (isCrossRiver(targetX, targetY)) {
            // 象不能过河
            return false;
        }
        if (Math.abs(startX - targetX) != 2 || Math.abs(startY - targetY) != 2) {
            // 象走田字
            return false;
        }

        // 判断象眼有没有被堵住
        int coreX = (startX + targetX) / 2;
        int coreY = (startY + targetY) / 2;
        PointState corePoint = ChessBoard.getInstance().getPointState(coreX, coreY);
        if (corePoint != PointState.NO_CHESS) {
            // 象眼有棋
            return false;
        }
        return true;
    }
}
