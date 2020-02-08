package chessai.util;

import chessai.common.SystemOut;
import chessai.model.PointState;

/**
 * 棋盘工具类
 */
public final class ChessBoardUtils {

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

}
