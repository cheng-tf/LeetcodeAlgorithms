package JianzhiOfferAlgorithms;

import org.junit.Test;

public class JianzhiOffer_53_SearchNumberInSortedArray_1_GetNumberOfK {

    //测试
    @Test
    public void test(){
        int[] nums = {1,2,3,3,3,3,3,3,4,8,34,223,375,375,375};

        System.out.println(getNumberOfK(nums,3));
        System.out.println(getNumberOfK(nums,375));
        System.out.println(getNumberOfK(nums,4));
        System.out.println(getNumberOfK(nums,0));
    }

    /**
     * 方法1：二叉查找基础上
     * 若中间位置的值与target相等，向两边顺序扫描，直到向左到low或非target，向右到high或非target。
     * 这种算法的在全是target的情况下时间复杂度为O(n)，与顺序扫描一样。
     */
    public int GetNumberOfK(int [] nums , int target) {
        int low = 0,high = nums.length-1;
        while(low <= high){
            int mid = (low+high)>>1;
            if(target < nums[mid]){
                high = mid-1;
            }else if(target > nums[mid]){
                low = mid + 1;
            }else{//在二分查找的基础上，改变了相等的情况
                int mid1 = mid;
                while(mid1 >= low&&nums[mid1] == target) mid1--;
                while(mid <= high&&nums[mid] == target) mid++;
                return mid-mid1-1;
            }
        }
        return 0;//不存在情况下，返回0
    }
    /*******************************方法二****************************/
    /**
     * 方法2：二分查找基础上
     * 由于方法1的时间复杂度为O(n)，而二分查找的复杂度为O(logn);
     * 如果只是寻找target连续的第一个位置和最后一个位置，作差加1就是次数，
     * 这样时间复杂度就是O(logn)
     * 二分查找的改进：
     *  nums[mid]>target和nums[mid]<target都不变，
     *  nums[mid]==target需要好好考虑下条件：
     *  需要返回mid的两种情况：
     *  mid==low或mid==high；或者nums[mid+1]!=target或nums[mid-1]!=target
     *  其他情况需要递归：high=mid-1或low=mid+1
     */
    public int getNumberOfK(int [] nums , int target) {
        if(nums==null||nums.length == 0) return 0;
        int firstIndex = getFirstIndexOfK(nums,target);
        int lastIndex = getLastIndexOfK(nums,target);
        System.out.println(firstIndex+","+lastIndex);
        return (firstIndex != -1 && lastIndex != -1) ? (lastIndex-firstIndex +1) : 0;
    }

    /**
     * 获取第一个索引位置
     * @param nums
     * @param target
     * @return
     */
    public int getFirstIndexOfK(int[] nums,int target){
        int low = 0,high = nums.length-1;
        while(low <= high){
            int mid = (low+high)>>1;//1. 位移效率高；2. 求mid一定在while循环内，前几次在while循环外，导致无限循环
            if(target < nums[mid]){
                high = mid-1;
            }else if(target > nums[mid]){
                low = mid + 1;
            }else{
                //找到第一个两种情况：1.mid==low，到达边界；2. 下一个元素不是target；其他情况需要继续循环
//                if(mid == low) return mid;//保证后续mid>low
//                if(nums[mid-1] != target)  return mid;
                if(mid == low||nums[mid-1]!=target) return mid;
                high = mid-1;
            }
        }
        return -1;
    }
    /**
     * 获取最后一个索引位置
     * @param nums
     * @param target
     * @return
     */
    public int getLastIndexOfK(int[] nums,int target){
        int low = 0,high = nums.length-1;
        while(low <= high){
            int mid = (low+high)>>1;
            if(target < nums[mid]){
                high = mid-1;
            }else if(target > nums[mid]){
                low = mid + 1;
            }else{
//                if(mid == high) return mid;//保证后续mid<high
//                if(nums[mid+1] != target) return mid;
                if(mid == high||nums[mid+1]!=target) return mid;
                low = mid+1;
            }
        }
        return -1;
    }

}
