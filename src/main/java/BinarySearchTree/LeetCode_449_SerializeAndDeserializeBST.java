package BinarySearchTree;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class LeetCode_449_SerializeAndDeserializeBST {


      //Definition for a binary tree node.
      public class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) {
              val = x;
          }
      }
      /*
      public class Codec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {

    }
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {

    }
}
       */
    @Test
    public void test() {
        int[] nums = {2, 3, 4, 5, -1, 34, 8, 2, 1, 4};
        List<TreeNode> nodeList = new ArrayList<TreeNode>();
        for (int i = 0; i < nums.length; i++) {//创建节点
            nodeList.add(new TreeNode(nums[i]));
        }
        for (int i = 1; i < nodeList.size(); i++) {//构建二叉查找树
            BST_insert(nodeList.get(0), nodeList.get(i));
        }
        TreeNode root = nodeList.get(0);
        String serializeStr = serialize(root);
        System.out.println("序列化："+serializeStr);
        TreeNode rootDe = deserialize(serializeStr);

    }

    /**
     * Encodes a tree to a single string.
     * 利用前序遍历将二叉查找树的值拼接成字符串
     * 注意：根节点为null时返回空字符串
     */
    public String serialize(TreeNode root) {
        if(root == null) return "";
        StringBuilder sb = new StringBuilder();
        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
        while(!(stack.isEmpty()&&root == null)){
            if(root != null){
                stack.push(root);
                sb.append(root.val+"#");
                root = root.left;
            }else{
                root = stack.pop().right;
            }
        }
        return sb.toString().substring(0,sb.length()-1);
    }

    /**
     * Decodes your encoded data to tree.
     * 利用二叉查找树的节点插入方法重新构建二叉查找树
     * 经过验证：按照前序遍历的顺序回复二叉查找树与原来的二叉查找树是一致的
     * 中序遍历与后序遍历的顺序肯定不行，因为第一个节点就不是原来的根节点root
     * 注意：空字符情况对应的是二叉查找树为空，返回null即可
     */
    public TreeNode deserialize(String data) {
        if(data == null|| data.length() == 0) return null;//对空字符串处理
        String[] values = data.split("#");
        List<TreeNode> nodes = new ArrayList<TreeNode>();
        for(String value:values){
            nodes.add(new TreeNode(Integer.parseInt(value)));//转换成节点
        }
        for(int i = 1;i< nodes.size();i++){//重建二叉查找树(按照前序遍历获取的顺序逐个插入，可以恢复(经过实践证明))
            BST_insert(nodes.get(0),nodes.get(i));
        }
        return nodes.get(0);
    }

    /**
     * 向以root为根节点的二叉查找树中插入节点insertNode
     */
    public void BST_insert(TreeNode root, TreeNode insertNode){
        if(insertNode.val <= root.val){
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

}
