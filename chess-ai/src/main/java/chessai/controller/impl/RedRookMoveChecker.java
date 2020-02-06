package chessai.controller.impl;

import chessai.controller.AbstractChessMoveChecker;
import chessai.model.PointState;
import org.springframework.stereotype.Component;

/**
 * 棋子移动检测器--红车
 */
@Component
public class RedRookMoveChecker extends AbstractChessMoveChecker {

    @Override
    public PointState getPointState() {
        return PointState.RED_ROOK;
    }

    @Override
    public boolean checkMove(int startX, int startY, int targetX, int targetY) {
        return false;
    }
}
