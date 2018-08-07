package BinaryTreeBasicAlgorithms;

import org.junit.Test;
import java.util.*;

public class BinaryTreeTraversals {

    /**
     * 二叉树的数据结构
     */
    public class BinaryTreeNode{
        BinaryTreeNode left;
        BinaryTreeNode right;
        int value;
        BinaryTreeNode(int value){
            this.value = value;
        }
    }

    @Test
    public void test(){
        BinaryTreeNode node1 = new BinaryTreeNode(2);
        BinaryTreeNode node2 = new BinaryTreeNode(3);
        BinaryTreeNode node3 = new BinaryTreeNode(6);
        BinaryTreeNode node4 = new BinaryTreeNode(5);
        BinaryTreeNode node5 = new BinaryTreeNode(7);
        BinaryTreeNode node6 = new BinaryTreeNode(9);
        BinaryTreeNode node7 = new BinaryTreeNode(11);
        BinaryTreeNode node8 = new BinaryTreeNode(22);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        node4.left = node8;
        //先序遍历
        List<Integer> resultPre = new ArrayList<Integer>();
        preOrderTraversal(node1,resultPre);
        System.out.println("先序遍历："+resultPre.toString());
        //中序遍历
        List<Integer> resultIn = new ArrayList<Integer>();
        inOrderTraversal(node1,resultIn);
        System.out.println("中序遍历："+resultIn.toString());
        //后序遍历
        List<Integer> resultPost = new ArrayList<Integer>();
        postOrderTraversal(node1,resultPost);
        System.out.println("后序遍历："+resultPost.toString());
        //先序遍历
        List<Integer> resultPreWhile = new ArrayList<Integer>();
        preOrderTraversal(node1,resultPreWhile);
        System.out.println("先序遍历："+resultPreWhile.toString());
        //中序遍历
        List<Integer> resultInWhile = new ArrayList<Integer>();
        inOrderTraversal(node1,resultInWhile);
        System.out.println("中序遍历："+resultInWhile.toString());
        //后序遍历
        List<Integer> resultPostWhile = new ArrayList<Integer>();
        postOrderTraversal(node1,resultPostWhile);
        System.out.println("后序遍历："+resultPostWhile.toString());
    }


    /**
     * 递归实现先序遍历(前序遍历)--PreOrderTraversal
     * @param root
     */
    public void preOrderTraversal(BinaryTreeNode root,List<Integer> result){
        if(root == null) return;
        result.add(root.value);
        preOrderTraversal(root.left,result);
        preOrderTraversal(root.right,result);
    }

    /**
     * 递归实现中序遍历(中序遍历)--InOrderTraversal
     * @param root
     */
    public void inOrderTraversal(BinaryTreeNode root,List<Integer> result){
        if(root == null) return;
        inOrderTraversal(root.left,result);
        result.add(root.value);
        inOrderTraversal(root.right,result);
    }

    /**
     * 递归实现后序遍历(后序遍历)--PostOrderTraversal
     * @param root
     */
    public void postOrderTraversal(BinaryTreeNode root,List<Integer> result){
        if(root == null) return;
        postOrderTraversal(root.left,result);
        postOrderTraversal(root.right,result);
        result.add(root.value);
    }

    //非递归实现
    public List<Integer> preOrderTraversalWhile(BinaryTreeNode root){
        List<Integer> result  = new ArrayList<Integer>();
        Deque<BinaryTreeNode> stack = new ArrayDeque<BinaryTreeNode>();
        while(!(stack.isEmpty()&&root == null)){
//        while(stack.isEmpty()||root == null){
            if(root != null){
                result.add(root.value);
                stack.push(root);
                root = root.left;
            }else{
                root = stack.pop().right;
            }
        }
        return result;
    }

    //非递归实现
    public List<Integer> inOrderTraversalWhile(BinaryTreeNode root){
        List<Integer> result  = new ArrayList<Integer>();
        Deque<BinaryTreeNode> stack = new ArrayDeque<BinaryTreeNode>();
        while(!(stack.isEmpty()&&root == null)){
//        while(stack.isEmpty()||root == null){
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


    //非递归实现
    public List<Integer> postOrderTraversalWhile(BinaryTreeNode root){
        List<Integer> result  = new LinkedList<Integer>();//需要逆序，利用链表
        Deque<BinaryTreeNode> stack = new ArrayDeque<BinaryTreeNode>();
        while(!(stack.isEmpty()&&root == null)){//前后中 ==> 中后前
//        while(stack.isEmpty()||root == null){
            if(root != null){
                result.add(0,root.value);
                stack.push(root);
                root = root.right;
            }else{
                root = stack.pop().left;
            }
        }
        return result;
    }




}
