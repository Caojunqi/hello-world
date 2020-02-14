package chessai.controller.evaluation.jia.chessevaluator.impl;

import chessai.controller.evaluation.jia.chessevaluator.AbstractChessEvaluator;
import chessai.model.PointState;
import chessai.model.Position;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 棋子评估器--黑炮
 */
@Component
public class BlackCannonEvaluator extends AbstractChessEvaluator {

    @Override
    public PointState getPointState() {
        return PointState.BLACK_CANNON;
    }

    @Override
    public int getChessBaseValue() {
        return jiaBoardEvaluator.CANNON_BASE_VALUE;
    }

    @Override
    public int getChessFlexValue() {
        return jiaBoardEvaluator.CANNON_FLEXIBILITY;
    }

    @Override
    public List<Position> getRelatePieces(PointState[][] boardPosition, int x, int y) {
        return null;
    }
}
