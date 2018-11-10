package ArrayAlgorithms;

import org.junit.Test;

public class JianzhiOffer_4_FindElementInTwoDimensionalArray {
    /*****************剑指Offer4:在二维数组中的查找**************************************/

    /**
     * 2018-11-01 第二遍刷
     * 题目描述
     * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，
     * 每一列都按照从上到下递增的顺序排序。
     * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     *
     * 思路分析：
     * 方法1：广度优先搜索。从(0,0)处开始搜索，可以按照向下和向右搜索，广度优先搜索，但是效率较低。
     * 方法2：从右上角或左下角开始搜索，一次可以删除一行或一列，效率更高。
     *        1. 从二维数组的右上角开始判断，每一次判断都可以删除一行或一列；
     *        2. 从二维数组的左下角开始判断，每一次判断都可以删除一行或一列。
     * 以右上角为例：
     *      A. 若相等，则返回true；
     *      B. 若大于targe，则删除当前列；
     *      C. 若小于target，则删除当前行。
     */

    /**
     * 方法1. 右上角
     */
    public boolean Find(int target, int[][] array) {
        if (array == null || array.length == 0 || array[0].length == 0) return false;//保证行数不为0
        int rows = array.length, cols = array[0].length;
        int i = 0, j = cols - 1;
        while (i < rows && j >= 0) {
            if (target > array[i][j]) i++;
            else if (target < array[i][j]) j--;
            else return true;
        }
        return false;
    }

    /**
     * 方法2. 左下角
     */
    public boolean Find2(int target, int[][] arr) {
        if (arr == null || arr.length == 0 || arr[0].length == 0) return false;//保证行数不为0
        int rows = arr.length, cols = arr[0].length;
//        for (int i = rows - 1, j = 0; i >= 0 && j < cols; ) {
        int i = 0, j = cols - 1;
        while (i < rows && j >= 0) {
            if (target > arr[i][j]) j++;
            else if (target < arr[i][j]) i--;
            else return true;
        }
        return false;
    }

    //测试
    @Test
    public void test() {
        int[][] array = {{1, 2, 3}, {4, 7, 8}, {5, 9, 10}, {6, 11, 12}};
        System.out.println("行数：" + array.length);
        System.out.println("列数：" + array[0].length);

        boolean result = Find(8, array);
        System.out.println("result = " + result);

        boolean result2 = Find(99, array);
        System.out.println("result2 = " + result2);

        boolean result3 = Find2(8, array);
        System.out.println("result3 = " + result3);

        boolean result4 = Find2(99, array);
        System.out.println("result4 = " + result4);
    }
}
