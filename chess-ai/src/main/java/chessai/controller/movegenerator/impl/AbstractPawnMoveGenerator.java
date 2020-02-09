package chessai.controller.movegenerator.impl;

import chessai.controller.movegenerator.AbstractChessMoveGenerator;
import chessai.model.PointState;

/**
 * 棋子走法生成器--双方兵移动
 */
public abstract class AbstractPawnMoveGenerator extends AbstractChessMoveGenerator {

    /**
     * 判断兵是否过河
     *
     * @param startX 移动起始点X坐标
     * @param startY 移动起始点Y坐标
     * @return true-小兵过河；false-小兵未过河
     */
    protected abstract boolean isCrossRiver(int startX, int startY);

    /**
     * 小兵前进
     *
     * @param startX 移动起始点的X坐标
     * @return 前进后的X坐标
     */
    protected abstract int moveForward(int startX);

    @Override
    public void generateMove(PointState[][] boardPosition, int startX, int startY, int nPly) {
        int targetX, targetY;
        // 小兵前进
        targetX = moveForward(startX);
        targetY = startY;
        checkAddMove(boardPosition, startX, startY, targetX, targetY, nPly);
        // 过河后小兵可以左右移动
        if (isCrossRiver(startX, startY)) {
            targetX = startX;
            targetY = startY - 1;
            checkAddMove(boardPosition, startX, startY, targetX, targetY, nPly);

            targetY = startY + 1;
            checkAddMove(boardPosition, startX, startY, targetX, targetY, nPly);
        }
    }

}
