package JianzhiOfferAlgorithms;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class JianzhiOffer_MaxValueInSlidingWindows {
    /******************剑指Offer：滑动窗口的最大值***************************************/

    /**
     * 给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。
     * 例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，那么一共存在6个滑动窗口，
     * 他们的最大值分别为{4,4,6,6,6,5}； 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个：
     * {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}， {2,3,[4,2,6],2,5,1}，
     * {2,3,4,[2,6,2],5,1}， {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
     * <p>
     * 思路：基于一个双端队列实现滑动窗口SlidingWindow。
     * 在每个滑动窗口中，队列头部就是最大值的索引，这样每次取出队列头部元素即可获取当前滑动窗口的最大值；
     * 保持从头部到尾部是索引对应值从大到小的顺序：方法while循环从尾部取值，若小于当前值，则从尾部删除该元素，直到尾部元素大于当前值或队列为空；
     * 具体实现分成两部分：1.欢滑动窗口初始化；2.逐步移动滑动窗口。
     * 首先对滑动窗口初始化：while循环，队列非空且从队列尾部不断取值，若比当前值小，就从尾部删除，直到队列为空或队列值大于当前值；
     * 循环之后，添加当前值至队列尾部；因为当前值属于最年轻的元素，有可能在前面元素都溢出窗口的时候，是后续窗口的最大值，所以无论如何都要插入到队列中，只是因为年轻。
     * 然后逐步移动滑动窗口：首先将队列头部元素保存至结果list中，然后同初始化操作一样，从尾部逐渐取值判断，若比当前值小，则从尾部删除该值，直到队列为空或队列值大于当前值；
     * 需要对头部元素是否过期进行判断：这就是为什么保存索引而不保存元素值的原因，索引差用于判断队列头部元素是否过期。若过期，则删除头部元素即可；
     * 最后无论如何都要从尾部插入当前值，只是因为年轻。
     * 最后返回结果list即可。
     */
    public ArrayList<Integer> maxInWindows(int[] nums, int size) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        //1. 边界条件判断：健壮性（必须的）
        if (nums == null || nums.length == 0 || size <= 0 || nums.length < size) return result;
        ArrayDeque<Integer> deque = new ArrayDeque<Integer>();//双端队列
        //2. 初始化滑动窗口
        for (int index = 0; index < size; index++) {
            while (!deque.isEmpty() && nums[index] >= nums[deque.peekLast()])
                deque.removeLast();//从队列尾部逐渐清理比当前值小的元素
            deque.addLast(index);//保存最新的索引
        }
        //3. 滑动窗口逐步移动
        for (int index = size; index < nums.length; index++) {
            result.add(nums[deque.peekFirst()]);//保存当前窗口的最大值
            while (!deque.isEmpty() && nums[index] >= nums[deque.peekLast()])
                deque.removeLast();//从队列尾部逐渐清理比当前值小的元素
            if (!deque.isEmpty() && index - deque.peekFirst() >= size)
                deque.removeFirst();//删除溢出的队列头部元素
            deque.addLast(index);//保存最新的索引
        }
        result.add(nums[deque.peekFirst()]);//保存最后一个滑动窗口的最大值
        return result;
    }

    @Test
    public void test() {
        int[] nums = {2, 3, 4, 2, 6, 2, 5, 1};
        int size = 3;
        ArrayList<Integer> result = maxInWindows(nums, size);
        System.out.println(result.toString());

    }

    /********************网易内推笔试：瞌睡  利用滑动窗口解决*****************************************/
    //见BiShiAlgorithms  package


    /***************************剑指Offer:队列的最大值****************************************/
    /**
     * 队列的最大值
     * 定义一个队列并实现函数max得到队列里的最大值，要求函数max、push_back和pop_front的时间复杂度都是O(1)。
     */

    ArrayDeque<Integer> data = new ArrayDeque<Integer>();
    ArrayDeque<Integer> maxNums = new ArrayDeque<Integer>();
    int currentIndex;

    public void push_back(Integer val) {
        while (!maxNums.isEmpty() && val >= maxNums.peekLast())
            maxNums.addLast(val);
        data.addLast(val);
    }

    public int pop_front() {
        return data.peekFirst();
    }

    public int max() {
        return maxNums.peekFirst();
    }



}
