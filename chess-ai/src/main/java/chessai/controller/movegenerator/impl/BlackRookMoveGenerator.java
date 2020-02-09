package chessai.controller.movegenerator.impl;

import chessai.model.PointState;
import org.springframework.stereotype.Component;

/**
 * 棋子走法生成器--黑车
 */
@Component
public class BlackRookMoveGenerator extends AbstractRookMoveGenerator {

    @Override
    public PointState getPointState() {
        return PointState.BLACK_ROOK;
    }

}
