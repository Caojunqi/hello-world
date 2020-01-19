package chessai.controller.impl;

import chessai.controller.AbstractChessMoveChecker;
import chessai.model.ChessBoard;
import chessai.model.PointState;
import chessai.model.Position;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 棋子移动检测器--黑帅
 */
@Component
public class BlackKingMoveChecker extends AbstractKingMoveChecker {

    @Override
    public PointState getPointState() {
        return PointState.BLACK_KING;
    }

    @Override
    public PointState getRivalKing() {
        return PointState.RED_KING;
    }

    @Override
    public boolean checkMove(ChessBoard chessBoard, int targetX, int targetY) {
        return false;
    }

}
