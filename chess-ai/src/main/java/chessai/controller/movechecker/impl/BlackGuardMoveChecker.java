package chessai.controller.movechecker.impl;

import chessai.model.PointState;
import chessai.util.ChessBoardUtils;
import org.springframework.stereotype.Component;

/**
 * 棋子移动检测器--黑士
 */
@Component
public class BlackGuardMoveChecker extends AbstractGuardMoveChecker {

    @Override
    public PointState getPointState() {
        return PointState.BLACK_GUARD;
    }

    @Override
    protected boolean inPalace(int targetX, int targetY) {
        return ChessBoardUtils.inBlackPalace(targetX, targetY);
    }
}
