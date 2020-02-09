package chessai.controller.movegenerator.impl;

import chessai.model.PointState;
import chessai.model.Position;
import chessai.util.ChessBoardUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 棋子走法生成器--红相
 */
@Component
public class RedMinisterMoveGenerator extends AbstractFixedPointChessMoveGenerator {

    @Override
    public PointState getPointState() {
        return PointState.RED_MINISTER;
    }

    @Override
    protected List<Position> getFixedPoints() {
        return ChessBoardUtils.RED_MINISTER_MOVE_POINTS;
    }
}
