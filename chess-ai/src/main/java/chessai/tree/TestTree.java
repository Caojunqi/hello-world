package chessai.tree;

/**
 * 用于测试的树形结构
 */
public class TestTree {

    private TestTree() {
        // 私有化构造器
    }

    /**
     * 三层测试树
     */
    public static Tree valueOfThree() {
        Tree tree = new Tree();
        tree.addNode(Node.valueOf(0, -1, 3));
        tree.addNode(Node.valueOf(1, 0, 2));
        tree.addNode(Node.valueOf(11, 1, 1));
        tree.addNode(Node.valueOf(111, 11, 0, 2));
        tree.addNode(Node.valueOf(112, 11, 0, 1));
        tree.addNode(Node.valueOf(12, 1, 1));
        tree.addNode(Node.valueOf(121, 12, 0, 6));
        tree.addNode(Node.valueOf(122, 12, 0, 7));
        tree.addNode(Node.valueOf(2, 0, 2));
        tree.addNode(Node.valueOf(21, 2, 1));
        tree.addNode(Node.valueOf(211, 21, 0, 3));
        tree.addNode(Node.valueOf(212, 21, 0, 0));
        tree.addNode(Node.valueOf(22, 2, 1));
        tree.addNode(Node.valueOf(221, 22, 0, 5));
        tree.addNode(Node.valueOf(222, 22, 0, 10));
        tree.addNode(Node.valueOf(3, 0, 2));
        tree.addNode(Node.valueOf(31, 3, 1));
        tree.addNode(Node.valueOf(311, 31, 0, 2));
        tree.addNode(Node.valueOf(312, 31, 0, 2));
        tree.addNode(Node.valueOf(32, 3, 1));
        tree.addNode(Node.valueOf(321, 32, 0, 12));
        tree.addNode(Node.valueOf(322, 32, 0, 3));
        return tree;
    }

    /**
     * 四层测试树
     */
    public static Tree valueOfFour() {
        Tree tree = new Tree();
        tree.addNode(Node.valueOf(0, -1, 4));
        tree.addNode(Node.valueOf(1, 0, 3));
        tree.addNode(Node.valueOf(11, 1, 2));
        tree.addNode(Node.valueOf(111, 11, 1));
        tree.addNode(Node.valueOf(1111, 111, 0, 2));
        tree.addNode(Node.valueOf(1112, 111, 0, 5));
        tree.addNode(Node.valueOf(112, 11, 1));
        tree.addNode(Node.valueOf(1121, 112, 0, 8));
        tree.addNode(Node.valueOf(1122, 112, 0, 3));
        tree.addNode(Node.valueOf(12, 1, 2));
        tree.addNode(Node.valueOf(121, 12, 1));
        tree.addNode(Node.valueOf(1211, 121, 0, 7));
        tree.addNode(Node.valueOf(1212, 121, 0, 1));
        tree.addNode(Node.valueOf(122, 12, 1));
        tree.addNode(Node.valueOf(1221, 122, 0, 6));
        tree.addNode(Node.valueOf(1222, 122, 0, 2));
        tree.addNode(Node.valueOf(2, 0, 3));
        tree.addNode(Node.valueOf(21, 2, 2));
        tree.addNode(Node.valueOf(211, 21, 1));
        tree.addNode(Node.valueOf(2111, 211, 0, 3));
        tree.addNode(Node.valueOf(2112, 211, 0, 7));
        tree.addNode(Node.valueOf(212, 21, 1));
        tree.addNode(Node.valueOf(2121, 212, 0, 6));
        tree.addNode(Node.valueOf(2122, 212, 0, 4));
        tree.addNode(Node.valueOf(22, 2, 2));
        tree.addNode(Node.valueOf(221, 22, 1));
        tree.addNode(Node.valueOf(2211, 221, 0, 1));
        tree.addNode(Node.valueOf(2212, 221, 0, 8));
        tree.addNode(Node.valueOf(222, 22, 1));
        tree.addNode(Node.valueOf(2221, 222, 0, 9));
        tree.addNode(Node.valueOf(2222, 222, 0, 4));
        tree.addNode(Node.valueOf(3, 0, 3));
        tree.addNode(Node.valueOf(31, 3, 2));
        tree.addNode(Node.valueOf(311, 31, 1));
        tree.addNode(Node.valueOf(3111, 311, 0, 3));
        tree.addNode(Node.valueOf(3112, 311, 0, 8));
        tree.addNode(Node.valueOf(312, 31, 1));
        tree.addNode(Node.valueOf(3121, 312, 0, 7));
        tree.addNode(Node.valueOf(3122, 312, 0, 4));
        tree.addNode(Node.valueOf(32, 3, 2));
        tree.addNode(Node.valueOf(321, 32, 1));
        tree.addNode(Node.valueOf(3211, 321, 0, 2));
        tree.addNode(Node.valueOf(3212, 321, 0, 5));
        tree.addNode(Node.valueOf(322, 32, 1));
        tree.addNode(Node.valueOf(3221, 322, 0, 1));
        tree.addNode(Node.valueOf(3222, 322, 0, 3));
        return tree;
    }
}
