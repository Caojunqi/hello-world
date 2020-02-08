package chessai.controller.impl;

import chessai.model.PointState;
import org.springframework.stereotype.Component;

/**
 * 棋子移动检测器--红炮
 */
@Component
public class RedCannonMoveChecker extends AbstractCannonMoveChecker {

    @Override
    public PointState getPointState() {
        return PointState.RED_CANNON;
    }

}
