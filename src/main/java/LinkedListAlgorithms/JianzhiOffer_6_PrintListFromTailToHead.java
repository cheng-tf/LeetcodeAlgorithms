package LinkedListAlgorithms;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class JianzhiOffer_6_PrintListFromTailToHead {

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

    /**********************剑指Offer6:从尾到头打印链表**************************/

    /**
     * 题目描述
     * 输入一个链表，按链表值从尾到头的顺序返回一个ArrayList。
     *
     * 思路分析：
     *  方法1：先反转链表，然后打印；前提：允许反转链表。(不建议)
     *  方法2：利用栈的FILO特点；
     *  方法3：递归。注意递归深度。（不建议）
     */

    /**
     * 方法2：利用栈实现
     */
    public ArrayList<Integer> printListFromTailToHead2(ListNode listNode) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        Deque<Integer> stack = new ArrayDeque<Integer>();
        ListNode currentNode = listNode;
        while(currentNode != null){
            stack.push(currentNode.val);//压栈
            currentNode = currentNode.next;
        }
        while(!stack.isEmpty()) result.add(stack.pop());//依次从栈中取出
        return result;
    }


    /**
     * 方法3： 递归方法实现
     */
    public ArrayList<Integer> printListFromTailToHead3(ListNode listNode) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        recursiveMethod(listNode,result);
        return result;
    }
    /**
     * 递归辅助方法
     */
    public void recursiveMethod(ListNode listNode,ArrayList<Integer> result){
        if(listNode == null) return;
        recursiveMethod(listNode.next,result);
        result.add(listNode.val);
    }

    /**
     * 方法1： 反转链表
     */
    public ArrayList<Integer> printListFromTailToHead1(ListNode listNode) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        ListNode preNode = null,currentNode = listNode;
        while(currentNode != null){
            ListNode nextNode = currentNode.next;
            currentNode.next = preNode;
            preNode = currentNode;
            currentNode = nextNode;
        }
        while(preNode != null) {
            result.add(preNode.val);
            preNode = preNode.next;
        }
        return result;
    }

    //测试
    @Test
    public void test1() {
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        printLinkedList(head);

        ArrayList<Integer> result2 = printListFromTailToHead2(head);
        System.out.println("result2.toString() = " + result2.toString());
        ArrayList<Integer> result3 = printListFromTailToHead3(head);
        System.out.println("result3.toString() = " + result3.toString());


        //该方法会反转链表，因此最后测试
        ArrayList<Integer> result1 = printListFromTailToHead1(head);
        System.out.println("result1.toString() = " + result1.toString());
    }




}
