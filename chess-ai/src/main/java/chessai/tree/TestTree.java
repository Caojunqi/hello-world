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
        tree.addNode(Node.valueOf(0, -1, 3));
        tree.addNode(Node.valueOf(1, 0, 2));
        tree.addNode(Node.valueOf(11, 1, 1));
        tree.addNode(Node.valueOf(111, 11, 0, 5));
        tree.addNode(Node.valueOf(112, 11, 0, 2));
        tree.addNode(Node.valueOf(12, 1, 1));
        tree.addNode(Node.valueOf(121, 12, 0, 3));
        tree.addNode(Node.valueOf(122, 12, 0, 1));
        tree.addNode(Node.valueOf(2, 0, 2));
        tree.addNode(Node.valueOf(21, 2, 1));
        tree.addNode(Node.valueOf(211, 21, 0, 7));
        tree.addNode(Node.valueOf(212, 21, 0, 10));
        tree.addNode(Node.valueOf(22, 2, 1));
        tree.addNode(Node.valueOf(221, 22, 0, 1));
        tree.addNode(Node.valueOf(222, 22, 0, 4));
        tree.addNode(Node.valueOf(3, 0, 2));
        tree.addNode(Node.valueOf(31, 3, 1));
        tree.addNode(Node.valueOf(311, 31, 0, 6));
        tree.addNode(Node.valueOf(312, 31, 0, 8));
        tree.addNode(Node.valueOf(32, 3, 1));
        tree.addNode(Node.valueOf(321, 32, 0, 2));
        tree.addNode(Node.valueOf(322, 32, 0, 10));
        return tree;
    }
}
