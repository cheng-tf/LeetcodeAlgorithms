package JianzhiOfferAlgorithms;

import org.junit.Test;

public class JianzhiOffer_20_isNumeric {

    /**************剑指Offer20:表示数值的字符串*********************/
    /**
     * 题目描述
     * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
     * 例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。
     * 但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。
     *
     * 思路分析：数值字符串可以归结为两种：A[.B][e|EC]和.B[e|EC]
     *           第一种必有整数部分，而小数和指数部分可以没有；
     *           第二种没有整数部分，必有小数部分，指数部分可以没有。
     *          其中A、B、C为整数部分，A、C可以带+、-号，而B是无符号的。
     *          因此只需要检验A和B两部分至少有一部分出现且合法即可，
     *          对于C有或没有都可以，但若有必须合法。
     *          特殊字符：A和B的正负号，小数以.开头，指数以e|E开头。
     */

    public boolean isNumeric(char[] chars) {
        if(chars == null || chars.length == 0) return false;
        boolean result = false;
        int index = 0;
        //检查A部分
        if(chars[index]=='+'||chars[index]=='-') index++;
        int indexA = index;
        while(index<chars.length&&chars[index]<='9'&&chars[index]>='0') index++;
        if(index > indexA ) result = true;//若至少有一位数字，则A部分合法；如 .123虽整体合法，但是A部分不合法，只要A或B一部分合法即可
        //检查B部分
        if(index<chars.length&&chars[index]=='.'){
            index++;
            int indexB = index;
            while(index<chars.length&&chars[index]<='9'&&chars[index]>='0') index++;
            result = result || (index>indexB);//123.整体合法，但B部分不合法；.123整体合法，但A部分不合法，B部分合法
        }
        if(index<chars.length&&(chars[index]=='e'||chars[index]=='E')){
            index++;
            if (index<chars.length&&(chars[index]=='+'||chars[index]=='-')) index++;
            int indexC = index;
            while(index<chars.length&&chars[index]<='9'&&chars[index]>='0') index++;
            result = result && (index > indexC);//e2整体部分法，A和B综合不合法，C合法；4e整体不合法，A和B综合合法，但C不合法
        }
        return result&&(index==chars.length);
    }

    //测试
    @Test
    public void test(){
        String[] strs = {"+123.24e-3","+123.e-3",".e-3",".123","123.",".123e-10"};
        for(String str : strs){
                boolean result = isNumeric(str.toCharArray());
                System.out.println("result = " + result);
        }
    }
}
