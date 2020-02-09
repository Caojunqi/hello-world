package chessai.controller.movegenerator.impl;

import chessai.model.PointState;
import chessai.model.Position;
import chessai.util.ChessBoardUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 棋子走法生成器--黑象
 */
@Component
public class BlackMinisterMoveGenerator extends AbstractFixedPointChessMoveGenerator {

    @Override
    public PointState getPointState() {
        return PointState.BLACK_MINISTER;
    }

    @Override
    protected List<Position> getFixedPoints() {
        return ChessBoardUtils.BLACK_MINISTER_MOVE_POINTS;
    }
}
