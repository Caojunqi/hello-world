package chessai.controller.impl;

import chessai.controller.AbstractChessMoveChecker;
import chessai.model.PointState;
import org.springframework.stereotype.Component;

/**
 * 棋子移动检测器--黑车
 */
@Component
public class BlackRookMoveChecker extends AbstractChessMoveChecker {

    @Override
    public PointState getPointState() {
        return PointState.BLACK_ROOK;
    }

    @Override
    public boolean checkMove(int targetX, int targetY) {
        return false;
    }
}
