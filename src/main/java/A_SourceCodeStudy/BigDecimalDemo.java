package A_SourceCodeStudy;

import BinaryTreeBasicAlgorithms.BinaryTreeTraversals;
import org.apache.commons.collections.bidimap.AbstractBidiMapDecorator;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BigDecimalDemo {
    @Test
    public void test(){
        Integer integer = new Integer("10");
        System.out.println("integer = " + integer);
        System.out.println(Integer.MAX_VALUE);//2147483647
        System.out.println(Integer.MIN_VALUE);//-2147483648

        byte[] bytes = {(byte)127,(byte)255,(byte)255,(byte)255};
        BigInteger bi = new BigInteger(bytes);
        System.out.println("bi = " + bi);
        boolean result = bi.equals(new BigInteger(Integer.MAX_VALUE+""));//true
        boolean result1 = bi.equals(Integer.MAX_VALUE);//false

        byte[] bytes2 = {(byte)255,(byte)255,(byte)255,(byte)255};
        BigInteger bi1 = new BigInteger(bytes2);
        BigInteger bi2 = new BigInteger(-1,bytes2);
        System.out.println("bi1 = " + bi1);
        System.out.println("bi2 = " + bi2);


        BigInteger bi3 = new BigInteger(new byte[]{(byte)255});
        System.out.println("bi3 = " + bi3);//-1


        BigDecimal bigDecimal = new BigDecimal("999999999999999999999999999999");
        BigDecimal newBig = bigDecimal.add(new BigDecimal(1));
        System.out.println("bigDecimal = " + bigDecimal);
        System.out.println("newBig = " + newBig);
        
        
        BigDecimal bd = new BigDecimal(new BigInteger("999888"),3);//999.888
        System.out.println("bd = " + bd);//
        BigDecimal bd2 = new BigDecimal("6666.888");
        System.out.println("bd2 = " + bd2);
        
    }
}
