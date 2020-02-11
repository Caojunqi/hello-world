package chessai.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * 树
 */
public class Tree {
    /**
     * 所有节点集合 <节点唯一ID, 节点>
     */
    private Map<Integer, Node> allNodes = new HashMap<>();
    /**
     * 根节点
     */
    private Node root;

    /**
     * 往树结构中添加节点
     *
     * @param node 待添加节点
     */
    public void addNode(Node node) {
        if (node.getParentId() < 0) {
            this.root = node;
        }
        allNodes.put(node.getId(), node);

        if (node.getParentId() != -1) {
            Node parentNode = allNodes.get(node.getParentId());
            if (parentNode == null) {
                throw new IllegalStateException("待添加节点的父节点不存在！！");
            } else {
                parentNode.addChild(node);
            }
        }
    }

    /**
     * 打印输出整个树形结构
     */
    public void printTree() {
        root.printTree(0);
    }

    public Node getRoot() {
        return root;
    }
}
