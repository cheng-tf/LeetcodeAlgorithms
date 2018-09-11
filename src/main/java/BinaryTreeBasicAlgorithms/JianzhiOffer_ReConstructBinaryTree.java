package BinaryTreeBasicAlgorithms;

import sun.reflect.generics.tree.Tree;

public class JianzhiOffer_ReConstructBinaryTree {
//    public class Solution {

//      Definition for binary tree
      public class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }

      }


    /**
     * 递归调用该方法
     */
    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        if(pre == null||in == null) return null;//防止pre或in为null
        return reConstructBinaryTree(pre,0,pre.length-1,in,0,in.length-1);
        }
    /**
     * 递归方法
     */
    public TreeNode reConstructBinaryTree(int[] pre,int preStart,int preEnd,int[] in,int inStart,int inEnd){
        //递归终止条件
        if(preStart> preEnd||inStart > inEnd || preEnd-preStart != inEnd-inStart) return null;
        TreeNode root = new TreeNode(pre[preStart]);
        int rootIndex = inStart;
        //在中序遍历结果中寻找当前子树的根节点索引
        while(rootIndex <= inEnd && in[rootIndex] != pre[preStart]) rootIndex++;
        //递归调用
        int len = rootIndex - inStart;
        root.left = reConstructBinaryTree(pre,preStart+1,preStart+len,in,inStart,rootIndex-1);
        root.right = reConstructBinaryTree(pre,preStart+len+1,preEnd,in,rootIndex + 1,inEnd);
        return root;
    }

}
