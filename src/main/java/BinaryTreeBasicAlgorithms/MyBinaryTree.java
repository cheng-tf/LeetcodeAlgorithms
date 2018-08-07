package BinaryTreeBasicAlgorithms;


/**
 * 提供二叉树的数据结构
 * 提供已经构建的二叉树MyBinaryTree
 */
public class MyBinaryTree {


    /**
     * 二叉树数据结构
     */
    public static  class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val){
            this.val = val;
        }
    }


    public static TreeNode getMyBinaryTree(){
        int[] vals = {0,1,2,3,4,5,6,7,8,9,10};
        TreeNode root = new TreeNode(vals[0]);
        TreeNode node1 = new TreeNode(vals[1]);
        TreeNode node2 = new TreeNode(vals[2]);
        TreeNode node3 = new TreeNode(vals[3]);
        TreeNode node4 = new TreeNode(vals[4]);
        TreeNode node5 = new TreeNode(vals[5]);
        TreeNode node6 = new TreeNode(vals[6]);
        TreeNode node7 = new TreeNode(vals[7]);
        TreeNode node8 = new TreeNode(vals[8]);
        TreeNode node9 = new TreeNode(vals[9]);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;
        node3.left = node7;
        node3.right = node8;
        node4.left = node9;
        return root;
    }
}
