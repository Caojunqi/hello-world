package chessai.controller.movegenerator.impl;

import chessai.model.PointState;
import org.springframework.stereotype.Component;

/**
 * 棋子走法生成器--黑马
 */
@Component
public class BlackKnightMoveGenerator extends AbstractKnightMoveGenerator {

    @Override
    public PointState getPointState() {
        return PointState.BLACK_KNIGHT;
    }
}
