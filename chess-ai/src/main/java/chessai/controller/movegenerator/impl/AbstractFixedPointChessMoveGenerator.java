package chessai.controller.movegenerator.impl;

import chessai.controller.movegenerator.AbstractChessMoveGenerator;
import chessai.model.PointState;
import chessai.model.Position;

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
    public void generateMove(PointState[][] boardPosition, int startX, int startY, int nPly) {
        for (Position position : getFixedPoints()) {
            checkAddMove(boardPosition, startX, startY, position.getX(), position.getY(), nPly);
        }
    }

}
