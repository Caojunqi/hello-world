package chessai.controller.impl;

import chessai.model.PointState;
import org.springframework.stereotype.Component;

/**
 * 棋子移动检测器--黑炮
 */
@Component
public class BlackCannonMoveChecker extends AbstractCannonMoveChecker {

    @Override
    public PointState getPointState() {
        return PointState.BLACK_CANNON;
    }

}
