package chessai.controller.movegenerator.impl;

import chessai.model.PointState;
import org.springframework.stereotype.Component;

/**
 * 棋子走法生成器--红车
 */
@Component
public class RedRookMoveGenerator extends AbstractRookMoveGenerator {

    @Override
    public PointState getPointState() {
        return PointState.RED_ROOK;
    }

}
