package BinaryTreeBasicAlgorithms;

import org.junit.Test;

import java.util.Scanner;

public class JianzhiOffer_ReConstructBinaryTree {

    /**
     * 第一题：根据前序遍历和中序遍历重构二叉树。
     * 第二题：<变式>根据前序遍历和中序遍历输出求和树的中序遍历。
     */
//      Definition for binary tree
      public class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
      }

    /**
     * 第一题：根据前序遍历和中序遍历重构二叉树。
     * 解题思路：递归方法，不断获取当前节点的左节点和右节点。
     */
    /**
     * 递归调用该方法
     */
    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        if(pre == null||in == null) return null;//防止pre或in为null
        return reConstructBinaryTree(pre,0,pre.length-1,in,0,in.length-1);
        }
    /**
     * 递归方法
     */
    public TreeNode reConstructBinaryTree(int[] pre,int preStart,int preEnd,int[] in,int inStart,int inEnd){
        if(preStart> preEnd||inStart > inEnd || preEnd-preStart != inEnd-inStart) return null;
        TreeNode root = new TreeNode(pre[preStart]);
        int rootIndex = inStart;
        //在中序遍历结果中寻找当前子树的根节点索引
        while(rootIndex <= inEnd && in[rootIndex] != pre[preStart]) rootIndex++;
        //递归调用
        int len = rootIndex - inStart;
        root.left = reConstructBinaryTree(pre,preStart+1,preStart+len,in,inStart,rootIndex-1);
        root.right = reConstructBinaryTree(pre,preStart+len+1,preEnd,in,rootIndex + 1,inEnd);
        return root;
    }

    /**
        //递归终止条件
     * 题目介绍：2018-09-26 快手笔试算法题第2题
     * 求和树：当前节点的值为原二叉树的左右子树的和。
     * 如：
     * 输入：
     *        10
     *  8   -4  7   5
     *    -2     6
     *  8   -4  7   5
     *  对应的求和树为
     *        20
     *     4     12
     *  0    0  0   0
     * 输入两行数据：第一行为二叉树的前序遍历，第二行是二叉树的中序遍历，中间空格隔开；
     *               输出该二叉树对应的求和树的中序遍历，以空格分隔。
     *
     * 测试样例1：对应上面的求和树展示。
     * 输入：
     * 10 -2 8 -4 6 7 5
     * 8 -2 -4 10 7 6 5
     * 输出：
     * 0 4 0 20 0 12 0
     * 测试样例2：
     * 输入：
     * 1 2 4 5 3 6 7
     * 4 2 5 1 6 3 7
     * 输出：
     * 0 9 0 27 0 13 0
     *
     *    1
     *  2   3
     * 4 5 6 7
     * 求和树
     *     27
     *   9   13
     * 0 0  0  0
     *
     * 思路1：
     *      S1：首先根据剑指Offer的算法，根据前序遍历和中序遍历重构二叉树；
     *      S2：然后，利用递归的方式得到求和树：
     *          递归方法返回值为 当前节点更改后的值与原来值的和；
     *          递归终止条件：左右子节点都为null，当前节点值置为0，返回该节点原来的值；
     *                       若左右节点之一为null，则递归调用递归方法，该递归方法返回值即为当前节点的新值，
     *                       并向上层返回新值与旧值之和；
     *                       若左右节点都不为null，则新值为递归左右节点之和，返回新值与旧值。
     *      S3：按照中序遍历打印输出得到的求和树。
     *
     * 方法2：不重构成二叉树，直接根据序列化序列得到求和树的中序遍历。
     *        核心思想和方法1类似。
     *        利用递归方法：逐个更改in数组，递归方法返回值为当前节点的旧值与新值之和。
     */

    /**
     * 方法1
     */
    public String getSumBinaryTreeInTraversal(int[] nums1,int[] nums2){
        getBinaryTreeValue(nums1,0,nums1.length-1,nums2,0,nums2.length-1);
        StringBuilder sb = new StringBuilder();
        for (int num : nums2)
            sb.append(num).append(" ");
        if(sb.length() >= 2)
            sb.delete(sb.length()-1,sb.length());
        return sb.toString();
    }
    /**
     * 递归方法：基于重构二叉树的改造
     */
    public  int getBinaryTreeValue(int[] pre,int preStart,int preEnd,int[] in,int inStart,int inEnd){
        //递归终止条件
        if(preStart > preEnd||inStart > inEnd || preEnd-preStart != inEnd-inStart)
            return 0;
        int rootIndex = inStart;
        //在中序遍历结果中寻找当前子树的根节点索引
        while(rootIndex <= inEnd && in[rootIndex] != pre[preStart])
            rootIndex++;
        //递归调用
        int len = rootIndex - inStart;
        int left = getBinaryTreeValue(pre,preStart+1,preStart+len,in,inStart,rootIndex-1);
        int right = getBinaryTreeValue(pre,preStart+len+1,preEnd,in,rootIndex + 1,inEnd);
        int oldValue = in[rootIndex];
        in[rootIndex] = left + right;
        return oldValue + in[rootIndex];
    }

    /**
     * 方法2
     */
    //方法1：重构二叉树
    public String getSumBinaryTreeInTraversal_2(int[] nums1,int[] nums2){
        //重构二叉树
        TreeNode root = reConstructBinaryTree(nums1,0,nums1.length-1,nums2,0, nums2.length-1);
        root = getSumBinaryTree(root);//得到求和树
        StringBuilder sb = new StringBuilder();
        print(root,sb);
        if(sb.length() >= 2){
            sb.delete(sb.length()-1,sb.length());
        }
        return sb.toString();
    }

    /**
     * 对原二叉树进行改造，得到求和树
     */
    public  TreeNode getSumBinaryTree(TreeNode root){
        TreeNode root2 = root;
        if(root == null) return null;
        recursive(root);
        return root;
    }
    /**
     * 递归方法得求和树
     */
    public  int recursive(TreeNode root){
        int originalValue = root.val;
        if(root.left == null&&root.right == null){
            root.val = 0;
            return originalValue + 0;
        }else if(root.left == null){
            root.val = recursive(root.right);
            return originalValue + root.val;
        }else if(root.right == null){
            root.val = recursive(root.left);
            return originalValue + root.val;
        }else{
            root.val = recursive(root.left)+recursive(root.right);
            return  originalValue + root.val;
        }
    }
    /**
     * 打印二叉树的中序遍历
     */
    public  void print(TreeNode root,StringBuilder sb){
        if(root == null) return ;
        print(root.left,sb);
        sb.append(root.val).append(" ");
        print(root.right,sb);
    }

    @Test
    public void test() {
//        int[][] nums = getInputNums();
        //nums[0]第一行
        //nums[1]第二行
//        int[] pre = {10,-2,8,-4,6,7,5};
//        int[] in = {8,-2,-4,10,7,6,5};
        int[] pre = {1,2,4,5,3,6,7};
        int[] in = {4,2,5,1,6,3,7};
        //答案为 0 4 0 20 0 12 0
        //方法2必须先执行，因为方法1会修改in数组
        String result2 = getSumBinaryTreeInTraversal_2(pre,in);
        //方法1
        String result1 = getSumBinaryTreeInTraversal(pre,in);
        System.out.println("result1 = " + result1);
        System.out.println("result2 = " + result2);
    }

    /**
     * 通过命令行输入，转换成int数组
     */
    public int[][] getInputNums(){
        Scanner scanner = new Scanner(System.in);
        String numStr1 = scanner.nextLine();
        String numStr2 = scanner.nextLine();
        String[] numsStr1 = numStr1.split(" ");
        String[] numsStr2 = numStr2.split(" ");
        int num = numsStr1.length;
        int num2 = numsStr2.length;
        if(num != num2)
            System.out.println("");
        int[] nums1 = new int[num];
        int[] nums2 = new int[num2];
        for(int i = 0;i < num;i++){
            nums1[i] = Integer.parseInt(numsStr1[i]);
            nums2[i] = Integer.parseInt(numsStr2[i]);
        }
        int[][] nums = {nums1,nums2};
        return nums;
    }

}
