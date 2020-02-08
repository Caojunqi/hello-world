package chessai.controller.movechecker.impl;

import chessai.model.PointState;
import chessai.util.ChessBoardUtils;
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
        return ChessBoardUtils.inBlackSide(targetX, targetY);
    }
}
