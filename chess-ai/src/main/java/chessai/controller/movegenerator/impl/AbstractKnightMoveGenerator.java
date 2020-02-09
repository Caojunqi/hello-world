package chessai.controller.movegenerator.impl;

import chessai.controller.movegenerator.AbstractChessMoveGenerator;
import chessai.model.PointState;

/**
 * 棋子走法生成器--双方马移动
 */
public abstract class AbstractKnightMoveGenerator extends AbstractChessMoveGenerator {

    @Override
    public void generateMove(PointState[][] boardPosition, int startX, int startY, int nPly) {
        int targetX, targetY;
        // 插入右下方的有效走法
        targetX = startX - 2;
        targetY = startY + 1;
        checkAddMove(boardPosition, startX, startY, targetX, targetY, nPly);
        // 插入右下方的有效走法
        targetX = startX - 1;
        targetY = startY + 2;
        checkAddMove(boardPosition, startX, startY, targetX, targetY, nPly);
        // 插入右上方的有效走法
        targetX = startX + 2;
        targetY = startY + 1;
        checkAddMove(boardPosition, startX, startY, targetX, targetY, nPly);
        // 插入右上方的有效走法
        targetX = startX + 1;
        targetY = startY + 2;
        checkAddMove(boardPosition, startX, startY, targetX, targetY, nPly);
        // 插入左下方的有效走法
        targetX = startX - 1;
        targetY = startY - 2;
        checkAddMove(boardPosition, startX, startY, targetX, targetY, nPly);
        // 插入左下方的有效走法
        targetX = startX - 2;
        targetY = startY - 1;
        checkAddMove(boardPosition, startX, startY, targetX, targetY, nPly);
        // 插入左上方的有效走法
        targetX = startX + 2;
        targetY = startY - 1;
        checkAddMove(boardPosition, startX, startY, targetX, targetY, nPly);
        // 插入左上方的有效走法
        targetX = startX + 1;
        targetY = startY - 2;
        checkAddMove(boardPosition, startX, startY, targetX, targetY, nPly);
    }

}
