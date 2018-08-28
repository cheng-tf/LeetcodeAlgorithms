package ArrayAlgorithms;

import org.junit.Test;

public class JianzhiOffer_xx_FindADuplicateNumber {

    // Parameters:
    //    numbers:     an array of integers
    //    length:      the length of array numbers
    //    duplication: (Output) the duplicated number in the array number,length of duplication array is 1,so using duplication[0] = ? in implementation;
    //                  Here duplication like pointor in C/C++, duplication[0] equal *duplication in C/C++
    //    这里要特别注意~返回任意重复的一个，赋值duplication[0]
    // Return value:       true if the input is valid, and there are some duplications in the array number
    //                     otherwise false

    /**
     * 题目描述
     * 在一个长度为n的数组里的所有数字都在0到n-1的范围内。
     * 数组中某些数字是重复的，但不知道有几个数字是重复的。也不知道每个数字重复几次。
     * 请找出数组中任意一个重复的数字。 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，
     * 那么对应的输出是第一个重复的数字2。
     *
     * 思路分析：
     *  方法1：排序，复杂度为O(NlogN)；
     *  方法2：利用一个O(N)的哈希表空间；选择boolean哈希表即可。
     *         遍历原数组，依次存放到哈希表中，若是第一次存放，则设置为true，
     *         下次存放之前检查是否为true，若true，则当前值即为重复值。
     *         该方法：不改变原数组的顺序，但是需要O(N)的空间复杂度。
     *  方法3：调整元素位置，如将元素3调整到索引3位置处。
     *         具体思路：让每一个位置上存放与索引相等的元素，需要两层循环，for循环用来遍历所有的位置，
     *         内循环保证当前位置存放的就是索引值。内循环用while循环实现，当i==nums[i]跳出循环，否则一直循环执行；
     *         执行过程：将该位置元素与对应元素值索引处值交换，若当前元素值与元素值索引处值相等，则找到了重复值。
     *
     */

    /**
     * 方法3:归位思路
     */
    public boolean duplicate3(int nums[],int length,int [] duplication) {//duplication[0]默认值为-1
        if (nums == null||nums.length == 0||length != nums.length) return false;//the input is valid
        for(int i = 0;i < length;i++){
//           if(i == nums[i])  continue;//元素nums[i]恰好与i相等,不改变
            while(i != nums[i]){//直到当前位置的元素与索引值相等。若不存在等于索引的值，会怎么样？后续肯定存在重复值，所以就不会一直循环下去。
                // 如2,1,1,3;没有0，那么经过若干次调整，会进入下面的if语句，return true; 如->1,1,2,3->索引1处有1了，则直接返回1；
                if(i!= nums[i]&&nums[i] == nums[nums[i]]){//i!=nums[i],如[2,1,3,0,4] 元素1在索引1处，但是不是重复元素
                    duplication[0] = nums[i];
                    return true;
                }
                swap(nums,i,nums[i]);
            }
        }
        return false;
    }
    public void swap(int[] nums,int i,int j){
       int temp = nums[i];
       nums[i] = nums[j];
       nums[j] = temp;
    }
    public void swap2(int[] nums,int i,int j){
        //通过异或实现交换操作
        //存在问题：如果i和j相同，则结果都会变成0；因此需要保证i和j索引不相同
        if(i == j) return;
        nums[i] = nums[i]^nums[j];
        nums[j] = nums[i]^nums[j];
        nums[i] = nums[i]^nums[j];
    }

    /**
     * 方法2：利用布尔哈希表的方法
     */
    public boolean duplicate(int nums[],int length,int [] duplication) {//duplication[0]默认值为-1
        if (nums == null||nums.length == 0||length != nums.length) return false;//the input is valid
        boolean[] exists = new boolean[length];
        for(int i = 0;i < length;i++){
            if(exists[nums[i]]) {
                duplication[0] = nums[i];
                return true;
            }else{
                exists[nums[i]] = true;
            }
        }
        return false;
    }


    //测试
    @Test
    public void test(){
        int[] nums = {2,1,2,3,4,2,1,3};
        int[] duplicate = new int[]{-1};
        int len = nums.length;
        boolean result = duplicate(nums,len,duplicate);
        System.out.println("result = "+result+ "; duplicate = " + duplicate[0]);

        int[] nums3 = {2,1,3,0,4};
        int[] nums2 = {2,1,3,1,4};
        int[] duplicate2 = new int[]{-1};
        int len2 = nums2.length;
        boolean result2 = duplicate(nums2,len2,duplicate2);
        System.out.println("result = "+result2+ "; duplicate2 = " + duplicate2[0]);
    }

}
