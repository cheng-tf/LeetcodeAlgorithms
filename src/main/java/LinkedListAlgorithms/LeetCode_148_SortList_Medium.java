package LinkedListAlgorithms;

import org.junit.Test;

public class LeetCode_148_SortList_Medium {
    /***********************LeetCode_148_SortList_Medium*********************************/

    /**
     * LeetCode_148_SortList_Medium
     * https://leetcode.com/problems/sort-list/description/
     * 难度：Medium
     * DateTime:2018-10-14  北邮教三
     * <p>
     * 题目介绍：
     * Sort a linked list in O(n log n) time using constant space complexity.
     * Example 1:
     * Input: 4->2->1->3
     * Output: 1->2->3->4
     * <p>
     * Example 2:
     * Input: -1->5->3->4->0
     * Output: -1->0->3->4->5
     * <p>
     * 思路分析：归并排序。
     */

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode subH1 = head;
        ListNode subH2 = head.next;
        subH1.next = null;//断开
        ListNode sortH1 = sortList(subH1);
        ListNode sortH2 = sortList(subH2);
        return merge(sortH1, sortH2);//将两个有序子链表合成一个;
    }

    /**
     *
     * 递归方法合并两个有序链表
     */
    public ListNode merge(ListNode head1, ListNode head2) {
        if (head1 == null) return head2;
        if (head2 == null) return head1;
        if (head1.val <= head2.val) {
            head1.next = merge(head1.next, head2);
            return head1;
        } else {
            head2.next = merge(head1, head2.next);
            return head2;
        }
    }

    /**********************************测试****************************************/
    @Test
    public void test(){
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(-2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(99);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(-88);
        ListNode node7 = new ListNode(5);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;

        printLinkedList(head);
        ListNode sortHead = sortList(head);
        printLinkedList(sortHead);
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
