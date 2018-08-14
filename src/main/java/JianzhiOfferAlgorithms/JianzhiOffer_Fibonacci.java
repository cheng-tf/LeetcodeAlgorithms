package JianzhiOfferAlgorithms;

import org.junit.Test;

public class JianzhiOffer_Fibonacci {

    @Test
    public void test(){
        for(int i = 0;i<10;i++){
            System.out.println("Fibonacci(i) = " + Fibonacci(i));
            System.out.println("Fibonacci2(i) = " + Fibonacci2(i));
        }
    }

    /**
     * 利用递归算法计算斐波那契序列
     * 递归算法存在重复计算：如计算F(9)=F(8)+F(7);
     * 而F(8) 内部又要计算F(7)
     */
    public int Fibonacci(int n){
        if(n <= 0) return 0;
        if(n == 1) return 1;
        return Fibonacci(n-1)+Fibonacci(n-2);
    }


    /**
     * 循环算法： 不断更新前两个值
     */
    public int Fibonacci2(int n){
        if(n<=0) return 0;
        if(n==1) return 1;
        int first = 0,second = 1;
        while(n-- >= 2){
            int third = first + second;
            first = second;
            second = third;
        }
        return second;
    }


    /**
     * 算法3：利用矩阵实现
     */




}
