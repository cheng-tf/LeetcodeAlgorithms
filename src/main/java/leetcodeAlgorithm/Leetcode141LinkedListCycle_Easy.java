package leetcodeAlgorithm;

import org.junit.Test;

public class Leetcode141LinkedListCycle_Easy {


    //Definition for singly-linked list.
     class ListNode {
          int val;
          ListNode next;
          ListNode(int x) {
              val = x;
              next = null;
          }
      }

/* //递归方法
 public class Solution {
     public boolean hasCycle(ListNode head) {
         if(head == null) return false;
         return isCycle(head,head.next,2);
     }
     private boolean isCycle(ListNode head,ListNode current,int level){
         ListNode temp = head;
         if(current == null) return false;
         for(int i = 1;i < level;i++){//遍历当前节点是否和前面的节点相同
             if(current == temp) return true;
             temp = temp.next;
         }
         return isCycle(head,current.next,level+1);
     }
 }*/


    public class Solution {
        public boolean hasCycle(ListNode head) {
            if(head == null) return false;
            ListNode fast=head,slow = head;
            while(fast!=null && fast.next!=null)
            {
                slow = slow.next;
                fast = fast.next.next;
                if(slow == fast){
                    return true;
                }
            }
            return false;
        }
    }
    @Test
    public  void solutionTest(){
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(1);
        ListNode node4 = new ListNode(1);
        node1.next = node2;
        node2.next = node1;
        boolean result = new Solution().hasCycle(node1);
        System.out.println("result = " + result);
    }
}
