package chessai.controller.movegenerator;

import chessai.controller.ChessManager;
import chessai.controller.ChessMoveManager;
import chessai.model.PointState;
import chessai.model.Position;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 棋子走法生成器抽象类
 */
public abstract class AbstractChessMoveGenerator {

    @Autowired
    private ChessManager chessManager;

    @Autowired
    protected ChessMoveManager chessMoveManager;

    @PostConstruct
    public void init() {
        chessManager.registerChessMoveGenerator(this);
    }

    /**
     * 所要移动的棋点状态
     *
     * @return 棋点状态
     */
    public abstract PointState getPointState();

    /**
     * 生成棋子走法
     *
     * @param boardPosition 所面对的棋局局势
     * @param startX        棋子移动的起始点X坐标
     * @param startY        棋子移动的起始点Y坐标
     * @return 棋点在当前局面可走的所有位置
     */
    public abstract List<Position> generateMove(PointState[][] boardPosition, int startX, int startY);
}
