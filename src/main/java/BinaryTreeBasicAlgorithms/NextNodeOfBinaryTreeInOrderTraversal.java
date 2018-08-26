package BinaryTreeBasicAlgorithms;

public class NextNodeOfBinaryTreeInOrderTraversal {

    public class TreeLinkNode {
        int val;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        TreeLinkNode next = null;//指向父节点

        TreeLinkNode(int val) {
            this.val = val;
        }
    }

    /**
     * 获取二叉树的下一个节点
     */
    public TreeLinkNode getNext(TreeLinkNode pNode){
        //分成三种情况：当前节点①为null;②有右子树;③无右子树，再根据左子节点和右子节点分成两类。
        if(pNode == null) return null;
        if(pNode.right != null){//有右子树
            pNode = pNode.right;
            while(pNode.left != null){//寻找右子树的最左节点
                pNode = pNode.left;
            }
            return pNode;
        }else{//没有右子树
            TreeLinkNode parent = pNode.next;//父节点
            if(parent == null) return null;//特殊情况:只有一个根节点
            if(pNode == parent.left){//当前节点为左子节点
                return parent;
            }else{//当前节点为右子节点
                //中序遍历的最后一个元素，会出现parent.next 为null，需要返回null
                while(parent.next != null && parent != parent.next.left){
                    parent = parent.next;
                }
                return parent.next;
            }
        }
    }

}
