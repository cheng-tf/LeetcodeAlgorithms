package LinkedListAlgorithms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;

public class Leetcode_21_23_JianzhiOffer_25_MergeTwoOrKSortedLinkedLists {
    /*****************Leetcode_21_23_JianzhiOffer_25_MergeTwoOrKSortedLinkedLists******************/

    /***************链表：合并两个或多个有序的链表***********************/

    /************题目1：合并两个有序的链表<Leetcode_21与JianzhiOffer_25>*****************/
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
     *  复杂度分析：假设两个链表的长度分别为N和M，则在最坏的情况下，需要比较N+M-1次,即复杂度可以认为是O（N+M）。
     *            如1->3; 2->4; 第一次比较1和2，得到1；第二次比较2和3，得到2；第三次比较3和4，得到3；最后得到4.
     */

    /**
     * 方法1：递归方法
     * 2018-08
     */
    public ListNode mergeTwoLists_1(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        ListNode mergeHead = null;
        if (list1.val <= list2.val) {
            mergeHead = list1;
            mergeHead.next = mergeTwoLists_1(list1.next, list2);
        } else {
            mergeHead = list2;
            mergeHead.next = mergeTwoLists_1(list1, list2.next);
        }
        return mergeHead;
    }

    /**
     * 方法1：递归方法<更简洁的代码>
     * 2018-10
     */
    public ListNode mergeTwoLists_2(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val <= l2.val) {
            l1.next = mergeTwoLists_2(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists_2(l1, l2.next);
            return l2;
        }
    }

    /**
     * 方法2：非递归方法while实现
     * 2018-10-05 北邮教三
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
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
        sb.delete(sb.length() - 3,sb.length());
        System.out.println(sb.toString());
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
        ListNode mergeHead2 = mergeTwoLists(head1, head2);
        printLinkedList(mergeHead2);
    }

    /**********************Leetcode_23_Merge k Sorted Lists******************************/

