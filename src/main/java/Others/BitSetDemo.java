package Others;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;

public class BitSetDemo {
    public static void main(String[] args) {
        BitSet bitset = new BitSet();
        bitset.set(0, false);
        System.out.println(bitset.get(0));

//HashMap
        Hashtable<String, String> h = new Hashtable<String, String>();
//		h.put("aaa", null);
        HashMap<String,String>  map = new HashMap<String,String>();
        map.put(null,null);

        System.out.println(map.get(null));
        //ConcurrentHashMap
        ConcurrentHashMap mmm = new ConcurrentHashMap();

    }
}
