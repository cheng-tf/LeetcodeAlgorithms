package HashTableAlgorithms;

import org.junit.Test;

public class Leetcode_76_MinimumWindowSubstring_Hard {
    /*******************Leetcode_76_MinimumWindowSubstring_Hard************/
    /**
     * 难度：Hard
     * https://leetcode.com/problems/minimum-window-substring/description/
     * Given a string S and a string T, find the minimum window in S
     * which will contain all the characters in T in complexity O(n).
     * Example:
     * Input: S = "ADOBECODEBANC", T = "ABC"
     * Output: "BANC"
     * Note:
     * If there is no such window in S that covers all characters in T,
     * return the empty string "".
     * If there is such window, you are guaranteed that there will always
     * be only one unique minimum window in S.
     *
     * 思路分析：双int[]字符哈希表和双指针最小窗口。需要两个int[]对S和T进行字符次数统计。
     *          首先统计T字符串的字符数目；然后利用双指针构造滑动窗口；
     *          right右移一个字符，首先hashMap_S对该字符数目加1，然后
     *          尽可能地移动left：通过while实现，移动条件有两个：hashMap_T中不存在left字符
     *          或者hashMap_S中该字符数目大于hashMap_T；
     *          然后再判断当前窗口是否满足要求且是否比上次更短，若是，则更新，否则continue。
     */

 public String minWindow(String s, String t) {
        if(s == null||t==null||s.length()==0||t.length() == 0||s.length() < t.length()) return "";
        int[] hashMap_T = new int[128];
        int[] hashMap_S = new int[128];
        char[] chars_t = t.toCharArray();
        for(char c:chars_t) hashMap_T[c]++;//对T字符串进行hash统计计算
        char[] chars_s = s.toCharArray();
        int left = 0,right = 0;//双指针，表示窗口的左和右端点
        int windowLen = chars_s.length;
        String result = "";
        for(;right < chars_s.length;right++){//遍历S
            hashMap_S[chars_s[right]]++;
            //尽可能让left向右移动
            //两种情况移动：left字符不在T中和left字符出现次数大于T中该字符次数
            while((left < right) && (hashMap_T[chars_s[left]] == 0|| hashMap_S[chars_s[left]]>hashMap_T[chars_s[left]])){
                hashMap_S[chars_s[left]]--;
                left++;
            }
            int currentLen = right-left+1;
            if(currentLen < chars_t.length) continue;
            if(windowLen >= currentLen && doesScoversT(hashMap_S,hashMap_T)){//一定包括等号，因为若S与T相等情况
                windowLen = currentLen;
                result = new String(chars_s,left,right-left+1);//保存
            }
        }
        return result;
    }
    /**
     * 判断此时窗口是否已经包含T的所有字符
     */
    public boolean doesScoversT(int[] hashMap_S,int[] hashMap_T){
        for(int key = 0;key < hashMap_T.length;key++)
            if(hashMap_T[key] > hashMap_S[key])
                return false;
        return true;
    }

/**
 * 不需要使用HashMap，使用int[]即可。
 */
/*    public String minWindow1(String s, String t) {
        if(s == null||t==null||s.length()==0||t.length() == 0||s.length() < t.length()) return "";
        HashMap<Character,Integer> hashMap_T = new HashMap<Character, Integer>();
        HashMap<Character,Integer> hashMap_S = new HashMap<Character, Integer>();
        char[] chars_t = t.toCharArray();
        for(char c:chars_t) {//对T字符串进行hash统计计算
            if(hashMap_T.containsKey(c))
                hashMap_T.put(c,hashMap_T.get(c)+1);
            else
                hashMap_T.put(c,1);
        }
        char[] chars_s = s.toCharArray();
        int left = 0,right = 0;//双指针，表示窗口的左和右端点
        int windowLen = chars_s.length;
        String result = "";
        for(;right < chars_s.length;right++){
            if(hashMap_S.containsKey(chars_s[right]))
                hashMap_S.put(chars_s[right],hashMap_S.get(chars_s[right])+1);
            else
                hashMap_S.put(chars_s[right],1);
            //尽可能让left向右移动
            //两种情况移动：left字符不在T中和left字符出现次数大于T中该字符次数
            while((left < right) && (hashMap_T.get(chars_s[left]) == null||hashMap_S.get(chars_s[left])>hashMap_T.get(chars_s[left]))){
                hashMap_S.put(chars_s[left],hashMap_S.get(chars_s[left])-1);
                left++;
            }
            int currentLen = right-left+1;
            if(currentLen < chars_t.length) continue;
            if(doesScoversT(hashMap_S,hashMap_T)&& windowLen >= currentLen){//一定包括等号，因为若S与T相等情况
                windowLen = currentLen;
                result = new String(chars_s,left,right-left+1);//保存
            }
        }
        return result;
    }

    *//**
     * 判断此时窗口是否已经包含T的所有字符
     *//*
    public boolean doesScoversT(HashMap<Character,Integer> hashMap_S,HashMap<Character,Integer> hashMap_T){
        for(char key : hashMap_T.keySet())
            if(!hashMap_S.containsKey(key)||hashMap_T.get(key)>hashMap_S.get(key))
                return false;
        return true;
    }*/

    //测试
    @Test
    public void test(){
        String s = "ADOBECODEBANC";
        String t = "ABC";
        String result = minWindow(s,t);
        System.out.println("result = " + result);
        String s1 = "a";
        String t1 = "a";
        String result2 = minWindow(s1,t1);
        System.out.println("result2 = " + result2);
    }
}
