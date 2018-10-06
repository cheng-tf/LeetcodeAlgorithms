package BitOperationsAlgorithms;

import org.junit.Test;

public class JianzhiOffer_NumberOfBit1 {




    /**
     * 基于正数的方法
     * 传入负数时，会陷入无限循环中
     */
    public int NumberOf1_1(int n){
        int num = 0;
        while(n != 0){
            if((n&1) != 0) num++;
            n = n>>1;
        }
        return num;
    }

    /**
     * 比较牵强的做法：改变右移为左移
     */
    public int NumberOf1_1_1(int n){
        int num = 0;
        while(n != 0){
            if((n&Integer.MIN_VALUE) != 0) num++;
            n = n<<1;
        }
        return num;
    }
    
    
    /**
     * 思路分析：由于n有正负之分，正数右移，左边补0；但是负数右移，左边补1，会陷入无限循环之中。
     * 变换思路：移动flag，由1逐渐左移，需要移动32次，即可判断比特1的位数。
     * @param n
     * @return
     */
    public int NumberOf1_2(int n) {
        int flag = 1;
        int num = 0;
        while(flag != 0){
            if((n & flag) != 0) num++;//注意条件是!=0不是==1了。
            flag = flag<<1;
        }
        return num;
    }

    /**
     * 基于减1再与的方法
     * 原理：n减1，会导致原为1的最低位为0，且该位左边的所有位都变成1，再与原n做与操作，就消去了为1的1位；
     * 直到n变成0为止。
     * 如1101，不为0，num++; 减1变成1100,相与之后为1100，不为0，num++;再减1为1011，相与之后为1000，不为0，num++；
     * 再减1为0111，相与之后为0000.为0跳出循环。
     */
    public int NumberOf1_3(int n){
        int num = 0;
        while(n != 0){
            num++;
            n = (n-1)&n;
        }
        return num;
    }

/**************************判断是不是2的幂****************************************/
    /**
     * 判断是不是2的幂
     */
    public boolean is2Power(int n){
        if(n < 0) return false;
//        if(n == 0) return true;
        return ((n-1)&n)==0;//n=0,也恰好返回true
    }

    @Test
    public void test2(){
        System.out.println("is2Power(0) = " + is2Power(0));
        System.out.println("is2Power(1) = " + is2Power(1));
        System.out.println("is2Power(128) = " + is2Power(128));
        System.out.println("is2Power(127) = " + is2Power(127));
    }


    /*****##m和n改变二进制位数##*****/
    /**
     * 输入两个整数m和n，计算需要改变m的二进制表中的多少位才能得到n。
     * 示例：1010变成1101需要改变1101的3位才可以变成1010。
     */
    public int bitNums(int m,int n){
        int mn = m^n;
        int num = 0;
        while(mn != 0){
            num++;
            mn = (mn-1)&mn;
        }
        return num;
    }

    @Test
    public void test3(){
        int n = 10,m=13;
        int num = bitNums(m,n);
        System.out.println(num);//4
    }



    public static int MAXIMUM_CAPACITY = 1>>30;
    /**
     * Returns a power of two size for the given target capacity.
     */
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }





    @Test
    public void test(){
        System.out.println("NumberOf1_2(1) = " + NumberOf1_1(1));
        System.out.println("NumberOf1_2(0) = " + NumberOf1_1(0));
//        System.out.println("NumberOf1_2(-1) = " + NumberOf1_1(-1));//无限循环

        System.out.println("NumberOf1_1_1(-1) = " + NumberOf1_1_1(-1));
        System.out.println("NumberOf1_1_1(0) = " + NumberOf1_1_1(0));
        System.out.println("NumberOf1_1_1(MAX_VALUE) = " + NumberOf1_1_1(Integer.MAX_VALUE));
        System.out.println("NumberOf1_1_1(MIN_VALUE) = " + NumberOf1_1_1(Integer.MIN_VALUE));

        System.out.println("Integer.MAX_VALUE = " + NumberOf1_2(Integer.MAX_VALUE));//0111111...111
        System.out.println("Integer.MIN_VALUE = " + NumberOf1_2(Integer.MIN_VALUE));//1000000,,,000
        System.out.println("NumberOf1(-1) = " + NumberOf1_2(-1));

        System.out.println("NumberOf1_3(1) = " + NumberOf1_3(1));
        System.out.println("NumberOf1_3(0) = " + NumberOf1_3(0));
        System.out.println("NumberOf1_3(-1) = " + NumberOf1_3(-1));
        System.out.println("NumberOf1_3(Integer.MAX_VALUE) = " + NumberOf1_3(Integer.MAX_VALUE));
        System.out.println("NumberOf1_3(Integer.MIN_VALUE) = " + NumberOf1_3(Integer.MIN_VALUE));

        int iii = Integer.MIN_VALUE-1;
        int j = Integer.MAX_VALUE+1;
        //Integer的最大值加1就是Integer的最小值；
        //Integer的最小值减1就是Integer的最大值；
        System.out.println(iii== Integer.MAX_VALUE);//true
        System.out.println(j==Integer.MIN_VALUE);//true

        System.out.println("4>>1 = " + (4>>1));//正数右移，高位补0
        System.out.println("0>>1 = " + (0>>1));//0右移任意位都是0
        System.out.println("-2>>1)) = " + (-2>>1));//负数右移，高位补1
        System.out.println("-1>>1)) = " + (-1>>1));//-1右移任意位都是-1

        System.out.println("(Integer.MIN_VALUE<<1) = " + (Integer.MIN_VALUE<<1));//0 keyi
        System.out.println("(-1<<1) = " + (-1<<1));//-2
        System.out.println("1<<31)) = " + (1<<31) );//1左移31位就是Integer.MIN_VALUE
    }

}
