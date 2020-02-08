package chessai.controller.movegenerator.impl;

import chessai.controller.movegenerator.AbstractChessMoveGenerator;
import chessai.model.PointState;
import org.springframework.stereotype.Component;

/**
 * 棋子走法生成器--黑炮
 */
@Component
public class BlackCannonMoveGenerator extends AbstractChessMoveGenerator {

    @Override
    public PointState getPointState() {
        return PointState.BLACK_CANNON;
    }

    @Override
    public void generateMove(PointState[][] boardPosition, int startX, int startY, int nPly) {

    }
}
