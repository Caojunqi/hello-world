package chessai.controller.impl;

import chessai.controller.AbstractChessMoveChecker;
import chessai.model.PointState;
import org.springframework.stereotype.Component;

/**
 * 棋子移动检测器--黑兵
 */
@Component
public class BlackPawnMoveChecker extends AbstractChessMoveChecker {

    @Override
    public PointState getPointState() {
        return PointState.BLACK_PAWN;
    }

    @Override
    public boolean checkMove(int startX, int startY, int targetX, int targetY) {
        return false;
    }
}
