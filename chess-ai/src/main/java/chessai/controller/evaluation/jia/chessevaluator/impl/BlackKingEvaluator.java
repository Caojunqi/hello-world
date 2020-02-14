package chessai.controller.evaluation.jia.chessevaluator.impl;

import chessai.controller.evaluation.jia.chessevaluator.AbstractChessEvaluator;
import chessai.model.PointState;
import org.springframework.stereotype.Component;

/**
 * 棋子评估器--黑帅
 */
@Component
public class BlackKingEvaluator extends AbstractChessEvaluator {

    @Override
    public PointState getPointState() {
        return PointState.BLACK_KING;
    }

    @Override
    public int getChessBaseValue() {
        return jiaBoardEvaluator.KING_BASE_VALUE;
    }

    @Override
    public int getChessFlexValue() {
        return jiaBoardEvaluator.KING_FLEXIBILITY;
    }

}
