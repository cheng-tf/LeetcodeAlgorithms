package LinkedListAlgorithms;


import org.junit.Test;

public class Leetcode_86_PartitionList_Medium {
    /******************Leetcode_86_PartitionList_Medium*******************/
    /**************DateTime: 2018-10-01**********************/
    /**
     * 题目介绍：Leetcode_86_PartitionList_Medium  链表划分
     * 难度：Medium
     * Given a linked list and a value x, partition it such that all nodes
     * less than x come before nodes greater than or equal to x.
     * You should preserve the original relative order of the nodes
     * in each of the two partitions.
     *
     * 思路分析： 将原链表根据x大小分割成两个子链表smaller和bigger；
     * 然后将smaller的尾节点指向bigger的头节点，
     * 将bigger的尾节点指向null即可。
     * 这里需要注意：smaller和bigger子链表的头节点需要手动初始化节点。
     */
    /******************************************************/
//  Definition for singly-linked list.
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    /**
     * partition方法
     */
    public ListNode partition(ListNode head, int x) {
        if (head == null) return null;
        ListNode temp = head;//保护head引用
        ListNode smallerHead = new ListNode(0);
        ListNode smallerTemp = smallerHead;
        ListNode biggerHead = new ListNode(0);
        ListNode biggerTemp = biggerHead;
        while (temp != null) {//遍历原链表
            if (temp.val < x) {
                smallerTemp.next = temp;
                smallerTemp = smallerTemp.next;
            } else {
                biggerTemp.next = temp;
                biggerTemp = biggerTemp.next;
            }
            temp = temp.next;//前进一步
        }
        smallerTemp.next = biggerHead.next;//连接两个子链表
        biggerTemp.next = null;//将尾节点置空
        return smallerHead.next;
    }
    /***********************END*******************************/
    /**
     * 打印链表，用于验证结果
     */
    public String printLinkedList(ListNode head) {
        StringBuilder sb = new StringBuilder();
        ListNode temp = head;
        while (temp != null) {
            sb.append(temp.val + "-->");
            temp = temp.next;
        }
        if (sb.length() > 3) {
            sb.delete(sb.length() - 3, sb.length());
        }
        return sb.toString();
    }

    //测试
    @Test
    public void test() {
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(2);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(2);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        String result = printLinkedList(head);
        ListNode newHead = partition(head, 3);
        String result2 = printLinkedList(newHead);
        System.out.println(result);
        System.out.println(result2);
    }
}
