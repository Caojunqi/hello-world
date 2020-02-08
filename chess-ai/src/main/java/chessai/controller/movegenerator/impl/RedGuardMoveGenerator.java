package chessai.controller.movegenerator.impl;

import chessai.controller.movegenerator.AbstractChessMoveGenerator;
import chessai.model.PointState;
import org.springframework.stereotype.Component;

/**
 * 棋子走法生成器--红仕
 */
@Component
public class RedGuardMoveGenerator extends AbstractChessMoveGenerator {

    @Override
    public PointState getPointState() {
        return PointState.RED_GUARD;
    }

    @Override
    public void generateMove(PointState[][] boardPosition, int startX, int startY, int nPly) {

    }
}
