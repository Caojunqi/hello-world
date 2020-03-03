package chessai.tree;

/**
 * 用于测试的树形结构
 */
public class TestTree {

    private TestTree() {
        // 私有化构造器
    }

    public static Tree valueOf() {
        Tree tree = new Tree();
        tree.addNode(Node.valueOf(0, -1));
        tree.addNode(Node.valueOf(1, 0));
        tree.addNode(Node.valueOf(11, 1));
        tree.addNode(Node.valueOf(111, 11, 5));
        tree.addNode(Node.valueOf(112, 11, 2));
        tree.addNode(Node.valueOf(12, 1));
        tree.addNode(Node.valueOf(121, 12, 3));
        tree.addNode(Node.valueOf(122, 12, 1));
        tree.addNode(Node.valueOf(2, 0));
        tree.addNode(Node.valueOf(21, 2));
        tree.addNode(Node.valueOf(211, 21, 7));
        tree.addNode(Node.valueOf(212, 21, 10));
        tree.addNode(Node.valueOf(22, 2));
        tree.addNode(Node.valueOf(221, 22, 1));
        tree.addNode(Node.valueOf(222, 22, 4));
        tree.addNode(Node.valueOf(3, 0));
        tree.addNode(Node.valueOf(31, 3));
        tree.addNode(Node.valueOf(311, 31, 6));
        tree.addNode(Node.valueOf(312, 31, 8));
        tree.addNode(Node.valueOf(32, 3));
        tree.addNode(Node.valueOf(321, 32, 2));
        tree.addNode(Node.valueOf(322, 32, 10));
        return tree;
    }
}
