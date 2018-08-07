package JianzhiOfferAlgorithms;

import org.junit.Test;

public class JianzhiOffer_StrToInt_IntegerValueOf {

    /**
     *题目描述
     * 将一个字符串转换成一个整数(实现Integer.valueOf(string)的功能，但是string不符合数字要求时返回0)，要求不能使用字符串转换整数的库函数。 数值为0或者字符串不是一个合法的数值则返回0。
     * 输入描述:
     * 输入一个字符串,包括数字字母符号,可以为空
     * 输出描述:
     * 如果是合法的数值表达则返回该数字，否则返回0
     */

    @Test
    public void test(){
        String[] strs = {"2334123","234","90909","-908","+1234214","1dd223","d898"};
        for(String str:strs){
            int result = StrToInt(str);
            System.out.println("result = " + result);
        }

    }


    public int StrToInt(String str) {
        if(str== null||str.length()==0) return 0;
        char[] chars = str.toCharArray();
        boolean isPositive = true;
        int result = 0;
        //对第一个字符判断处理
        if(chars[0] == '-'){
            isPositive = false;
        }else if(chars[0]=='+'){
            isPositive = true;
        }else if(chars[0]<='9'&&chars[0]>='0'){
            result = result*10 + (chars[0]- '0');
        }else{
            return 0;
        }
        //对后序字符处理
        for(int i = 1;i < chars.length;i++){
            if(chars[i]>='0'&& chars[i]<='9'){
                result = result*10 + (chars[i]- '0');
            }else{
                return 0;
            }
        }
        return isPositive?result:-result;
    }
}
