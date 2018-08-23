package SortAlgorithms;

import java.util.Arrays;

public class JianzhiOffer_xx_InversePairsBasedOnMergeSort {

    public int InversePairs(int [] array) {
        if(array == null||array.length <= 1) return 0;
        int[] copyArray = Arrays.copyOf(array,array.length);
        int count = countInversePairs(array,copyArray,0,array.length-1);
        return 0;
    }

    public int countInversePairs(int[] array,int[] copyArray,int low,int high){
        if(low == high){//复制这一个即可
            copyArray[low] = array[low];
        }
        int halfLen = (high - low + 1)>>1;//一般的长度
        countInversePairs(array,copyArray,low,low+ halfLen -1);
        countInversePairs(array,copyArray,low+halfLen,high);

        return 0;
    }
}
