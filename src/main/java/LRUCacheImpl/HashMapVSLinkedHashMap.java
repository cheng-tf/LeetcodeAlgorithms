package LRUCacheImpl;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class HashMapVSLinkedHashMap {
    /**
     * HashMap的元素是无序的；
     * 而LinkedHashMap中的元素是有序的，按照先入先出的顺序。
     */

    //测试
    @Test
    public void test(){
        //HashMap是无序的
        HashMap<Integer,Integer> hashMap = new HashMap<Integer, Integer>();
        hashMap.put(122,1);
        hashMap.put(333,9);
        hashMap.put(21,6);
        hashMap.put(52,10);
        for (Integer key : hashMap.keySet()){
            System.out.println("key = "+key+"; value = " + hashMap.get(key));
        }

        //LinkedHashMap是有序的
        LinkedHashMap<Integer,Integer> linkedHashMap = new LinkedHashMap<Integer, Integer>();
        linkedHashMap.put(122,1);
        linkedHashMap.put(333,9);
        linkedHashMap.put(21,6);
        linkedHashMap.put(52,10);
        for (Integer key : linkedHashMap.keySet()){
            System.out.println("key = "+key+"; value = " + linkedHashMap.get(key));
        }
    }
}
