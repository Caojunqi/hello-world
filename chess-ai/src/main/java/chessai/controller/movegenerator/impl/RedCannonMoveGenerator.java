package chessai.controller.movegenerator.impl;

import chessai.controller.movegenerator.AbstractChessMoveGenerator;
import chessai.model.PointState;
import org.springframework.stereotype.Component;

/**
 * 棋子走法生成器--红炮
 */
@Component
public class RedCannonMoveGenerator extends AbstractChessMoveGenerator {

    @Override
    public PointState getPointState() {
        return PointState.RED_CANNON;
    }

    @Override
    public void generateMove(PointState[][] boardPosition, int startX, int startY, int nPly) {

    }
}
