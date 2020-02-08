package chessai.controller.impl;

import chessai.model.PointState;
import org.springframework.stereotype.Component;

/**
 * 棋子移动检测器--黑马
 */
@Component
public class BlackKnightMoveChecker extends AbstractKnightMoveChecker {

    @Override
    public PointState getPointState() {
        return PointState.BLACK_KNIGHT;
    }

}
