package chessai.controller.impl;

import chessai.controller.AbstractChessMoveChecker;
import chessai.model.ChessBoard;
import chessai.model.PointState;
import org.springframework.stereotype.Component;

/**
 * 棋子移动检测器--黑车
 */
@Component
public class BlackCarMoveChecker extends AbstractChessMoveChecker {

    @Override
    public PointState getPointState() {
        return PointState.BLACK_CAR;
    }

    @Override
    public boolean checkMove(ChessBoard chessBoard, int targetX, int targetY) {
        return false;
    }
}
