package leetcodeAlgorithm;

import org.junit.Test;

public class Leetcode28ImplementstrStrindexOf_Easy {

//    https://leetcode.com/problems/implement-strstr/description/o
    //根据indexOf的源码
    class Solution {
        public int strStr(String haystack, String needle) {
            if(needle == null || "".equals(needle)) return 0;
            if(haystack == null || "".equals(haystack)) return -1;
            char[] hays = haystack.toCharArray();
            char[] needles = needle.toCharArray();
            int index = 0;
            int max = hays.length-needles.length+1;//优化了循环次数,省去了下面的index + needles.length>hays.length判断
            for(;index < max;index++ ){
                if(hays[index] == needles[0]){
                    int i = 1;
                    // if(index + needles.length>hays.length) return -1;
                    // for(;i < needles.length;i++){
                    //     if(hays[index+i] != needles[i]) break;
                    // }
                    for(;i < needles.length&&hays[index+i] == needles[i];i++);//与上面的等价
                    if(i == needles.length) return index;
                }
            }
            return -1;
        }
    }



    //主要试分析了indexOf的源码
/*    class Solution {
        public int strStr(String haystack, String needle) {
//            needle.toCharArray();//toCharArray()的源码就是把字符串的value字符数组复制一份返回。
            return haystack.indexOf(needle);
        }
    }*/

/*    class Solution {
        public int strStr(String haystack, String needle) {
            if(needle == null || "".equals(needle)) return 0;
            if(haystack == null || "".equals(haystack)) return -1;
            char[] hays = haystack.toCharArray();
            char[] needles = needle.toCharArray();
            int index = 0;
            for(;index < hays.length;index++ ){
                if(hays[index] == needles[0]){
                    int i = 1;
                    if(index + needles.length>hays.length) return -1;
                    for(i = 1;i<needles.length;i++){
                        if(hays[index+i] != needles[i]) break;
                    }
                    if(i == needles.length) return index;
                }
            }
            return -1;
        }
    }*/

@Test
    public void solutionTest(){
    //用于验证源码的判断
//    System.out.println("aaa".indexOf("",4));//3
////    System.out.println("aaa".indexOf("",100));//3

    System.out.println(new Solution().strStr("hello","ll"));//2
    System.out.println(new Solution().strStr("aaaa","d"));//-1
}
}
