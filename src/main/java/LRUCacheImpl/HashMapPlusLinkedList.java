package LRUCacheImpl;

import java.util.HashMap;

public class HashMapPlusLinkedList<K,V> {

    //链表数据结构
    public class  ListNode<K,V>{
        public K key;
        public V value;
        public ListNode next;
        public ListNode pre;
    }

    public HashMap<K,ListNode<K,V>> hashMap;


}
