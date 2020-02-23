package chessai.tree;

import chessai.common.SystemOut;

/**
 * 算法测试类
 */
public class AlgorithmTest {
    private static final int ALPHA = -1999;
    private static final int BETA = 1999;
    private static Node BEST_BODE = null;

    public static void main(String[] args) {
        Tree tree = TestTree.valueOf();
        alphaBetaFailSoft(tree.getRoot(), true, ALPHA, BETA);
        if (BEST_BODE == null) {
            SystemOut.error("没有找到最佳路径！！");
        } else {
            SystemOut.error("最佳子节点是：" + BEST_BODE.getId());
        }
    }

    /**
     * Alpha-Beta剪枝精简版算法1，在发生剪枝的时候，返回beta值
     */
    private static int alphaBeta_1(Node node, boolean isMaxNode, int alpha, int beta) {
        if (node.getChildren().isEmpty()) {
            SystemOut.error("估值节点：" + node.getId());
            return isMaxNode ? node.getValue() : -node.getValue();
        }

        for (Node child : node.getChildren()) {
            int value = -alphaBeta_1(child, !isMaxNode, -beta, -alpha);
            if (value >= beta) {
                // 程序运行到这里，表明这个child子节点的后续兄弟节点都应该被减去，同时这个child的父节点node其实也已经是个无关紧要的节点了，
                // 所以这个返回beta还是返回alpha，其实意义不大，其目的都是让child的父节点对爷节点不起作用；
                // 返回beta是一种比较柔和的措施，是为了让爷节点的alpha beta值不发生变化，就不动
                // 返回alpha是一种比较强硬的措施，是为了试图改变爷节点的alpha beta值，让alpha和beta相等，这样alpha和beta就冲突了，一旦冲突，就会直接中断，其实也不会改变alpha beta的值。
                return beta;
            }
            if (value > alpha) {
                alpha = value;
                if (child.getParentId() == 0) {
                    BEST_BODE = child;
                }
            }
        }
        return alpha;
    }

    /**
     * Alpha-Beta剪枝精简版算法2，在发生剪枝的时候，返回beta值
     */
    private static int alphaBeta_2(Node node, boolean isMaxNode, int alpha, int beta) {
        if (node.getChildren().isEmpty()) {
            SystemOut.error("估值节点：" + node.getId());
            return isMaxNode ? node.getValue() : -node.getValue();
        }

        for (Node child : node.getChildren()) {
            int value = -alphaBeta_2(child, !isMaxNode, -beta, -alpha);
            if (value >= beta) {
                // 具体的说明参看alphaBeta_1里面的解释
                break;
                // 或 return alpha;
            }
            if (value > alpha) {
                alpha = value;
                if (child.getParentId() == 0) {
                    BEST_BODE = child;
                }
            }
        }
        return alpha;
    }

    /**
     * Fail Soft Alpha Beta剪枝，可以返回一些信息
     */
    private static int alphaBetaFailSoft(Node node, boolean isMaxNode, int alpha, int beta) {
        int current = ALPHA;
        if (node.getChildren().isEmpty()) {
            SystemOut.error("估值节点：" + node.getId());
            return isMaxNode ? node.getValue() : -node.getValue();
        }

        for (Node child : node.getChildren()) {
            int value = -alphaBetaFailSoft(child, !isMaxNode, -beta, -alpha);
            if (value > current) {
                current = value;
                if (value >= beta) {
                    break;
                }
                if (value > alpha) {
                    alpha = value;
                    if (child.getParentId() == 0) {
                        BEST_BODE = child;
                    }
                }
            }
        }
        return current;
    }
}
