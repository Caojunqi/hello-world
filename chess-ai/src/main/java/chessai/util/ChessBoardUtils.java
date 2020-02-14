package chessai.util;

import chessai.common.SystemOut;
import chessai.model.CampType;
import chessai.model.PointState;
import chessai.model.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * 棋盘工具类
 */
public final class ChessBoardUtils {

    private ChessBoardUtils() {
        // 此工具类不允许被实例化
    }

    /**
     * 棋局采取红方先手，黑方为AI，如果棋面呈胶着状态，棋面估值为0，黑方占优势，估值为正数，红方占优势，估值为负数。
     */
    public static int MAX_EVALUATE_VALUE = 20000;
    /**
     * 棋局默认红方是玩家，黑方是AI
     */
    public static CampType AI_CAMP = CampType.BLACK;
    /**
     * 每次预测步数
     */
    public static int SEARCH_DEPTH = 1;

    /**
     * 红仕可移动位置集合
     */
    public static List<Position> RED_GUARD_MOVE_POINTS = new ArrayList<>();
    /**
     * 黑士可移动位置集合
     */
    public static List<Position> BLACK_GUARD_MOVE_POINTS = new ArrayList<>();
    /**
     * 红相可移动位置集合
     */
    public static List<Position> RED_MINISTER_MOVE_POINTS = new ArrayList<>();
    /**
     * 黑象可移动位置集合
     */
    public static List<Position> BLACK_MINISTER_MOVE_POINTS = new ArrayList<>();

    static {
        initRedGuardMovePoints();
        initBlackGuardMovePoints();
        initRedMinisterMovePoints();
        initBlackMinisterMovePoints();
    }

    /**
     * 判断指定坐标点是否在棋盘中
     *
     * @param x 坐标点X坐标
     * @param y 坐标点Y坐标
     * @return true-坐标点在棋盘中；坐标点不在棋盘中。
     */
    public static boolean inBoard(int x, int y) {
        if (x < 0 || x > 9) {
            return false;
        }
        if (y < 0 || y > 8) {
            return false;
        }
        return true;
    }

