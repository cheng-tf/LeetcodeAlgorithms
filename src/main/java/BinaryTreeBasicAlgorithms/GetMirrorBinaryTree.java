package BinaryTreeBasicAlgorithms;

public class GetMirrorBinaryTree {

    /**
     * 获取二叉树镜像
     * 算法：按照前序遍历顺序逐渐交换节点的左右节点
     */
    public void Mirror(TreeNode root) {
        if(root == null) return;//递归终止条件
        //交换当前节点的左右节点
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        Mirror(root.left);
        Mirror(root.right);
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
        }
    }
}
