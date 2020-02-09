package chessai.controller.movegenerator.impl;

import chessai.controller.movegenerator.AbstractChessMoveGenerator;
import chessai.model.PointState;

/**
 * 棋子走法生成器--双方车移动
 */
public abstract class AbstractRookMoveGenerator extends AbstractChessMoveGenerator {

    @Override
    public void generateMove(PointState[][] boardPosition, int startX, int startY, int nPly) {
        // 向上下左右四个方向遍历探索，一旦碰到一个不可达的位置，就表明之后的位置都不可达
        int targetX, targetY;
        // 水平方向探索
        targetX = startX;
        targetY = startY + 1;
        while (chessMoveManager.isValidMove(boardPosition, startX, startY, targetX, targetY)) {
            chessMoveManager.addMove(startX, startY, targetX, targetY, nPly);
            targetY++;
        }

        targetY = startY - 1;
        while (chessMoveManager.isValidMove(boardPosition, startX, startY, targetX, targetY)) {
            chessMoveManager.addMove(startX, startY, targetX, targetY, nPly);
            targetY--;
        }

        // 垂直方向探索
        targetY = startY;
        targetX = startX + 1;
        while (chessMoveManager.isValidMove(boardPosition, startX, startY, targetX, targetY)) {
            chessMoveManager.addMove(startX, startY, targetX, targetY, nPly);
            targetX++;
        }

        targetY = startY - 1;
        while (chessMoveManager.isValidMove(boardPosition, startX, startY, targetX, targetY)) {
            chessMoveManager.addMove(startX, startY, targetX, targetY, nPly);
            targetY--;
        }

    }
}
