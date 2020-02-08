package chessai.controller.movegenerator.impl;

import chessai.common.SystemOut;
import chessai.controller.movegenerator.AbstractChessMoveGenerator;
import chessai.model.PointState;
import org.springframework.stereotype.Component;

/**
 * 棋子走法生成器--无棋
 */
@Component
public class NoChessMoveGenerator extends AbstractChessMoveGenerator {

    @Override
    public PointState getPointState() {
        return PointState.NO_CHESS;
    }

    @Override
    public void generateMove(PointState[][] boardPosition, int startX, int startY, int nPly) {
        SystemOut.error("无棋的点不应该执行走法生成逻辑！！");
    }
}
