package chessai.controller.impl;

import chessai.controller.AbstractChessMoveChecker;
import chessai.model.ChessBoard;

/**
 * 棋子移动检测器-双方车移动
 */
public abstract class AbstractRookMoveChecker extends AbstractChessMoveChecker {
    @Override
    public boolean checkMove(int startX, int startY, int targetX, int targetY) {
        if (startX != targetX && startY != targetY) {
            // 车走直线
            return false;
        }

        return !ChessBoard.getInstance().hasOtherChess(startX, startY, targetX, targetY);
    }
}
