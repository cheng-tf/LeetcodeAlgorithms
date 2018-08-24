package JianzhiOfferAlgorithms;

import org.junit.Test;

import java.util.Arrays;

public class JianzhiOffer_66_BuildMultiplyArray {

    /**********剑指Offer66：构建乘积数组********************************/
    /**
     * 题目描述
     * 给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],
     * 其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。
     * 不能使用除法。
     *
     * 思路分析：
     * 方法1：如果可以用除法，只需要计算所有元素的乘积，然后分别处以对应的元素即可；
     * 方法2：依次求N-1个元素的乘积，时间复杂度就是O(N^2);
     * 方法3：将B数组每个元素拆成两部分，i之前元素乘积和i之后元素的乘积；
     *        只要找到对应的这两部分，对应相乘即可。为了不开辟额外空间，先利用B数组
     *        依次保存i之前的元素，然后再依次构造i之后的乘积，直接相乘即可。
     *        利用数学描述：C[i] = A[0]*A[1]*...*A[i-1];
     *                      D[i] = A[i+1]*A[i+2]*...*A[len-1];
     *                      B[i] = C[i]*D[i];
     */

    public int[] multiply(int[] A) {
        if(A == null) return null;
        int[] B = new int[A.length];
        B[0] = 1;
        for(int i = 1;i < A.length;i++){
            B[i] = B[i-1]*A[i-1];//构建C数组
        }
//        B[A.length-1] = B[A.length-1]*1;
        for(int j = A.length-2,temp = 1;j>=0;j--){
            temp *= A[j+1];
            B[j] *= temp;
        }
        return B;
    }
    
    
    //测试
    @Test
    public void test(){
        int[] A = {1,2,3,4,5,6};
        int[] B  = multiply(A);
        System.out.println("Arrays.toString(B) = " + Arrays.toString(B));
    }

}
