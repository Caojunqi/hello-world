package chessai.controller.impl;

import chessai.controller.AbstractChessMoveChecker;
import chessai.model.ChessBoard;
import chessai.model.PointState;
import org.springframework.stereotype.Component;

/**
 * 棋子移动检测器--红仕
 */
@Component
public class RedBishopMoveChecker extends AbstractChessMoveChecker {

    @Override
    public PointState getPointState() {
        return PointState.RED_BISHOP;
    }

    @Override
    public boolean checkMove(ChessBoard chessBoard, int targetX, int targetY) {
        return false;
    }
}
