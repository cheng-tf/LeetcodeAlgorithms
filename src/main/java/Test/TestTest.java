package Test;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestTest {

    public int NumberOf1(int n) {
        int i = 1;
        int count = 0;
        method1();
        while(i != 0){
            if((n & i) != 0){
                count++;
            }
            i <<= 1;
        }
        return count;
    }
    
    @Test
    public void test(){
        for (int i = 0;i<10;i++){
            NumberOf1(i);
            System.out.println("i = " + i);
        }


        NumberOf1(11);
        NumberOf1(11);
        NumberOf1(11);
        System.out.println("NumberOf1(10) = " + NumberOf1(10));

    }

    public void method1(){
        System.out.println(" = " );
        return ;
    }

    @Test
    public void test2(){

        String str2 = String.format("dddd%sddddd%s"+"","AAAAAAAAAA","dddd");
        System.out.println("str = " + str2);


        SimpleDateFormat slf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = "2018-11-11  29:00:00";
        Date date = null;
        try {
            date = slf.parse(str);
        }catch(ParseException pe){
            System.out.println("pe = " + pe);
            pe.printStackTrace();
        }
        System.out.println("date = " + date);
        System.out.println("date.getTime() = " + date.getTime());

    }

}
