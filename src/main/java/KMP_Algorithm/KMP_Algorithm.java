package KMP_Algorithm;

import org.junit.Test;

public class KMP_Algorithm {
    /**
     * KMP算法
     * Date：2018-12-25 22:00
     * 题目：给定两个字符串str和match，长度分别为N和M。实现一个算法，
     * 如果字符串str中含有含有子串match，则返回match在str中的开始位置，
     * 不含有则返回-1。
     * <p>
     * 普通解法：时间复杂度较高。从每个字符出发，匹配的代价都可能是O(M),
     * 一共N个字符，所以，整体的时间复杂度为O(MN)。
     * 原因分析:普通解法的时间复杂度之所以这么高，是因为每次遍历到一个字符时，
     * 检查工作相当于从无开始，之前的遍历检查不能优化当前的遍历检查。
     * <p>
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
     * <p>
     * KMP算法实现的分解：
     * S1：构建nextArr数组;<核心：一个while循环实现>
     * S2：逐个字符进行匹配，直到match字符完全匹配(找到)，或str结束(未匹配到).<核心：一个while循环实现>
     * KMP算法的实现就是两个while循环。
     * <p>
     * 仔细描述：
     * S1：构建nextArr数组:<这部分画图比较好理解>
     * 首先初始化,nextArr[0]=-1,nextArr[1]=0; -1标志开始第一个字符的匹配；
     * index从2开始，nextArr[index]在nextArr[index-1]的基础上推算：
     * cn表示nextArr[index-1]的值，即最大匹配的子串的长度；
     * A、 如果str[pos-1]==str[cn],则nextArr[i]=nextArr[i-1]+1;
     * B、 如果不相等，则更新cn，cn=nextArr[cn]; 然后继续比较str[pos-1]==str[cn]；
     * C、 如果cn等于0，则说明没有匹配子串，直接赋值为nextArr[i]=0.
     * <p>
     * 时间复杂度分析：由于只有在B情况下，pos不会自增，但是更新cn，cn的跳跃是比较大的，
     * 其实总体循环次数是不会超过2M的，因此时间复杂度可以认为是O(M)。
     * S2: 已经有了nextArr数组，下面只需要逐个比较字符即可。
     * 当遇到字符不相等的时候，就需要将匹配字符串match右移动，最坏情况下，需要匹配N次，
     * 因此时间复杂度为O(N);
     */

    public int getFirstIndexOf(String str, String match) {
        if (str == null || match == null || str.length() < 1 || str.length() < match.length()) return -1;
        char[] strChars = str.toCharArray();
        char[] matchChars = match.toCharArray();
        int sc_pos = 0, mc_pos = 0;
        int[] nextArr = getNextArr(matchChars);
        //while循环逐渐比较
        while (sc_pos < strChars.length && mc_pos < matchChars.length) {
            if (strChars[sc_pos] == matchChars[mc_pos]) {
                sc_pos++;
                mc_pos++;
            } else if (nextArr[mc_pos] == -1) {//只有第一个才会为-1
                sc_pos++;
            } else {
                mc_pos = nextArr[mc_pos];//在match中继续查找
            }
        }
        return mc_pos == matchChars.length ? sc_pos - matchChars.length : -1;
    }

    /**
     * 获取nextArr
     * 利用while循环构建nextArr数组
     * 时间复杂度: O(2M) --> O(M)
     */
    public int[] getNextArr(char[] match) {
        if (null == match || match.length == 0) {
            return new int[]{};
        } else if (match.length == 1) {
            return new int[]{-1};
        } else if (match.length == 2) {
            return new int[]{-1, 0};
        }
        int[] nextArr = new int[match.length];
        nextArr[0] = -1;
        nextArr[1] = 0;
        int pos = 2, cn = 0;
        //核心：while循环
        while (pos < match.length) {
            if (match[pos - 1] == match[cn]) {
                nextArr[pos++] = ++cn;//在前一个基础上加1即可
            } else if (cn > 0) {
                cn = nextArr[cn];//改变cn为cn位置处的值
            } else {
                nextArr[pos++] = 0;//没有匹配
            }
        }
        return nextArr;
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
