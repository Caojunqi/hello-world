package chessai.controller.impl;

import chessai.controller.AbstractChessMoveChecker;
import chessai.model.PointState;
import org.springframework.stereotype.Component;

/**
 * 棋子移动检测器--黑士
 */
@Component
public class BlackGuardMoveChecker extends AbstractChessMoveChecker {

    @Override
    public PointState getPointState() {
        return PointState.BLACK_GUARD;
    }

    @Override
    public boolean checkMove(int targetX, int targetY) {
        return false;
    }
}
