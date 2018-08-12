package BinaryTreeBasicAlgorithms;

import org.junit.Test;
import sun.reflect.generics.tree.Tree;

import java.util.ArrayDeque;
import java.util.Deque;

public class KthBSTNode_BinarySearchTree {
    @Test
    public void test(){
        TreeNode root = new TreeNode(6);
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(5);
        TreeNode node4 = new TreeNode(8);
        TreeNode node5 = new TreeNode(7);
        TreeNode node6 = new TreeNode(9);
        root.left = node1;
        root.right = node4;
        node1.left = node2;
        node1.right = node3;
        node4.left = node5;
        node4.right = node6;

        inOrderTraversal(root);
        inOrderTraversalwhile(root);

    }


    TreeNode KthNode(TreeNode pRoot, int k){
        return  KthNode( pRoot, new int[]{k});
    }
    /**
     * BinaryTree
     * 给定一棵二叉搜索树，请找出其中的第k小节点。
     * 例如， （5，3，7，2，4，6，8） 中，
     * 按节点数值大小顺序第三小节点的值为4。
     * 递归思想：首先从左子树中寻找，若返回非null，表示寻找到，直接返回该节点；
     * 否则表示左子树中没有该节点；然后判断中间节点，即k是否为1(并减1)，是的话就返回当前节点；
     * 否则判断右子树。
     * 注意：二叉树中的任意一个节点都是中间节点，即使叶子节点也有两个null左右子树，
     * 因此二叉树中的任意一个节点都会经过k的判断，且经过1个，k就减1，正好找到那个k为1的节点。
     */
    TreeNode KthNode(TreeNode pRoot, int[] k){
        //递归终止条件
        if(pRoot == null||k[0] < 1) return null;
        //首先遍历左子树,若结果不为null，则返回；否在往下执行
        TreeNode result = KthNode(pRoot.left,k);
        if(result != null) return result;
        //遍历中节点
        if(k[0]-- == 1)  return pRoot;
        //遍历右节点
        return KthNode(pRoot.right,k);
    }

    /**
     * 非递归方式：利用栈和while循环
     * @param pRoot
     */
    TreeNode KthNode1(TreeNode pRoot, int k){
        return  KthNodeWhile( pRoot, new int[]{k});
    }
    public TreeNode KthNodeWhile(TreeNode root,int[] k){
        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
        while(!(stack.isEmpty()&&root == null)){
            if(root != null){
                stack.push(root);
                root = root.left;
            }else{
                root = stack.pop();
                if(k[0]-- == 1) return root;
                root = root.right;
            }
        }
        return null;
    }


    /**
     * 递归的中序遍历
     */
    public void inOrderTraversal(TreeNode root){
        if(root == null) return;
        inOrderTraversal(root.left);
        System.out.println("val = " + root.val);
        inOrderTraversal(root.right);
    }

    public void inOrderTraversalwhile(TreeNode root){
        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
        while(!(stack.isEmpty()&&root == null)){
            if(root != null){
                stack.push(root);
                root = root.left;
            }else{
                root = stack.pop();
                System.out.println("val = " + root.val);
                root = root.right;
            }
        }
    }


    /**
     * 二叉树结构
     */
     class TreeNode{
        TreeNode left;
        TreeNode right;
        int val;
        TreeNode(int val){
            this.val = val;
        }
     }
}
