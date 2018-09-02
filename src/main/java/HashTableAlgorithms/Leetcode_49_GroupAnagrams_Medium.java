package HashTableAlgorithms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Leetcode_49_GroupAnagrams_Medium {

    /*****************Leetcode49:GroupAnagrams_同字符词语分组_Medium***************/
    /**
     * 难度：Medium
     * https://leetcode.com/problems/group-anagrams/description/
     * Given an array of strings, group anagrams together.
     * Example:
     * Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
     * Output:
     * [
     *   ["ate","eat","tea"],
     *   ["nat","tan"],
     *   ["bat"]
     * ]
     * Note:
     * All inputs will be in lowercase.
     * The order of your output does not matter.
     *
     * 思路分析：构造哈希表实现。
     *  算法1-第一种映射：获取每个单词时，首先对其进行字典排序，如eat、tea、ate排序后都是aet；
     *         以aet做为key，存放到hashMap中，value为ArrayList的索引；
     *         若hashMap存在该key，则获取索引，向List<List>中添加；
     *         若hashMap中不存在该key，则将索引自动加1设置进去。(有个变量记录索引的增长)
     *  算法2-利用ArrayList作为HashMap的key，同一个ArrayList对应，不同的值对应hash值不同；
     *         对每个单词更新ArrayList内容，记录每个单词出现次数，然后作为HashMap的key；
     *         同样List<List>索引作为value。其余与算法1类似。
     *         注意：不能使用int[]数组当做HashMap的key，因为int[]的hash值只根据int[]地址计算，
     *         不同值情况下，int[]的hash值相同，无法区分开不同单词。
     *
     *         算法1和2区别：算法1利用字典排序后的字符串作为HashMap的key,求的是字符串的哈希；
     *                       算法2利用ArrayList作为HashMap的key，求的是ArrayList的哈希值。
     *         相比之下，算法2效率更高些。
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<List<String>>();
        if(strs == null||strs.length == 0) return result;
        int listIndex = 0;//result中索引
        HashMap<String,Integer> hashMap = new HashMap<String,Integer>();//构建HashMap
        for(String str:strs){
            if(hashMap.containsKey(strSort(str))){//已经存在
                result.get(hashMap.get(strSort(str))).add(str);
            }else{//不存在
                ArrayList<String> list = new ArrayList<String>();
                list.add(str);
                result.add(list);
                hashMap.put(strSort(str),listIndex++);
            }
        }
        return result;
    }
    //辅助方法：排序字符串，如tea==>aet
    private String strSort(String str){
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    /**
     * 算法2：利用ArrayList统计单词中字符出现的次数，并作为HashMap的key。
     */
    public List<List<String>> groupAnagrams2(String[] strs) {
        List<List<String>> result = new ArrayList<List<String>>();
        if(strs == null||strs.length == 0) return result;
        int listIndex = 0;
        HashMap<ArrayList<Integer>,Integer> hashMap = new HashMap<ArrayList<Integer>,Integer>();
        ArrayList<Integer> hashTable = new ArrayList<>(26);//利用ArrayList作为HashMap的key
        for (int i = 0;i < 26;i++) hashTable.add(0);//初始容量26不起作用，需要手动初始化
        for(String str : strs){
            char[] chars = str.toCharArray();
            for(char c:chars)  hashTable.set(c-'a',hashTable.get(c-'a')+1);//一定是set修改，不是add添加
            if(hashMap.containsKey(hashTable)){
                result.get(hashMap.get(hashTable)).add(str);
            }else{
                ArrayList<String> list = new ArrayList<String>();
                list.add(str);
                result.add(list);
                hashMap.put(hashTable,listIndex++);
            }
            for(int i = 0;i < 26;i++) hashTable.set(i,0);//将hashTable清空为0
        }
        return result;
    }
/*
 *//**
     * 算法2：利用int[]做HashMap的key，不行。
     *  原因：HashMap中对int[]数组的hash计算只根据对应地址，同一个int[]，无论存放什么值，
     *         hash值都是一样的。另外，如果每次都创建一个新的int[]，那么每次hash都不一样。
     *//*
    public List<List<String>> groupAnagrams2(String[] strs) {
        List<List<String>> result = new ArrayList<List<String>>();
        if(strs == null||strs.length == 0) return result;
        int listIndex = 0;
        HashMap<int[],Integer> hashMap = new HashMap<int[],Integer>();
        int[] hashTable = new int[26];
        for(String str : strs){
            char[] chars = str.toCharArray();
            for(char c:chars)  hashTable[c-'a']++;
            if(hashMap.containsKey(hashTable)){
                result.get(hashMap.get(hashTable)).add(str);
            }else{
                ArrayList<String> list = new ArrayList<String>();
                list.add(str);
                result.add(list);
                hashMap.put(hashTable,listIndex++);
            }
            for(int i = 0;i < hashTable.length;i++) hashTable[i]=0;//清空为0
        }
        return result;
    }*/

    //测试
    @Test
    public void test(){
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> result = groupAnagrams(strs);
        System.out.println("result = " + result);
        String[] strs2 = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> result2 = groupAnagrams2(strs2);
        System.out.println("result2 = " + result2);
    }
}
