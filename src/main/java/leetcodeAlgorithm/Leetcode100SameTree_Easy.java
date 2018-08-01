package leetcodeAlgorithm;

import org.junit.Test;

public class Leetcode100SameTree_Easy {

    /*
    *Given two binary trees, write a function to check if they are the same or not.
Two binary trees are considered the same if they are structurally identical and the nodes have the same value.
Example 1:
Input:     1         1
          / \       / \
         2   3     2   3

        [1,2,3],   [1,2,3]
Output: true
Example 2:
Input:     1         1
          /           \
         2             2
        [1,2],     [1,null,2]
Output: false
Example 3:
Input:     1         1
          / \       / \
         2   1     1   2
        [1,2,1],   [1,1,2]
Output: false
    * */


      //Definition for a binary tree node.
      public class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
      }

    class Solution {
        public boolean isSameTree(TreeNode p, TreeNode q) {
            if(p == null && q == null) return true;
            if(p == null || q == null) return false;
            //递归调用
            if(p.val != q.val) return false;
            return (isSameTree(p.left,q.left)&&(isSameTree(p.right,q.right)));
        }
    }
    @Test
    public void solutionTest(){
          TreeNode p = new TreeNode(10);
          TreeNode q = new TreeNode(10);
          TreeNode t1 = new TreeNode(1);
          TreeNode t2 = new TreeNode(3);
          TreeNode t3 = new TreeNode(7);
          TreeNode t4 = new TreeNode(9);
          TreeNode t5 = new TreeNode(4);
          p.left = t1;
          q.left = t1;
          p.right = t2;
          q.right = t2;//t5
          t1.left = t3;
          t1.right = t4;

          boolean result = new Solution().isSameTree(p,q);
        System.out.println("result = " + result);
    }
}
