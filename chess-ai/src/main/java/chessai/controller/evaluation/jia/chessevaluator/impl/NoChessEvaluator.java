package chessai.controller.evaluation.jia.chessevaluator.impl;

import chessai.controller.evaluation.jia.chessevaluator.AbstractChessEvaluator;
import chessai.model.PointState;
import chessai.model.Position;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

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

    @Override
    public List<Position> getRelatePieces(PointState[][] boardPosition, int x, int y) {
        // 任何位置都与无棋点无关
        return Collections.emptyList();
    }
}
