package Test;

import org.junit.Test;

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

}
