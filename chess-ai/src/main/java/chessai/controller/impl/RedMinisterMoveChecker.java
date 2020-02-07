package chessai.controller.impl;

import chessai.model.ChessBoard;
import chessai.model.PointState;
import org.springframework.stereotype.Component;

/**
 * 棋子移动检测器--红象
 */
@Component
public class RedMinisterMoveChecker extends AbstractMinisterMoveChecker {

    @Override
    public PointState getPointState() {
        return PointState.RED_MINISTER;
    }

    @Override
    protected boolean isCrossRiver(int targetX, int targetY) {
        return ChessBoard.getInstance().inBlackSide(targetX, targetY);
    }
}
