package chessai.controller.impl;

import chessai.controller.AbstractChessMoveChecker;
import chessai.model.ChessBoard;
import chessai.model.PointState;
import chessai.model.Position;

/**
 * 棋子移动检测器-双方老将移动
 */
public abstract class AbstractKingMoveChecker extends AbstractChessMoveChecker {

    /**
     * 获取敌方老将
     *
     * @return 敌方老将类型
     */
    public abstract PointState getRivalKing();

    /**
     * 是否老将见面
     *
     * @param targetX 棋子移动的目标点X坐标
     * @param targetY 棋子移动的目标点Y坐标
     * @return true-老将见面；false-老将不见面。
     */
    protected abstract boolean isKingFace(int targetX, int targetY);

    /**
     * 移动目标点是否在九宫格中
     *
     * @param targetX 棋子移动的目标点X坐标
     * @param targetY 棋子移动的目标点Y坐标
     * @return true-在九宫格中；false-不在九宫格中。
     */
    protected abstract boolean inPalace(int targetX, int targetY);

    @Override
    public boolean checkMove(int startX, int startY, int targetX, int targetY) {
        if (isKingFace(targetX, targetY)) {
            // 老将见面，移动不合法
            return false;
        }
        if (!inPalace(targetX, targetY)) {
            // 目标点不在九宫格中，移动不合法
            return false;
        }
        if (Math.abs(startX - targetX) + Math.abs(startY - targetY) > 1) {
            // 将帅只能走一步直线
            return false;
        }
        return true;
    }

    /**
     * 获取棋盘上敌方老将的位置
     *
     * @return 敌方老将位置信息
     */
    protected Position getRivalKingPosition() {
        for (int i = 0; i < ChessBoard.getInstance().getHeight(); i++) {
            for (int j = 0; j < ChessBoard.getInstance().getLength(); j++) {
                if (ChessBoard.getInstance().getPointState(i, j) == getRivalKing()) {
                    return Position.valueOf(i, j);
                }
            }
        }
        throw new IllegalStateException(getRivalKing() + " 老将不见了！！");
    }


}
