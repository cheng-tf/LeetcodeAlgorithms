package LinkedListAlgorithms;

import org.junit.Test;

public class LeetCode_206_I_92_II_JianzhiOffer_24_Reverse_Linked_List {

    /**************两个题目：1.反转整个链表；2.反转链表中的部分链表；*******************/
    /**
     * LeetCode_206_Reverse_Linked_List
     * LeetCode_92_Reverse_Linked_List_II
     */

    /************************反转链表_LeetCode_206_Reverse_Linked_List************************/
    /**
     * 链表T1：反转链表_LeetCode_206_Reverse_Linked_List
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
     *         2. 递归实现。
     *            递归方法的实现。递归方法返回后n-1个节点翻转之后的头节点。
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
     * @param preTail : 已经逆序好的尾节点
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

    /*****************LeetCode_92_Reverse_Linked_List_II*********************/
    /**
     * LeetCode_92_Reverse_Linked_List_II_反转链表II-升级版
     * 难度：Medium
     * DateTime：2018-10-06
     * https://leetcode.com/problems/reverse-linked-list-ii/description/
     *
     * 题目介绍：
     * Reverse a linked list from position m to n. Do it in one-pass.
     * Note: 1 ≤ m ≤ n ≤ length of list.
     * Example:
     * Input: 1->2->3->4->5->NULL, m = 2, n = 4
     * Output: 1->4->3->2->5->NULL
     *
     * 思路分析：
     *          步骤分割：第一步：首先前进m-1步，到达第m节点；
     *                  第二步：反转m->n节点;
     *                  第三步：反转链表的头尾处理。
     * 两种实现。while循环实现。(递归不适合）
     */

    /**
     * 循环实现
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || m < 1 || m > n) return null;
        ListNode head1 = head;
        ListNode preTail = null;//需要逆置节点的前驱节点

        //第一步：前进至第m节点，即行走m-1步;注意考虑m为1的情况，此时preTail=null
        int i = 1;
        while(head1 != null && i++ < m){
            preTail = head1;
            head1 = head1.next;
        }
        //第二步：反转m->n节点
        if(head1 == null) return head;//head1为null，不需要后翻转了
        ListNode preNode = null;
        ListNode reverseListPreHead = head1;
        ListNode nextNode;
        while (head1 != null && m++ <= n) {
            nextNode = head1.next;
            head1.next = preNode;
            preNode = head1;
            head1 = nextNode;
        }
        //第三步：反转链表的头尾处理
        reverseListPreHead.next = head1;//处理尾巴
        if (preTail != null) {//处理头部
            preTail.next = preNode;
            return head;
        }else{
            return preNode;//若preTail为null,返回翻转链表段的最后一个节点
        }
    }

    /**
     * 循环实现:将上述整体中翻转部分节点部分拆分出来，原理一样的。
     */
    public ListNode reverseBetween_2(ListNode head, int m, int n) {
        if (head == null || m < 1 || m > n) return null;
        ListNode head1 = head;
        ListNode preTail = null;//需要逆置节点的前驱节点

        //第一步：前进至第m节点，即行走m-1步;注意考虑m为1的情况，此时preTail=null
        int i = 1;
        while(head1 != null && i++ < m){
            preTail = head1;
            head1 = head1.next;
        }
        //第二步：反转m->n节点
        if(head1 == null) return head;//head1为null，不需要后翻转了
        ListNode reverseListTail = reverseListBetween(head1,m,n);
        //第三步：反转链表的头处理
        if (preTail != null) {//处理头部
            preTail.next = reverseListTail;
            return head;
        }else{
            return reverseListTail;//若preTail为null,返回翻转链表段的最后一个节点
        }
    }

    /**
     * while循环实现的翻转部分
     * @param head1
     * @return 返回的是翻转链表的最后一个节点，也就是新的头节点
     */
    public ListNode reverseListBetween(ListNode head1,int m,int n) {
        ListNode reverseListPreHead = head1;
        ListNode preNode = null;
        ListNode nextNode;
        while (head1 != null && m++ <= n) {
            nextNode = head1.next;
            head1.next = preNode;
            preNode = head1;
            head1 = nextNode;
        }
        reverseListPreHead.next = head1;//处理尾巴
        return preNode;
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
//        printLinkedList(head2);
//        ListNode newHead2 = reverseBetween(head2, 2, 2);
//        printLinkedList(newHead2);

        printLinkedList(head2);
        ListNode newHead2 = reverseBetween_2(head2, 2, 2);
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
        String r = sb.toString();
        System.out.println(r.substring(0, sb.length() - 3));
    }

}

