package chessai.controller.impl;

import chessai.model.ChessBoard;
import chessai.model.PointState;
import org.springframework.stereotype.Component;

/**
 * 棋子移动检测器--黑兵
 */
@Component
public class BlackPawnMoveChecker extends AbstractPawnMoveChecker {

    @Override
    public PointState getPointState() {
        return PointState.BLACK_PAWN;
    }

    @Override
    protected boolean isCrossRiver(int startX, int startY) {
        return ChessBoard.getInstance().inRedPalace(startX, startY);
    }

    @Override
    protected boolean isMoveBack(int startX, int startY, int targetX, int targetY) {
        return startX > targetX;
    }
}
