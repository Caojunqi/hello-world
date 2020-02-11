package chessai;

import chessai.tree.Node;
import chessai.tree.TestTree;
import chessai.tree.Tree;

/**
 * 启动类
 */
public class Start {

    /**
     * 程序调用计数器
     */
    private static int COUNT = 0;
    /**
     * 最大值常量
     */
    private static int MAX_VALUE = 99999;

    public static void main(String[] args) {
        Tree testTree = TestTree.valueOf();
        COUNT = 0;
        // System.out.println(negamax(testTree.getRoot(), 3, 3));
        System.out.println(alphaBeta(testTree.getRoot(), 3, 3, -MAX_VALUE, MAX_VALUE));
        System.out.println(COUNT);
    }

    /**
     * 利用负值最大函数对指定节点进行估值
     *
     * @param node     待估值节点
     * @param curDepth 当前剩余搜索层数
     * @param maxDepth 本次搜索计划的最大搜索层数
     * @return 最优结果
     */
    public static int negamax(Node node, int curDepth, int maxDepth) {
        COUNT++;
        int best = Integer.MIN_VALUE;
        if (curDepth <= 0) {
            return evaluate(node, maxDepth);
        }
        for (Node child : node.getChildren()) {
            int val = -negamax(child, curDepth - 1, maxDepth);
            if (val > best) {
                best = val;
            }
        }
        node.setValue(best);
        return best;
    }

    /**
     * 估值函数
     *
     * @param node     待估值节点
     * @param maxDepth 本次搜索计划的最大搜索层数
     * @return 估值结果
     */
    public static int evaluate(Node node, int maxDepth) {
        int ratio = maxDepth % 2 == 0 ? 1 : -1;
        return ratio * node.getValue();
    }

    public static int alphaBeta(Node node, int curDepth, int maxDepth, int alpha, int beta) {
        COUNT++;
        if (curDepth <= 0) {
            return evaluate(node, maxDepth);
        }
        for (Node child : node.getChildren()) {
            int val = -alphaBeta(child, curDepth - 1, maxDepth, -beta, -alpha);
            if (val >= beta) {
                return beta;
            }
            if (val > alpha) {
                alpha = val;
            }
        }
        node.setValue(alpha);
        return alpha;
    }
}
