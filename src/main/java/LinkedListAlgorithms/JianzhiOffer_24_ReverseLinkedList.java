package LinkedListAlgorithms;


import org.junit.Test;

public class JianzhiOffer_24_ReverseLinkedList {

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


    /************************链表T1：反转链表************************/
    /**
     * 题目描述   链表T1：反转链表
     * 输入一个链表，反转链表后，输出新链表的表头。
     *
     * 思路分析：需要三个引用，断开链表之前，保存下一节点；
     *          在头节点head之前引入null，作为head的pre节点。
     */
    public ListNode ReverseList(ListNode head) {
//        if(head == null||head.next == null) return head;//下面的已经处理这种特殊情况
        ListNode currentNode = head,preNode = null;
        while(currentNode != null){
            ListNode nextNode = currentNode.next;
            currentNode.next = preNode;
            preNode = currentNode;
            currentNode = nextNode;
        }
        return preNode;
    }

    @Test
    public void test(){
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
        printLinkedList(head1);
        ListNode newHead = ReverseList(head);
        printLinkedList(newHead);

        ListNode newHead2 = ReverseList(null);
        printLinkedList(newHead2);

        ListNode head3 = new ListNode(100);
        ListNode newHead3 = ReverseList(head3);
        printLinkedList(newHead3);

    }

}
