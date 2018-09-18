package SearchAlgorithm;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;


/**
 * 二叉查找树BinarySearchTree又称为二叉排序树，因为中序遍历输出就是从小到大排序
 * 注意：二叉查找树的特征：左子树上左右节点的值都小于等于根的值，右子树上所有节点的值都大于根的值。
 */
public class BinarySearchTree {

    @Test
    public void test(){
        int[] nums = {2,3,4,5,-1,34,8,2,1,4};
        List<BSTNode> nodeList = new ArrayList<BSTNode>();
        for(int i = 0;i<nums.length;i++){//创建节点
           nodeList.add(new BSTNode(nums[i]));
        }
        for(int i = 1;i<nodeList.size();i++){//构建二叉查找树
            BST_insert(nodeList.get(0),nodeList.get(i));
        }
        BSTNode root = nodeList.get(0);
        //二叉搜索树若按照中序遍历输出，即从小到大顺序，因此又称为二叉树排序树
        List<Integer> result = inOrderTraversal(root);
        System.out.println(result.toString());//从小到大排序
        
        //判断是否存在某个值
        for(int i = 0;i<nums.length;i++){
            System.out.println("searchValue("+nums[i]+") = " + searchValue(root,nums[i]));
        }
        System.out.println("searchValue(10) = " + searchValue(root,10));

    }

    /**
     * 判断某个值是否在二叉查找树中；存在返回true，否则返回false
     */
    public boolean searchValue(BSTNode root,int value){
        if(value == root.value) return true;
        if(value < root.value){//小于根节点值
            if(root.left != null){
                return searchValue(root.left,value);
            }else{
                return false;
            }
        }else{//大于根节点值 (value > root.value)
            if(root.right != null){
                return searchValue(root.right,value);
            }else{//相等
                return false;
            }
        }
    }
    /**
     * 判断某个值是否在二叉查找树中；存在返回该节点，否则返回NULL
     * LeetCode_700_Search in a Binary Search Tree
     * 难度：easy
     * https://leetcode.com/problems/search-in-a-binary-search-tree/description/
     * 注意：需要将BSTNode中的value改为val；BSTNode 换成TreeNode
     */
    public BSTNode searchBST(BSTNode root,int value){
        if(root == null) return null;
        if(value == root.value) return root;
        if(value < root.value){//小于根节点值
            if(root.left != null){
                return searchBST(root.left,value);
            }else{
                return null;
            }
        }else{//大于根节点值 (value > root.value)
            if(root.right != null){
                return searchBST(root.right,value);
            }else{//相等
                return null;
            }
        }
    }


    /**
     * 定义二叉搜索树的数据结构
     */
    public class BSTNode{
        BSTNode left;
        BSTNode right;
        int value;
        public BSTNode(int value){
            this.value = value;
        }
    }

    /**
     * 向以root为根节点的二叉查找树中插入节点insertNode
     */
    public void BST_insert(BSTNode root,BSTNode insertNode){
        if(insertNode.value <= root.value){
            if(root.left != null){
                BST_insert(root.left,insertNode);//递归插入左子树
            }else{
                root.left = insertNode;
            }
        }else{
            if(root.right != null){
                BST_insert(root.right,insertNode);//递归插入右子树
            }else{
                root.right = insertNode;
            }
        }
    }

    /**
     * 中序遍历
     * @param root
     * @return
     */
    public List<Integer> inOrderTraversal(BSTNode root){
        List<Integer> result = new ArrayList<Integer>();
        Deque<BSTNode> stack = new ArrayDeque<BSTNode>();
        while(!(stack.isEmpty()&&root == null)){
            if(root != null){
                stack.push(root);
                root = root.left;
            }else{
                root = stack.pop();
                result.add(root.value);
                root = root.right;
            }
        }
        return result;
    }
}
