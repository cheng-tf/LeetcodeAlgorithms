package leetcodeAlgorithm;

import org.junit.Test;

public class Leetcode_3_LongestSubstringWithoutRepeatingCharacters_Medium {
    /*************Leetcode3:Longest Substring Without Repeating Characters***************/

    /**
     * 题目介绍：
     *  Given a string, find the length of the longest substring without repeating characters.
     * Example 1:
     * Input: "abcabcbb"
     * Output: 3
     * Explanation: The answer is "abc", which the length is 3.
     * Example 2:
     * Input: "bbbbb"
     * Output: 1
     * Explanation: The answer is "b", with the length of 1.
     * Example 3:
     * Input: "pwwkew"
     * Output: 3
     * Explanation: The answer is "wke", with the length of 3.
     *              Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
     *
     * 思路分析：结合哈希表与滑动窗口共同实现。
     *          首先利用两个指针，left和right，一个result记录最大长度；
     *          初始时left和right都指向开头，result=0，然后right向右移动，利用布尔哈希表统计字符在当年窗口是否存在，
     *          布尔哈希表默认为false，若字符第一次出现，则设置true，若某字符第二次出现，先计算当前窗口的长度，
     *          与result比较，若大于result则更新result，然后将left指针向右滑动，滑到该字符第一次出现之后为止，
     *          并将移除窗口的字符设置false； 就是始终保持哈希表的字符对应为true，其他都为false；
     *          不断与result判断，寻找最大长度；
     *          当right指针到达字符串末尾即结束；最后result保存的就是最大无重复字符的长度。
     */

    public int lengthOfLongestSubstring(String str) {
        if(str == null || str.length() == 0) return 0;
        if(str.length() == 1) return 1;
        char[] chars = str.toCharArray();//字符串转成数组
        int left = 0,right = 0,result = 0;//初始化参数
        boolean[] hashTable = new boolean[128];
        while(right < chars.length ){
            if(!hashTable[chars[right]]){//字符第一次出现
                hashTable[chars[right]] = true;
            }else{//第二次出现
                result = (result >= (right - left)) ? result : (right - left);//更新result
                while(chars[left] != chars[right]){
                    hashTable[chars[left]] = false;//将移除窗口的置为false
                    left++;
                }
                left++;
            }
            right++;
        }
        //若最后一字符不是重复的，则需要再次更新result，如"abc"、"abcaefg"
        result = (result >= (right - left)) ? result : (right - left);
        return result;
    }

    //测试
    @Test
    public void test(){
        String[] strs = {"abcabcbb","bbbbb","pwwkew","au","tmmzuxt"};
        for(String str:strs){
            int result = lengthOfLongestSubstring(str);
            System.out.println("str : " + result);
        }
    }

}
