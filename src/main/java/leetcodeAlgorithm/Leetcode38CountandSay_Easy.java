package leetcodeAlgorithm;

import org.junit.Test;

public class Leetcode38CountandSay_Easy {

/**
 * 题目描述：
 * 38. Count and Say
 * The count-and-say sequence is the sequence of integers with the first five terms as following:
 *             1.     1
 *             2.     11
 *             3.     21
 *             4.     1211
 *             5.     111221
 *             1 is read off as "one 1" or 11.
 *             11 is read off as "two 1s" or 21.
 *             21 is read off as "one 2, then one 1" or 1211.
 *     Given an integer n, generate the nth term of the count-and-say sequence.
 *
 *     Note: Each term of the sequence of integers will be represented as a string.
 *     Example 1:  Input: 1   Output: "1"
 *     Example 2:  Input: 4   Output: "1211"
 * 题目理解：以1开始，后续都是对上一个的计数和读，1后面是11,11后面是21,
 *         21后面是1211,1211后面是111221，后面依次类推。
 * 思路分析：显然是一个递归算法题目，要想直接计数读出第n个，需要知道n-1是什么。
 *         递归终止条件就是n==1。
 */
    class Solution {
        public String countAndSay(int n) {
            if(n < 1) return "";
            if(n == 1) return "1";//递归终止条件
            char[] chars = countAndSay(n-1).toCharArray();//这里是递归调用n-1
            StringBuilder sb = new StringBuilder();
            char prev = chars[0];
            int count = 1;
            for(int i = 1;i < chars.length;i++){
                if(chars[i] == prev){
                    count++;
                }else{
                    sb.append(count).append(prev);
                    count = 1;
                    prev = chars[i];
                }
            }
            sb.append(count).append(prev);
            return sb.toString();
        }
    }

    @Test
    public  void solutionTest(){
        Solution solution = new Solution();
        for(int i= 1;i<10;i++)
            System.out.println(solution.countAndSay(i));
        System.out.println(solution.countAndSay(20));
    }

}
