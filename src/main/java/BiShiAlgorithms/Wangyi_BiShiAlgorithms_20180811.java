package BiShiAlgorithms;
import java.util.Scanner;

public class Wangyi_BiShiAlgorithms_20180811 {

        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
            String firstLine = in.nextLine();
            String secondLine = in.nextLine();
            String thirdLine = in.nextLine();
            int[] firstNums = helper(firstLine);
            int[] secondNums = helper(secondLine);
            int[] thirdNums = helper(thirdLine);
            if(firstNums[0]==0)
                System.out.println(0);
            int sum = 0;
            //计算所有为1的总分数
            for(int i = 0;i < firstNums[0];i++){
                if(thirdNums[i]==1){
                    sum += secondNums[i];
                }
            }
            if(firstNums[1]==1)
                System.out.println(sum);
            //用于遍历所有
            int sumBase = 0;
            for (int j = 0;j < firstNums[0]; j++ ){//遍历所有
                if(thirdNums[j] == 1) continue;
                //下面是 为0的
                int count = firstNums[1];
                int sum2 = 0;
//                int sum2 = secondNums[j];
                for(int k = 0;k<count&&j+k<firstNums[0];k++){
                    if(thirdNums[j+k] == 0){
                        sum2 += secondNums[j+k];
                    }
                }
                if(sum2 > sumBase) sumBase = sum2;
            }
            System.out.println(sum+sumBase);
        }

        public static int[] helper(String line){
            String[] lineStrs = line.split(" ");
            int[] nums = new int[lineStrs.length];
            for(int i = 0;i<lineStrs.length;i++){
                nums[i] = Integer.valueOf(lineStrs[i]);
            }
            return nums;
        }

}
