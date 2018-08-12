package JianzhiOfferAlgorithms;

import org.junit.Test;

public class FindNumberOfAppearOnlyOnce {
    @Test
    public void test(){
        int[] nums = {108,112,108,112,23,43,54,43,23,54,666,77,77};
        int result = getNumberOfOnlyOnce(nums);
        System.out.println(result);
        int num = 10;
        System.out.println(num>>0);

        for(int i = 0;i<10;i++)
            System.out.println(findBitIs1Num(i));

    }

    /**
     * 数组中只有一个数只出现一次，其他数都出现两次；
     * 找出只出现一次的这个数；
     * 思路：异或即可解决
     */
    public int getNumberOfOnlyOnce(int[] nums){
        int result = 0;
        for(int num :nums)
            result ^= num;
        return result;
    }
    /**
     * 数组中有两个数只出现一次，其他数都出现两次；
     * 找出只出现一次的这两个数；
     * 思路：异或。首先异或所有，结果中肯定至少有1个Bit为1；根据这个Bit是1和0分成两小组；
     * 分别异或各个小组，各个小组的异或结果即为答案。
     */
    public int[] getTwoNumberOfOnlyOnce(int[] nums){
        int result = 0;
        for(int num :nums)
            result ^= num;

        return null;
    }

    /**
     * 需要右移的比特数目
     */
    public int findBitIs1Num(int num){
        int n = 0;
        while((num & 1) == 0 && n < 32){
            num = num>>1;
            n++;
        }
        return n;
    }
}
