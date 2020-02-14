package chessai.controller.evaluation.jia.chessevaluator.impl;

import chessai.controller.evaluation.jia.chessevaluator.AbstractChessEvaluator;
import chessai.model.PointState;
import chessai.model.Position;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 棋子评估器--红马
 */
@Component
public class RedKnightEvaluator extends AbstractChessEvaluator {

    @Override
    public PointState getPointState() {
        return PointState.RED_KNIGHT;
    }

    @Override
    public int getChessBaseValue() {
        return jiaBoardEvaluator.KNIGHT_BASE_VALUE;
    }

    @Override
    public int getChessFlexValue() {
        return jiaBoardEvaluator.KNIGHT_FLEXIBILITY;
    }

    @Override
    public List<Position> getRelatePieces(PointState[][] boardPosition, int x, int y) {
        return null;
    }
}
