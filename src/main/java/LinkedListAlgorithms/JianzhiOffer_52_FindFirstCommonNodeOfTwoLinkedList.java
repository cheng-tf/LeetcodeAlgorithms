package LinkedListAlgorithms;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

public class JianzhiOffer_52_FindFirstCommonNodeOfTwoLinkedList {


    /*****************剑指Offer52:两个链表的第一个公共节点********************************/

    /***
     * 题目描述:
     * 输入两个链表，找出它们的第一个公共结点。
     *
     * 思路分析：
     * 方法1：蛮力法，遍历第一个链表的每一个节点的时候，遍历第二个节点，时间复杂度为O(mn);显然该方法不可取。
     * 继续分析：
     * 由于两个链表有公共节点，那么应该成"Y"的结构，即从某个节点开始，以后都一样。那么如果从后往前查找
     * 就只需要O(n)的时间复杂度。因此，有了方法2：
     * 方法2：利用两个栈分别存储两个链表，从栈顶依次判断共同的节点，返回最后一个共同节点。
     * 方法3：先计算两个栈的长度，然后先让长的栈指针走几步，然后两指针同时出发判断即可。
     */

    /**
     * 方法2：利用两个栈实现
     */
    public ListNode FindFirstCommonNode2(ListNode pHead1, ListNode pHead2) {
        if(pHead1==null||pHead2==null) return null;
        ListNode head1 = pHead1,head2 = pHead2;//不改变原来的引用
        Deque<ListNode> stack1 = new ArrayDeque<ListNode>();
        Deque<ListNode> stack2 = new ArrayDeque<ListNode>();
        while(head1 != null){
            stack1.push(head1);
            head1 = head1.next;
        }
        while(head2 != null){
            stack2.push(head2);
            head2 = head2.next;
        }
        ListNode preCommonNode = null;
        while(!stack1.isEmpty() && !stack2.isEmpty()){
            ListNode node1 = stack1.pop();
            ListNode node2 = stack2.pop();
            if(node1 == node2) preCommonNode = node1;//倒着看应该获取最后一个公共节点
        }
        return preCommonNode;
    }



    /**
     * 方法3：先求链表的长度，然后利用两指针遍历链表
     */
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if(pHead1==null||pHead2==null) return null;
        ListNode head1 = pHead1,head2 = pHead2;//不改变原来的引用
        //求链表长度
        int len1 = getLengthOfLinkedList(head1);
        int len2 = getLengthOfLinkedList(head2);
        int diffLen = len1 - len2;//链表长度之差
        if(len1 < len2){//默认是链表1长于链表2
            ListNode temp = head1;
            head1 = head2;
            head2 = temp;
            diffLen = -diffLen;//取相反数
        }
        while(diffLen-- != 0)  head1 = head1.next;//先走几步
        while(head1 != head2){//同时前进
            head1 = head1.next;
            head2 = head2.next;
        }
        return head1;
    }

    /**
     *  获取链表的长度
     */
    public int getLengthOfLinkedList(ListNode head){
        ListNode head1 = head;//不改变head引用
        int len = 0;
        while(head1 != null){
            len++;
            head1 = head1.next;
        }
        return len;
    }


    //测试
    @Test
    public void test(){
        ListNode head1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        head1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        printLinkedList(head1);

        ListNode head2 = new ListNode(6);
        ListNode node22 = new ListNode(7);
        ListNode node23 = new ListNode(12);
        ListNode node26 = new ListNode(0);
        ListNode node27 = new ListNode(8);

        head2.next = node22;
        node22.next = node23;
        node23.next = node26;
        node26.next = node27;
        node27.next = node4;
        printLinkedList(head2);

        ListNode commonNode = FindFirstCommonNode(head1,head2);
        System.out.println("commonNode = " + commonNode.val);

        printLinkedList(head1);
        printLinkedList(head2);
        ListNode commonNode2 = FindFirstCommonNode2(head1,head2);
        System.out.println("commonNode2 = " + commonNode2.val);
    }


/*****************************链表的基本数据结构**********************************************/
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
}
