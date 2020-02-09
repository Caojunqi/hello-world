package chessai.controller.movegenerator.impl;

import chessai.model.PointState;
import chessai.model.Position;
import chessai.util.ChessBoardUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 棋子走法生成器--黑士
 */
@Component
public class BlackGuardMoveGenerator extends AbstractFixedPointChessMoveGenerator {

    @Override
    public PointState getPointState() {
        return PointState.BLACK_GUARD;
    }

    @Override
    protected List<Position> getFixedPoints() {
        return ChessBoardUtils.BLACK_GUARD_MOVE_POINTS;
    }
}
