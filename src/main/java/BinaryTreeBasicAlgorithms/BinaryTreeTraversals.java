package BinaryTreeBasicAlgorithms;

import org.junit.Test;

import java.util.*;


public class BinaryTreeTraversals {
    
    /**
     * 二叉树的数据结构
     */
    private class TreeNode{
        TreeNode left;
        TreeNode right;
        int val;
        TreeNode(int val){
            this.val = val;
        };
    }

    @Test
    public void test(){
        TreeNode root = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(6);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(7);
        TreeNode node6 = new TreeNode(9);
        TreeNode node7 = new TreeNode(11);
        TreeNode node8 = new TreeNode(22);
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        node4.left = node8;
        //先序遍历
        List<Integer> resultPre = new ArrayList<Integer>();
        preOrderTraversal(root,resultPre);
        System.out.println("先序遍历："+resultPre.toString());
        //中序遍历
        List<Integer> resultIn = new ArrayList<Integer>();
        inOrderTraversal(root,resultIn);
        System.out.println("中序遍历："+resultIn.toString());
        //后序遍历
        List<Integer> resultPost = new ArrayList<Integer>();
        postOrderTraversal(root,resultPost);
        System.out.println("后序遍历："+resultPost.toString());
        //先序遍历
        List<Integer> resultPreWhile = new ArrayList<Integer>();
        preOrderTraversal(root,resultPreWhile);
        System.out.println("先序遍历："+resultPreWhile.toString());
        //中序遍历
        List<Integer> resultInWhile = new ArrayList<Integer>();
        inOrderTraversal(root,resultInWhile);
        System.out.println("中序遍历："+resultInWhile.toString());
        //后序遍历
        List<Integer> resultPostWhile = new ArrayList<Integer>();
        postOrderTraversal(root,resultPostWhile);
        System.out.println("后序遍历："+resultPostWhile.toString());
        
        //按层遍历
        List<Integer> list = traversalByLevel(root);
        System.out.println("按层遍历：" + list.toString());
        //按每一层遍历
        ArrayList<ArrayList<Integer>> lists = traversalByEveryLevel(root);
        for(ArrayList list1 : lists)
             System.out.println("按每一层遍历：" + list1.toString());

        //按每一层遍历
        List<List<Integer>> lists2 = binaryTreeLevelOrderTraversal_II(root);
        for(List list2 : lists2)
            System.out.println("按每一层遍历：" + list2.toString());

        //按锯齿形遍历每一层
        ArrayList<ArrayList<Integer>> lists3 =traversalByZigZagLevel(root);
        for(List list3 : lists3)
            System.out.println("锯齿形遍历每一层：" + list3.toString());

        
    }

    /***
     * 1.前序、中序、后序遍历的递归实现；
     * 2.前序、中序、后序遍历的非递归实现；
     * 3.按层遍历；TraversalByLevel
     * 4.按锯齿形或之字形遍历；
     */
/*******************************递归方法：三种基本遍历********************************************/
    /**
     * 递归实现先序遍历(前序遍历)--PreOrderTraversal
     */
    public void preOrderTraversal(TreeNode root,List<Integer> result){
        if(root == null) return;
        result.add(root.val);
        preOrderTraversal(root.left,result);
        preOrderTraversal(root.right,result);
    }

    /**
     * 递归实现中序遍历(中序遍历)--InOrderTraversal
     */
    public void inOrderTraversal(TreeNode root,List<Integer> result){
        if(root == null) return;
        inOrderTraversal(root.left,result);
        result.add(root.val);
        inOrderTraversal(root.right,result);
    }

    /**
     * 递归实现后序遍历(后序遍历)--PostOrderTraversal
     * @param root
     */
    public void postOrderTraversal(TreeNode root,List<Integer> result){
        if(root == null) return;
        postOrderTraversal(root.left,result);
        postOrderTraversal(root.right,result);
        result.add(root.val);
    }

    /*******************************非递归方法：三种基本遍历********************************************/
    //非递归前序遍历
    public List<Integer> preOrderTraversalWhile(TreeNode root){
        List<Integer> result  = new ArrayList<Integer>();
        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
        while(!(stack.isEmpty()&&root == null)){
//        while(stack.isEmpty()||root == null){
            if(root != null){
                result.add(root.val);
                stack.push(root);
                root = root.left;
            }else{
                root = stack.pop().right;
            }
        }
        return result;
    }
    //非递归中序遍历

    public List<Integer> inOrderTraversalWhile(TreeNode root){
        List<Integer> result  = new ArrayList<Integer>();
        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
        while(!(stack.isEmpty()&&root == null)){
//        while(stack.isEmpty()||root == null){
            if(root != null){
                stack.push(root);
                root = root.left;
            }else{
                root = stack.pop();
                result.add(root.val);
                root = root.right;
            }
        }
        return result;
    }

    //非递归后序遍历
    public List<Integer> postOrderTraversalWhile(TreeNode root){
        List<Integer> result  = new LinkedList<Integer>();//需要逆序，利用链表
        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
        while(!(stack.isEmpty()&&root == null)){//前后中 ==> 中后前
//        while(stack.isEmpty()||root == null){
            if(root != null){
                result.add(0,root.val);
                stack.push(root);
                root = root.right;
            }else{
                root = stack.pop().left;
            }
        }
        return result;
    }

    /***************************************按层遍历**********************************/
    /**
     * 1. 按层遍历，存放到一个List中；
     * 2. 按层遍历，每一层存放到一个List中，返回一个大List<List>；
     */

