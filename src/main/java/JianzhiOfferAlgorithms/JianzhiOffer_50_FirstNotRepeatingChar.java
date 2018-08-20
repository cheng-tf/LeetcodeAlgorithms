package JianzhiOfferAlgorithms;

import org.junit.Test;

public class JianzhiOffer_50_FirstNotRepeatingChar {

    /***********************第一题：剑指Offer_50_找出字符串中第一个只出现一次的字符的位置************/
    /**
     * 测试
     */
    @Test
    public void test(){
        String str = "abdcdefgdbadADSFKJdgkdsjabddzhkesng";
        int index = FirstNotRepeatingChar(str);
        System.out.println("index = " + index);
    }
    /**
     *在一个字符串(0<=字符串长度<=10000，全部由字母组成)中
     * 找到第一个只出现一次的字符,并返回它的位置, 如果没有则返回 -1（需要区分大小写）.
     *
     *  思路分析：基于哈希表的原理
     *      只有大小写字母，那么只需要一个长度为52的int数组，记录字母出现的次数；
     *      利用字母的ascii码作为统计数组的索引，首先一次遍历是统计出现次数；
     *      第二次遍历需要判断该字母出现次数是否是1，若是1直接返回即可。
     */
    public int FirstNotRepeatingChar(String str) {
        int[] charsCount = new int[52];
        char[] chars = str.toCharArray();
        //遍历，统计字母出现次数
        for(char c:chars){
            int index = (c>'Z')?(c-'a'+26):(c-'A');
            charsCount[index]++;
        }
        //遍历，判断出第一个只出现一次的字符
        for(int i = 0;i < chars.length;i++){
            int index = (chars[i]>'Z')?(chars[i]-'a'+26):(chars[i]-'A');
            if(charsCount[index]==1) return i;
        }
        return -1;
    }

    /***************拓展题目1:****************************/
    /**
     * 定义一个函数，输入两个字符串，从第一个字符串中删除在第二个字符串中出现过的所有字符。
     *
     * 思路介绍：第二个字符串利用类似哈希表存储，然后遍历第一个字符串即可。
     *
     */


    /*************拓展题目2：******************/
    /**
     * 题目描述：删除字符串中所有重复出现的字符。
     *
     *  思路介绍：利用一个类似哈希表的布尔型数组，标识该字符是否出现过。
     *
     */


    /**************拓展题目3：变位词**********************/
    /**
     *
     * 题目描述：请完成一个函数，判断输入的两个字符串是不是互为变位词。
     * 变位词：在英语中，如果两个单词中出现的字母相同，并且每个字母出现的次数
     * 也相同，那么这两个单词互为变位词。(Anagram)
     *
     * 思路分析：利用数组实现的简单哈希表，统计字符串串中每个字符出现的次数。
     * 遍历第一个字符串的时候，哈希表的对应项值加1，接下来，遍历第二个字符串的时候；
     * 哈希表的对应项的值减1。最终哈希表的值都是0，说明两个字符串就互为变位词。
     */




}
