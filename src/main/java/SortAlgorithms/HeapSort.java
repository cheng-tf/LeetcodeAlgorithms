package SortAlgorithms;

import org.junit.Test;

import java.util.Arrays;

public class HeapSort {

    /************************实现堆排序***************************/

    /**
     * 堆排序
     * Date:2018-11-09 22:35
     * 堆排序的主要思想：
     * 根据大顶堆的特点，堆的顶元素为整个堆的最大值，因此，首先就是构建大顶堆，
     * 然后循环执行：将顶元素与有效堆的最后一个元素交换，再次调整堆；
     * 注意：循环一次，堆的长度减1，因为堆顶元素移动最后，最后一个元素不参与下次堆的调整。
     * 从小到大排序采用大顶堆；从大到小排序采用小顶堆。
     * 堆排序的代码实现主要分成两部分：
     * A. 构建大顶堆；(重点)
     * B. for循环执行：堆的顶节点值与最后一个节点值交换，并调整大顶堆；
     * 堆排序的核心方法：adjustHeap；大顶堆的初始化以及后续大顶堆的调整都依赖adjustHeap方法；
     * adjustHeap方法功能：
     * 在大顶堆已经构建的基础上，当改变了某个非叶子节点的时候，此时调整该非叶子节点，
     * 但是如果将该非叶子节点的值与左或右子节点的值进行交换，且左右子节点也是非叶子节点，
     * 此时仍需要递进地调整交换了的左或右子节点,直至到达不需交换或是叶子节点；否则，直接break。
     * <p>
     * 1. 大顶堆的初始化：
     * adjustHeap方法本来是在大顶堆已经建立的基础上，调整堆的，而大顶堆的初始化是通过从下而上，
     * 对每一个非叶子节点都执行一次adjustHeap,因此后一次调用在前一次执行的基础之上，因此满足adjustHeap的条件，
     * 最后是调整顶节点，因此最终完成大顶堆的初始化。
     * 具体实现过程： 依次从最后一个非叶子节点向上遍历，进行调整；
     * 假如节点个数为N，则最后一个非叶子节点序号为N/2-1,即N/2个非叶子节点，
     * 也就是需要调用N/2次adjustHeap方法；从N/2-1到0.
     * 为什么最后一个叶子节点的序号肯定为N/2-1呢？
     * 证明：因为堆是一个完全二叉树，父节点的编号为i，则左子节点为2*i+1，右子节点为2*i+2。
     * 假设最后一个非叶子节点的编号为k，则其要么只有左子节点，要么左、右子节点都有。
     * ①只有左子节点：左子节点编号为2*k+1,此时N=2*k+1+1;那么N/2-1 = k；
     * ②左右子节点都存在：右子节点编号为2*k+2,此时N=2*k+2+1，那么N/2-1 = k;
     * 综合①和②，N/2-1=k正好是最后一个非叶子节点的编号。
     * 2.时间复杂度的分析：
     * * 时间复杂度：最好、最坏、平均都是N*logN；不稳定；
     * * 时间复杂度分析：初始构建大顶堆的时间为：(从下往上构建)
     * * 后续每次将大顶堆的顶元素移动到最后，然后调整大顶堆；从上往下。
     */

    /**
     * 堆排序heapSort
     */
    public void heapSort(int[] arr) {
        //1.构建大顶堆：从第一个非叶子结点从下至上调整堆结构
        for (int i = (arr.length >> 1) - 1; i >= 0; i--)
            adjustHeap(arr, i, arr.length);
        //2.交换堆顶元素与末尾元素并调整堆结构
        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr, 0, i);//将堆顶元素与末尾元素进行交换
            adjustHeap(arr, 0, i);//重新对堆进行调整
        }
    }

    /**
     * 从非叶子节点i开始，调整大顶堆；
     * 先是 k1 = 2*i+1;  k2 = 2*k1+1; ......
     * 首先比较左右子节点，利用leftIndex指向较大者，
     * 然后与根节点比较，若大于根节点则将该较大值放入根节点处，并更新rootIndex和leftIndex，
     * 否则，直接break退出循环。
     * 最后，记得将最初的根节点的值置入此时的rootIndex处。
     * 其实，就是把父节点值尽可能往下移动，较大的值往上移动。
     */
    public void adjustHeap(int[] arr, int rootIndex, int len) {
        int rootValue = arr[rootIndex];//先取出根节点值
        int leftIndex = rootIndex * 2 + 1;
        while (leftIndex < len) {
            //找出左右子节点的最大值
            if (leftIndex + 1 < len && arr[leftIndex + 1] > arr[leftIndex])
                leftIndex = leftIndex + 1;//更新为较大者的索引
            if (arr[leftIndex] > rootValue) {//大于根节点值，需要交换，否则不需要
                arr[rootIndex] = arr[leftIndex];
                rootIndex = leftIndex;
                leftIndex = rootIndex * 2 + 1;
            } else
                break;//左右子节点都小于等于根节点，直接break
        }
        arr[rootIndex] = rootValue;//关键：最后向rootIndex处填上rootValue值
    }

    /**
     * 交换数组中的两个元素
     */
    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    //测试
    @Test
    public void test() {
        int[] arr = {-2, 100, 9, 8, 7, 6, 11, 5, 4, 3, 2, 1};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
