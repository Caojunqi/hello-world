package chessai.controller.evaluation.jia.chessevaluator.impl;

import chessai.controller.evaluation.jia.chessevaluator.AbstractChessEvaluator;
import chessai.model.PointState;
import org.springframework.stereotype.Component;

/**
 * 棋子评估器--黑马
 */
@Component
public class BlackKnightEvaluator extends AbstractChessEvaluator {

    @Override
    public PointState getPointState() {
        return PointState.BLACK_KNIGHT;
    }

    @Override
    public int getChessBaseValue() {
        return jiaBoardEvaluator.KNIGHT_BASE_VALUE;
    }

    @Override
    public int getChessFlexValue() {
        return jiaBoardEvaluator.KNIGHT_FLEXIBILITY;
    }

}
