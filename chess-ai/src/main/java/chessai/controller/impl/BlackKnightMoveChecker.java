package chessai.controller.impl;

import chessai.controller.AbstractChessMoveChecker;
import chessai.model.PointState;
import org.springframework.stereotype.Component;

/**
 * 棋子移动检测器--黑马
 */
@Component
public class BlackKnightMoveChecker extends AbstractChessMoveChecker {

    @Override
    public PointState getPointState() {
        return PointState.BLACK_KNIGHT;
    }

    @Override
    public boolean checkMove(int startX, int startY, int targetX, int targetY) {
        return false;
    }
}
