package StringAlgorithms;

import org.junit.Test;

public class JianzhiOffer_5_ReplaceSpace {

    /**********************剑指Offer5:替换空格**********************************/

    /**
     * 题目描述
     * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。
     * 例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
     *
     * 思路分析：首先遍历一遍统计空格个数，然后分配内存空间，
     *           从后往前依次替换空格即可。
     */
    public String replaceSpace(StringBuffer str) {
        if(str == null || str.length() == 0) return "";
        int len = str.length();
        int spaceCount = 0;
        for(int i  = 0;i < len;i++){
            if(str.charAt(i) == ' ')  spaceCount++;
        }
        int newLen = len + (spaceCount<<1);
        str.setLength(newLen);//设置新的长度
        for(int i = len-1,j = newLen-1;i >= 0&&j>=0;){
            if(i == j) break;//i与j相等表示没有空格了或者利用spaceCount--也可以，直到减到0
            if(str.charAt(i)==' '){
                str.setCharAt(j--,'0');
                str.setCharAt(j--,'2');
                str.setCharAt(j--,'%');
            }else{
                str.setCharAt(j--,str.charAt(i));
            }
            i--;
        }
        return str.toString();
    }

    //测试
    @Test
    public void test(){
        StringBuffer sb = new StringBuffer();
        sb.append("We are family!");
        System.out.println("sb.toString() = " + sb.toString());
        String str = replaceSpace(sb);
        System.out.println(str);
    }

}
