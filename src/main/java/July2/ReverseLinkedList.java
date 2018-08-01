package July2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class ReverseLinkedList {
       public static NodeList reverseLinkedList(NodeList head){
        NodeList prevNode = null;
        while(head != null){
            NodeList nextNodeTemp = head.next;
            head.next = prevNode;
            prevNode = head;
            head = nextNodeTemp;
        }
        return  prevNode;
    }
    public static NodeList reverseLinkedList3(NodeList head){
        Deque<NodeList> deque = new ArrayDeque<>();
        while(head != null){
            deque.push(head);
            head = head.next;
        }
        NodeList preNode = deque.pop();
        head = preNode;
        while(preNode != null){
            NodeList node = null;
            if(deque.size() > 0)
                node = deque.pop();
            preNode.next = node;
            preNode = node;
        }
        return  head;
    }
    public static NodeList reverseLinkedList2(NodeList head){
           ArrayList<Integer> arr = new ArrayList<Integer>();
           NodeList temp = head;
            while(temp != null){
                arr.add(temp.val);
                temp = temp.next;
            }
            int len = arr.size();
            temp = head;
            while(len > 0){
                temp.val = arr.get(--len);
                temp = temp.next;
            }
            return  head;
    }
}
