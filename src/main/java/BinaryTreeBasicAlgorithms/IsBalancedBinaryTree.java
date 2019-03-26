package BinaryTreeBasicAlgorithms;


/**
 * 判断是否是二叉树
 * 递归思想
 */
public class IsBalancedBinaryTree {
    /**
     * 题目描述
     * 输入一棵二叉树，判断该二叉树是否是平衡二叉树。
     * 解题思路：
     * 算法1：先序遍历的顺序。存在节点多次访问的问题，效率低下。
     * 算法2：后序遍历的顺序。先判断左右子树是否平衡性，并计算根节点的深度。
     *       需要利用数组作为参数保存根节点的深度。
     */

    /**
     * 算法1：简单。
     * 通过分别求解每个节点的左子树和右子树的深度。
     * 只有左右子树的深度差值小于等于1,且左右子树都是平衡树的时候，才能说明当前树是平衡树。
     * 问题：存在一个节点被重复遍历多次的问题，时间效率不高。
     */
    public boolean isBalancedBinaryTreeByDepth(TreeNode root) {
        if (root == null) return true;//递归的终止条件
        //分别获取左子树和右子树的深度
//        if (Math.abs(getDepth(root.left) - getDepth(root.right)) > 1) {
//            return false;
//        } else {
//            return isBalancedBinaryTreeByDepth(root.left) && isBalancedBinaryTreeByDepth(root.right);
//        }
        return (Math.abs(getDepth(root.left) - getDepth(root.right)) <= 1)
                && isBalancedBinaryTreeByDepth(root.left)
                && isBalancedBinaryTreeByDepth(root.right);
    }

    /**
     * 根据二叉树的根节点，获取该二叉树的深度(路径的最大长度）
     */
    public int getDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(getDepth(root.left), getDepth(root.right)) + 1;
    }


/**************************************************************************/
    /**
     * 算法2：从最底层子树判断开始，直到整个二叉树，避免了一个节点被多次遍历的情况.
     * 具体实现：采用后序遍历的方式，从底层开始判断二叉树的平衡性，避免了节点多次访问的情况。
     */
    public boolean isBalancedBinaryTree(TreeNode root) {
        int[] depth = new int[]{0};//初始值为0,用于存储当前子树的深度
        return helper(root, depth);//其实这里的depth就是整个二叉树的深度
    }

    public boolean helper(TreeNode root, int[] depth) {
        if (root == null) {
            depth[0] = 0;//空，深度为0
            return true;
        }
        int[] leftDepth = new int[]{0};//用于存储当前节点的左子树的深度
        int[] rightDepth = new int[]{0};//用于存储当前节点的右子树的深度
        //左子树和右子树都是平衡树，且两者的深度差值不能超过1,返回true
        if (helper(root.left, leftDepth)
                && helper(root.right, rightDepth)
                && Math.abs(leftDepth[0] - rightDepth[0]) <= 1) {
            //返回true之前，保存当前节点为根节点的二叉树的深度，递归的上一层函数需要
            depth[0] = Math.max(leftDepth[0], rightDepth[0]) + 1;
            return true;
        }
        return false;
    }

    /**
     * 二叉树数据结构
     */
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

}
