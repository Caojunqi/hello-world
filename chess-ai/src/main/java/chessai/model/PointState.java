package chessai.model;

/**
 * 棋盘上每个点的状态
 */
public enum PointState {
    /**
     * 没有棋子
     */
    NO_CHESS(0, 88, CampType.NO),
    /**
     * 黑帅
     */
    BLACK_KING(1, 1, CampType.BLACK),
    /**
     * 黑车
     */
    BLACK_ROOK(2, 2, CampType.BLACK),
    /**
     * 黑马
     */
    BLACK_KNIGHT(3, 2, CampType.BLACK),
    /**
     * 黑炮
     */
    BLACK_CANNON(4, 2, CampType.BLACK),
    /**
     * 黑士
     */
    BLACK_GUARD(5, 2, CampType.BLACK),
    /**
     * 黑象
     */
    BLACK_MINISTER(6, 2, CampType.BLACK),
    /**
     * 黑兵
     */
    BLACK_PAWN(7, 5, CampType.BLACK),
    /**
     * 红将
     */
    RED_KING(8, 1, CampType.RED),
    /**
     * 红车
     */
    RED_ROOK(9, 2, CampType.RED),
    /**
     * 红马
     */
    RED_KNIGHT(10, 2, CampType.RED),
    /**
     * 红炮
     */
    RED_CANNON(11, 2, CampType.RED),
    /**
     * 红仕
     */
    RED_GUARD(12, 2, CampType.RED),
    /**
     * 红相
     */
    RED_MINISTER(13, 2, CampType.RED),
    /**
     * 红卒
     */
    RED_PAWN(14, 5, CampType.RED),
    ;

    /**
     * 编号
     */
    private int code;
    /**
     * 棋盘中处于此状态下的棋点的最大数目
     */
    private int maxNum;
    /**
     * 该棋点所属阵营
     */
    private CampType campType;

    PointState(int code, int maxNum, CampType campType) {
        this.code = code;
        this.maxNum = maxNum;
        this.campType = campType;
    }

    public int getCode() {
        return code;
    }

    public int getMaxNum() {
        return maxNum;
    }

    public CampType getCampType() {
        return campType;
    }

    /**
     * 判断棋盘点是否被黑方控制
     *
     * @return true-被黑方控制；false-不被黑方控制。
     */
    public boolean isBlack() {
        return this.campType == CampType.BLACK;
    }

    /**
     * 判断棋盘点是否被红方控制
     *
     * @return true-被红方控制；false-不被红方控制。
     */
    public boolean isRed() {
        return this.campType == CampType.RED;
    }

    /**
     * 判断当前棋点和目标棋点是否属于同一阵营
     *
     * @param targetState 目标棋点
     * @return true-属于同一阵营；false-不属于同一阵营
     */
    public boolean isSameCamp(PointState targetState) {
        return this.campType == targetState.campType;
    }

    /**
     * 判断当前棋点是否属于指定阵营
     *
     * @param campType 阵营类型
     * @return true-当前棋点属于指定阵营；false-当前棋点不属于指定阵营。
     */
    public boolean isSameCamp(CampType campType) {
        return this.campType == campType;
    }

    /**
     * 获取指定编号对应的棋盘点状态
     *
     * @param code 编号
     * @return 棋盘点状态
     */
    public static PointState getPointState(int code) {
        for (PointState state : values()) {
            if (state.code == code) {
                return state;
            }
        }
        throw new IllegalArgumentException("不存在指定编号对应的棋盘点状态！编号：" + code);
    }
}
