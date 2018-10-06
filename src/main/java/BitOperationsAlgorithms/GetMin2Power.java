package BitOperationsAlgorithms;

import org.junit.Test;

public class GetMin2Power {
    /**
     * 题目介绍
     * 任意给定一个数，返回大于等于它的最小的2的乘方。
     *
     * 思路分析：主要思想：将该数最高位为1及之下的位全部设为1，然后加1返回即可。
     * n = n-1 的目的是：将
     *      若原数已经是2的幂，则只有一位为1，减1之后，得到后面的位全是1，下面不断右移再或，最后也是全是1，加1后恢复原值；
     *      若原数值不是2的幂，则减1之后，最高位1不变，然后先右移一位之后与原值或操作，则保证高两位全为1，
     *      再右移2位后与原值或操作，可以得到高四为全为1，紧接着右移4位再或，得到高8位为1，依次类推，
     *      最终可以所有数的后面位全为1。当然大多数不需要移动这么多位，为了保证全面性。
     */
    public int getMin2Power(int n){
        n = n - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0)?1:n+1;
    }

    @Test
    public void test(){
        System.out.println("getMin2Power(13) = " + getMin2Power(13));
        System.out.println("getMin2Power(13) = " + getMin2Power(16));
    }

}
