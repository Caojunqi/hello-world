public class Solution2 {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.appendValue(4);
        l1.appendValue(3);
        l1.appendValue(5);
        printListNode(l1);
        ListNode l2 = new ListNode(5);
        l2.appendValue(6);
        l2.appendValue(4);
        printListNode(l2);

        printListNode(addTwoNumbers(l1, l2));
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return addTwoNumbers(l1, l2, 0);
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2, int inheritValue) {
        int value1 = l1 == null ? 0 : l1.value;
        int value2 = l2 == null ? 0 : l2.value;

        int totalValue = value1 + value2 + inheritValue;
        if (totalValue == 0) {
            return null;
        }
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
