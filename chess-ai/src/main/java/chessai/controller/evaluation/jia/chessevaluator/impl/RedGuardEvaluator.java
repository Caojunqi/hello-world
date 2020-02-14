package chessai.controller.evaluation.jia.chessevaluator.impl;

import chessai.controller.evaluation.jia.chessevaluator.AbstractChessEvaluator;
import chessai.model.PointState;
import org.springframework.stereotype.Component;

/**
 * 棋子评估器--红仕
 */
@Component
public class RedGuardEvaluator extends AbstractChessEvaluator {

    @Override
    public PointState getPointState() {
        return PointState.RED_GUARD;
    }

    @Override
    public int getChessBaseValue() {
        return jiaBoardEvaluator.GUARD_BASE_VALUE;
    }

    @Override
    public int getChessFlexValue() {
        return jiaBoardEvaluator.GUARD_FLEXIBILITY;
    }

}
