package chessai.controller.movechecker.impl;

import chessai.controller.movechecker.AbstractChessMoveChecker;
import chessai.model.PointState;

/**
 * 棋子移动检测器-双方仕移动
 */
public abstract class AbstractGuardMoveChecker extends AbstractChessMoveChecker {

    /**
     * 移动目标点是否在九宫格中
     *
     * @param targetX 棋子移动的目标点X坐标
     * @param targetY 棋子移动的目标点Y坐标
     * @return true-在九宫格中；false-不在九宫格中。
     */
    protected abstract boolean inPalace(int targetX, int targetY);

    @Override
    public boolean checkMove(PointState[][] boardPosition, int startX, int startY, int targetX, int targetY) {
        if (!inPalace(targetX, targetY)) {
            // 目标点不在九宫格中，移动不合法
            return false;
        }

        if (Math.abs(startX - targetX) != 1 || Math.abs(startY - targetY) != 1) {
            // 仕走斜线
            return false;
        }

        return true;
    }
}
