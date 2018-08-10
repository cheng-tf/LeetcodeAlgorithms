package BinaryTreeBasicAlgorithms;

import java.util.ArrayList;

public class FindPathsEqualsTarget {

    /**
     * 寻找二叉树中节点值的和为输入整数的所有路径
     */

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        ArrayList<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> tempList = new ArrayList<Integer>();
        if(root == null) return null;
        findPathHandler(root,tempList,lists,target,0);
        return lists;
    }

    public  void findPathHandler(TreeNode root,ArrayList<Integer> tempList, ArrayList<ArrayList<Integer>> lists,int target,int currentSum){
        if(root == null){
            if(currentSum == target){
                lists.add(tempList);
            }else{
//                tempList.
                return;
            }
        }


    }



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

}
