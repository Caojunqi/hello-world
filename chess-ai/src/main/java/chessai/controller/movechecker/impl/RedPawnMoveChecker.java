package chessai.controller.movechecker.impl;

import chessai.model.PointState;
import chessai.util.ChessBoardUtils;
import org.springframework.stereotype.Component;

/**
 * 棋子移动检测器--红兵
 */
@Component
public class RedPawnMoveChecker extends AbstractPawnMoveChecker {

    @Override
    public PointState getPointState() {
        return PointState.RED_PAWN;
    }

    @Override
    protected boolean isCrossRiver(int startX, int startY) {
        return ChessBoardUtils.inBlackSide(startX, startY);
    }

    @Override
    protected boolean isMoveBack(int startX, int startY, int targetX, int targetY) {
        return targetX > startX;
    }
}
