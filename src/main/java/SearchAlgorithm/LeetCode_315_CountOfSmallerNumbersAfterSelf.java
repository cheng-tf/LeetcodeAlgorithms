package SearchAlgorithm;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_315_CountOfSmallerNumbersAfterSelf {
    /**
     * 定义一个带有count属性的二叉树结构
     */
    class BSTNode{
        BSTNode left;
        BSTNode right;
        int value;
        int count;
        public BSTNode(int value){
            this.value = value;
        }
    }

    public void BST_insert(BSTNode node,BSTNode insert_node,int count_smaller){
        if(insert_node.value <= node.value){
            node.count++;
            if(node.left != null){
                BST_insert(node.left,insert_node,count_smaller);
            }else{
                node.left = insert_node;
            }
        }else{
            count_smaller += node.count + 1;
            if(node.right != null){
                BST_insert(node.right,insert_node,count_smaller);
            }else{
                node.right = insert_node;
            }
        }
    }

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> counts = new ArrayList<Integer>();
        List<BSTNode> nodeList = new ArrayList<BSTNode>();
        List<Integer> results = new ArrayList<Integer>();
        for(int i = nums.length;i>= 0;i--){
            nodeList.add(new BSTNode(nums[i]));
        }
        counts.add(0);//第一个节点count_smaller为0
        for(int i = 1;i < nodeList.size();i++){
            int count_smaller = 0;
            BST_insert(nodeList.get(0),nodeList.get(i),count_smaller);
            counts.add(count_smaller);
        }
        for(int i = nodeList.size() - 1;i >= 0;i--){
            results.add(counts.get(i));
        }
        return results;
    }


}
