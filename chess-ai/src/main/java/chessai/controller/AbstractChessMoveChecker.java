package chessai.controller;

import chessai.model.ChessBoard;
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
     * @param chessBoard 棋盘
     * @param targetX    移动目标点X坐标
     * @param targetY    移动目标点Y坐标
     * @return true-可移动至目标点；false-不可移动至目标点。
     */
    public abstract boolean checkMove(ChessBoard chessBoard, int targetX, int targetY);
}
