package chessai.controller.movegenerator.impl;

import chessai.controller.movegenerator.AbstractChessMoveGenerator;
import chessai.model.PointState;
import chessai.model.Position;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 棋子走法生成器--黑帅
 */
@Component
public class BlackKingMoveGenerator extends AbstractChessMoveGenerator {

    @Override
    public PointState getPointState() {
        return PointState.BLACK_KING;
    }

    @Override
    public List<Position> generateMove(PointState[][] boardPosition, int startX, int startY) {
        List<Position> positions = new ArrayList<>();
        for (int x = 0; x < 3; x++) {
            for (int y = 3; y < 6; y++) {
                if (chessMoveManager.isValidMove(boardPosition, startX, startY, x, y)) {
                    positions.add(Position.valueOf(x, y));
                }
            }
        }
        return positions;
    }
}
