package SearchAlgorithm;

import org.junit.Test;

public class BinarySearch {

    @Test
    public void test(){
        int[] arr = {0,0,0,0,-1,2,8,32,54,89,100,100,100};
        int index = binarySearchFirst(arr,0,arr.length-1,0);
        System.out.println("index:"+index);

    }

    public int binarySearch(int[] arr,int low,int high,int value){
        while(low <= high){
            int middleIndex = (low+high)/2;
            if(value < arr[middleIndex]){
                high = middleIndex - 1;
            }else if(value > arr[middleIndex]){
                low = middleIndex + 1;
            }else{
                return middleIndex;//返回查找的索引
            }
        }
        return -1;//没有找到
    }
    public int binarySearchFirst(int[] arr,int low,int high,int value){
        while(low <= high){
            int middleIndex = (low+high)/2;
            if(value < arr[middleIndex]){
                high = middleIndex - 1;
            }else if(value > arr[middleIndex]){
                low = middleIndex + 1;
            }else{
                while(--middleIndex >= low &&arr[middleIndex] == value);
                return middleIndex+1;//返回查找的索引
            }
        }
        return -1;//没有找到
    }
    public int binarySearchLast(int[] arr,int low,int high,int value){
        while(low <= high){
            int middleIndex = (low+high)/2;
            if(value < arr[middleIndex]){
                high = middleIndex - 1;
            }else if(value > arr[middleIndex]){
                low = middleIndex + 1;
            }else{
                while(++middleIndex <= high && arr[middleIndex] == value);
                return middleIndex-1;//返回查找的索引
            }
        }
        return -1;//没有找到
    }

}

