package BinaryTreeBasicAlgorithms;

import org.junit.Test;

import java.util.ArrayDeque;

public class GetDepthOfBinaryTree {
    /*******************剑指Offer:获取二叉树的深度********************/

    /**
     * 二叉树的深度定义：从根节点至叶子节点的最长路径的长度。
     * <p>
     * 思路分析：
     * 两种算法：①递归-深度优先搜索DFS；②循环-广度优先搜索BFS。
     *    第一种:递归;本质就是：深度优先搜索DFS;
     *       利用递归方法，递归左子树和右子树，分别获取它们深度的最大值再加1即可。
     *    第二种：循环-广度优先搜索BFS。
     *       利用队列数据结构，分别将一层一层的节点添加到队列中，然后利用sum记录一层的数量，
     *       遍历这一层节点，同时添加它们的子节点到队列中，当sum为0时，更新sum值，同时depth++，开始下一层的遍历。
     *       直到队列为空时，退出循环。
     */

    /**
     * 递归:深度优先搜索DFS
     */
    public int getTreeDepthDFS(TreeNode root) {
        if (root == null) return 0;//递归终止条件
        return Math.max(getTreeDepthDFS(root.left), getTreeDepthDFS(root.right)) + 1;
//        int leftDepth = getTreeDepthDFS(root.left);
//        int rightDepth = getTreeDepthDFS(root.right);
//        return (leftDepth > rightDepth ? leftDepth : rightDepth) + 1;//求得左子树和右子树的最大值加1即可
    }


    /**
     * 循环-广度优先搜索
     */
    public int getTreeDepthBFS1(TreeNode root) {
        if (root == null) return 0;
        ArrayDeque<TreeNode> deque = new ArrayDeque<>();
        deque.addLast(root);
        int nextSum = 1, currCount = 0;//nextSum,进入到队列中的元素个数;currCount遍历队列中元素计数
        int depth = 0;
        while (!deque.isEmpty()) {
            TreeNode node = deque.pollFirst();
            currCount++;
            if (node.left != null) deque.addLast(node.left);
            if (node.right != null) deque.addLast(node.right);
            if (currCount == nextSum) {//当前层遍历结束
                nextSum = deque.size();//下一层的元素个数
                currCount = 0;//置位0
                depth++;//深度加1
            }
        }
        return depth;
    }

    /**
     * 循环-广度优先搜索
     */
    public int getTreeDepthBFS2(TreeNode root) {
        if (root == null) return 0;
        ArrayDeque<TreeNode> deque = new ArrayDeque<>();
        deque.addLast(root);//将第一层加入队列中
        int sum = deque.size();//加入队列的一层的节点数
        int depth = 0;
        TreeNode node;
        while (!deque.isEmpty()) {
            //删除当前层的节点，并将下层节点加入到队列中
            while (sum-- > 0) {
                node = deque.pollFirst();
                if (node.left != null) deque.addLast(node.left);
                if (node.right != null) deque.addLast(node.right);
            }
            sum = deque.size();
            depth++;
        }
        return depth;
    }

    /**
     * 二叉树的数据结构
     */
    private class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;

        TreeNode(int val) {
            this.val = val;
        }
    }

    /*****************************测试**********************/
    @Test
    public void test() {
        TreeNode root = new TreeNode(0);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node3.left = node4;
        node4.left = node5;
        System.out.println(getTreeDepthDFS(root));
        System.out.println(getTreeDepthBFS1(root));
        System.out.println(getTreeDepthBFS2(root));
    }
}
