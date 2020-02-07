package chessai.controller.impl;

import chessai.model.ChessBoard;
import chessai.model.PointState;
import chessai.model.Position;
import org.springframework.stereotype.Component;

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
    protected boolean isKingFace(int targetX, int targetY) {
        Position rivalKingPosition = getRivalKingPosition();
        if (rivalKingPosition.getX() != targetX) {
            // 两个老将不在同一列
            return false;
        }

        for (int i = targetY + 1; i < rivalKingPosition.getY(); i++) {
            if (ChessBoard.getInstance().getPointState(targetX, i) != PointState.NO_CHESS) {
                // 两个棋子中间有棋子
                return false;
            }
        }
        return true;
    }

    @Override
    protected boolean inPalace(int targetX, int targetY) {
        return ChessBoard.getInstance().inBlackPalace(targetX, targetY);
    }
}
