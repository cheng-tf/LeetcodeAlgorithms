package July5;

import org.junit.Test;

public class RecursiveTest {
    class Solution{
        public int jiecheng(int n){
            if(n < 0) return -1;
            if(n == 0|| n == 1) return 1;
            return n*jiecheng(n-1);
        }
    }
    @Test
    public void solutionTest(){
        Solution solution = new Solution();
        for(int i = -2;i<10;i++)
            System.out.println(i+"! = "+solution.jiecheng(i));
    }
}
