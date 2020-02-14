package chessai.model;

import chessai.util.ChessBoardUtils;

/**
 * 阵营类型
 */
public enum CampType {
    /**
     * 无阵营
     */
    NO {
        @Override
        public boolean isWin(PointState[][] boardPosition) {
            // 中立方永远不可能获胜
            return false;
        }

        @Override
        public boolean isLose(PointState[][] boardPosition) {
            // 中立方永远不可能失败
            return false;
        }

        @Override
        public PointState getEnemyKing() {
            throw new IllegalStateException("不应该对无子棋点判断敌方老将！！");
        }

        @Override
        public PointState getSelfKing() {
            throw new IllegalStateException("不应该对无子棋点判断己方老将！！");
        }
    },
    /**
     * 红方
     */
    RED {
        @Override
        public boolean isWin(PointState[][] boardPosition) {
            // 黑帅阵亡，红方获胜
            return !ChessBoardUtils.isBlackLive(boardPosition);
        }

        @Override
        public boolean isLose(PointState[][] boardPosition) {
            // 红将阵亡，红方失败
            return !ChessBoardUtils.isRedLive(boardPosition);
        }

        @Override
        public PointState getEnemyKing() {
            return PointState.BLACK_KING;
        }

        @Override
        public PointState getSelfKing() {
            return PointState.RED_KING;
        }
    },
    /**
     * 黑方
     */
    BLACK {
        @Override
        public boolean isWin(PointState[][] boardPosition) {
            // 红将阵亡，黑方获胜
            return !ChessBoardUtils.isRedLive(boardPosition);
        }

        @Override
        public boolean isLose(PointState[][] boardPosition) {
            // 黑帅阵亡，黑方失败
            return !ChessBoardUtils.isBlackLive(boardPosition);
        }

        @Override
        public PointState getEnemyKing() {
            return PointState.RED_KING;
        }

        @Override
        public PointState getSelfKing() {
            return PointState.BLACK_KING;
        }
    },
    ;

    /**
     * 是否获胜
     *
     * @param boardPosition 所面对的棋局局势
     * @return true-获胜；false-失败。
     */
    public abstract boolean isWin(PointState[][] boardPosition);

    /**
     * 是否失败
     *
     * @param boardPosition 所面对的棋局局势
     * @return true-失败；false-获胜。
     */
    public abstract boolean isLose(PointState[][] boardPosition);

    /**
     * 获取敌方老将的状态类型
     *
     * @return 敌方老将的状态类型
     */
    public abstract PointState getEnemyKing();

    /**
     * 获取己方老将的状态类型
     *
     * @return 己方老将的状态类型
     */
    public abstract PointState getSelfKing();
}
