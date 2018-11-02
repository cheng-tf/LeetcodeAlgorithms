package LinkedListAlgorithms;

import org.junit.Test;

public class JianzhiOffer_22_FindKthToTailLinkedListNode {
    /*******************链表T4：链表倒数第k个节点*************************************/

    /**
     * 链表T4：链表倒数第k个节点*
     * 2018-11-02 第二次刷
     * 题目描述
     * 输入一个链表，输出该链表中倒数第k个结点。
     * <p>
     * 思路分析：链表无法逆序遍历。
     * 一般方法：需要遍历两次，不好。
     * 首先，统计链表的节点总数；
     * 然后，再次遍历只需要找到第n-k+1个节点即可。
     * 巧妙方法：遍历一次即可。
     * 利用两个指针，ahead和behind，先让ahead前进k步，
     * 然后ahead和behind同时前进，步伐都是1，当ahead到达尾节点，
     * behind正好指向倒数第k个节点。
     * <p>
     * 注意边界情况：1. k <= 0时，直接返回null;
     * 2. k 大于链表长度时,需要返回false。
     */
    public ListNode FindKthToTail(ListNode head, int k) {
        if (head == null || k <= 0) return null;//代码鲁棒性，特别注意k=0时返回null
        ListNode ahead = head, behind = head;
        //向前走k-1步;k=1时不走;k=0时，已经返回null;k=2时,走1步。
        //由于ahead和behind都是从head出发，因此ahead走k-1步;
        //对应下面的ahead和behind前进，必须判断ahead.next是否为null,这样才能让ahead超前behind指针k步。
        while (--k > 0) {
            ahead = ahead.next; //ahead与behind之间相差k-1步
            if (ahead == null) return null;//总节点数小于k，则返回null
        }
        while (ahead.next != null) {//ahead不到尾节点时，ahead和behind同时后移一位;
            //一定要判断ahead的next是否为null，不要判断ahead;因为ahead的next为null,此时behind还没有前进
            ahead = ahead.next;
            behind = behind.next;
        }
        return behind;
    }

    /**
     * 共用的链表结构ListNode
     */
    private class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 打印链表
     */
    public void printLinkedList(ListNode head) {
        if (head == null) {
            System.out.println("NULL");
            return;
        }
        ListNode headTemp = head;
        StringBuilder sb = new StringBuilder();
        while (headTemp != null) {
            sb.append(headTemp.val).append("-->");
            headTemp = headTemp.next;
        }
        System.out.println(sb.toString().substring(0, sb.length() - 3));
    }

    @Test
    public void test4() {
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        ListNode kthNode = FindKthToTail(head, 3);
        System.out.println("kthNode.val = " + kthNode.val);

        ListNode kthNode1 = FindKthToTail(head, 5);
        System.out.println("kthNode1.val = " + kthNode1.val);
    }
}
