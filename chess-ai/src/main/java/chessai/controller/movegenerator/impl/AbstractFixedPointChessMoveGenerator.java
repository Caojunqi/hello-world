package chessai.controller.movegenerator.impl;

import chessai.controller.movegenerator.AbstractChessMoveGenerator;
import chessai.model.PointState;
import chessai.model.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * 固定移动点棋子走法生成器--黑象、红相、黑士、红仕
 */
public abstract class AbstractFixedPointChessMoveGenerator extends AbstractChessMoveGenerator {

    /**
     * 获取棋子的固定移动点集合
     *
     * @return 固定移动点集合
     */
    protected abstract List<Position> getFixedPoints();

    @Override
    public List<Position> generateValidMove(PointState[][] boardPosition, int startX, int startY) {
        List<Position> positions = new ArrayList<>();
        for (Position position : getFixedPoints()) {
            if (chessMoveManager.isValidMove(boardPosition, startX, startY, position.getX(), position.getY())) {
                positions.add(Position.valueOf(position.getX(), position.getY()));
            }
        }
        return positions;
    }

    @Override
    public List<Position> generateRelateMove(PointState[][] boardPosition, int startX, int startY) {
        List<Position> positions = new ArrayList<>();
        for (Position position : getFixedPoints()) {
            if (chessMoveManager.isRelateMove(boardPosition, startX, startY, position.getX(), position.getY())) {
                positions.add(Position.valueOf(position.getX(), position.getY()));
            }
        }
        return positions;
    }

}
