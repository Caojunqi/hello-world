package chessai.controller.movechecker.impl;

import chessai.common.SystemOut;
import chessai.controller.movechecker.AbstractChessMoveChecker;
import chessai.model.PointState;

/**
 * 棋子移动检测器-双方马移动
 */
public abstract class AbstractKnightMoveChecker extends AbstractChessMoveChecker {
    @Override
    public boolean checkMove(PointState[][] boardPosition, int startX, int startY, int targetX, int targetY) {
        if (!isRiMove(startX, startY, targetX, targetY)) {
            // 马走日
            return false;
        }
        return !isBlockMove(boardPosition, startX, startY, targetX, targetY);
    }

    /**
     * 判断马的移动是否是个"日"字
     *
     * @param startX  移动起始点X坐标
     * @param startY  移动起始点Y坐标
     * @param targetX 移动目标点X坐标
     * @param targetY 移动目标点Y坐标
     * @return true-是“日”字移动，false-不是“日”字移动
     */
    private boolean isRiMove(int startX, int startY, int targetX, int targetY) {
        if (Math.abs(startX - targetX) == 1 && Math.abs(startY - targetY) == 2) {
            return true;
        }

        if (Math.abs(startX - targetX) == 2 && Math.abs(startY - targetY) == 1) {
            return true;
        }
        return false;
    }

    /**
     * 判断马腿有没有被绊住
     *
     * @param boardPosition 所面对的棋局局势
     * @param startX        移动起始点X坐标
     * @param startY        移动起始点Y坐标
     * @param targetX       移动目标点X坐标
     * @param targetY       移动目标点Y坐标
     * @return true-马腿被绊住，不能移动，false-马腿没有被绊住，可以移动。
     */
    private boolean isBlockMove(PointState[][] boardPosition, int startX, int startY, int targetX, int targetY) {
        int coreX = 0;
        int coreY = 0;
        if (startX - targetX == 2) {
            coreX = startX - 1;
            coreY = startY;
        } else if (targetX - startX == 2) {
            coreX = startX + 1;
            coreY = startY;
        } else if (startY - targetY == 2) {
            coreX = startX;
            coreY = startY - 1;
        } else if (targetY - startY == 2) {
            coreX = startX;
            coreY = startY + 1;
        } else {
            SystemOut.error("没有找到马腿所在位置，移动起始点：[" + startX + "," + startY + "]，移动目标点：[" + targetX + "," + targetY + "]");
            return true;
        }
        return boardPosition[coreX][coreY] != PointState.NO_CHESS;
    }
}
