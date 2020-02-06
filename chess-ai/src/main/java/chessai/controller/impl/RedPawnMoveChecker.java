package chessai.controller.impl;

import chessai.controller.AbstractChessMoveChecker;
import chessai.model.PointState;
import org.springframework.stereotype.Component;

/**
 * 棋子移动检测器--红卒
 */
@Component
public class RedPawnMoveChecker extends AbstractChessMoveChecker {

    @Override
    public PointState getPointState() {
        return PointState.RED_PAWN;
    }

    @Override
    public boolean checkMove(int startX, int startY, int targetX, int targetY) {
        return false;
    }
}
