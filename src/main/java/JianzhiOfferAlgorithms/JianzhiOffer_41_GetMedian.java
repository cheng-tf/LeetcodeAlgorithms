package JianzhiOfferAlgorithms;

import org.junit.Test;

import java.util.ArrayList;

public class JianzhiOffer_41_GetMedian {
    /*******************************JianzhiOffer_41_GetMedian****************/

    /**
     * JianzhiOffer_41_GetMedian
     * DateTime: 2018-11-10 17:51
     * 题目描述：
     * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，
     * 那么中位数就是所有数值排序之后位于中间的数值。
     * 如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
     * 我们使用Insert()方法读取数据流，使用GetMedian()方法获取当前读取数据的中位数。
     *
     * 思路分析：
     * 方法1:基于链表ArrayList实现。
     * 考虑到ArrayList的查询快，插入慢，因此对于GetMedian快，而Insert慢些；
     * 而利用LinkedList的查询慢，插入快，因此GetMedian慢，而Insert中也有查询过程，Insert也会慢。
     * 综上考虑，选用ArrayList。
     * 方法2:基于堆Heap：大顶堆和小顶堆实现。
     *       由于目前向堆中添加元素知识比较匮乏，因此暂时代码没有实现。
     * 基本思想：采用大顶堆和小顶堆共同实现。
     * (注意下面说的总数指的是当前大顶堆和小顶堆元素之和)
     * 当总数为偶数时，向小顶堆中添加元素，为奇数时，向大顶堆中添加元素。
     * 必须保证小顶堆中顶元素(即最小元素)大于大顶堆中的任意元素，即大于大顶堆中的顶元素即可。
     * 当总数为偶数，需要插入到小顶堆中，但是却小于大顶堆中的顶元素，该如何处理呢？
     * 首先将该元素插入到大顶堆中，然后再取出大顶堆中的顶元素，插入到小顶堆中即可。
     * 同理，当总数为奇数，需要插入到大顶堆中，但是却大于小顶堆中的顶元素，该如何处理呢？
     * 首先将该元素插入到小顶堆中，然后再取出小顶堆中的顶元素，插入到大顶堆中即可。
     * 获取中位数：若总数为偶数，取小顶堆和大顶堆的顶元素的平均值；
     *             若总数为奇数，取小顶堆的顶元素即可。
     */

    /**
     * 基于链表ArrayList实现
     */
    private ArrayList<Integer> list = new ArrayList<Integer>();

    /**
     * 向列表中插入数num
     */
    public void Insert(Integer num) {
        boolean isInsert = false;
        for (int i = 0; i < list.size(); i++) {
            if (num <= list.get(i)) {
                list.add(i, num);
                isInsert = true;
                break;//不要忘记
            }
        }
        if (!isInsert) list.add(num);//最后添加num
    }

    /**
     * 获取中位数
     */
    public Double GetMedian() {
        if (list.size() == 0) return Double.valueOf(0);
        int mid = list.size() >> 1;
        if ((list.size() & 1) == 0)
            return Double.valueOf(((double) list.get(mid - 1) + (double) list.get(mid)) / 2);
        else
            return Double.valueOf(list.get(mid));
    }

    //测试
    @Test
    public void test() {
        int[] nums = {2, 3, 4, 1, 0, 2, 3, 5, 2, 6, 5, 3, 6};
        for (int i = 0; i < nums.length; i++) {
            Insert(nums[i]);
            System.out.println(list.toString());
            System.out.println("Median = " + GetMedian());
        }
    }
}
