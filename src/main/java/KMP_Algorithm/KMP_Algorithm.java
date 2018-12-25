package KMP_Algorithm;

import org.junit.Test;

public class KMP_Algorithm {

    /**
     * 题目：给定两个字符串str和match，长度分别为N和M。实现一个算法，
     * 如果字符串str中含有含有子串match，则返回match在str中的开始位置，
     * 不含有则返回-1。
     *
     * 普通解法：时间复杂度较高。从每个字符出发，匹配的代价都可能是O(M),
     * 一共N个字符，所以，整体的时间复杂度为O(MN)。
     * 原因分析:普通解法的时间复杂度之所以这么高，是因为每次遍历到一个字符时，
     * 检查工作相当于从无开始，之前的遍历检查不能优化当前的遍历检查。
     *
     * KMP算法
     * 时间复杂度：O(M+N)
     * 空间复杂度：O(M)
     * KMP算法原理：首先构建一个大小等于match长度的数组nextArr,
     * 这个数组元素的含义：nextArr[i]表示在match[i]之前的字符串match[0...i-1]中，
     * 前缀子串与后缀子串最大匹配长度；
     * 其中前缀子串必须以match[0]开头最长以match[i-2]结尾，
     * 后缀子串必须以match[i-1]结尾最长以match[1]开头。
     * 如"aaaab"对应的nextArr[4]=3;
     * 因为前缀子串aaa...，后缀子串...aaa，最大匹配"aaa"长度为3；
     * 如"abc1abc1" 则nextArr[7]=3
     * 前缀子串abc1ab...,后缀子串...bc1abc，最大匹配"abc"长度为3。
     *
     * KMP算法实现的分解：
     *
     * KMP算法的实现就是两个while循环。
     *
     */


    /**
     * 算法实现：分成三步骤
     * S1：构建nextArray数组;
     * S2：
     */

    public int getFirstIndexOf(String str, String match) {
        if (str == null || match == null || str.length() < 1 || str.length() < match.length()) return -1;
        char[] strChars = str.toCharArray();
        char[] matchChars = match.toCharArray();
        int sc_pos = 0, mc_pos = 0;
        int[] nextArray = getNextArray(matchChars);
        //while循环逐渐比较
        while (sc_pos < strChars.length && mc_pos < matchChars.length) {
            if (strChars[sc_pos] == matchChars[mc_pos]) {
                sc_pos++;
                mc_pos++;
            } else if (nextArray[mc_pos] == -1) {//只有第一个才会为-1
                sc_pos++;
            } else {
                mc_pos = nextArray[mc_pos];//在match中继续查找
            }
        }
        return mc_pos == matchChars.length ? sc_pos - matchChars.length : -1;
    }

    /**
     * 获取nextArray
     * 利用while循环构建nextArray数组
     * 时间复杂度: O(2M) --> O(M)
     */
    public int[] getNextArray(char[] match) {
        if (null == match || match.length == 0) {
            return new int[]{};
        } else if (match.length == 1) {
            return new int[]{-1};
        } else if (match.length == 2) {
            return new int[]{-1, 0};
        }
        int[] nextArray = new int[match.length];
        nextArray[0] = -1;
        nextArray[1] = 0;
        int pos = 2, cn = 0;
        //核心：while循环
        while (pos < match.length) {
            if (match[pos - 1] == match[cn]) {
                nextArray[pos++] = ++cn;//在前一个基础上加1即可
            } else if (cn > 0) {
                cn = nextArray[cn];//改变cn为cn位置处的值
            } else {
                nextArray[pos++] = 0;//没有匹配
            }
        }
        return nextArray;
    }

    /**
     * 测试
     */
    @Test
    public void test() {
        String str = "ddd abc the picture of abc";
        String match = "picture";
        int index = getFirstIndexOf(str, match);
        System.out.println("index = " + index);
    }
}
