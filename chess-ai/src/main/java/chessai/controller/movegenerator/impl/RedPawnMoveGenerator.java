package chessai.controller.movegenerator.impl;

import chessai.model.PointState;
import chessai.util.ChessBoardUtils;
import org.springframework.stereotype.Component;

/**
 * 棋子走法生成器--红卒
 */
@Component
public class RedPawnMoveGenerator extends AbstractPawnMoveGenerator {

    @Override
    public PointState getPointState() {
        return PointState.RED_PAWN;
    }

    @Override
    protected int moveForward(int startX) {
        return startX - 1;
    }

    @Override
    protected boolean isCrossRiver(int startX, int startY) {
        return ChessBoardUtils.inBlackSide(startX, startY);
    }
}
