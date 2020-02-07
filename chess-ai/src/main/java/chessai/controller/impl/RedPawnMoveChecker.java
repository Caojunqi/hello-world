package chessai.controller.impl;

import chessai.model.ChessBoard;
import chessai.model.PointState;
import org.springframework.stereotype.Component;

/**
 * 棋子移动检测器--红卒
 */
@Component
public class RedPawnMoveChecker extends AbstractPawnMoveChecker {

    @Override
    public PointState getPointState() {
        return PointState.RED_PAWN;
    }

    @Override
    protected boolean isCrossRiver(int startX, int startY) {
        return ChessBoard.getInstance().inBlackSide(startX, startY);
    }

    @Override
    protected boolean isMoveBack(int startX, int startY, int targetX, int targetY) {
        return targetX > startX;
    }
}
