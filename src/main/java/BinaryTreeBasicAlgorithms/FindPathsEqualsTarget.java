package BinaryTreeBasicAlgorithms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;


public class FindPathsEqualsTarget {
/**
 * 题目描述：输入一颗二叉树的跟节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
 * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
 * (注意: 在返回值的list中，数组长度大的数组靠前)
 * https://www.nowcoder.com/practice/b736e784e3e34731af99065031301bca?tpId=13&tqId=11177
 * &tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 */

    /**
     * 题目：寻找二叉树中和为某一值的所有路径
     * 回溯法，在退出递归方法中，做回溯动作。
     * 思路：首先确定是一条路径的条件：叶子节点且当前值等于target
     * 即(root.left==null&&root.right==null)&&(currentSum==target)
     * 然后根据这个条件的其他情况分析：
     * 1. 叶子节点但当前值不等于target;只需要tempList删除当前节点值即可；
     * 2. 左节点存在，递归左节点；
     * 3. 右节点存在，递归右节点。
     * 注意：由于currentSum是int传递，因此，在递归过程跳出时，不需要减去当前节点值；
     * 但是对于tempList是个对象，需要在退出函数之前删除当前节点的值。
     */

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        ArrayList<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>();
        if (root == null) return lists;
        ArrayList<Integer> tempList = new ArrayList<Integer>();
        findPathHandler(root, tempList, lists, target, 0);
//        System.out.println(tempList.toString());//最后结果tempList一定是空的[]
        return lists;
    }

    /**
     * 递归方法
     */
    public void findPathHandler(TreeNode root, ArrayList<Integer> tempList, ArrayList<ArrayList<Integer>> lists, int target, int currentSum) {
        currentSum += root.val;//当前访问路径的和
        tempList.add(root.val);//将该值存入list集合中
        //找到一条路径的条件：当前节点为叶子节点且路径之和为目标值
        //如果是叶子节点但不等于目标值，只需要tempList删除当前节点值即可
        if ((root.left == null && root.right == null) && (currentSum == target)) {
            ArrayList<Integer> newList = new ArrayList<Integer>();
            for (int i = 0; i < tempList.size(); i++) newList.add(0);//初始化
            Collections.copy(newList, tempList);//复制tempList至newList
            lists.add(newList);//添加一条路径
        }
        if (root.left != null)
            findPathHandler(root.left, tempList, lists, target, currentSum);
        if (root.right != null)
            findPathHandler(root.right, tempList, lists, target, currentSum);
        tempList.remove(tempList.size() - 1);//很重要;回溯法
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

    /**
     * 测试
     */
    @Test
    public void test() {
        TreeNode root = new TreeNode(10);
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(10);
        TreeNode node3 = new TreeNode(5);
        TreeNode node4 = new TreeNode(5);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        ArrayList<ArrayList<Integer>> lists = FindPath(root, 20);
        System.out.println(lists.toString());
    }


}
