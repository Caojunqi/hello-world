package chessai.controller.movechecker.impl;

import chessai.controller.movechecker.AbstractChessMoveChecker;
import chessai.model.PointState;
import chessai.util.ChessBoardUtils;

/**
 * 棋子移动检测器-双方车移动
 */
public abstract class AbstractRookMoveChecker extends AbstractChessMoveChecker {
    @Override
    public boolean checkMove(PointState[][] boardPosition, int startX, int startY, int targetX, int targetY) {
        if (startX != targetX && startY != targetY) {
            // 车走直线
            return false;
        }

        return !ChessBoardUtils.hasOtherChess(boardPosition, startX, startY, targetX, targetY);
    }
}
