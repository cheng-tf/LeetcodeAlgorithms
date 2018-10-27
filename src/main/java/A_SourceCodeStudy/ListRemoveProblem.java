package A_SourceCodeStudy;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;

public class ListRemoveProblem {
    /**
     *
     */
    @Test
    public void removeOneItem() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("AA");
        list.add("BBB");
        list.add("CCCC");
        list.add("DDDD");
        list.add("EEE");

        /*//1. 普通for循环：存在漏删的情况
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).length() == 4) {
                list.remove(i);
            }
        }
        for (String s : list) System.out.print(s + ",");//AA,BBB,DDDD,EEE,*/

    /*    //2. foreach循环：删除元素后必须break跳出，否则报出异常java.util.ConcurrentModificationException
        for (String s : list) {
            if (s.length() == 4) {
                list.remove(s);
                break;
            }
        }
        for (String s : list) System.out.print(s + ",");//AA,BBB,DDDD,EEE,*/
        //3. 迭代器Iterator:完美
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().length() == 4) {
                iterator.remove();
            }
        }
        for (String s : list) System.out.print(s + ",");//AA,BBB,EEE,
        // 4. 创建新对象
        ArrayList<String> newList = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).length() < 3) {//条件为需要保留元素的条件
                newList.add(list.get(i));
            }
        }
        list = newList;
        for (String s : list) System.out.print(s + ",");//AA,

    }
}
