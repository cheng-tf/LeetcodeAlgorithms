package LRUCacheImpl;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;

public class Leetcode_146_LRUCache_Hard {
    /************************************************/
    /**
     * 题目难度：Hard
     * https://leetcode.com/problems/lru-cache/description/
     * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.
     * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
     * put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
     * <p>
     * Follow up:
     * Could you do both operations in O(1) time complexity?
     * <p>
     * Example:
     * <p>
     * LRUCache cache = new LRUCache( 2 );//capacity
     * cache.put(1,1);
     * cache.put(2,2);
     * cache.get(1);       // returns 1
     * cache.put(3,3);    // evicts key 2
     * cache.get(2);       // returns -1 (not found)
     * cache.put(4,4);    // evicts key 1
     * cache.get(1);       // returns -1 (not found)
     * cache.get(3);       // returns 3
     * cache.get(4);       // returns 4
     * <p>
     * 思路分析：若不存在，则返回-1；
     */

    class LRUCache {
        //链表数据结构
        class ListNode {
            int key;
            int val;
            ListNode prev;
            ListNode next;
            ListNode(int key, int val, ListNode prev, ListNode next) {
                this.key = key;
                this.val = val;
                this.prev = prev;
                this.next = next;
            }
        }

        int capacity;
        ListNode guard_head;
        ListNode guard_tail;
        HashMap<Integer, ListNode> hashMap = new HashMap<Integer, ListNode>();

        //构造方法
        public LRUCache(int capacity) {
            this.capacity = capacity;
            //初始化链表
            guard_head = new ListNode(0, 0, null, null);
            guard_tail = new ListNode(0, 0, null, null);
            guard_head.next = guard_tail;
            guard_tail.prev = guard_head;
        }

        public int get(int key) {
            if (hashMap.get(key) == null) return -1;//若不存在，返回-1
            ListNode node = hashMap.get(key);
            //删除当前节点
            node.prev.next = node.next;
            node.next.prev = node.prev;
            //调整到头部
            guard_head.next.prev = node;
            node.next = guard_head.next;
            guard_head.next = node;
            node.prev = guard_head;
            return node.val;
        }

        //添加或修改
        public void put(int key, int value) {
            if (hashMap.size() == this.capacity&&hashMap.get(key) == null) {//达到容量了
                //删除尾节点
                ListNode delete = guard_tail.prev;
                hashMap.remove(delete.key);//从hashMap中删除该节点
                delete.prev.next = guard_tail;
                guard_tail.prev = delete.prev;
            }
            ListNode node = hashMap.get(key);
            if (node != null){//若已经存在，更新
                node.val = value;
                //删除当前节点
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }else{//创建新的节点
                node = new ListNode(key, value, null, null);
                hashMap.put(key,node);
            }
            //插入到头部
            guard_head.next.prev = node;
            node.next = guard_head.next;
            guard_head.next = node;
            node.prev = guard_head;
        }


        /**
         * Your LRUCache object will be instantiated and called as such:
         * LRUCache obj = new LRUCache(capacity);
         * int param_1 = obj.get(key);
         * obj.put(key,value);
         */
        public void printLinkedList() {
            ListNode node = guard_head;
            StringBuilder sb = new StringBuilder();
            while(node != null){
                sb.append("<").append(node.key).append(",").append(node.val).append(">==");
                node = node.next;
            }
            if(sb.length()>1) sb.delete(sb.length()-2,sb.length());
            System.out.println("sb.toString() = " + sb.toString());
        }
    }


    //测试
    @Test
    public void test() {
        LRUCache lru = new LRUCache(2);
        lru.printLinkedList();//打印链表
        int val = lru.get(1);
        System.out.println("val = " + val);
        lru.put(4, 444);
        System.out.println(" lru.put(4, 444);");
        lru.put(3,333);
        System.out.println(" lru.put(3,333);");
        lru.put(6,666);
        System.out.println(" lru.put(6,666);");
        lru.printLinkedList();//打印链表
        System.out.println("lru.get(4) = " + lru.get(4));
        lru.printLinkedList();//打印链表
        System.out.println("lru.get(3) = " + lru.get(3));
        lru.printLinkedList();//打印链表


    }

    @Test
    public void test2() {
        LRUCache lru = new LRUCache(2);
        //[[2],[2,1],[1,1],[2,3],[4,1],[1],[2]]
        System.out.println("lru.get(2) = " + lru.get(2));
        lru.printLinkedList();//打印链表
        lru.put(2,1);
        System.out.println(" lru.put(2, 1);");
        lru.printLinkedList();//打印链表
        lru.put(1,1);
        System.out.println(" lru.put(1,1);");
        lru.printLinkedList();//打印链表
        lru.put(2,3);
        System.out.println(" lru.put(2,3);");
        lru.printLinkedList();//打印链表
        lru.put(4,1);
        System.out.println(" lru.put(4,1);");
        lru.printLinkedList();//打印链表
        System.out.println("lru.get(1) = " + lru.get(1));
        lru.printLinkedList();//打印链表
        System.out.println("lru.get(2) = " + lru.get(2));
        lru.printLinkedList();//打印链表


    }
}
