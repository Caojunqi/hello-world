package chessai.controller;

import chessai.controller.movechecker.AbstractChessMoveChecker;
import chessai.model.PointState;
import chessai.util.ChessBoardUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 棋子走法管理类
 */
@Component
public class ChessMoveManager {

    private static ChessMoveManager self;

    public static ChessMoveManager getInstance() {
        return self;
    }

    @PostConstruct
    public void init() {
        self = this;
    }

    /**
     * 判断指定棋子移动是否合法
     *
     * @param boardPosition 所面对的棋局局势
     * @param startX        要移动的起点X坐标
     * @param startY        要移动的起点Y坐标
     * @param targetX       要移动的目标点X坐标
     * @param targetY       要移动的目标点Y坐标
     * @return true-棋子移动合法；false-棋子移动不合法。
     */
    public boolean isValidMove(PointState[][] boardPosition, int startX, int startY, int targetX, int targetY) {
        if (!ChessBoardUtils.inBoard(targetX, targetY)) {
            // 目标点超出棋盘范围
            return false;
        }

        if (startX == targetX && startY == targetY) {
            // 目标与源相同
            return false;
        }

        PointState startState = boardPosition[startX][startY];
        if (startState == PointState.NO_CHESS) {
            // 不能移动无子的棋点
            return false;
        }
        PointState targetState = boardPosition[targetX][targetY];
        if (startState.isSameCamp(targetState)) {
            // 吃自己的棋，非法
            return false;
        }

        AbstractChessMoveChecker moveChecker = ChessManager.getInstance().getChessMoveChecker(startState);
        return moveChecker.checkMove(boardPosition, startX, startY, targetX, targetY);
    }

    /**
     * 判断指定棋子是否能移动到相关联的位置，包括所有合理位置，以及自己能提供保护的位置
     * 注：此接口与{@link ChessMoveManager#isValidMove(PointState[][], int, int, int, int)}接口的区别只在于，本接口是可以把棋子走到己方棋子的位置上的，因为这样就能为己方棋子提供保护
     *
     * @param boardPosition 所面对的棋局局势
     * @param startX        要移动的起点X坐标
     * @param startY        要移动的起点Y坐标
     * @param targetX       要移动的目标点X坐标
     * @param targetY       要移动的目标点Y坐标
     * @return true-棋子可移动至相关联的位置；false-棋子不可移动至相关联的位置。
     */
    public boolean isRelateMove(PointState[][] boardPosition, int startX, int startY, int targetX, int targetY) {
        if (!ChessBoardUtils.inBoard(targetX, targetY)) {
            // 目标点超出棋盘范围
            return false;
        }

        if (startX == targetX && startY == targetY) {
            // 目标与源相同
            return false;
        }

        PointState startState = boardPosition[startX][startY];
        if (startState == PointState.NO_CHESS) {
            // 不能移动无子的棋点
            return false;
        }

        AbstractChessMoveChecker moveChecker = ChessManager.getInstance().getChessMoveChecker(startState);
        return moveChecker.checkMove(boardPosition, startX, startY, targetX, targetY);
    }

}
