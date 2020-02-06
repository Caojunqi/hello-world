package chessai.controller.impl;

import chessai.controller.AbstractChessMoveChecker;
import chessai.model.PointState;
import org.springframework.stereotype.Component;

/**
 * 棋子移动检测器--红车
 */
@Component
public class RedCarMoveChecker extends AbstractChessMoveChecker {

    @Override
    public PointState getPointState() {
        return PointState.RED_CAR;
    }

    @Override
    public boolean checkMove(int targetX, int targetY) {
        return false;
    }
}
