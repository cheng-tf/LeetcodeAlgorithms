package LinkedListAlgorithms;

import org.junit.Test;

public class Leetcode_21_23_JianzhiOffer_25_MergeTwoOrKSortedLinkedLists {
    /***********************Leetcode_21_23_JianzhiOffer_25_MergeTwoOrKSortedLinkedLists******************************/

    /**********************链表：合并两个或多个有序的链表**********************************/

    /************题目1：合并两个有序的链表<Leetcode_21与JianzhiOffer_25>*******************************/
    /**
     *  难度：Easy
     *  DateTime：2018-10-05
     *  https://leetcode.com/problems/merge-two-sorted-lists/description/
     *  题目描述：
     *  输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
     *  Merge two sorted linked lists and return it as a new list.
     *  The new list should be made by splicing together the nodes of the first two lists.
     * Example:
     * Input: 1->2->4, 1->3->4
     * Output: 1->1->2->3->4->4
     *
     *  思路分析：
     *  方法1：递归思想。不断地获取合并链表的下一节点，直到原两个链表访问到null。
     *         递归终止条件：链表其中之一为null;
     *  方法2：循环思想。
     *         当两个链表都非null时，while循环不断判断；
     *         当其中一个链表为null，通过if语句判断出那个为null，然后将非null链表接到合并链表上即可。
     */

    /**
     * 方法1：递归方法
     * 2018-10
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        ListNode mergeHead = null;
        if (list1.val <= list2.val) {
            mergeHead = list1;
            mergeHead.next = mergeTwoLists(list1.next, list2);
        } else {
            mergeHead = list2;
            mergeHead.next = mergeTwoLists(list1, list2.next);
        }
        return mergeHead;
    }

    /**
     * 方法1：递归方法<更简洁的代码>
     * 2018-10
     */
    public ListNode mergeTwoLists_1(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode mergeHead = null;
        if (l1.val <= l2.val) {
            l1.next = mergeTwoLists_1(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists_1(l1, l2.next);
            return l2;
        }
    }

    /**
     * 方法2：非递归方法while实现
     * 2018-10-05 北邮教三
     */
    public ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
        ListNode mergedListHead = new ListNode(-1);//初始化一个头节点
        ListNode head1 = list1, head2 = list2;
        ListNode temp = mergedListHead;//行走的node
        //两个链表都非null时
        while (head1 != null && head2 != null) {
            if (head1.val <= head2.val) {
                temp.next = head1;
                head1 = head1.next;
            } else {
                temp.next = head2;
                head2 = head2.next;
            }
            temp = temp.next;
        }
        //一个或两个都为null时
        if (null == head1)
            temp.next = head2;
        if (null == head2)
            temp.next = head1;
        return mergedListHead.next;
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

    /********************测试************************/
    @Test
    public void test() {
        ListNode head1 = new ListNode(1);
        ListNode node3 = new ListNode(3);
        ListNode node5 = new ListNode(5);
        ListNode node7 = new ListNode(7);
        head1.next = node3;
        node3.next = node5;
        node5.next = node7;
        ListNode head2 = new ListNode(0);
        ListNode node2 = new ListNode(2);
        ListNode node4 = new ListNode(4);
        ListNode node6 = new ListNode(6);
        head2.next = node2;
        node2.next = node4;
        node4.next = node6;

//        //测试递归方法
//        printLinkedList(head1);
//        printLinkedList(head2);
//        ListNode mergeHead1 = Merge(head1, head2);
//        printLinkedList(mergeHead1);
        //测试while循环方法<两种方法需要分开测>
        printLinkedList(head1);
        printLinkedList(head2);
        ListNode mergeHead2 = mergeTwoLists2(head1, head2);
        printLinkedList(mergeHead2);
    }

    /**********************Leetcode_23_Merge k Sorted Lists******************************/

    /**
     * 难度:Hard
     * DateTime:2018-10-05 10:29 北邮教三
     * 链接：
     * 题目描述：https://leetcode.com/problems/merge-k-sorted-lists/description/
     * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
     * Example:
     * Input:
     * [
     *   1->4->5,
     *   1->3->4,
     *   2->6
     * ]
     * Output: 1->1->2->3->4->4->5->6
     *
     */
    public ListNode mergeKLists(ListNode[] lists) {

        return null;
    }
}
