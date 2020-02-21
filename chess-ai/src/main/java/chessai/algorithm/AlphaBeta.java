package chessai.algorithm;

/**
 * Alpha-Beta剪枝算法伪代码
 */
public class AlphaBeta {

    /**
     * alpha-beta算法伪代码
     *
     * @param nPly  搜索层数
     * @param alpha alpha值
     * @param beta  beta值
     * @return alpha-beta算法估值结果
     */
//    public int alphaBeta(int nPly, int alpha, int beta) {
//        if (game over){
//            // 比赛结束，返回估值
//            return evaluation;
//        }
//
//        if (nPly == 0) {
//            // 叶子节点，返回估值
//            return evaluation;
//        }
//
//        if (isMinNode) {
//            // 当前节点是取极小值的节点
//            for (each possible move m){
//                // 遍历每一步可能的走法m
//                // 执行走法m
//                make move m;
//                // 递归搜索子节点
//                int eval = alphaBeta(nPly - 1, alpha, beta);
//                // 撤销走法m
//                unmake move m;
//                // 极小值节点负责生产beta
//                if (eval < beta) {
//                    beta = eval;
//                    if (alpha >= beta) {
//                        // 意外情况，alpha大于beta了，进行alpha剪枝，抛弃后继节点
//                        return alpha;
//                    }
//                }
//            }
//            // 遍历完所有子节点后，返回最小值
//            return beta;
//        } else {
//            // 当前节点是取极大值的节点
//            for (each possible move m){
//                // 遍历每一步可能的走法m
//                // 执行走法m
//                make move m;
//                // 递归搜索子节点
//                int eval = alphaBeta(nPly - 1, alpha, beta);
//                // 撤销走法m
//                unmake move m;
//                // 极大值节点负责生产alpha
//                if (eval > alpha) {
//                    alpha = eval;
//                    if (alpha >= beta) {
//                        // 意外情况，alpha大于beta了，进行beta剪枝，抛弃后继节点
//                        return beta;
//                    }
//                }
//            }
//            // 遍历完所有子节点后，返回最大值
//            return alpha;
//        }
//    }

}
