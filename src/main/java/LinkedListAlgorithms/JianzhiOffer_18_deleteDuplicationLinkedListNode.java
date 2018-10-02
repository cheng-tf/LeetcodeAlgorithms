package LinkedListAlgorithms;

import org.junit.Test;

public class JianzhiOffer_18_deleteDuplicationLinkedListNode {
    /**************************链表T2：删除链表节点******************************************/

    /**
     * 题目描述：在O(1)时间内删除链表节点。
     *
     * 思路分析:若遍历寻找待删除的节点前一节点，则时间复杂度为O(n)，不符合要求。
     *      O(1)的实现方式：复制后一节点到该节点,然后只需要删除后一节点即可。
     *      特殊情况：待删除节点是尾节点，①若只有1个节点的情况；②多个节点，该节点是尾节点。
     *  //注意：这里假定链表中肯定存在待删除的节点。
     */
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
        String result = sb.toString().substring(0,sb.length()-3);
        System.out.println(result);
    }

    public void deleteListNode(ListNode head, ListNode toBeDeleted){
        if(head == null || toBeDeleted == null) return;
        if(toBeDeleted.next != null){//非尾节点
            ListNode next = toBeDeleted.next;
            toBeDeleted.val = next.val;
            toBeDeleted.next = next.next;
        }else{//尾节点
            if(head == toBeDeleted){//只有一个节点
                //解释为head=null不起作用？ 因为Java方法只有值传递，对于引用传递都是地址，
                //形参与实参都指向同一个对象地址，形参试图改变指向地址，不改变实参；
                //但是形参和实参对该指向的对象的操作是等价的，比如形参引用可以改变对象的属性。
                head = null;
                toBeDeleted = null;
            }else{//多个节点，只能顺序查找待删除节点的前一节点了
                ListNode head1 = head;
                while(head1.next != toBeDeleted){
                    head1 = head1.next;
                }
                head1.next = null;
                toBeDeleted = null;
            }
        }
    }

    //测试
    @Test
    public void test2(){
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
        deleteListNode(head,node2);
        deleteListNode(head,head);
        printLinkedList(head);

        deleteListNode(head,null);
        deleteListNode(null,node3);
    }


    /*******************************链表T3：删除有序链表中重复的节点***********************/
    /**
     *  题目描述：删除有序链表中重复的节点。(注意：已经排序了)
     *  在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，
     *  重复的结点不保留，返回链表头指针。
     *  例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
     *
     *  思路分析：是排序链表，因此重复元素肯定是相邻的；
     *      和反转链表类似，需要三个引用：preNode、currentNode、nextNode。
     *      当前节点和下一节点的值作判断，若相等，则继续判断，直到找到不相等的节点或null，
     *      让preNode指向nextNode(这里preNode可能为null，所以需要判断是否null，null即表示开头即重复元素)，
     *      若不相等，继续后移即可。
     */
    public ListNode deleteDuplication(ListNode head){
        if(head == null||head.next == null) return head;
        ListNode preNode = null;
        ListNode currentNode = head;
        while(currentNode != null){
            ListNode nextNode = currentNode.next;
            if(nextNode == null) return head;
            if(nextNode.val == currentNode.val){//出现重复节点
                nextNode = nextNode.next;
                while(nextNode != null && nextNode.val == currentNode.val){
                    nextNode = nextNode.next;
                }
                if(preNode == null) {//头节点就是重复节点
                    head = nextNode;
                }else{
                    preNode.next = nextNode;
                }
                //preNode 不变，currentNode指向nextNode
                currentNode = nextNode;//若忽略了则出现无限循环
            }else{//不重复
                preNode = currentNode;
                currentNode = nextNode;
            }
        }
        return head;
    }

    //测试
    @Test
    public void test(){
        ListNode head = new ListNode(2);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        ListNode head1 = head;
        printLinkedList(head1);
        ListNode newHead = deleteDuplication(head);
        printLinkedList(newHead);
    }

}
