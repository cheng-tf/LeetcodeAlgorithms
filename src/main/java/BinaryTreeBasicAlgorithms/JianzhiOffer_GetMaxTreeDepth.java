package BinaryTreeBasicAlgorithms;

import BinaryTreeBasicAlgorithms.MyBinaryTree.TreeNode;
import org.junit.Test;

public class JianzhiOffer_GetMaxTreeDepth {

        @Test
        public void test(){
            TreeNode root = MyBinaryTree.getMyBinaryTree();
            int maxDepth = TreeDepth(root);
            System.out.println(maxDepth);
        }

        public int TreeDepth(TreeNode root) {
            //递归终止条件
            if(root == null) return 0;
            //递归调用
            return Math.max(TreeDepth(root.left),TreeDepth(root.right))+1;
        }
    }

