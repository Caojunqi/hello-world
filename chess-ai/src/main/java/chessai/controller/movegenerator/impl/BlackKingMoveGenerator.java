package chessai.controller.movegenerator.impl;

import chessai.controller.movegenerator.AbstractChessMoveGenerator;
import chessai.model.PointState;
import org.springframework.stereotype.Component;

/**
 * 棋子走法生成器--黑帅
 */
@Component
public class BlackKingMoveGenerator extends AbstractChessMoveGenerator {

    @Override
    public PointState getPointState() {
        return PointState.BLACK_KING;
    }

    @Override
    public void generateMove(PointState[][] boardPosition, int startX, int startY, int nPly) {
        for (int x = 0; x < 3; x++) {
            for (int y = 3; y < 6; y++) {
                checkAddMove(boardPosition, startX, startY, x, y, nPly);
            }
        }
    }
}