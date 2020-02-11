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
        tree.addNode(Node.valueOf(111, 11, 76));
        tree.addNode(Node.valueOf(112, 11, 25));
        tree.addNode(Node.valueOf(113, 11, 66));
        tree.addNode(Node.valueOf(114, 11, 5));
        tree.addNode(Node.valueOf(12, 1));
        tree.addNode(Node.valueOf(121, 12, 27));
        tree.addNode(Node.valueOf(122, 12, 39));
        tree.addNode(Node.valueOf(123, 12, 80));
        tree.addNode(Node.valueOf(124, 12, 59));
        tree.addNode(Node.valueOf(13, 1));
        tree.addNode(Node.valueOf(131, 13, 41));
        tree.addNode(Node.valueOf(132, 13, 70));
        tree.addNode(Node.valueOf(133, 13, 26));
        tree.addNode(Node.valueOf(134, 13, 84));
        tree.addNode(Node.valueOf(14, 1));
        tree.addNode(Node.valueOf(141, 14, 60));
        tree.addNode(Node.valueOf(142, 14, 92));
        tree.addNode(Node.valueOf(143, 14, 43));
        tree.addNode(Node.valueOf(144, 14, 27));
        tree.addNode(Node.valueOf(2, 0));
        tree.addNode(Node.valueOf(21, 2));
        tree.addNode(Node.valueOf(211, 21, 42));
        tree.addNode(Node.valueOf(212, 21, 57));
        tree.addNode(Node.valueOf(213, 21, 95));
        tree.addNode(Node.valueOf(214, 21, 3));
        tree.addNode(Node.valueOf(22, 2));
        tree.addNode(Node.valueOf(221, 22, 23));
        tree.addNode(Node.valueOf(222, 22, 30));
        tree.addNode(Node.valueOf(223, 22, 14));
        tree.addNode(Node.valueOf(224, 22, 24));
        tree.addNode(Node.valueOf(23, 2));
        tree.addNode(Node.valueOf(231, 23, 98));
        tree.addNode(Node.valueOf(232, 23, 66));
        tree.addNode(Node.valueOf(233, 23, 57));
        tree.addNode(Node.valueOf(234, 23, 39));
        tree.addNode(Node.valueOf(24, 2));
        tree.addNode(Node.valueOf(241, 24, 47));
        tree.addNode(Node.valueOf(242, 24, 31));
        tree.addNode(Node.valueOf(243, 24, 42));
        tree.addNode(Node.valueOf(244, 24, 9));
        tree.addNode(Node.valueOf(3, 0));
        tree.addNode(Node.valueOf(31, 3));
        tree.addNode(Node.valueOf(311, 31, 37));
        tree.addNode(Node.valueOf(312, 31, 31));
        tree.addNode(Node.valueOf(313, 31, 78));
        tree.addNode(Node.valueOf(314, 31, 84));
        tree.addNode(Node.valueOf(32, 3));
        tree.addNode(Node.valueOf(321, 32, 4));
        tree.addNode(Node.valueOf(322, 32, 9));
        tree.addNode(Node.valueOf(323, 32, 46));
        tree.addNode(Node.valueOf(324, 32, 64));
        tree.addNode(Node.valueOf(33, 3));
        tree.addNode(Node.valueOf(331, 33, 75));
        tree.addNode(Node.valueOf(332, 33, 88));
        tree.addNode(Node.valueOf(333, 33, 35));
        tree.addNode(Node.valueOf(334, 33, 33));
        tree.addNode(Node.valueOf(34, 3));
        tree.addNode(Node.valueOf(341, 34, 13));
        tree.addNode(Node.valueOf(342, 34, 5));
        tree.addNode(Node.valueOf(343, 34, 15));
        tree.addNode(Node.valueOf(344, 34, 89));
        tree.addNode(Node.valueOf(4, 0));
        tree.addNode(Node.valueOf(41, 4));
        tree.addNode(Node.valueOf(411, 41, 18));
        tree.addNode(Node.valueOf(412, 41, 78));
        tree.addNode(Node.valueOf(413, 41, 20));
        tree.addNode(Node.valueOf(414, 41, 74));
        tree.addNode(Node.valueOf(42, 4));
        tree.addNode(Node.valueOf(421, 42, 4));
        tree.addNode(Node.valueOf(422, 42, 48));
        tree.addNode(Node.valueOf(423, 42, 52));
        tree.addNode(Node.valueOf(424, 42, 7));
        tree.addNode(Node.valueOf(43, 4));
        tree.addNode(Node.valueOf(431, 43, 59));
        tree.addNode(Node.valueOf(432, 43, 98));
        tree.addNode(Node.valueOf(433, 43, 35));
        tree.addNode(Node.valueOf(434, 43, 46));
        tree.addNode(Node.valueOf(44, 4));
        tree.addNode(Node.valueOf(441, 44, 41));
        tree.addNode(Node.valueOf(442, 44, 69));
        tree.addNode(Node.valueOf(443, 44, 39));
        tree.addNode(Node.valueOf(444, 44, 21));
        return tree;
    }
}
