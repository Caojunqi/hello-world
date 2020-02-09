package chessai.controller.movegenerator.impl;

import chessai.model.PointState;
import chessai.model.Position;
import chessai.util.ChessBoardUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 棋子走法生成器--红仕
 */
@Component
public class RedGuardMoveGenerator extends AbstractFixedPointChessMoveGenerator {

    @Override
    public PointState getPointState() {
        return PointState.RED_GUARD;
    }

    @Override
    protected List<Position> getFixedPoints() {
        return ChessBoardUtils.RED_GUARD_MOVE_POINTS;
    }
}
