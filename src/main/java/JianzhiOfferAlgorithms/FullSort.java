package JianzhiOfferAlgorithms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.TreeSet;

public class FullSort {

    @Test
    public void test(){
//        int[] nums = {1,2,3};
        int[] nums = {1,2,3,4};
        ArrayList<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>();
        fullSortArray(nums,0,lists);
        //一共应该是n!种情况
        System.out.println("lists.size() = " + lists.size());
        System.out.println("lists = " + lists);
    }

/*******************************数字数组的全排列**************************************/
    /**
     *  全排列
     * @param nums  数组
     * @param start 开始索引
     * @param lists 结果集合
     */
    public void fullSortArray(int[] nums,int start,ArrayList<ArrayList<Integer>> lists){
        if(start == nums.length){
            ArrayList<Integer> list = new ArrayList<Integer>();
            for(int num : nums)
                list.add(num);
            lists.add(list);
//            System.out.println(Arrays.toString(nums));
        }
        for(int i = start;i<nums.length;i++){
            swap(nums,i,start);//交换位置
            fullSortArray(nums,start + 1,lists);//递归调用
            swap(nums,i,start);//复位
        }
    }
    //交换数组中两个位置的元素
    public void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /*******************************剑指Offer:字符串的全排列***************************/
    /**
     * 题目描述
     * 输入一个字符串,按字典序打印出该字符串中字符的所有排列。
     * 例如输入字符串abc,则打印出由字符a,b,c所能排列出来的
     * 所有字符串abc,acb,bac,bca,cab和cba。
     * 输入描述:
     * 输入一个字符串,长度不超过9(可能有字符重复),字符只包括大小写字母。
     *
     * 思路分析：
     *          锁定第一位，然后对后面N-1位置排序，同样，锁定第二位对N-2位排序......
     *          将第一位一次与后面N-1位交换位置，然后递归即可。
     *          这里要求：1.字典顺序，首先用TreeSet存储排序；
     *                    2.重复字符，交换字符时，需要判断。
     */

    /**
     * 第一题：假设没有重复字符的全排列(最简单的全排列)
     */
    public ArrayList<String> Permutation(String str) {
        ArrayList<String> list = new ArrayList<String>();
        if(str == null|| str.length()==0) return list;
        char[] chars = str.toCharArray();
        fullSort(list,chars,0);
        return list;
    }
    public void fullSort(ArrayList list,char[] chars,int start){
        if(start == chars.length){
            list.add(new String(chars));
            return;
        }
        for(int i = start;i<chars.length;i++){
            swap(chars,start,i);
            fullSort(list,chars,start+1);//一定是start+1，不是i+1
            swap(chars,start,i);
        }
    }

    /**
     * 第二题：假设有重复字符
     */
    public ArrayList<String> Permutation2(String str) {
        ArrayList<String> list = new ArrayList<String>();
        if(str == null|| str.length()==0) return list;
        char[] chars = str.toCharArray();
        fullSort2(list,chars,0);
        return list;
    }
    public void fullSort2(ArrayList list,char[] chars,int start){
        if(start == chars.length){
            list.add(new String(chars));
            return;
        }
        for(int i = start;i<chars.length;i++){
            if(i!= start && chars[i]==chars[start]) continue;
            swap(chars,start,i);
            fullSort2(list,chars,start+1);//一定是start+1，不是i+1
            swap(chars,start,i);
        }
    }

    /**
     * 第三题：假设有重复字符，且必须按照字典顺序输出字符串
     */
    public ArrayList<String> Permutation3(String str) {
        ArrayList<String> list = new ArrayList<String>();
        if(str == null|| str.length()==0) return list;
        char[] chars = str.toCharArray();
        TreeSet<String> set = new TreeSet<String>();
        fullSort3(set,chars,0);
        list.addAll(set);
        return list;
    }
    public void fullSort3(TreeSet<String> set,char[] chars,int start){
        if(start == chars.length){
            set.add(new String(chars));
            return;
        }
        for(int i = start;i<chars.length;i++){
            if(i!= start && chars[i]==chars[start]) continue;
            swap(chars,start,i);
            fullSort3(set,chars,start+1);//一定是start+1，不是i+1
            swap(chars,start,i);
        }
    }
    public void swap(char[] chars,int i,int j){
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }
    @Test
    public void test2(){

        String str = "aabc";
        ArrayList<String> list = Permutation(str);
        System.out.println("list = " + list);

        String str2 = "aca";
        ArrayList<String> list2 = Permutation2(str2);
        System.out.println("list2 = " + list2);

        String str3 = "abbc";
        ArrayList<String> list3 = Permutation3(str3);
        System.out.println("list3 = " + list3);
    }
}
