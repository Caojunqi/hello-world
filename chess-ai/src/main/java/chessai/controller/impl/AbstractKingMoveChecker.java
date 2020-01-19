package chessai.controller.impl;

import chessai.controller.AbstractChessMoveChecker;
import chessai.model.ChessBoard;
import chessai.model.PointState;
import chessai.model.Position;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 棋子移动检测器-双方老将移动
 */
@Component
public abstract class AbstractKingMoveChecker extends AbstractChessMoveChecker {

    /**
     * 获取敌方老将
     *
     * @return 敌方老将类型
     */
    public abstract PointState getRivalKing();

    /**
     * 获取棋盘上敌方老将的位置
     *
     * @return 敌方老将位置信息
     */
    private Position getRivalKingPosition() {
        for (int i = 0; i < ChessBoard.getInstance().getHeight(); i++) {
            for (int j = 0; j < ChessBoard.getInstance().getLength(); j++) {
                if (ChessBoard.getInstance().getPointState(i, j) == getRivalKing()) {
                    return Position.valueOf(i, j);
                }
            }
        }
        throw new IllegalStateException(getRivalKing() + " 老将不见了！！");
    }

    /**
     * 走一步后双方老将是否会碰面
     *
     * @param targetX 移动目标点X坐标
     * @param targetY 移动目标点Y坐标
     * @return true-老将会碰面；false-老将不会碰面。
     */
    protected boolean isKingFace(int targetX, int targetY) {
        Position rivalKingPosition = getRivalKingPosition();
        if (rivalKingPosition.getY() != targetY) {
            return false;
        }

        过完年回来，先写老将见面逻辑
    }
}
