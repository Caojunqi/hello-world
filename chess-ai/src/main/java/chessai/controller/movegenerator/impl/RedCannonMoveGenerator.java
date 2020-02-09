package chessai.controller.movegenerator.impl;

import chessai.model.PointState;
import org.springframework.stereotype.Component;

/**
 * 棋子走法生成器--红炮
 */
@Component
public class RedCannonMoveGenerator extends AbstractCannonMoveGenerator {

    @Override
    public PointState getPointState() {
        return PointState.RED_CANNON;
    }

}
