import org.junit.Test;

import java.util.Date;

/**
 * @Description 注释
 * @Author zhaohong
 * @Time 2019-03-08 16:49
 */
public class Test1 {
    public static void main(String[] args) {

        String str1 = String.format("% 4.2f",444.2);
        String str2 = String.format("%04.2f",444.2);
        System.out.println("str1 = " + str1);
        System.out.println("str2 = " + str2);

        String str3 = String.format("%4d",444);
        String str4 = String.format("%04d",444);
        System.out.println("str3 = " + str3);
        System.out.println("str4 = " + str4);
    }

    @Test
    public void test(){
        String dateStr1 = String.format("%tT",new Date());
        String dateStr2 = String.format("%tF",new Date());
        System.out.println("dateStr1 = " + dateStr1);//dateStr1 = 17:00:10
        System.out.println("dateStr2 = " + dateStr2);//dateStr2 = 2019-03-08
    }
}
