package chessai.model;

/**
 * 棋盘上每个点的状态
 */
public enum PointState {
    /**
     * 没有棋子
     */
    NO_CHESS(0, 88),
    /**
     * 黑帅
     */
    BLACK_KING(1, 1),
    /**
     * 黑车
     */
    BLACK_CAR(2, 2),
    /**
     * 黑马
     */
    BLACK_HORSE(3, 2),
    /**
     * 黑炮
     */
    BLACK_CANNON(4, 2),
    /**
     * 黑士
     */
    BLACK_BISHOP(5, 2),
    /**
     * 黑象
     */
    BLACK_ELEPHANT(6, 2),
    /**
     * 黑兵
     */
    BLACK_PAWN(7, 5),
    /**
     * 红将
     */
    RED_KING(8, 1),
    /**
     * 红车
     */
    RED_CAR(9, 2),
    /**
     * 红马
     */
    RED_HORSE(10, 2),
    /**
     * 红炮
     */
    RED_CANNON(11, 2),
    /**
     * 红仕
     */
    RED_BISHOP(12, 2),
    /**
     * 红相
     */
    RED_ELEPHANT(13, 2),
    /**
     * 红卒
     */
    RED_PAWN(14, 5),
    ;

    /**
     * 编号
     */
    private int code;
    /**
     * 棋盘中处于此状态下的棋点的最大数目
     */
    private int maxNum;

    PointState(int code, int maxNum) {
        this.code = code;
        this.maxNum = maxNum;
    }

    public int getCode() {
        return code;
    }

    public int getMaxNum() {
        return maxNum;
    }

    /**
     * 判断棋盘点是否被黑方控制
     *
     * @return true-被黑方控制；false-不被黑方控制。
     */
    public boolean isBlack() {
        return this.code >= BLACK_KING.code && this.code <= BLACK_PAWN.code;
    }

    /**
     * 判断棋盘点是否被红方控制
     *
     * @return true-被红方控制；false-不被红方控制。
     */
    public boolean isRed() {
        return this.code >= RED_KING.code && this.code <= RED_PAWN.code;
    }

    /**
     * 判断当前棋点和目标棋点是否属于同一阵营
     *
     * @param targetState 目标棋点
     * @return true-属于同一阵营；false-不属于同一阵营
     */
    public boolean isSameSide(PointState targetState) {
        if (isBlack() && targetState.isBlack()) {
            return true;
        }
        if (isRed() && targetState.isRed()) {
            return true;
        }
        return false;
    }

    /**
     * 获取指定编号对应的棋盘点状态
     *
     * @param code 编号
     * @return 棋盘点状态
     */
    public static PointState getPointState(int code) {
        for (PointState state : values()) {
            state.code = code;
            return state;
        }
        throw new IllegalArgumentException("不存在指定编号对应的棋盘点状态！编号：" + code);
    }
}
