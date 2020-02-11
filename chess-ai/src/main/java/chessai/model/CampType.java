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
}
