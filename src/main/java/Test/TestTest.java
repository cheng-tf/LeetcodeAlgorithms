package Test;

import org.junit.Test;

public class TestTest {

    public int NumberOf1(int n) {
        int i = 1;
        int count = 0;
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
        System.out.println("NumberOf1(10) = " + NumberOf1(10));
    }

}
