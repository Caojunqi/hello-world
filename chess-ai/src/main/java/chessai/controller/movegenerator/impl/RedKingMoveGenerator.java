package chessai.controller.movegenerator.impl;

import chessai.controller.movegenerator.AbstractChessMoveGenerator;
import chessai.model.PointState;
import org.springframework.stereotype.Component;

/**
 * 棋子走法生成器--红将
 */
@Component
public class RedKingMoveGenerator extends AbstractChessMoveGenerator {

    @Override
    public PointState getPointState() {
        return PointState.RED_KING;
    }

    @Override
    public void generateMove(PointState[][] boardPosition, int startX, int startY, int nPly) {
        for (int x = 7; x < 10; x++) {
            for (int y = 3; y < 6; y++) {
                checkAddMove(boardPosition, startX, startY, x, y, nPly);
            }
        }
    }

}
