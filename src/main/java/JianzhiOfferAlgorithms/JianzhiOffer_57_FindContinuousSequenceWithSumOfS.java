package JianzhiOfferAlgorithms;

import org.junit.Test;

import java.util.ArrayList;

public class JianzhiOffer_57_FindContinuousSequenceWithSumOfS {

    /************剑指Offer57:和为S的连续正数序列******************************/

    /**
     * 题目介绍：
     * 题目描述
     * 小明很喜欢数学,有一天他在做数学作业时,要求计算出9~16的和,他马上就写出了正确答案是100。
     * 但是他并不满足于此,他在想究竟有多少种连续的正数序列的和为100(至少包括两个数)。
     * 没多久,他就得到另一组连续正数和为100的序列:18,19,20,21,22。现在把问题交给你,你能不能也很快的找出所有和为S的连续正数序列? Good Luck!
     * 输出描述:
     *      输出所有和为S的连续正数序列。序列内按照从小至大的顺序，序列间按照开始数字从小到大的顺序
     *
     * 思路分析： 首先两个正数最小为3，则S小于3则返回空list；至少是连个正数，则N-1+N<=S;则N<=(S+1)/2,循环终止条件就是right大于(N+!)/2
     *            定义两个变量left和right，伸缩移动，有个sum保存left到right的和，
     *            若sum小于S，则right向前移动，同时sum加上新的right值；
     *            若sum大于S，则left向前移动，同时sum减去旧的left值；
     *            若sum等于S，则left至right就是一个结果，存到结果集合中；然后right就行移动一位，left向前移动两位。
     */

    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int S) {
        ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>();
        if(S < 3) return results;//连个正数最小为3
        int left = 1,right = 2,sum = 3;
        int maxRight = (S+1)>>1;
        while(right <= maxRight){//&&left<right是多余的，因为任何小于maxRight的连续两个数之和肯定小于S，
            // 下一步肯定是right右移,不可能让left和right重合更不可能left超过right
            if(sum < S){
                sum += ++right;//加上新的right值
            }else if(sum > S){
                sum -= left++;//减去旧的left值
            }else{
                ArrayList<Integer> result = new ArrayList<Integer>();
                for(int i = left;i <= right;i++)
                    result.add(i);
                results.add(result);
                sum += ++right;
                sum -= left++;
                sum -= left++;
            }
        }
        return results;
    }


    @Test
    public void test(){
        ArrayList<ArrayList<Integer>> results = FindContinuousSequence(100);
        for(ArrayList<Integer> result : results)
            System.out.println("result = " + result);
    }
}
