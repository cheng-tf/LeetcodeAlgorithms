package BinaryTreeBasicAlgorithms;

/**
 *判断是否是对称二叉树
 * 方法：利用前序遍历和前序遍历的对称遍历方式:中左右和中右边
 * 边遍历边判断，不需要都遍历一遍
 */
public class IsSymmetricalBinaryTreeByTraversal {

    /**
     * 对称的二叉树
     * 题目描述
     * 请实现一个函数，用来判断一颗二叉树是不是对称的。注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
     */

    /**
     * 二叉树的数据结构
     */
    public class TreeNode{
        TreeNode left;
        TreeNode right;
        int val;
        TreeNode(int val){
            this.val = val;
        }
    }


    public boolean isSymmetrical(TreeNode root) {
        return isSymmetricalHandler(root,root);
    }
    /**
     * 递归方法
     * 对于root1：中左右；
     * 对于root2：中右左；
     * 每一次判断，对应的两个节点都是对称位置的。
     */
    public boolean isSymmetricalHandler(TreeNode root1,TreeNode root2){
        if(root1 == null && root2 == null) return true;
        if(root1 == null || root2 == null) return false;
        if(root1.val != root2.val) {
            return false;
        }else{//只有在当前节点与对称节点一致时，才递归下一节点
            return isSymmetricalHandler(root1.left,root2.right)&&isSymmetricalHandler(root1.right,root2.left);
        }
    }


}
