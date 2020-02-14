package chessai.controller.evaluation.jia.chessevaluator.impl;

import chessai.controller.evaluation.jia.chessevaluator.AbstractChessEvaluator;
import chessai.model.PointState;
import org.springframework.stereotype.Component;

/**
 * 棋子评估器--红相
 */
@Component
public class RedMinisterEvaluator extends AbstractChessEvaluator {
    @Override
    public PointState getPointState() {
        return PointState.RED_MINISTER;
    }

    @Override
    public int getChessBaseValue() {
        return jiaBoardEvaluator.MINISTER_BASE_VALUE;
    }

    @Override
    public int getChessFlexValue() {
        return jiaBoardEvaluator.MINISTER_FLEXIBILITY;
    }

}
