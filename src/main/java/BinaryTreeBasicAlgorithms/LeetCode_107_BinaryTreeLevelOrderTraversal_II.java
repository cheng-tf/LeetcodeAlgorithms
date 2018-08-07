package BinaryTreeBasicAlgorithms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_107_BinaryTreeLevelOrderTraversal_II {

    @Test
    public void test(){
        MyBinaryTree.TreeNode root = MyBinaryTree.getMyBinaryTree();
        List<List<Integer>> lists = binaryTreeLevelOrderTraversal_II(root);
        System.out.println("lists = " + lists);
    }

    public List<List<Integer>> binaryTreeLevelOrderTraversal_II(MyBinaryTree.TreeNode root){
        int maxDepth = getMaxDepth(root);
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        for(int i = 0;i < maxDepth;i++){
            lists.add(new ArrayList<Integer>());
        }
        perLevelTraversal(root,1,lists,maxDepth);
        return lists;
    }

    public void perLevelTraversal(MyBinaryTree.TreeNode root,int level,List<List<Integer>> lists,int depth){
        if(root == null) return;
        lists.get(depth-level).add(root.val);
        perLevelTraversal(root.left,level+1,lists,depth);
        perLevelTraversal(root.right,level+1,lists,depth);
    }

    public int getMaxDepth(MyBinaryTree.TreeNode root){
        if(root == null) return 0;
        return Math.max(getMaxDepth(root.left),getMaxDepth(root.right))+1;
    }

}
