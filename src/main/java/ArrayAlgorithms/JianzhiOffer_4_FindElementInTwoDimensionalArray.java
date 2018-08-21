package ArrayAlgorithms;

import org.junit.Test;

public class JianzhiOffer_4_FindElementInTwoDimensionalArray {


    /*****************剑指Offer4:在二维数组中的查找**************************************/

    /**
     * 题目描述
     * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，
     * 每一列都按照从上到下递增的顺序排序。
     * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     *
     * 思路分析：从二维数组的右上角或左下角开始判断，每一次判断都可以删除一行或一列。
     * 以右上角为例：
     *      A. 若相等，则返回true；
     *      B. 若大于targe，则删除当前列；
     *      C. 若小于target，则删除当前行。
     */

    /**
     * 右上角
     */
    public boolean Find(int target, int [][] array) {
        if (array == null || array.length == 0) return false;//保证行数不为0
        int rows = array.length,columns = array[0].length;
        for(int row = 0,column = columns-1;row < rows&&column >= 0;){
            if(array[row][column] > target){
                column--;
            }else if(array[row][column] < target){
                row++;
            }else{
                return true;
            }
        }
        return false;
    }
    /**
     * 左下角
     */
    public boolean Find2(int target, int [][] array) {
        if (array == null || array.length == 0) return false;//保证行数不为0
        int rows = array.length,columns = array[0].length;
        for(int row = rows-1,column = 0;row >= 0&&column < columns;){
            if(array[row][column] > target){
                row--;
            }else if(array[row][column] < target){
                column++;
            }else{
                return true;
            }
        }
        return false;
    }

    //测试
    @Test
    public void test(){
        int[][] array = {{1,2,3},{4,7,8},{5,9,10},{6,11,12}};
        System.out.println("行数："+array.length);
        System.out.println("列数："+array[0].length);
        
        boolean result = Find(8,array);
        System.out.println("result = " + result);

        boolean result2 = Find(99,array);
        System.out.println("result2 = " + result2);

        boolean result3 = Find2(8,array);
        System.out.println("result3 = " + result3);

        boolean result4 = Find2(99,array);
        System.out.println("result4 = " + result4);

     }

}
