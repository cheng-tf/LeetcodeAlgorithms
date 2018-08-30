package JianzhiOfferAlgorithms;

import org.junit.Test;

import java.util.Arrays;

public class JianzhiOffer_61_PlayingCardsIsContinuous {

    /*************剑指Offer61:扑克牌中的顺序********************************/

    /**
     * Easy
     * 题目描述
     * LL今天心情特别好,因为他去买了一副扑克牌,发现里面居然有2个大王,2个小王(一副牌原本是54张^_^)...
     * 他随机从中抽出了5张牌,想测测自己的手气,看看能不能抽到顺子,如果抽到的话,他决定去买体育彩票,嘿嘿！！
     * “红心A,黑桃3,小王,大王,方片5”,“Oh My God!”不是顺子.....LL不高兴了,他想了想,决定大\小 王可以看成任何数字,
     * 并且A看作1,J为11,Q为12,K为13。上面的5张牌就可以变成“1,2,3,4,5”(大小王分别看作2和4),“So Lucky!”。
     * LL决定去买体育彩票啦。 现在,要求你使用这幅牌模拟上面的过程,然后告诉我们LL的运气如何，
     * 如果牌能组成顺子就输出true，否则就输出false。为了方便起见,你可以认为大小王是0。
     *
     * 思路分析：首先对数组进行排序，然后统计大小王即0出现的个数zeroNum;
     *           然后将后续排序的间隙统计出来gapNum，
     *           若gapNum>=zeroNum，则返回true，否则false。
     */
    public boolean isContinuous(int [] numbers) {
        if(numbers == null||numbers.length == 0) return false;
        Arrays.sort(numbers);
        int zeroNum = 0,gapNum = 0;
        int index = -1;
        while(numbers[++index] == 0) zeroNum++;
        while(index <  numbers.length-1){
            int current = numbers[index],next = numbers[index+1];
            if(current == next) return false;
            if(current+1 != next) gapNum += next-current-1;
            index++;
        }
        return zeroNum>=gapNum;
    }


    //测试
    @Test
    public void test(){
        int[] numbers = {1,3,5,0,0};
        boolean result = isContinuous(numbers);
        System.out.println("result = " + result);
    }

}
