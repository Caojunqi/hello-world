package chessai.tree;

import chessai.common.SystemOut;
import chessai.model.transpositiontable.HashItem;
import chessai.model.transpositiontable.HashItemType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 置换表测试
 */
public class TranspositionTableTest {
    private static final int ALPHA = -1999;
    private static final int BETA = 1999;
    private static Node BEST_BODE = null;
    /**
     * 测试用置换表--存放最大值节点
     */
    private static Map<Integer, HashItem> transpositionTableMax = new HashMap<>();
    /**
     * 测试用置换表--存放最小值节点
     */
    private static Map<Integer, HashItem> transpositionTableMin = new HashMap<>();

    public static void main(String[] args) {
        Tree tree = TestTree.valueOfThree();
        principalVariationShort(tree.getRoot(), true, ALPHA, BETA);
        if (BEST_BODE == null) {
            SystemOut.error("没有找到最佳路径！！");
        } else {
            SystemOut.error("最佳子节点是：" + BEST_BODE.getId());
        }
    }

    /**
     * “PVS+置换表”搜索算法（精简版）
     */
    private static int principalVariationShort(Node node, boolean isMaxNode, int alpha, int beta) {
        int current = ALPHA;
        Integer score = lookUpHashTable(alpha, beta, node.getDepth(), isMaxNode, node.getId());
        if (score != null) {
            // 置换表命中
            return score;
        }
        if (node.getChildren().isEmpty()) {
            System.out.println("估值节点：" + node.getId());
            return isMaxNode ? node.getValue() : -node.getValue();
        }

        List<Node> allChildren = node.getChildren();

        for (int i = 0; i < allChildren.size(); i++) {
            int value = 0;
            Node child = allChildren.get(i);
            if (i == 0) {
                // 使用全窗口搜索第一个节点
                value = -principalVariationShort(child, !isMaxNode, -beta, -alpha);
            } else {
                // 用极小窗口搜索其他节点
                value = -principalVariationShort(child, !isMaxNode, -alpha - 1, -alpha);
                if (value > alpha && value < beta) {
                    // 用全窗口重新搜索
                    // 一旦跳入全窗口搜索，就表示前面搜索的结果不准确，删掉
                    removeHashTable(!isMaxNode, child.getId());
                    value = -principalVariationShort(child, !isMaxNode, -beta, -value);
                }
            }

            if (value > current) {
                current = value;
                if (value >= beta) {
                    enterHashTable(HashItemType.LowerBound, node.getDepth(), value, isMaxNode, node.getId());
                    return value;
                }
                if (value > alpha) {
                    alpha = value;

                    if (child.getParentId() == 0) {
                        BEST_BODE = child;
                    }
                }
            }
        }
        enterHashTable(HashItemType.Exact, node.getDepth(), current, isMaxNode, node.getId());
        return current;
    }

    /**
     * Fail Soft Alpha Beta剪枝 + 置换表
     */
    private static int alphaBetaFailSoft(Node node, boolean isMaxNode, int alpha, int beta) {
        int current = ALPHA;
        Integer score = lookUpHashTable(alpha, beta, node.getDepth(), isMaxNode, node.getId());
        if (score != null) {
            // 置换表命中
            return score;
        }
        if (node.getChildren().isEmpty()) {
            System.out.println("估值节点：" + node.getId());
            return isMaxNode ? node.getValue() : -node.getValue();
        }

        for (Node child : node.getChildren()) {
            int value = -alphaBetaFailSoft(child, !isMaxNode, -beta, -alpha);
            if (value > current) {
                current = value;
                if (value >= beta) {
                    enterHashTable(HashItemType.LowerBound, node.getDepth(), value, isMaxNode, node.getId());
                    return value;
                }
                if (value > alpha) {
                    alpha = value;
                    if (child.getParentId() == 0) {
                        BEST_BODE = child;
                    }
                }
            }
        }
        enterHashTable(HashItemType.Exact, node.getDepth(), current, isMaxNode, node.getId());
        return current;
    }

    private static Integer lookUpHashTable(int alpha, int beta, int depth, boolean isMaxNode, int hashKey) {
        Map<Integer, HashItem> itemMap = isMaxNode ? transpositionTableMax : transpositionTableMin;
        HashItem item = itemMap.get(hashKey);
        if (item == null) {
            return null;
        }
        if (item.getDepth() < depth) {
            return null;
        }
        if (item.getCheckSum() != hashKey) {
            return null;
        }
        return item.getItemType().checkValue(alpha, beta, item.getValue());
    }

    public static void enterHashTable(HashItemType itemType, int depth, int value, boolean isMaxNode, int hashKey) {
        HashItem item = HashItem.valueOf(itemType, hashKey, depth, value);
        Map<Integer, HashItem> itemMap = isMaxNode ? transpositionTableMax : transpositionTableMin;
        itemMap.put(hashKey, item);
    }

    public static void removeHashTable(boolean isMaxNode, int hashKey) {
        Map<Integer, HashItem> itemMap = isMaxNode ? transpositionTableMax : transpositionTableMin;
        HashItem item = itemMap.remove(hashKey);
    }
}
