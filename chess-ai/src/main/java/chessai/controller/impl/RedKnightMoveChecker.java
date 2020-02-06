package chessai.controller.impl;

import chessai.controller.AbstractChessMoveChecker;
import chessai.model.PointState;
import org.springframework.stereotype.Component;

/**
 * 棋子移动检测器--红马
 */
@Component
public class RedKnightMoveChecker extends AbstractChessMoveChecker {

    @Override
    public PointState getPointState() {
        return PointState.RED_KNIGHT;
    }

    @Override
    public boolean checkMove(int targetX, int targetY) {
        return false;
    }
}
