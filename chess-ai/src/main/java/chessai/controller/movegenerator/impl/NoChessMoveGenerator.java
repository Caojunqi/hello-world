package chessai.controller.movegenerator.impl;

import chessai.controller.movegenerator.AbstractChessMoveGenerator;
import chessai.model.PointState;
import chessai.model.Position;
import org.springframework.stereotype.Component;

import java.util.List;

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
    public List<Position> generateMove(PointState[][] boardPosition, int startX, int startY) {
        throw new IllegalStateException("无棋的点不应该执行走法生成逻辑！！");
    }
}
