package chessai.controller.evaluation.jia.chessevaluator.impl;

import chessai.controller.evaluation.jia.chessevaluator.AbstractChessEvaluator;
import chessai.model.PointState;
import chessai.model.Position;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 棋子评估器--黑车
 */
@Component
public class BlackRookEvaluator extends AbstractChessEvaluator {

    @Override
    public PointState getPointState() {
        return PointState.BLACK_ROOK;
    }

    @Override
    public int getChessBaseValue() {
        return jiaBoardEvaluator.ROOK_BASE_VALUE;
    }

    @Override
    public int getChessFlexValue() {
        return jiaBoardEvaluator.ROOK_FLEXIBILITY;
    }

    @Override
    public List<Position> getRelatePieces(PointState[][] boardPosition, int x, int y) {
        return null;
    }
}