    /**
     * 题目要求:按层遍历，存到一个List中；
     * 题目描述：从上往下按层打印出二叉树的每个节点，同层节点从左至右打印。
     *
     * 思路介绍：借助双端队列Deque即可。
     *          首先将根节点放入队列中，终止条件队列为空；
     *          从队列头部取节点，同时将该节点的左、右子节点分别从队列后端添加进去。
     */
    public ArrayList<Integer> traversalByLevel(TreeNode root) { //PrintFromTopToBottom
        ArrayList<Integer> list = new ArrayList<Integer>();
        if(root == null) return list;
        ArrayDeque<TreeNode> deque = new ArrayDeque<TreeNode>();
        deque.addLast(root);
        while(!deque.isEmpty()){//队列为空表示结束
            TreeNode pollNode = deque.pollFirst();
            list.add(pollNode.val);
            if(pollNode.left!=null) deque.addLast(pollNode.left);
            if(pollNode.right!=null) deque.addLast(pollNode.right);
        }
        return list;
    }

    /***
     * 按层遍历：每一层放到一个List中，返回List<List>
     * 思路分析：利用双端队列Deque解决。
     *          增加两个统计数，一个是currentLevelCount一个是nextLevelCount；
     *          用于标识当前层的范围，其他思路与按层遍历整个二叉树类似。
     */
    public ArrayList<ArrayList<Integer>> traversalByEveryLevel(TreeNode root){
        ArrayList<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>();
        if(root == null) return lists;
        ArrayList<Integer> list = new ArrayList<Integer>();
        int currentLevelCount = 1,nextLevelCount = 0;
        ArrayDeque<TreeNode> deque = new ArrayDeque<TreeNode>();
        deque.addLast(root);
        while(!deque.isEmpty()){//一次循环只能取出一个元素
            TreeNode pollNode = deque.pollFirst();
            currentLevelCount--;
            list.add(pollNode.val);
            if(pollNode.left!= null){
                deque.addLast(pollNode.left);
                nextLevelCount++;
            }
            if(pollNode.right!= null){
                deque.addLast(pollNode.right);
                nextLevelCount++;
            }
            if(currentLevelCount == 0){//当前层没有了
                lists.add(list);
                if(!deque.isEmpty()){
                    list = new ArrayList<Integer>();
                    currentLevelCount = nextLevelCount;
                    nextLevelCount = 0;
                }
            }
        }
        return lists;
    }

    /**
     * 题目：LeetCode_107_BinaryTreeLevelOrderTraversal_II
     * 改进：从下往上按层遍历二叉树；首先打印最后一层，最后打印第一层。也就是要求List<List>的第一子List为最后一层。
     * 递归方法解决：首先获取二叉树的深度，初始化lists结果集合；
     *              然后在递归的时候传入当前层的层数，利用层数标识往lists的第几个list中添加元素；
     *              按照前序遍历的顺序递归，递归终止条件是node==null。
     */
    public List<List<Integer>> binaryTreeLevelOrderTraversal_II(TreeNode root){
        int maxDepth = getMaxDepth(root);//获取二叉树深度
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        for(int i = 0;i < maxDepth;i++)  lists.add(new ArrayList<Integer>());//初始化lists
        perLevelTraversal(root,1,lists,maxDepth);
        return lists;
    }
    //递归方法
    public void perLevelTraversal(TreeNode root,int level,List<List<Integer>> lists,int depth){
        if(root == null) return;
        lists.get(depth-level).add(root.val);
        perLevelTraversal(root.left,level+1,lists,depth);
        perLevelTraversal(root.right,level+1,lists,depth);
    }
    public int getMaxDepth(TreeNode root){
    //获取二叉树深度
        if(root == null) return 0;
        return Math.max(getMaxDepth(root.left),getMaxDepth(root.right))+1;
    }

    /********************按照锯齿形或之字形遍历打印二叉树**********************/
    /**
     * 题目介绍：
     *         按照锯齿形或之字形遍历打印二叉树
     * 思路分析：利用两个栈，一个存储当前层的节点引用，另一个存储下一层的节点引用。
     *          奇数层时先存左节点，再存右节点；偶数层时先存右节点，再存左节点。
     *          因此设置一个flag作为0或1的标识；0与1交换：flag=(flag+1)&1;
     */
    public ArrayList<ArrayList<Integer>> traversalByZigZagLevel(TreeNode root){//锯齿形
        ArrayList<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>();
        if(root == null) return lists;
        ArrayDeque<TreeNode>[] stacks = new ArrayDeque[2];
        stacks[0] = new ArrayDeque<TreeNode>();
        stacks[1] = new ArrayDeque<TreeNode>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        int flag = 1;
        stacks[flag].push(root);
//        while(!stacks[0].isEmpty()||!stacks[1].isEmpty()){//都空了才结束；只要有一个没空就继续
        while(!stacks[flag].isEmpty()){//只要当前要取元素的栈非空即可，若空了在循环体结束部分更换flag
            TreeNode popNode = stacks[flag].pop();
            list.add(popNode.val);
            if(flag == 1) {
                if (popNode.left != null) stacks[0].push(popNode.left);
                if (popNode.right != null) stacks[0].push(popNode.right);
            }else{
                if (popNode.right != null) stacks[1].push(popNode.right);
                if (popNode.left != null) stacks[1].push(popNode.left);
            }
            if(stacks[flag].isEmpty()){
                flag = (flag+1)&1;//0变成1，1变成0
                lists.add(list);
                if(!stacks[flag].isEmpty()) list = new ArrayList<Integer>();
            }
        }
        return lists;
    }

}