    /**
     * 判断指定坐标点是否处于黑方九宫格中
     *
     * @param x 坐标点X坐标
     * @param y 坐标点Y坐标
     * @return true-坐标点处于黑方九宫格中；false-坐标点不处于黑方九宫格中。
     */
    public static boolean inBlackPalace(int x, int y) {
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
    public static boolean inRedPalace(int x, int y) {
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
    public static boolean inBlackSide(int x, int y) {
        return x < 5;
    }

    /**
     * 判断指定坐标点是否处于红方领土
     *
     * @param x 坐标点X坐标
     * @param y 坐标点Y坐标
     * @return true-坐标点处于红方领土中；false-坐标点处于黑方领土。
     */
    public static boolean inRedSide(int x, int y) {
        return !inBlackSide(x, y);
    }

    /**
     * 判断棋盘上两点之间是否有其他棋子
     *
     * @param boardPosition 所面对的棋局局势
     * @param x1            1点的X坐标
     * @param y1            1点的Y坐标
     * @param x2            2点的X坐标
     * @param y2            2点的Y坐标
     * @return true-两点之间有其他棋子；false-两点之间没有其他棋子。
     */
    public static boolean hasOtherChess(PointState[][] boardPosition, int x1, int y1, int x2, int y2) {
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
                    if (boardPosition[x1][i] != PointState.NO_CHESS) {
                        return true;
                    }
                }
            } else {
                for (int i = y2 + 1; i < y1; i++) {
                    if (boardPosition[x1][i] != PointState.NO_CHESS) {
                        return true;
                    }
                }
            }
        }
        // 两点垂直
        else {
            if (x1 < x2) {
                for (int i = x1 + 1; i < x2; i++) {
                    if (boardPosition[i][y1] != PointState.NO_CHESS) {
                        return true;
                    }
                }
            } else {
                for (int i = x2 + 1; i < x1; i++) {
                    if (boardPosition[i][y1] != PointState.NO_CHESS) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * 判断两点之间是否只有一个棋子
     *
     * @param boardPosition 所面对的棋局局势
     * @param x1            点1的X坐标
     * @param y1            点1的Y坐标
     * @param x2            点2的X坐标
     * @param y2            点2的Y坐标
     * @return true-移动的起点和终点之间有一个棋子；false-移动的起点和终点之间的棋子数量。
     */
    public static boolean hasOneChess(PointState[][] boardPosition, int x1, int y1, int x2, int y2) {
        int count = 0;
        if (x1 != x2 && y1 != y2) {
            //两点不在同一水平或同一垂直线上，状态不正常
            SystemOut.error("不能使用hasOneChess接口来判断两个不在同一水平或同一垂直线的点之间是否只有一个其余棋子，点1：[" + x1 + "," + y1 + "]，点2：[" + x2 + "," + y2 + "]");
            return false;
        }

        if (x1 == x2 && y1 == y2) {
            // 两点为同一点
            return false;
        }

        // 两点水平
        if (x1 == x2) {
            if (y1 < y2) {
                for (int i = y1 + 1; i < y2; i++) {
                    if (boardPosition[x1][i] != PointState.NO_CHESS) {
                        count++;
                    }
                }
                return count == 1;
            } else {
                for (int i = y2 + 1; i < y1; i++) {
                    if (boardPosition[x1][i] != PointState.NO_CHESS) {
                        count++;
                    }
                }
                return count == 1;
            }
        }
        // 两点垂直
        else {
            if (x1 < x2) {
                for (int i = x1 + 1; i < x2; i++) {
                    if (boardPosition[i][y1] != PointState.NO_CHESS) {
                        count++;
                    }
                }
                return count == 1;
            } else {
                for (int i = x2 + 1; i < x1; i++) {
                    if (boardPosition[i][y1] != PointState.NO_CHESS) {
                        count++;
                    }
                }
                return count == 1;
            }
        }
    }

    /**
     * 指定棋局中红方是否依然存活
     *
     * @param boardPosition 所面对的棋局局势
     * @return true-红方存活；false-红方失败
     */
    public static boolean isRedLive(PointState[][] boardPosition) {
        for (int x = 7; x < 10; x++) {
            for (int y = 3; y < 6; y++) {
                if (boardPosition[x][y] == PointState.RED_KING) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 指定棋局中黑方是否依然存活
     *
     * @param boardPosition 所面对的棋局局势
     * @return true-黑方存活；false-黑方失败
     */
    public static boolean isBlackLive(PointState[][] boardPosition) {
        for (int x = 0; x < 3; x++) {
            for (int y = 3; y < 6; y++) {
                if (boardPosition[x][y] == PointState.BLACK_KING) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 初始化红仕可移动位置集合
     */
    private static void initRedGuardMovePoints() {
        RED_GUARD_MOVE_POINTS.add(Position.valueOf(9, 3));
        RED_GUARD_MOVE_POINTS.add(Position.valueOf(9, 5));
        RED_GUARD_MOVE_POINTS.add(Position.valueOf(8, 4));
        RED_GUARD_MOVE_POINTS.add(Position.valueOf(7, 3));
        RED_GUARD_MOVE_POINTS.add(Position.valueOf(7, 5));
    }

    /**
     * 初始化黑士可移动位置集合
     */
    private static void initBlackGuardMovePoints() {
        BLACK_GUARD_MOVE_POINTS.add(Position.valueOf(0, 3));
        BLACK_GUARD_MOVE_POINTS.add(Position.valueOf(0, 5));
        BLACK_GUARD_MOVE_POINTS.add(Position.valueOf(1, 4));
        BLACK_GUARD_MOVE_POINTS.add(Position.valueOf(2, 3));
        BLACK_GUARD_MOVE_POINTS.add(Position.valueOf(2, 5));
    }

    /**
     * 初始化红相可移动位置集合
     */
    private static void initRedMinisterMovePoints() {
        RED_MINISTER_MOVE_POINTS.add(Position.valueOf(9, 2));
        RED_MINISTER_MOVE_POINTS.add(Position.valueOf(7, 0));
        RED_MINISTER_MOVE_POINTS.add(Position.valueOf(5, 2));
        RED_MINISTER_MOVE_POINTS.add(Position.valueOf(7, 4));
        RED_MINISTER_MOVE_POINTS.add(Position.valueOf(9, 6));
        RED_MINISTER_MOVE_POINTS.add(Position.valueOf(7, 8));
        RED_MINISTER_MOVE_POINTS.add(Position.valueOf(5, 6));
    }

    /**
     * 初始化黑象可移动位置集合
     */
    private static void initBlackMinisterMovePoints() {
        BLACK_MINISTER_MOVE_POINTS.add(Position.valueOf(0, 2));
        BLACK_MINISTER_MOVE_POINTS.add(Position.valueOf(2, 0));
        BLACK_MINISTER_MOVE_POINTS.add(Position.valueOf(4, 2));
        BLACK_MINISTER_MOVE_POINTS.add(Position.valueOf(2, 4));
        BLACK_MINISTER_MOVE_POINTS.add(Position.valueOf(0, 6));
        BLACK_MINISTER_MOVE_POINTS.add(Position.valueOf(2, 8));
    }

}
