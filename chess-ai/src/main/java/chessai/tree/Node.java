package chessai.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 节点
 */
public class Node {
    /**
     * 唯一ID标识
     */
    private int id;
    /**
     * 父节点唯一ID标识，若为-1，标识当前节点为根节点
     */
    private int parentId;
    /**
     * 节点深度
     */
    private int depth;
    /**
     * 当前节点的值，-9999表示当前节点为初始化
     */
    private int value;
    /**
     * 当前节点子节点
     */
    private List<Node> children = new ArrayList<>();

    private Node() {
        // 私有化构造器
    }

    public static Node valueOf(int id, int parentId, int depth) {
        return valueOf(id, parentId, depth, -9999);
    }

    public static Node valueOf(int id, int parentId, int depth, int value) {
        Node node = new Node();
        node.id = id;
        node.parentId = parentId;
        node.depth = depth;
        node.value = value;
        return node;
    }

    @Override
    public String toString() {
        return "[" + id + "," + parentId + "," + value + "] ";
    }

    /**
     * 打印输出子树
     *
     * @param floor 第几层，控制制表符的数量
     */
    public void printTree(int floor) {
        for (int i = 0; i < floor; i++) {
            System.out.print("\t");
        }
        System.out.println(String.format("[id:%d,value:%d]", id, value));
        if (!children.isEmpty()) {
            floor++;
            for (Node child : children) {
                child.printTree(floor);
            }
        }
    }

    public int getId() {
        return id;
    }

    public int getParentId() {
        return parentId;
    }

    public int getDepth() {
        return depth;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void addChild(Node node) {
        if (node.parentId != id) {
            return;
        }
        children.add(node);
    }

    public List<Node> getChildren() {
        return children;
    }
}
