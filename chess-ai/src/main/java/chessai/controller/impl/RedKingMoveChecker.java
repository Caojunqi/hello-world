package chessai.controller.impl;

import chessai.controller.AbstractChessMoveChecker;
import chessai.model.ChessBoard;
import chessai.model.PointState;
import org.springframework.stereotype.Component;

/**
 * 棋子移动检测器--红将
 */
@Component
public class RedKingMoveChecker extends AbstractKingMoveChecker {

    @Override
    public PointState getPointState() {
        return PointState.RED_KING;
    }

    @Override
    public PointState getRivalKing() {
        return PointState.BLACK_KING;
    }

    @Override
    public boolean checkMove(ChessBoard chessBoard, int targetX, int targetY) {
        return false;
    }
}
