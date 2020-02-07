package chessai.controller.impl;

import chessai.controller.AbstractChessMoveChecker;

/**
 * 棋子移动检测器-双方兵移动
 */
public abstract class AbstractPawnMoveChecker extends AbstractChessMoveChecker {
    /**
     * 判断棋子移动前是否处于过河状态
     *
     * @param startX 移动起始点X坐标
     * @param startY 移动起始点Y坐标
     * @return true-棋子已过河；false-棋子未过河。
     */
    protected abstract boolean isCrossRiver(int startX, int startY);

    /**
     * 判断棋子是否后退
     *
     * @param startX  棋子移动起始点X坐标
     * @param startY  棋子移动起始点Y坐标
     * @param targetX 棋子移动目标点X坐标
     * @param targetY 棋子移动目标点Y坐标
     * @return true-棋子后退了；false-棋子没有后退。
     */
    protected abstract boolean isMoveBack(int startX, int startY, int targetX, int targetY);

    @Override
    public boolean checkMove(int startX, int startY, int targetX, int targetY) {
        if (isMoveBack(startX, startY, targetX, targetY)) {
            // 兵不能回头
            return false;
        }
        if (!isCrossRiver(startX, startY) && startY != targetY) {
            // 没有过河的兵只能直走
            return false;
        }
        if (Math.abs(startX - targetX) + Math.abs(startY - targetY) > 1) {
            // 兵只能走一步直线
            return false;
        }
        return false;
    }
}
