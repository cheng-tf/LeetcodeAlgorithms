package formatOutput;

import org.junit.Test;

/**
 * @Description 注释
 * @Author zhaohong
 * @Time 2019-02-27 8:55
 */
public class IntegerFormat {

    /**
     * Java数字格式化输出时
     * ①前面空格填充
     * ②前面补0
     */
    @Test
    public void test(){
        //一共输出指定长度如17位，前面不够用空格补充
        String res1 = String.format("%17d",1);//                1
        //前面不够用0补充
        String res2 = String.format("%017d",1);//00000000000000001
        System.out.print(res1+"\n"+res2);
    }

}
