package chessai.controller.movegenerator.impl;

import chessai.model.PointState;
import org.springframework.stereotype.Component;

/**
 * 棋子走法生成器--黑炮
 */
@Component
public class BlackCannonMoveGenerator extends AbstractCannonMoveGenerator {

    @Override
    public PointState getPointState() {
        return PointState.BLACK_CANNON;
    }

}
