package chessai.controller.evaluation.jia.chessevaluator.impl;

import chessai.controller.evaluation.jia.chessevaluator.AbstractChessEvaluator;
import chessai.model.PointState;
import org.springframework.stereotype.Component;

/**
 * 棋子评估器--红炮
 */
@Component
public class RedCannonEvaluator extends AbstractChessEvaluator {

    @Override
    public PointState getPointState() {
        return PointState.RED_CANNON;
    }

    @Override
    public int getChessBaseValue() {
        return jiaBoardEvaluator.CANNON_BASE_VALUE;
    }

    @Override
    public int getChessFlexValue() {
        return jiaBoardEvaluator.CANNON_FLEXIBILITY;
    }

}
