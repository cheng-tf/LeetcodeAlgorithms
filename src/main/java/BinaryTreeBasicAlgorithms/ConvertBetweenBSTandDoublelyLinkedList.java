package BinaryTreeBasicAlgorithms;

import org.junit.Test;

import java.util.ArrayList;

public class ConvertBetweenBSTandDoublelyLinkedList {


    /***********剑指Offer36:二叉搜索树与双向链表之间的转换************************/
    /**
     * 题目描述
     * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。
     * 要求不能创建任何新的结点，只能调整树中结点指针的指向。
     * <p>
     * 思路分析：二叉搜索树的中序遍历就是排序序列，因此遍历方式采用中序遍历；
     * 规律：1. 对于每一个非叶子节点，其left指向其左子树的最右叶子节点，其right指向其右子树的最左叶子节点；
     * 2. 对于叶子节点，较为复杂。对于某个根节点root的左子树的最右叶子节点的right指向root；root的右子树的最左叶子节点的left指向root。
     * 编程实现：利用一个长度为1的数组保存当前双向链表的末节点，这样在遍历过程中只需要找到链表的下一个节点即可；
     * 也就是相对于普通的中序遍历多了一个lastLinkedNode即当前链表末节点，并增加了双向指向和更新末节点操作。
     * <p>
     * 思路再次整理：整体思想：递归中序遍历+当前双向链表末节点。
     * 利用数组保存当前双向链表的末节点，使用普通引用不可行，因为需要递归多层深度，且java是值传递。
     * 先遍历左子树，然后处理当前root，更新末节点，最后遍历右子树。
     * <p>
     * 递归方法书写思路：首先递归终止条件：当前节点为null，则return；
     * 判断左子树是不是存在，存在则递归转换该左子树；
     * 遍历左子树之后，让根节点(就是当前节点)的left指向链表最后节点，同时让链表的最后节点的right指向根节点；
     * 判断是否存在右子树，若存在，则递归转换右子树。
     * 说明：右子树递归进去，找到最左节点之后，其left指向根节点即此时双向链表的最后一个节点；
     * 同时链表最后节点的right指向右子树的最左节点；操作步骤与上述第2步一致，因此递归即可。
     * 相似分析：第2步，左子树转换出来后，当前节点为根节点，链表最后一节点为左子树的最右节点；
     * 右子树遍历进去，首先找到右子树的最左节点，即当前节点为右子树的最左节点，链表最后一节点为根节点；
     * 两者操作一样：让当前节点的left指向链表最后一节点，最后节点的right指向当前节点。
     */

    public TreeNode Convert(TreeNode root) {
        if (root == null) return null;
        //存储双端队列的末尾节点
        TreeNode[] lastLinkedNode = new TreeNode[]{null};
        convertRecursive(root, lastLinkedNode);
        while (root.left != null) root = root.left;//找到原树的最左叶子节点
        return root;
    }

    public void convertRecursive(TreeNode root, TreeNode[] lastLinkedNode) {
        if (root == null) return;//递归终止条件
        //1. 若左子树非空，则转换左子树
        if (root.left != null) convertRecursive(root.left, lastLinkedNode);
        //2. 处理当前根节点：lastLinkedNode <==> root
        root.left = lastLinkedNode[0];
        if (lastLinkedNode[0] != null) lastLinkedNode[0].right = root;
        lastLinkedNode[0] = root;//更新双向链表的最后一个节点
        //3. 若右子树非空，则转换右子树
        if (root.right != null) convertRecursive(root.right, lastLinkedNode);
    }

    /**
     * 递归实现的中序遍历
     *
     * @param root
     * @return
     */
    public ArrayList<Integer> inOrderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (root == null) return result;
        inOrderRecur(root, result);
        return result;
    }

    public void inOrderRecur(TreeNode root, ArrayList<Integer> result) {
        if (root == null) return;
        inOrderRecur(root.left, result);
        result.add(root.val);//重点更改这一部分即可
        inOrderRecur(root.right, result);
    }


    //测试
    public void printDoublelyLinkedList(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if (root == null) {
            System.out.println();
            return;
        }
        while (root.right != null) {
            sb.append(root.val).append("->");
            root = root.right;
        }
        sb.append(root.val).append("->");
        if (sb.length() > 2) sb.delete(sb.length() - 2, sb.length());
        sb.append("\n");
        while (root.left != null) {
            sb.append(root.val).append("->");
            root = root.left;
        }
        sb.append(root.val).append("->");
        if (sb.length() > 2) sb.delete(sb.length() - 2, sb.length());
        System.out.println(sb.toString());
    }

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

    @Test
    public void test() {
        TreeNode root = new TreeNode(10);
        TreeNode node1 = new TreeNode(6);
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
        node4.right = node6;

        ArrayList<Integer> result = inOrderTraversal(root);
        System.out.println("result.toString() = " + result.toString());

        TreeNode head = Convert(root);
        printDoublelyLinkedList(head);
    }


}
