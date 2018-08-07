package JianzhiOfferAlgorithms;

import org.junit.Test;

public class JianzhiOffer_LeftRotateString {


    @Test
    public void test(){
        System.out.println(LeftRotateString("",6));//注意特殊情况
        System.out.println(LeftRotateString(null,6));//注意特殊情况
        String str = "abcdefghijk";
        for(int i = -3;i < 4;i++)
          System.out.println(LeftRotateString(str,i));
    }

    public String LeftRotateString(String str,int n) {
        if(str == null || str.length() == 0) return "";//注意特殊情况
        char[] chars = str.toCharArray();
        int len = chars.length;
        int m = n%len;
        if(m<0){m += len;}
        char[] chars1 = new char[len];
        for(int i = 0;i < len;i++){
            chars1[i] = chars[(i + m)%len];
        }
        return new String(chars1);
    }
}
