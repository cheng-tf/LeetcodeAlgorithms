package BiShiAlgorithms;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Baidu_20180926 {

        @Test
        public void test2() {
            int numNodes = 4;
            List<Integer> values = new ArrayList<>();
            values.add(-1);
            values.add(2);
            values.add(3);
            values.add(2);
            List<List<Integer>> lists = new ArrayList<List<Integer>>();
            List<Integer> value1 = new ArrayList<>();
            value1.add(1);
            value1.add(2);
            List<Integer> value2 = new ArrayList<>();
            value2.add(1);
            value2.add(3);
            List<Integer> value3 = new ArrayList<>();
            value3.add(3);
            value3.add(4);

            lists.add(value1);
            lists.add(value2);
            lists.add(value3);
            int max = maxProduct(numNodes,values,lists);
            System.out.println("max = " + max);
        }

        /**
         * 思路分析
         * 节点个数：
         */
        public int maxProduct(int numNodes, List<Integer> values, List<List<Integer>> edges) {
            if(numNodes <= 1) return 0;
            if(values.size() != numNodes ) return 0;
            if(values.get(0) == 0) return 0;
            addCurrentMax(numNodes,values,edges);
            int value = values.get(0);
            int max = value*edges.get(0).get(2);
            for(int i = 1;i < edges.size()&&edges.get(i).get(0) == 1;i++){
                if(max < value*edges.get(i).get(2))
                    max = value*edges.get(i).get(2);
            }
            return max;
        }

        public void addCurrentMax(int numNodes, List<Integer> values, List<List<Integer>> edges){
            for(int i = edges.size()-1;i >= 0;i--) {
                int downValue = 1;
                int rightIndex = edges.get(i).get(1);
                for(int j = i + 1;j < edges.size();j++){
                    if(edges.get(j).get(0) == rightIndex){
                        int k = j;
                        while(k < edges.size() && edges.get(k).get(0) == rightIndex ){
                            if(edges.get(k).get(2) > downValue){
                                downValue = edges.get(k).get(2);
                            }
                            k++;
                        }
                        break;
                    }
                }
                    edges.get(i).add(values.get(edges.get(i).get(1)-1)*downValue);
                }
            }


//        /**
//         * 判断是否是叶子节点
//         */
//        boolean isLeefNode(int i,List<List<Integer>> edges){
//            List<Integer> list = edges.get(i);
//            int value = list.get(1);
//            for(int j = i+1;j<edges.size();j++){
//                if(j == edges.get(j).get(0)) return false;
//            }
//            return true;
//        }


    }

