package chessai.controller.movechecker.impl;

import chessai.controller.movechecker.AbstractChessMoveChecker;
import chessai.model.PointState;
import org.springframework.stereotype.Component;

/**
 * 棋子移动检测器--没有棋子
 */
@Component
public class NoChessMoveChecker extends AbstractChessMoveChecker {

    @Override
    public PointState getPointState() {
        return PointState.NO_CHESS;
    }

    @Override
    public boolean checkMove(PointState[][] boardPosition, int startX, int startY, int targetX, int targetY) {
        // 没有棋子不能移动
        return false;
    }
}
