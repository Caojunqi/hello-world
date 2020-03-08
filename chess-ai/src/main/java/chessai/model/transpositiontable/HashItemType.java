package chessai.model.transpositiontable;

/**
 * 置换表中数据项的类型
 */
public enum HashItemType {
    /**
     * 精确，可以明确判定这个节点的值
     */
    Exact {
        @Override
        public Integer checkValue(int alpha, int beta, int value) {
            return value;
        }
    },
    /**
     * 下边界，不能确定这个节点明确的值，只知道这个节点的准确值一定大于等于存储值
     * 所以这个存储值只有在可以剪枝的时候，才能派上用场
     * 如果存储值大于等于beta，就表明这个节点的准确值也一定大于等级beta，一定能触发剪枝
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
     * 上边界，同上，也不能确定这个节点明确的值，只知道这个节点的值一定小于等于存储值
     * 同样的，这个存储值也只有在可以触发剪枝的时候，才能派上用场
     * 如果存储值小于等于alpha，那就表明这个节点的准确值也一定小于等于alpha，一定能触发剪枝
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
