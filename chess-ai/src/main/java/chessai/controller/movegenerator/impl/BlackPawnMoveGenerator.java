package chessai.controller.movegenerator.impl;

import chessai.model.PointState;
import chessai.util.ChessBoardUtils;
import org.springframework.stereotype.Component;

/**
 * 棋子走法生成器--黑卒
 */
@Component
public class BlackPawnMoveGenerator extends AbstractPawnMoveGenerator {

    @Override
    public PointState getPointState() {
        return PointState.BLACK_PAWN;
    }

    @Override
    protected int moveForward(int startX) {
        return startX + 1;
    }

    @Override
    protected boolean isCrossRiver(int startX, int startY) {
        return ChessBoardUtils.inRedSide(startX, startY);
    }

}
