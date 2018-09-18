package leetcodeAlgorithm;

import org.junit.Test;

public class Leetcode14LongestCommonPrefix {
    /*
    https://leetcode.com/problems/longest-common-prefix/description/
Write a function to find the longest common prefix string amongst an array of strings.
If there is no common prefix, return an empty string "".

Example 1:
Input: ["flower","flow","flight"]
Output: "fl"
Example 2:
Input: ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.
Note:All given inputs are in lowercase letters a-z.
     */
//最精简最巧妙的方法
    class Solution {
        public String longestCommonPrefix(String[] strs) {
            if(strs == null || strs.length == 0) return "";
//            if(strs.length == 1) return strs[0];
            String pre = strs[0];
            for(int i = 1;i < strs.length;i++){
                while(strs[i].indexOf(pre) != 0){
                    pre = pre.substring(0,pre.length()-1);
                }
            }
            return pre;
        }
    }




    /*class Solution {
        public String longestCommonPrefix(String[] strs) {
            if(strs == null || strs.length == 0) return "";
            List<char[]> list = new ArrayList<>();
            int minLen = strs[0].length();
            for(String str:strs){
                if(minLen > str.length()) minLen = str.length();
                list.add(str.toCharArray());
            }
            for(int i = 0;i < minLen;i++){
                if(!helper(list,i)) return strs[0].substring(0,i);
            }
            return strs[0].substring(0,minLen);
        }
        //判断索引i处的字符是否相等
        private boolean helper(List<char[]> list,int i){
            char a = list.get(0)[i];
            for(char[] chars:list) if (a != chars[i]) return false;
            return true;
        }
    }*/
    @Test
    public void soulutionTest(){
        String[] strs = {"flower","flow","flight"};
        String result = new Solution().longestCommonPrefix(strs);
        System.out.println("result = " + result);
    }

}
