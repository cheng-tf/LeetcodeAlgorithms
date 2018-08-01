package SearchAlgorithm;

import org.junit.Test;

public class LeetCode_35_SearchInsertPosition {

    @Test
    public void test() {
        int[] arr = {1,3,4,66,73,100};
        for(int i = 2;i< 122;i+= 10){
            System.out.println("searchInsert(arr,i) = " + i +"----"+searchInsert(arr,i));
        }
    }

    public  int searchInsert(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        while(true){
            int midIndex = (low + high)/2;
            if(target > arr[midIndex]){
                //两种情况下对应跳出：1、midIndex达到high，target大于数组中任何一个值,返回midIndex+1；
                // 2、arr[midIndex]< target < arr[midIndex + 1]，返回midIndex+1；
                if(midIndex == high||target < arr[midIndex + 1])
                    return midIndex+1;
                low = midIndex + 1;
            }else if(target < arr[midIndex]){
                //两种情况下对应跳出：1、midIndex达到low，target小于数组中任何一个值,返回midIndex；
                // 2、arr[midIndex-1]< target < arr[midIndex]，返回midIndex；
                if(midIndex == low||target > arr[midIndex - 1])
                    return midIndex;
                high = midIndex - 1;
            }else{
                //target == arr[midIndex]；直接返回midIndex
                return midIndex;
            }
        }
    }


}
