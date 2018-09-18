package StringAlgorithms;

import org.junit.Test;

public class JianzhiOffer_58_ReverseSentence {

    /***********剑指Offer58：翻转单词顺序列************************/

    /***
     * 题目描述
     * 牛客最近来了一个新员工Fish，每天早晨总是会拿着一本英文杂志，写些句子在本子上。
     * 同事Cat对Fish写的内容颇感兴趣，有一天他向Fish借来翻看，但却读不懂它的意思。
     * 例如，“student. a am I”。后来才意识到，这家伙原来把句子单词的顺序翻转了，
     * 正确的句子应该是“I am a student.”。Cat对一一的翻转这些单词顺序可不在行，
     * 你能帮助他么？
     *
     * 思路：首先翻转整个句子，然后再一次翻转每一个单词。
     *       工具方法：翻转start和end范围内的字符。
     *       句子翻转：start=0；end=length-1；
     *       单词翻转：寻找每一个单词是关键。寻找空格，一个空格表示一个单词的结束；
     *       特别注意：最后一个单词是结尾，后面没有空格。
     *
     * 测试： 特殊用例：空字符串；只有一个空格(重要)。
     *
     */

    public String ReverseSentence(String str) {
        if(str == null || str.length()==0) return "";
        if(str.length() == 1) return str;
        char[] chars = str.toCharArray();
        int start = 0,end = chars.length-1;
        reverseChars(chars,start,end);//翻转整个字符串
        int left = 0,right = 0;
        while(right < chars.length){//结束标志，右索引小于end
            while(left < chars.length && chars[left] == ' ') left++;//排除多个空格
            right = left + 1;
            while(right < chars.length && chars[right] != ' ') right++;//单词结束了
            reverseChars(chars,left,right-1);//翻转该单词
            left =  ++right;//调整到下一单词开始
        }
        return new String(chars);
    }

    //翻转字符数组中start到end的字符段
    public void reverseChars(char[] chars,int start,int end){
        if(start >= end) return;
        if(end >= chars.length) return;//防止数组越界
        while(start < end){
            swap(chars,start++,end--);
        }
    }

    //交换字符数组中两个字符
    public void swap(char[] chars,int i,int j){
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }



    //测试
    @Test
    public void test(){
        String str = "student. a am I";
        String newStr = ReverseSentence(str);
        System.out.println("str = " + str);
        System.out.println("newStr = " + newStr);

        System.out.println("ReverseSentence(\"\") = " + ReverseSentence(""));
        System.out.println("ReverseSentence(\" \") = " + ReverseSentence(" "));
    }
}
