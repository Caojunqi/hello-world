package chessai.controller.evaluation.jia.chessevaluator.impl;

import chessai.controller.evaluation.jia.chessevaluator.AbstractChessEvaluator;
import chessai.model.PointState;
import org.springframework.stereotype.Component;

/**
 * 棋子评估器--红车
 */
@Component
public class RedRookEvaluator extends AbstractChessEvaluator {

    @Override
    public PointState getPointState() {
        return PointState.RED_ROOK;
    }

    @Override
    public int getChessBaseValue() {
        return jiaBoardEvaluator.ROOK_BASE_VALUE;
    }

    @Override
    public int getChessFlexValue() {
        return jiaBoardEvaluator.ROOK_FLEXIBILITY;
    }

}
