package chessai.controller.movechecker.impl;

import chessai.model.PointState;
import chessai.util.ChessBoardUtils;
import org.springframework.stereotype.Component;

/**
 * 棋子移动检测器--黑卒
 */
@Component
public class BlackPawnMoveChecker extends AbstractPawnMoveChecker {

    @Override
    public PointState getPointState() {
        return PointState.BLACK_PAWN;
    }

    @Override
    protected boolean isCrossRiver(int startX, int startY) {
        return ChessBoardUtils.inRedPalace(startX, startY);
    }

    @Override
    protected boolean isMoveBack(int startX, int startY, int targetX, int targetY) {
        return startX > targetX;
    }
}
