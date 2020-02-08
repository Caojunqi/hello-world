package chessai.controller.movechecker;

import chessai.controller.ChessManager;
import chessai.model.PointState;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * 棋子移动检测器抽象类，判断棋子移动是否合法
 */
public abstract class AbstractChessMoveChecker {

    @Autowired
    private ChessManager chessManager;

    @PostConstruct
    public void init() {
        chessManager.registerChessMoveChecker(this);
    }

    /**
     * 所要移动的棋点状态
     *
     * @return 棋点状态
     */
    public abstract PointState getPointState();

    /**
     * 检测棋子移动
     *
     * @param boardPosition 所面对的棋局局势
     * @param startX        棋子移动的起始点X坐标
     * @param startY        棋子移动的起始点Y坐标
     * @param targetX       棋子移动的目标点X坐标
     * @param targetY       棋子移动的目标点Y坐标
     * @return true-可移动至目标点；false-不可移动至目标点。
     */
    public abstract boolean checkMove(PointState[][] boardPosition, int startX, int startY, int targetX, int targetY);
}
