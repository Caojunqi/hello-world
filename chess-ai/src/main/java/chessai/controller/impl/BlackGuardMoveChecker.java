package chessai.controller.impl;

import chessai.model.ChessBoard;
import chessai.model.PointState;
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
        return ChessBoard.getInstance().inBlackPalace(targetX, targetY);
    }
}
