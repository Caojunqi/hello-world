package chessai.controller.movechecker.impl;

import chessai.model.PointState;
import chessai.model.Position;
import chessai.util.ChessBoardUtils;
import org.springframework.stereotype.Component;

/**
 * 棋子移动检测器--红帅
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
    protected boolean isKingFace(PointState[][] boardPosition, int targetX, int targetY) {
        Position rivalKingPosition = getRivalKingPosition(boardPosition);
        if (rivalKingPosition.getX() != targetX) {
            // 两个老将不在同一列
            return false;
        }

        for (int i = targetY - 1; i < rivalKingPosition.getY(); i--) {
            if (boardPosition[targetX][i] != PointState.NO_CHESS) {
                // 两个棋子中间有棋子
                return false;
            }
        }
        return true;
    }

    @Override
    protected boolean inPalace(int targetX, int targetY) {
        return ChessBoardUtils.inRedPalace(targetX, targetY);
    }
}
