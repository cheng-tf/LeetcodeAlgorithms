package LRUCacheImpl;

import org.junit.Test;

import java.util.HashMap;

public class Leetcode_146_LRUCache_Hard {
    /**********************Leetcode_146_LRUCache_Hard**************************/
    /**
     * 题目难度：Hard
     * https://leetcode.com/problems/lru-cache/description/
     * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.
     * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
     * put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
     * Follow up:
     * Could you do both operations in O(1) time complexity?
     * Example:
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
     *
     * 思路分析：利用HashMap+双端链表实现；可以利用HashMap+LinkedList实现。
     *           HashMap中存放key和链表节点的引用，双端链表将节点连在一起。
     *           对于双端链表需要定义两个端点节点head和tail，因为在容量满的时候，
     *           需要直接删除最后一节点，在get到或put到某节点，需要把该节点调整到
     *           最前端，必须定义head和tail，便于操作。
     *           构造方法：设定容量，HashMap、head、tail。
     *           对于get操作：没有找到该key，直接返回-1，若存在key，
     *           需要将该节点移动到双端队列的头节点：包括两步：删除当前节点，并在头节点插入。
     *           对于put操作：若存在，则修改，并删除当前节点移动到头节点；
     *                        若不存在，则直接添加到头节点。
     *
     *    易错点：deleteTailNode中需要从hashMap中删除<key,Node>;
     *    deleteCurrentNode不需要从hashMap中删除。
     */

    class LRUCache {
        
        //双向链表数据结构
        class DListNode {
            int key;
            int val;
            DListNode prev;
            DListNode next;
            DListNode(int key, int val, DListNode prev, DListNode next) {
                this.key = key;
                this.val = val;
                this.prev = prev;
                this.next = next;
            }
        }
        
        int capacity;//缓存队列容量
        DListNode head;
        DListNode tail;
        HashMap<Integer,DListNode> hashMap;

        //构造方法
        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.hashMap = new HashMap<Integer, DListNode>();
            //初始化链表
            head = new DListNode(0, 0, null, null);
            tail = new DListNode(0, 0, null, null);
            head.next = tail;
            tail.prev = head;
        }
        //get方法
        public int get(int key) {
            if (hashMap.get(key) == null) return -1;//若不存在，返回-1
            DListNode node = hashMap.get(key);
            deleteCurrentNode(node);//删除当前节点
            moveToHead(node);//移动到头节点
            return node.val;
        }
        //put方法
        public void put(int key, int value) {
            if (hashMap.size() == this.capacity && hashMap.get(key) == null)//达到容量了
                deleteTailNode();
            DListNode node = hashMap.get(key);//从hashMap中获取节点引用
            if (node != null){//若已经存在，更新并删除当前节点
                node.val = value;
                //删除当前节点
                deleteCurrentNode(node);
            }else{//创建新的节点
                node = new DListNode(key, value, null, null);
                hashMap.put(key,node);
            }
            moveToHead(node);//插入到头部
        }

        /************************辅助方法*******************************/
        //从双端队列中删除当前节点
        public void deleteCurrentNode(DListNode node){
            //删除当前节点
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        //删除尾节点，并从hashMap中删除该节点
        public void deleteTailNode(){
            //删除尾节点
            DListNode delete = tail.prev;
            hashMap.remove(delete.key);//从hashMap中删除该节点
            delete.prev.next = tail;
            tail.prev = delete.prev;
        }
        //调整到头部
        public void moveToHead(DListNode node){
            head.next.prev = node;
            node.next = head.next;
            head.next = node;
            node.prev = head;
        }
        /***********************************结束*************************************/
        //测试使用打印方法
        public void printLinkedList() {
            DListNode node = head;
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
    public void test1() {
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
