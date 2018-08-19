package LinkedListAlgorithms;

import org.junit.Test;

public class JianzhiOffer_25_MergeTwoSortedLinkedList {
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
    public void printLinkedList(ListNode head){
        if(head == null){
            System.out.println("NULL");
            return;
        }
        ListNode headTemp = head;
        StringBuilder sb = new StringBuilder();
        while(headTemp != null){
            sb.append(headTemp.val).append("-->");
            headTemp = headTemp.next;
        }
        System.out.println(sb.toString().substring(0,sb.length()-3));
    }

    /**********************链表：合并两个排序的链表**********************************/
    /**
     *  题目描述：
     *  输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
     *
     *  思路分析：递归思想。
     *  不断地获取合并链表的下一节点，直到原两个链表访问到null。
     */
    public ListNode Merge(ListNode list1,ListNode list2) {
        if(list1 == null) return list2;
        if(list2 == null) return list1;
        ListNode mergeHead = null;
        if(list1.val <= list2.val){
            mergeHead = list1;
            mergeHead.next = Merge(list1.next,list2);
        }else{
            mergeHead = list2;
            mergeHead.next = Merge(list1,list2.next);
        }
        return mergeHead;
    }

    //测试
    @Test
    public void test(){
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

        printLinkedList(head1);
        printLinkedList(head2);
        ListNode mergeHead = Merge(head1,head2);
        printLinkedList(mergeHead);
    }


}
