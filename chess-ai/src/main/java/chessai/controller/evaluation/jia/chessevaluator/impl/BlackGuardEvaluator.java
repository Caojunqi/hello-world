package chessai.controller.evaluation.jia.chessevaluator.impl;

import chessai.controller.evaluation.jia.chessevaluator.AbstractChessEvaluator;
import chessai.model.PointState;
import org.springframework.stereotype.Component;

/**
 * 棋子评估器--黑士
 */
@Component
public class BlackGuardEvaluator extends AbstractChessEvaluator {

    @Override
    public PointState getPointState() {
        return PointState.BLACK_GUARD;
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
