package LRUCacheImpl;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class LRUCache_BasedOn_LinkedHashMap {

    /**
     * 基于LinkedHashMap实现LRU算法
     * 两点：1、accessOrder必须为true；2. 覆盖removeEldestEntry方法；
     * <p>
     * 注意：LinkedHashMap默认采用的是尾插法：
     * 即新元素放在尾部，满时删除头元素。
     */

    //测试
    @Test
    public void test() {
        final int size = 4;
        LinkedHashMap<Integer, Integer> myLRU = new LinkedHashMap<Integer, Integer>(size, 0.75F, true) {
            @Override
            protected boolean removeEldestEntry(HashMap.Entry<Integer, Integer> eldest) {
                boolean tooBig = size() > size;
                if (tooBig) System.out.println("最近最少使用的key = " + eldest.getKey());
                return tooBig;
            }
        };

        myLRU.put(1, 10);
        System.out.println(myLRU.toString());
        myLRU.put(2, 14);
        System.out.println(myLRU.toString());
        myLRU.put(3, 15);
        System.out.println(myLRU.toString());
        myLRU.put(4, 18);
        System.out.println(myLRU.toString());
        myLRU.put(5, 12);
        System.out.println(myLRU.toString());
        myLRU.put(6, 99);
        System.out.println(myLRU.toString());

        myLRU.get(4);
        System.out.println(myLRU.toString());
        myLRU.get(5);
        System.out.println(myLRU.toString());

    }


}
