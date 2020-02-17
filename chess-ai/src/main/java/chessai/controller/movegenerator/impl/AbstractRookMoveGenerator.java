package chessai.controller.movegenerator.impl;

import chessai.controller.movegenerator.AbstractChessMoveGenerator;
import chessai.model.PointState;
import chessai.model.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * 棋子走法生成器--双方车移动
 */
public abstract class AbstractRookMoveGenerator extends AbstractChessMoveGenerator {

    @Override
    public List<Position> generateValidMove(PointState[][] boardPosition, int startX, int startY) {
        List<Position> positions = new ArrayList<>();
        // 向上下左右四个方向遍历探索，一旦碰到一个不可达的位置，就表明之后的位置都不可达
        int targetX, targetY;
        // 水平方向探索
        targetX = startX;
        targetY = startY + 1;
        while (chessMoveManager.isValidMove(boardPosition, startX, startY, targetX, targetY)) {
            positions.add(Position.valueOf(targetX, targetY));
            targetY++;
        }

        targetY = startY - 1;
        while (chessMoveManager.isValidMove(boardPosition, startX, startY, targetX, targetY)) {
            positions.add(Position.valueOf(targetX, targetY));
            targetY--;
        }

        // 垂直方向探索
        targetY = startY;
        targetX = startX + 1;
        while (chessMoveManager.isValidMove(boardPosition, startX, startY, targetX, targetY)) {
            positions.add(Position.valueOf(targetX, targetY));
            targetX++;
        }

        targetX = startX - 1;
        while (chessMoveManager.isValidMove(boardPosition, startX, startY, targetX, targetY)) {
            positions.add(Position.valueOf(targetX, targetY));
            targetX--;
        }
        return positions;
    }

    @Override
    public List<Position> generateRelateMove(PointState[][] boardPosition, int startX, int startY) {
        List<Position> positions = new ArrayList<>();
        // 向上下左右四个方向遍历探索，一旦碰到一个不可达的位置，就表明之后的位置都不可达
        int targetX, targetY;
        // 水平方向探索
        targetX = startX;
        targetY = startY + 1;
        while (chessMoveManager.isRelateMove(boardPosition, startX, startY, targetX, targetY)) {
            positions.add(Position.valueOf(targetX, targetY));
            targetY++;
        }

        targetY = startY - 1;
        while (chessMoveManager.isRelateMove(boardPosition, startX, startY, targetX, targetY)) {
            positions.add(Position.valueOf(targetX, targetY));
            targetY--;
        }

        // 垂直方向探索
        targetY = startY;
        targetX = startX + 1;
        while (chessMoveManager.isRelateMove(boardPosition, startX, startY, targetX, targetY)) {
            positions.add(Position.valueOf(targetX, targetY));
            targetX++;
        }

        targetX = startX - 1;
        while (chessMoveManager.isRelateMove(boardPosition, startX, startY, targetX, targetY)) {
            positions.add(Position.valueOf(targetX, targetY));
            targetX--;
        }
        return positions;
    }
}
