package chessai.controller.movegenerator.impl;

import chessai.controller.movegenerator.AbstractChessMoveGenerator;
import chessai.model.PointState;
import chessai.model.Position;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 棋子走法生成器--红帅
 */
@Component
public class RedKingMoveGenerator extends AbstractChessMoveGenerator {

    @Override
    public PointState getPointState() {
        return PointState.RED_KING;
    }

    @Override
    public List<Position> generateValidMove(PointState[][] boardPosition, int startX, int startY) {
        List<Position> positions = new ArrayList<>();
        for (int x = 7; x < 10; x++) {
            for (int y = 3; y < 6; y++) {
                if (chessMoveManager.isValidMove(boardPosition, startX, startY, x, y)) {
                    positions.add(Position.valueOf(x, y));
                }
            }
        }
        return positions;
    }

    @Override
    public List<Position> generateRelateMove(PointState[][] boardPosition, int startX, int startY) {
        List<Position> positions = new ArrayList<>();
        for (int x = 7; x < 10; x++) {
            for (int y = 3; y < 6; y++) {
                if (chessMoveManager.isRelateMove(boardPosition, startX, startY, x, y)) {
                    positions.add(Position.valueOf(x, y));
                }
            }
        }
        return positions;
    }
}
