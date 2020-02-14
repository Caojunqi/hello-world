package chessai.controller.movegenerator.impl;

import chessai.controller.movegenerator.AbstractChessMoveGenerator;
import chessai.model.PointState;
import chessai.model.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * 棋子走法生成器--双方马移动
 */
public abstract class AbstractKnightMoveGenerator extends AbstractChessMoveGenerator {

    @Override
    public List<Position> generateMove(PointState[][] boardPosition, int startX, int startY) {
        List<Position> positions = new ArrayList<>();
        int targetX, targetY;
        // 插入右下方的有效走法
        targetX = startX - 2;
        targetY = startY + 1;
        if (chessMoveManager.isValidMove(boardPosition, startX, startY, targetX, targetY)) {
            positions.add(Position.valueOf(targetX, targetY));
        }
        // 插入右下方的有效走法
        targetX = startX - 1;
        targetY = startY + 2;
        if (chessMoveManager.isValidMove(boardPosition, startX, startY, targetX, targetY)) {
            positions.add(Position.valueOf(targetX, targetY));
        }
        // 插入右上方的有效走法
        targetX = startX + 2;
        targetY = startY + 1;
        if (chessMoveManager.isValidMove(boardPosition, startX, startY, targetX, targetY)) {
            positions.add(Position.valueOf(targetX, targetY));
        }
        // 插入右上方的有效走法
        targetX = startX + 1;
        targetY = startY + 2;
        if (chessMoveManager.isValidMove(boardPosition, startX, startY, targetX, targetY)) {
            positions.add(Position.valueOf(targetX, targetY));
        }
        // 插入左下方的有效走法
        targetX = startX - 1;
        targetY = startY - 2;
        if (chessMoveManager.isValidMove(boardPosition, startX, startY, targetX, targetY)) {
            positions.add(Position.valueOf(targetX, targetY));
        }
        // 插入左下方的有效走法
        targetX = startX - 2;
        targetY = startY - 1;
        if (chessMoveManager.isValidMove(boardPosition, startX, startY, targetX, targetY)) {
            positions.add(Position.valueOf(targetX, targetY));
        }
        // 插入左上方的有效走法
        targetX = startX + 2;
        targetY = startY - 1;
        if (chessMoveManager.isValidMove(boardPosition, startX, startY, targetX, targetY)) {
            positions.add(Position.valueOf(targetX, targetY));
        }
        // 插入左上方的有效走法
        targetX = startX + 1;
        targetY = startY - 2;
        if (chessMoveManager.isValidMove(boardPosition, startX, startY, targetX, targetY)) {
            positions.add(Position.valueOf(targetX, targetY));
        }
        return positions;
    }

}
