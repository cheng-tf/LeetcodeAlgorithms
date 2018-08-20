package JianzhiOfferAlgorithms;

import org.junit.Test;

public class JianzhiOffer_56_FindNumberOfAppearOnlyOnce {
    @Test
    public void test(){
        int[] nums = {108,112,108,112,23,43,54,43,23,54,999,77,77};
        int result = getNumberOfOnlyOnce(nums);
        System.out.println(result);
//        int num = 10;
//        System.out.println(num>>0);
        int[] nums2 = {108,112,108,112,23,43,54,43,23,54,77,77,10234,3421};
        int[] result1 = new int[1];
        int[] result2 = new int[1];
        FindNumsAppearOnce(nums2,result1,result2);
        System.out.println("result1[0] = " + result1[0]+";"+"result2[0] = " + result2[0]);
    }


    /*******************************题目1：找出只出现一次的数字***********************************************/
    /**
     * 数组中只有一个数只出现一次，其他数都出现两次；
     * 找出只出现一次的这个数；
     * 思路：异或即可解决。
     */
    public int getNumberOfOnlyOnce(int[] nums){
        int result = 0;
        for(int num :nums)  result ^= num;
        return result;
    }
    /******************************题目2：找出只出现1次的两个数**************************************************/
    /**
     * 数组中有两个数只出现一次，其他数都出现两次；
     * 找出只出现一次的这两个数；
     * 思路：异或。首先异或所有，结果中肯定至少有1个Bit为1；根据这个Bit是1和0分成两小组；
     * 分别异或各个小组，各个小组的异或结果即为答案。
     */
    public void FindNumsAppearOnce(int [] nums,int num1[] , int num2[]) {
        int result = 0;
        for(int num :nums) result ^= num;
        int indexRight = findIndexOfBitIs1FromRight(result);//需要右移的位数
        for(int num:nums){
            if(theBitIs1(num,indexRight))  num1[0] ^= num;
            else  num2[0] ^= num;
        }
    }

    /**
     * 需要右移的比特数目
     */
    public int findIndexOfBitIs1FromRight(int num){
        int n = 0;//不需要移位
        //利用右移，判断正数可以
        while((num & 1)==0 && n++<32)
            num = num >>1;
        return n;
    }

    /**
     * 判断num的第indexBit位是否为1(从右边数)
     */
    public boolean theBitIs1(int num,int indexBit){
        num = num >> indexBit;
        return (num&1) == 1;
    }

    /********************************题目三：找出只出现1次的一个数，其他数都出现3次*************************/
    /**
     * 题目介绍：一个数组中只有一个数只出现1次，其他数均出现3次，找出只出现1次的这个数。
     * 思路分析：
     *          将所有数对应比特相加起来，出现三次的肯定是3的倍数，
     *          最终对3求余，得到结果的每一个比特值
     *
     */
    @Test
    public void test3(){
        int[] nums = {3,2,3,3,2,2,5,5,5,89,89,32,89};
        int number = findNumberAppearOnlyOnce(nums);
        System.out.println("number = " + number);
    }

    public int findNumberAppearOnlyOnce(int[] nums){
        int result = 0;
        int[] bits = new int[32];
        for(int i = 0; i < 32;i++){
            for(int j = 0;j < nums.length;j++){
                bits[i] +=  theBitIs1(nums[j],i)?1:0;
            }
            bits[i] %= 3;
        }
        //将长度为32的数组转换成一个int值
        for(int i = 31;i > 0;i--){
            result = (result|bits[i])<<1;
        }
        return result|bits[0];
    }

    public boolean theBitIsOne(int num,int index){
        num = num>>index;
        return (num&1) ==1;
    }
}
