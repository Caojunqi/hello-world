package chessai.controller.movegenerator;

import chessai.controller.ChessManager;
import chessai.controller.ChessMoveManager;
import chessai.model.PointState;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

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
     * @param nPly          搜索层数
     */
    public abstract void generateMove(PointState[][] boardPosition, int startX, int startY, int nPly);

    /**
     * 检测并添加棋子走法
     *
     * @param boardPosition 所面对的棋局局势
     * @param startX        棋子移动起始点X坐标
     * @param startY        棋子移动起始点Y坐标
     * @param targetX       棋子移动目标点X坐标
     * @param targetY       棋子移动目标点Y坐标
     * @param nPly          搜索层数
     */
    protected void checkAddMove(PointState[][] boardPosition, int startX, int startY, int targetX, int targetY, int nPly) {
        if (chessMoveManager.isValidMove(boardPosition, startX, startY, targetX, targetY)) {
            chessMoveManager.addMove(startX, startY, targetX, targetY, nPly);
        }
    }
}
