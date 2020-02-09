package chessai.controller.movegenerator.impl;

import chessai.model.PointState;
import org.springframework.stereotype.Component;

/**
 * 棋子走法生成器--红马
 */
@Component
public class RedKnightMoveGenerator extends AbstractKnightMoveGenerator {

    @Override
    public PointState getPointState() {
        return PointState.RED_KNIGHT;
    }

}
