package chessai.controller.movegenerator.impl;

import chessai.controller.movegenerator.AbstractChessMoveGenerator;
import chessai.model.PointState;
import org.springframework.stereotype.Component;

/**
 * 棋子走法生成器--黑车
 */
@Component
public class BlackRookMoveGenerator extends AbstractChessMoveGenerator {

    @Override
    public PointState getPointState() {
        return PointState.BLACK_ROOK;
    }

    @Override
    public void generateMove(PointState[][] boardPosition, int startX, int startY, int nPly) {

    }
}
