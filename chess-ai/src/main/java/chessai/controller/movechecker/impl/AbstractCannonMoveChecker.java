package chessai.controller.movechecker.impl;

import chessai.controller.movechecker.AbstractChessMoveChecker;
import chessai.model.PointState;
import chessai.util.ChessBoardUtils;

/**
 * 棋子移动检测器-双方炮移动
 */
public abstract class AbstractCannonMoveChecker extends AbstractChessMoveChecker {

    @Override
    public boolean checkMove(PointState[][] boardPosition, int startX, int startY, int targetX, int targetY) {

        if (startX != targetX && startY != targetY) {
            // 炮走直线
            return false;
        }

        if (!canKillEnemy(boardPosition, targetX, targetY)) {
            // 炮不吃子时经过的路线中不能有棋子
            return !ChessBoardUtils.hasOtherChess(boardPosition, startX, startY, targetX, targetY);
        } else {
            // 炮吃子时，经过的路线上必须有一个棋子
            return ChessBoardUtils.hasOneChess(boardPosition, startX, startY, targetX, targetY);
        }
    }

    /**
     * 此步移动是否能够吃子
     *
     * @param boardPosition 所面对的棋局局势
     * @param targetX       移动目标点X坐标
     * @param targetY       移动目标点Y坐标
     * @return true-可以吃子；false-不能吃子。
     */
    private boolean canKillEnemy(PointState[][] boardPosition, int targetX, int targetY) {
        PointState targetState = boardPosition[targetX][targetY];
        if (targetState == PointState.NO_CHESS) {
            return false;
        }
        return !targetState.isSameCamp(getPointState());
    }

}
