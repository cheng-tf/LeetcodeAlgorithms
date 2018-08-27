package BinaryTreeBasicAlgorithms;

import org.junit.Test;
import sun.reflect.generics.tree.Tree;

public class ConvertBetweenBSTandDoublelyLinkedList {
    /**
     * 二叉搜索树的数据结构
     */
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
        public TreeNode(int val) {
            this.val = val;
        }
    }

    /***********剑指Offer36:二叉搜索树与双向链表之间的转换************************/
    /**
     * 题目描述
     * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。
     * 要求不能创建任何新的结点，只能调整树中结点指针的指向。
     *
     * 思路分析：二叉搜索树的中序遍历就是排序序列，因此遍历方式采用欧冠中序遍历；
     *          规律查找：发现每一个相对根节点的left需要指向其左子树的最右节点，
     *           其右节点需要指向其右子树的最左节点；显然利用递归实现；先对左子树转换；
     *           转换后的链表的最后一个节点为左子树的最右节点，此时将最右节点的right指向根节点，
     *           并让根节点的left指向该链表的最后节点；
     *  递归方法书写思路：首先递归终止条件：当前节点为null，则return；
     *                    判断左子树是不是存在，存在则递归转换该左子树；
     *                    遍历左子树之后，让根节点(就是当前节点)的left指向链表最后节点，同时让链表的最后节点的right指向根节点；
     *                    判断是否存在右子树，若存在，则递归转换右子树。
     *   说明：右子树递归进去，找到最左节点之后，其left指向根节点即此时双向链表的最后一个节点；
     *   同时链表最后节点的right指向右子树的最左节点；操作步骤与上述第2步一致，因此递归即可。
     *   相似分析：第2步，左子树转换出来后，当前节点为根节点，链表最后一节点为左子树的最右节点；
     *             右子树遍历进去，首先找到右子树的最左节点，即当前节点为右子树的最左节点，链表最后一节点为根节点；
     *             两者操作一样：让当前节点的left指向链表最后一节点，最后节点的right指向当前节点。
     *
     */

    public TreeNode Convert(TreeNode pRootOfTree) {
        if(pRootOfTree == null) return null;
        TreeNode root = pRootOfTree;
        TreeNode[] lastLinkedNode = new TreeNode[]{null};
        convertRecursive(root, lastLinkedNode );
        while(root.left != null) root = root.left;//找到原树的最左叶子节点
        return root;
    }

    public void convertRecursive(TreeNode root,TreeNode[] lastLinkedNode){
        if(root == null) return;//递归终止条件
        TreeNode currentNode = root;
        //若左子树非空，则转换左子树
        if(currentNode.left != null) convertRecursive(currentNode.left,lastLinkedNode);
        //处理当前根节点
        currentNode.left = lastLinkedNode[0];
        if(lastLinkedNode[0] != null) {
            lastLinkedNode[0].right = currentNode;
        }
        lastLinkedNode[0] = currentNode;
        //若右子树非空，则转换右子树
        if(currentNode.right != null) convertRecursive(currentNode.right,lastLinkedNode);
    }


    //测试
   public void printDoublelyLinkedList(TreeNode root){
        StringBuilder sb = new StringBuilder();
        if(root == null){
            System.out.println();
            return;
        }
        while(root.right != null){
            sb.append(root.val).append("->");
            root = root.right;
        }
        sb.append(root.val).append("->");
        if(sb.length()>2)
            sb.delete(sb.length()-2,sb.length());
        sb.append("\n");
        while(root.left != null){
            sb.append(root.val).append("->");
            root = root.left;
        }
       sb.append(root.val).append("->");
       if(sb.length()>2)
           sb.delete(sb.length()-2,sb.length());
       System.out.println(sb.toString());
   }

   @Test
    public void test(){
        TreeNode root = new TreeNode(10);
   /*     TreeNode node1 = new TreeNode(6);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(8);
        TreeNode node4 = new TreeNode(14);
        TreeNode node5 = new TreeNode(12);
        TreeNode node6 = new TreeNode(16);
        root.left = node1;
        node1.left = node2;
        node1.right = node3;
        root.right = node4;
        node4.left = node5;
        node4.right = node6;*/

        TreeNode head = Convert(root);
        printDoublelyLinkedList(head);

   }




}
