package chessai.controller.evaluation.jia.chessevaluator.impl;

import chessai.controller.evaluation.jia.chessevaluator.AbstractChessEvaluator;
import chessai.model.PointState;
import org.springframework.stereotype.Component;

/**
 * 棋子评估器--无棋
 */
@Component
public class NoChessEvaluator extends AbstractChessEvaluator {

    @Override
    public PointState getPointState() {
        return PointState.NO_CHESS;
    }

    @Override
    public int getChessBaseValue() {
        return 0;
    }

    @Override
    public int getChessFlexValue() {
        return 0;
    }

}
