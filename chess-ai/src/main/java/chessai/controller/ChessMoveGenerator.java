package chessai.controller;

import chessai.model.ChessBoard;
import chessai.model.PointState;

/**
 * 棋子走法生成器
 */
public class ChessMoveGenerator {

    /**
     * 判断指定棋子移动是否合法
     *
     * @param startX  要移动的起点X坐标
     * @param startY  要移动的起点Y坐标
     * @param targetX 要移动的目标点X坐标
     * @param targetY 要移动的目标点Y坐标
     * @return true-棋子移动合法；false-棋子移动不合法。
     */
    public boolean isValidMove(int startX, int startY, int targetX, int targetY) {
        if (startX == targetX && startY == targetY) {
            // 目标与源相同
            return false;
        }

        PointState startState = ChessBoard.getInstance().getPointState(startX, startY);
        PointState targetState = ChessBoard.getInstance().getPointState(targetX, targetY);
        if (startState.isSameSide(targetState)) {
            // 吃自己的棋，非法
            return false;
        }

        AbstractChessMoveChecker moveChecker = ChessManager.getInstance().getChessMoveChecker(startState);
        return moveChecker.checkMove(targetX, targetY);
    }

}
