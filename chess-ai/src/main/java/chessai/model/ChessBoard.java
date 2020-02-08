package chessai.model;

import chessai.common.SystemOut;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * 棋盘
 */
@Component
public class ChessBoard {

    private static ChessBoard self;

    /**
     * 棋盘长
     */
    private final int CHESS_BOARD_LENGTH = 9;
    /**
     * 棋盘高
     */
    private final int CHESS_BOARD_HEIGHT = 10;
    /**
     * 初始棋盘
     */
    private final int[][] INIT_CHESS_BOARD = {
            {2, 3, 6, 5, 1, 5, 6, 3, 2},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 4, 0, 0, 0, 0, 0, 4, 0},
            {7, 0, 7, 0, 7, 0, 7, 0, 7},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {14, 0, 14, 0, 14, 0, 14, 0, 14},
            {0, 11, 0, 0, 0, 0, 0, 11, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {9, 10, 13, 12, 8, 12, 13, 10, 9}
    };

    /**
     * 当前的棋盘状态
     */
    private PointState[][] curChessBoard;

    /**
     * 初始化棋盘
     */
    @PostConstruct
    public void init() {
        self = this;

        PointState[][] curChessBoard = new PointState[CHESS_BOARD_LENGTH][CHESS_BOARD_HEIGHT];
        for (int i = 0; i < CHESS_BOARD_HEIGHT; i++) {
            for (int j = 0; j < CHESS_BOARD_LENGTH; j++) {
                curChessBoard[i][j] = PointState.getPointState(INIT_CHESS_BOARD[i][j]);
            }
        }
        this.curChessBoard = curChessBoard;
    }

    public static ChessBoard getInstance() {
        return self;
    }

    /**
     * 获取指定坐标点的状态
     *
     * @param x X坐标
     * @param y Y坐标
     * @return 棋盘点状态
     */
    public PointState getPointState(int x, int y) {
        return curChessBoard[x][y];
    }

    /**
     * 获取棋盘上所有指定状态的位置集合
     *
     * @param pointState 所要找的棋点状态
     * @return 棋点位置集合
     */
    public List<Position> getPositions(PointState pointState) {
        List<Position> result = new ArrayList<>();
        for (int i = 0; i < CHESS_BOARD_HEIGHT; i++) {
            for (int j = 0; j < CHESS_BOARD_LENGTH; j++) {
                if (curChessBoard[i][j] == pointState) {
                    result.add(Position.valueOf(i, j));
                    if (result.size() >= pointState.getMaxNum()) {
                        // 已经找到所有满足条件的棋点位置了，不用继续遍历下去了
                        return result;
                    }
                }
            }
        }
        return result;
    }

    /**
     * 判断指定坐标点是否处于黑方九宫格中
     *
     * @param x 坐标点X坐标
     * @param y 坐标点Y坐标
     * @return true-坐标点处于黑方九宫格中；false-坐标点不处于黑方九宫格中。
     */
    public boolean inBlackPalace(int x, int y) {
        if (y > 5) {
            return false;
        }

        if (y < 3) {
            return false;
        }

        if (x > 2) {
            return false;
        }

        return true;
    }

    /**
     * 判断指定坐标点是否处于红方九宫格中
     *
     * @param x 坐标点X坐标
     * @param y 坐标点Y坐标
     * @return true-坐标点处于红方九宫格中；false-坐标点不处于红方九宫格中。
     */
    public boolean inRedPalace(int x, int y) {
        if (x < 7) {
            return false;
        }

        if (y < 3) {
            return false;
        }

        if (y > 5) {
            return false;
        }

        return true;
    }

    /**
     * 判断指定坐标点是否处于黑方领土
     *
     * @param x 坐标点X坐标
     * @param y 坐标点Y坐标
     * @return true-坐标点处于黑方领土中；false-坐标点处于红方领土。
     */
    public boolean inBlackSide(int x, int y) {
        return x < 5;
    }

    /**
     * 判断指定坐标点是否处于红方领土
     *
     * @param x 坐标点X坐标
     * @param y 坐标点Y坐标
     * @return true-坐标点处于红方领土中；false-坐标点处于黑方领土。
     */
    public boolean inRedSide(int x, int y) {
        return !inBlackSide(x, y);
    }

    /**
     * 判断棋盘上两点之间是否有其他棋子
     *
     * @param x1 1点的X坐标
     * @param y1 1点的Y坐标
     * @param x2 2点的X坐标
     * @param y2 2点的Y坐标
     * @return true-两点之间有其他棋子；false-两点之间没有其他棋子。
     */
    public boolean hasOtherChess(int x1, int y1, int x2, int y2) {
        if (x1 != x2 && y1 != y2) {
            //两点不在同一水平或同一垂直线上，状态不正常，返回有棋子
            SystemOut.error("不能使用hasOtherChess接口来判断两个不在同一水平或同一垂直线的点之间的状态，点1：[" + x1 + "," + y1 + "]，点2：[" + x2 + "," + y2 + "]");
            return true;
        }

        if (x1 == x2 && y1 == y2) {
            // 两点为同一点
            return false;
        }

        // 两点水平
        if (x1 == x2) {
            if (y1 < y2) {
                for (int i = y1 + 1; i < y2; i++) {
                    if (getPointState(x1, i) != PointState.NO_CHESS) {
                        return false;
                    }
                }
            } else {
                for (int i = y2 + 1; i < y1; i++) {
                    if (getPointState(x1, i) != PointState.NO_CHESS) {
                        return false;
                    }
                }
            }
        }
        // 两点垂直
        else {
            if (x1 < x2) {
                for (int i = x1 + 1; i < x2; i++) {
                    if (getPointState(i, y1) != PointState.NO_CHESS) {
                        return false;
                    }
                }
            } else {
                for (int i = x2 + 1; i < x1; i++) {
                    if (getPointState(i, y1) != PointState.NO_CHESS) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * 获取棋盘长度
     *
     * @return 棋盘长
     */
    public int getLength() {
        return CHESS_BOARD_LENGTH;
    }

    /**
     * 获取棋盘宽度
     *
     * @return 棋盘宽
     */
    public int getHeight() {
        return CHESS_BOARD_HEIGHT;
    }

}
