package Others;

import org.junit.Test;

public class StringInternTest {

    public void test(){
        String str1 = "BUPT";//仅仅在ConstantPool中创建,返回的是Pool中的字符串地址
        String str2 = new String("BUPT");//创建两次,若ConstantPool中已经存在,则不创建;返回的是Heap中的对象地址
        System.out.println(str1 == str2);//false
        String str3 = str2.intern();
        String str4 = "BUPT";
        String str5 = new String("BUPT");
        System.out.println(str2 == str4);//true
        System.out.println(str1 == str4);//true
        System.out.println(str3 == str4);//true
        System.out.println(str3 == str5);//false
        str1.toString();
        //hashCode都相同
        System.out.println(str1.hashCode());
        System.out.println(str2.hashCode());
        System.out.println(str3.hashCode());
        System.out.println(str4.hashCode());
        System.out.println(str5.hashCode());

    }
    //@Test
    public void test1(){
        String str1 = "BUPT";
        String str2 = "BUPT";
        System.out.println(str1 == str2);//true
    }

    //@Test
    public void test2(){
        String str1 = new String("BUPT");
        String str2 = new String("BUPT");
        System.out.println(str1 == str2);//false
    }
    //@Test
    public void test3(){
        String str1 = new String("BUPT");
        String str2 = "BUPT";
        System.out.println(str1 == str2);//false
    }
    //@Test
    public void test4(){
        String str0 = new String("BUPT");
        String str1 = new String("BUPT");
        String str2 = "BUPT";
        System.out.println(str1 == str2);//false
        System.out.println(str0 == str2);//false
    }
    //@Test
    public void test5(){
        String str0 = new StringBuilder("BU").append("PT").toString();
        System.out.println(str0.intern() == str0);//true  JDK1.7的改进(P57深入理解Java虚拟机)
        String str1 = new StringBuilder("ja").append("va").toString();
        System.out.println(str1.intern() == str1);//false  java不是"首次出现"
        String str2 = new StringBuilder("Bei").append("jing").toString();
        System.out.println(str2.intern() == str2);//true
    }
    @Test
    public void test6(){
        String str0 = new String("BUPT");
        System.out.println(str0.intern() == str0);//false
        String str1 =new String("java");
        System.out.println(str1.intern() == str1);//false
        String str2 = new String("Beijing");
        System.out.println(str2.intern() == str2);//false
    }

}

