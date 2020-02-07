package chessai.controller.impl;

import chessai.model.ChessBoard;
import chessai.model.PointState;
import org.springframework.stereotype.Component;

/**
 * 棋子移动检测器--黑象
 */
@Component
public class BlackMinisterMoveChecker extends AbstractMinisterMoveChecker {

    @Override
    public PointState getPointState() {
        return PointState.BLACK_MINISTER;
    }

    @Override
    protected boolean isCrossRiver(int targetX, int targetY) {
        return ChessBoard.getInstance().inRedSide(targetX, targetY);
    }
}
