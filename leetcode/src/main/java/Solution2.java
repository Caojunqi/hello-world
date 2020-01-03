/**
 * 2.两数相加
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * <p>
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * <p>
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 示例：
 * <p>
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution2 {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.appendValue(6);
        l1.appendValue(0);
        l1.appendValue(3);
        l1.appendValue(3);
        l1.appendValue(6);
        l1.appendValue(7);
        l1.appendValue(2);
        l1.appendValue(0);
        l1.appendValue(1);
        printListNode(l1);
        ListNode l2 = new ListNode(6);
        l2.appendValue(3);
        l2.appendValue(0);
        l2.appendValue(8);
        l2.appendValue(9);
        l2.appendValue(6);
        l2.appendValue(6);
        l2.appendValue(9);
        l2.appendValue(6);
        l2.appendValue(1);
        printListNode(l2);

        printListNode(addTwoNumbers(l1, l2));
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return addTwoNumbers(l1, l2, 0);
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2, int inheritValue) {
        if (l1 == null && l2 == null && inheritValue == 0) {
            return null;
        }

        int value1 = l1 == null ? 0 : l1.value;
        int value2 = l2 == null ? 0 : l2.value;

        int totalValue = value1 + value2 + inheritValue;
        int nodeValue = totalValue % 10;
        inheritValue = totalValue / 10;

        ListNode result = new ListNode(nodeValue);
        ListNode nextL1 = l1 == null ? null : l1.next;
        ListNode nextL2 = l2 == null ? null : l2.next;
        if (nextL1 != null || nextL2 != null || inheritValue != 0) {
            ListNode next = addTwoNumbers(nextL1, nextL2, inheritValue);
            if (next != null) {
                result.setNext(next);
            }
        }
        return result;
    }

    public static void printListNode(ListNode listNode) {
        StringBuilder sb = new StringBuilder();
        sb.append("====================\n");
        if (listNode != null) {
            sb.append(listNode.value + " ");
            ListNode nextNode = listNode.next;
            while (nextNode != null) {
                sb.append(nextNode.value + " ");
                nextNode = nextNode.next;
            }
        }
        System.out.println(sb.toString());
    }

    static class ListNode {
        int value;
        ListNode next;

        ListNode(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public ListNode getNext() {
            return next;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }

        public void appendValue(int value) {
            ListNode node = this;
            ListNode nextNode = this.next;
            while (nextNode != null) {
                node = nextNode;
                nextNode = nextNode.next;
            }
            node.next = new ListNode(value);
        }
    }
}