    /**
     * 难度:Hard
     * DateTime:2018-10-05 10:29 北邮教三
     * 链接：
     * 题目描述：https://leetcode.com/problems/merge-k-sorted-lists/description/
     * Merge k sorted linked lists and return it as one sorted list.
     * Analyze and describe its complexity.<重点：会分析复杂度>
     * Example:
     * Input:
     * [
     *   1->4->5,
     *   1->3->4,
     *   2->6
     * ]
     * Output: 1->1->2->3->4->4->5->6
     *
     *  复杂度分析的前提：长度为N和M的两个链表，合并在一起最坏情况下时间复杂度为O（N+M）。
     *
     * 方法1：先合并2个，然后将结果再与一个新链表合并，依次类推，直至合并完毕。
     *       假设4个链表A、B、C、D，首先合并A、B得到AB，
     *       然后合并AB、C，得到ABC，最后合并ABC、D，得到ABCD。
     *       复杂度分析：假设每个链表长度为N，共k个链表，则
     *       2N+3N+4N+...+kN=((k*k+k-2)/2)*N=O(k*k*N);
     * 方法2：将这些链表的节点存放到list中，然后调用list的sort方法，指定根据val大小排序的比较器，
     *       进行排序，最后将这些节点依次连在一起即可。
     *       时间复杂度：就是排序的时间复杂度(N*logN):(k*N)*log(k*N);
     * 方法3：利用分治策略。
     *       合并k个链表，只需要先分成2组，先合并k/2个链表，
     *       同样，合并k/2个链表，也分成2组，先合并k/4个链表；...
     *       最终，只需要2个链表即可，然后再依次返回。
     *       利用递归的方法解决。
     *       时间复杂度分析：k个链表，每个链表长度为N，一共可以分治成logk层。
     *       第一层，只需要合并1次，每合并1次，时间复杂度为O(k*N/2+k*N/2)=O(k*N);
     *       第二层，需要合并2次，每合并1次，时间复杂度为O（k*N/4+k*N/4）,则该层复杂度为2*O（k*N/2）=O(k*N);
     *       第三层，需要合并4次，每合并1次，时间复杂度为O（k*N/8+k*N/8）,则该层复杂度为4*O（k*N/4）=O(k*N);
     *       ......
     *       第logk层，需要合并2^(logk -1)=k/2次，每合并1次，时间复杂度为O（2N），则该层复杂度为k/2*O(2N)=O(k*N).
     *       因此可以得出，每一层的时间复杂度都是O(k*N);logk层，因此最终时间复杂度为O(k*N*logk).
     *
     * 三种算法的时间复杂度相比较：O(k*k*N)>(k*N)*log(k*N)>O(k*N*logk);
     * 因此，第三种分治算法最优。
     * 注意：方法1和方法3都需要利用mergeTwoLists方法。
     */
    /**
     * 算法1：逐个合并。
     * Leetcode Submission Result: Accepted
     */
    public ListNode mergeKLists_1(ListNode[] lists) {
        if(null == lists||lists.length == 0) return null;
        ListNode mergedList = lists[0];
        for(int i = 1;i < lists.length;i++){
            mergedList = mergeTwoLists(mergedList,lists[i]);
        }
        return mergedList;
    }
    /**
     * 算法2：对节点排序。
     * Submission Result: Accepted
     */
    public ListNode mergeKLists_2(ListNode[] lists) {
        if(null == lists||lists.length == 0) return null;
        ArrayList<ListNode> nodeList = new ArrayList<ListNode>();
        for(ListNode head:lists){//将所有链表的节点都添加到nodeList中
            while(null != head){
                nodeList.add(head);
                head = head.next;
            }
        }
        if(nodeList.size() == 0) return null;//排除所有链表都是null的特殊情况,否则不通过
        nodeList.sort(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val-o2.val;
            }
        });
        for(int i = 1;i < nodeList.size();i++){
            nodeList.get(i-1).next = nodeList.get(i);
        }
        return nodeList.get(0);
    }
     /**
     * 算法3：分治策略与递归实现。divideAndConque
     * Submission Result: Accepted
      * 代码分成三部分：
      * 1. 递归终止条件；
      * 2. 分割ListNode数组为两个子数组；
      * 3. 递归调用分别合并子数组，然后再利用mergeToList合并递归方法返回的链表。
     */
    public ListNode mergeKLists_divideAndConque(ListNode[] lists) {
        //第一部分
        if(null == lists||lists.length == 0) return null;
        if(lists.length == 1) return lists[0];
        //第二部分
        int num = lists.length;
        int mid = num/2;
        ListNode[] subLists1 = new ListNode[mid];
        ListNode[] subLists2 = new ListNode[num-mid];
        for(int i = 0;i < mid;i++){
            subLists1[i] = lists[i];
        }
        for(int i = mid;i < num;i++){
            subLists2[i-mid] = lists[i];
        }
        //第三部分
        ListNode mergedList1 = mergeKLists_divideAndConque(subLists1);
        ListNode mergedList2 = mergeKLists_divideAndConque(subLists2);
        return mergeTwoLists(mergedList1,mergedList2);
    }

    /********************测试************************/
    @Test
    public void test2() {
        ListNode head1 = new ListNode(1);
        ListNode node3 = new ListNode(3);
        ListNode node5 = new ListNode(5);
        ListNode node7 = new ListNode(7);
        head1.next = node3;
        node3.next = node5;
        node5.next = node7;
        ListNode head2 = new ListNode(0);
        ListNode node2 = new ListNode(21);
        ListNode node4 = new ListNode(34);
        ListNode node6 = new ListNode(69);
        head2.next = node2;
        node2.next = node4;
        node4.next = node6;
        ListNode head3 = new ListNode(-3);
        ListNode node31 = new ListNode(20);
        ListNode node32 = new ListNode(42);
        ListNode node33 = new ListNode(66);
        head3.next = node31;
        node31.next = node32;
        node32.next = node33;
        ListNode[] lists = new ListNode[]{head1,head2,head3};

//        ListNode result1 = mergeKLists_1(lists);
//        printLinkedList(result1);

//        ListNode result2 = mergeKLists_2(lists);
//        printLinkedList(result2);
//
        ListNode result3 = mergeKLists_divideAndConque(lists);
        printLinkedList(result3);

    }


}
