package chessai.controller.evaluation.jia.chessevaluator.impl;

import chessai.controller.evaluation.jia.chessevaluator.AbstractChessEvaluator;
import chessai.model.PointState;
import org.springframework.stereotype.Component;

/**
 * 棋子评估器--黑兵
 */
@Component
public class BlackPawnEvaluator extends AbstractChessEvaluator {

    @Override
    public PointState getPointState() {
        return PointState.BLACK_PAWN;
    }

    @Override
    public int getChessBaseValue() {
        return jiaBoardEvaluator.PAWN_BASE_VALUE;
    }

    @Override
    public int getChessFlexValue() {
        return jiaBoardEvaluator.PAWN_FLEXIBILITY;
    }

}
