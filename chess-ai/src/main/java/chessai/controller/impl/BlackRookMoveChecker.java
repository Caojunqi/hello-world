package chessai.controller.impl;

import chessai.model.PointState;
import org.springframework.stereotype.Component;

/**
 * 棋子移动检测器--黑车
 */
@Component
public class BlackRookMoveChecker extends AbstractRookMoveChecker {

    @Override
    public PointState getPointState() {
        return PointState.BLACK_ROOK;
    }

}
