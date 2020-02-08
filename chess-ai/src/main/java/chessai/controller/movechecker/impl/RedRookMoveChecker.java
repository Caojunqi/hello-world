package chessai.controller.movechecker.impl;

import chessai.model.PointState;
import org.springframework.stereotype.Component;

/**
 * 棋子移动检测器--红车
 */
@Component
public class RedRookMoveChecker extends AbstractRookMoveChecker {

    @Override
    public PointState getPointState() {
        return PointState.RED_ROOK;
    }

}
