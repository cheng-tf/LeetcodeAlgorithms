package LinkedListAlgorithms;


import org.junit.Test;

public class JianzhiOffer_24_ReverseLinkedList {
    /**************两个题目：1.反转整个链表；2.反转链表中的部分链表；*******************/

    /************************链表题目1：反转链表_LeetCode_206. Reverse Linked List************************/
    /**
     * 链表T1：反转链表_LeetCode_206. Reverse Linked List
     * 难度：Easy
     * https://leetcode.com/problems/reverse-linked-list/description/
     * 题目描述   链表T1：反转链表
     * 输入一个链表，反转链表后，输出新链表的表头。
     * Reverse a singly linked list.
     * Example:
     * Input: 1->2->3->4->5->NULL
     * Output: 5->4->3->2->1->NULL
     * Follow up:
     * A linked list can be reversed either iteratively or recursively.
     * Could you implement both?
     *
     * 思路分析：两种实现：1、循环实现；2、递归实现。
     *          1. 循环实现。需要三个引用，前一个节点preNode，当前节点currentNode，
     *          后一个节点nextNode；断开链表之前，暂时保存下一节点nextNode；
     *         在头节点head之前引入null，作为head的pre节点。
     */

    /**
     * iteratively : while循环实现
     */
    public ListNode reverseList(ListNode head) {
//        if(head == null||head.next == null) return head;//下面的已经处理这种特殊情况
        ListNode currentNode = head, preNode = null;
        while (currentNode != null) {
            ListNode nextNode = currentNode.next;
            currentNode.next = preNode;
            preNode = currentNode;
            currentNode = nextNode;
        }
        return preNode;
    }

    /**
     * recursively : 递归实现
     */
    public ListNode reverseList_recursive(ListNode head) {
        if (head == null || head.next == null) return head;
        return reverseOne(null, head);
    }

    /**
     * 该方法递归实现逆序整个链表
     *
     * @param preTail       : 已经逆序好的尾节点
     * @param head：剩下链表的头节点
     * @return ： 返回逆序后链表的头节点
     */
    public ListNode reverseOne(ListNode preTail, ListNode head) {
        if (head == null) return preTail;
        ListNode next = head.next;
        head.next = preTail;
        preTail = head;
        return reverseOne(preTail, next);
    }


    /************************测试**************************/
    @Test
    public void test() {
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        ListNode head1 = head;
        //测试reverseList
        printLinkedList(head1);
        ListNode newHead = reverseList(head);
        printLinkedList(newHead);

        ListNode newHead2 = reverseList(null);
        printLinkedList(newHead2);

        ListNode head3 = new ListNode(100);
        ListNode newHead3 = reverseList(head3);
        printLinkedList(newHead3);
        //测试reverseList_recursive
//        printLinkedList(head1);
//        ListNode newHead = reverseList_recursive(head);
//        printLinkedList(newHead);
//
//        ListNode newHead2 = reverseList_recursive(null);
//        printLinkedList(newHead2);
//
//        ListNode head3 = new ListNode(100);
//        ListNode newHead3 = reverseList_recursive(head3);
//        printLinkedList(newHead3);

    }

    /*****************LeetCode_206. Reverse Linked List*********************/
    /**
     * Reverse a singly linked list.
     * Example:
     * Input: 1->2->3->4->5->NULL
     * Output: 5->4->3->2->1->NULL
     * Follow up:
     * A linked list can be reversed either iteratively or recursively.
     * Could you implement both?
     *
     * 思路分析：
     *          步骤分割：第一步：首先前进m-1步，到达第m节点；
     *                  第二步：反转m->n节点;
     *                  第三步：反转链表的头尾处理。
     * 两种实现。
     * 1. while循环实现。
     * 2. 递归实现。
     */

    /**
     * 循环实现
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || m < 1 || m > n) return null;
        ListNode head1 = head;
        ListNode preTail = null;

        //第一步：前进至第m-1节点，即行走m-2步
        for (int i = 1; i < m - 1 && head1 != null; i++) {
            head1 = head1.next;
        }
        if (m > 1) {//说明m不是1，m若是1，则preTail==null
            preTail = head1;
        }
        //这一步不一定要走，如果m=n的情况下，一步都需要走
        if (head1 != null&& m!=1 ) head1 = head1.next;//第m节点
        //第二步：反转m->n节点
        ListNode preNode = null;
        if(head1 == null) return head;//head1为1，不需要后翻转了
        ListNode reverseListPreHead = head1;
        ListNode nextNode;
        while (head1 != null && m++ <= n) {
            nextNode = head1.next;
            head1.next = preNode;
            preNode = head1;
            head1 = nextNode;
        }
        //第三步：反转链表的头尾处理
        reverseListPreHead.next = head1;
        if (preTail != null) {//若preTail为null，则忽略
            preTail.next = preNode;
            return head;
        }else{
            return preNode;//需要改变返回的头节点
        }
    }

    /************************测试**************************/
    @Test
    public void test2() {
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        ListNode head1 = head;
        //测试reverseList
        printLinkedList(head1);
        ListNode newHead = reverseBetween(head1, 2, 4);
        printLinkedList(newHead);

        ListNode head2 = new ListNode(3);
        ListNode node21 = new ListNode(5);
        head2.next = node21;
        //测试reverseList
        printLinkedList(head2);
        ListNode newHead2 = reverseBetween(head2, 2, 2);
        printLinkedList(newHead2);
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


}
