package chessai.controller.impl;

import chessai.model.PointState;
import org.springframework.stereotype.Component;

/**
 * 棋子移动检测器--红马
 */
@Component
public class RedKnightMoveChecker extends AbstractKnightMoveChecker {

    @Override
    public PointState getPointState() {
        return PointState.RED_KNIGHT;
    }

}
