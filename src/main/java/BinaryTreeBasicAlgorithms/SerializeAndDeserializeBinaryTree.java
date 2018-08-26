package BinaryTreeBasicAlgorithms;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

public class SerializeAndDeserializeBinaryTree {

    /**
     * 二叉树的数据结构
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
    public void test(){
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        node1.left = node2;
        node1.right = node3;
        node3.right = node4;
        node4.right = node5;
        String result = Serialize(node1);
        String result2 = Serialize2(node1);
        System.out.println("result = " + result);
        System.out.println("result2= " + result2);

        TreeNode root = Deserialize(result);
    }

    /**************************序列化递归实现********************************************/
    /**
     * 序列化递归实现
     */
    String Serialize(TreeNode root) {//前序遍历
        StringBuilder sb = new StringBuilder();
        helper(root,sb);
        return sb.toString().substring(0,sb.length()-1);
    }
    public void helper(TreeNode root,StringBuilder sb){
        if(root == null) {//递归终止条件
            sb.append("$").append(",");
            return;
        }
        //前序遍历递归实现
        sb.append(root.val).append(",");
        helper(root.left,sb);
        helper(root.right,sb);
    }

    /*****************************结束*****************************************/


    /*****************************序列化非递归实现*****************************************/
    /**
     * 栈和while循环实现
     */
    String Serialize2(TreeNode root) {//前序遍历
        if(root == null) return "";
        StringBuilder sb = new StringBuilder();
        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
        while(!stack.isEmpty()||root != null){
            if(root != null){
                stack.push(root);
                sb.append(root.val).append(",");
                root = root.left;
            }else{
                sb.append("$").append(",");
                root = stack.pop().right;
            }
        }
        sb.append("$").append(",");
        return sb.toString().substring(0,sb.length()-1);
    }
    /*****************************结束****************************************/


    /*****************************反序列化***************************************/
    TreeNode Deserialize(String str) {
        if(str == null||str.length() == 0) return null;
        String[] strs = str.split(",");
        int[] index = new int[]{-1};
        return deHelper(strs,index);
    }

    TreeNode deHelper(String[] strs,int[] index){
        ++index[0];
//        if( index[0] >= strs.length) return null;//没有必要判断，递归的次数完全可以由$的出现返回
        if(!"$".equals(strs[index[0]])){//不要使用!= 判断字符串相等或不相等
            /*将整体看做三部分：根节点、左子树和右子树 */
            TreeNode root = new TreeNode(Integer.valueOf(strs[index[0]]));
            root.left = deHelper(strs,index);
            root.right = deHelper(strs,index) ;
            return root;
        }
        return null;
    }

    /*****************************结束***************************************/



}
