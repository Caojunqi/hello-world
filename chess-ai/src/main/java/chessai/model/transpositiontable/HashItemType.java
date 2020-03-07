package chessai.model.transpositiontable;

/**
 * 置换表中数据项的类型
 */
public enum HashItemType {
    /**
     * 精确
     */
    Exact {
        @Override
        public Integer checkValue(int alpha, int beta, int value) {
            return value;
        }
    },
    /**
     * 下边界
     */
    LowerBound {
        @Override
        public Integer checkValue(int alpha, int beta, int value) {
            if (value >= beta) {
                return value;
            }
            return null;
        }
    },
    /**
     * 上边界
     */
    UpperBound {
        @Override
        public Integer checkValue(int alpha, int beta, int value) {
            if (value <= alpha) {
                return value;
            }
            return null;
        }
    },
    ;

    /**
     * 判断置换表中存储的数据是否满足alpha-beta搜索的需求
     *
     * @param alpha alpha-beta搜索的上边界
     * @param beta  alpha-beta搜索的下边界
     * @param value 置换表中存储的数据值
     * @return 若满足要求，返回存储的数据值；若不满足要求，返回null
     */
    public abstract Integer checkValue(int alpha, int beta, int value);
}
