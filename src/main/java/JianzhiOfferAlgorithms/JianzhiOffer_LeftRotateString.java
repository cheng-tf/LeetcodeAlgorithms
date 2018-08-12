package JianzhiOfferAlgorithms;

import org.junit.Test;

public class JianzhiOffer_LeftRotateString {


    @Test
    public void test(){
        System.out.println(leftRotateString("",6));//注意特殊情况
        System.out.println(leftRotateString(null,6));//注意特殊情况

        String str = "We are family.";
        System.out.println(reverseString(str));//翻转字符串
        //翻转句子中的单词顺序
        System.out.println(reverseEnglishSentence(str));
        for(int i = -5;i < 6;i++) {//i小于0，右旋；i大于0左旋
            System.out.println(leftRotateString(str, i));
        }

    }

    /**
     * 另外开辟了一个字符数组，耗费了空间内存，一般不允许
     */
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


    /**
     * 利用String的subString方法
     */
    public String LeftRotateString2(String str,int n) {
        if(str == null || str.length() == 0) return "";//注意特殊情况
        int len = str.length();
        n = n%len;
        if(n<0){n += len;}
        str = str + str.substring(0,n);
        return str.substring(n);
    }
    /**
     * 左旋字符串/右旋字符串
     */
    public String leftRotateString(String str,int n){
        if(str == null || "".equals(str)) return str;
        char[] chars = str.toCharArray();
        n = n%chars.length;
        if(n < 0)  n += chars.length;
        reverseChars(chars,0,n-1);//前一部分
        reverseChars(chars,n,chars.length-1);//后一部分
        reverseChars(chars,0,chars.length-1);//整体翻转
        return new String(chars);
    }


    /**
     * 翻转单词顺序
     */
    public String reverseEnglishSentence(String sentence){
        if(sentence==null||"".equals(sentence)) return sentence;
        char[] chars = sentence.toCharArray();
        reverseChars(chars,0,chars.length-1);//翻转整个句子
        //下面逐个翻转单词
        int wordStart = 0,wordEnd = 0;
        while(wordStart < chars.length){
            if(chars[wordStart]==' '&&chars[wordEnd]==' '){//都指向空格
                wordStart++;
                wordEnd++;
            }else if(wordEnd==chars.length||chars[wordEnd]==' '){
                reverseChars(chars,wordStart,wordEnd-1);
                wordStart = wordEnd;
            }else{
                wordEnd++;
            }
        }
        return new String(chars);
    }


    /**
     * 翻转字符串
     */
    public String reverseString(String str){
        if(str == null||"".equals(str)) return str;
        return reverseString(str,0,str.length()-1);
    }
    /**
     * 翻转[start,end]范围内的字符串
     */
    public String reverseString(String str,int start,int end){
        if(str== null||"".equals(str)) return str;
        char[] chars = str.toCharArray();
        reverseChars(chars,start,end);
        return new String(chars);
    }
    /**
     * 翻转字符数组的[start,end]部分
     */
    public void reverseChars(char[] chars,int start,int end){
        if(start <0 || end > chars.length-1) return;
        while(start < end){
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
            start++;
            end--;
        }
    }
}
