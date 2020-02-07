package chessai.controller.impl;

import chessai.model.ChessBoard;
import chessai.model.PointState;
import org.springframework.stereotype.Component;

/**
 * 棋子移动检测器--红仕
 */
@Component
public class RedGuardMoveChecker extends AbstractGuardMoveChecker {

    @Override
    public PointState getPointState() {
        return PointState.RED_GUARD;
    }

    @Override
    protected boolean inPalace(int targetX, int targetY) {
        return ChessBoard.getInstance().inRedPalace(targetX, targetY);
    }

}
