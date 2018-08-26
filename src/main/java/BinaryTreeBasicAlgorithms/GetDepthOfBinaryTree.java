package BinaryTreeBasicAlgorithms;

public class GetDepthOfBinaryTree {
    /*******************剑指Offer:获取二叉树的深度********************/

    /**
     * 二叉树的深度定义：从根节点至叶子节点的最长路径的长度
     *
     * 思路分析：利用递归方法，递归左子树和右子树，
     * 分别获取它们深度的最大值再加1即可。
     */
    public int getDepth(TreeNode root){
        if(root == null) return 0;//递归终止条件
        return Math.max(getDepth(root.left),getDepth(root.right))+1;//求得左子树和右子树的最大值加1即可
    }


    /**
     * 二叉树的数据结构
     */
    private class TreeNode{
        TreeNode left;
        TreeNode right;
        int val;
        TreeNode(int val){
            this.val = val;
        };
    }
}
