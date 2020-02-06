package chessai.controller.impl;

import chessai.controller.AbstractChessMoveChecker;
import chessai.model.PointState;
import org.springframework.stereotype.Component;

/**
 * 棋子移动检测器--红炮
 */
@Component
public class RedCannonMoveChecker extends AbstractChessMoveChecker {

    @Override
    public PointState getPointState() {
        return PointState.RED_CANNON;
    }

    @Override
    public boolean checkMove(int startX, int startY, int targetX, int targetY) {
        return false;
    }
}
