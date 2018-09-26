package BiShiAlgorithms;

import java.util.Scanner;

public class KuaiShou_0925 {

    public static void main1(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String numStr = scanner.nextLine();
        String line = scanner.nextLine();
        int num = Integer.parseInt(numStr);
        if(num <= 1)
            System.out.println(0);
        String[] numsStr = line.split(" ");
        int[] nums = new int[num];
        for(int i = 0;i < num;i++)
            nums[i] = Integer.parseInt(numsStr[i]);
        int i = 0, j = num -1;
        int preSum = nums[i];
        int postSum = nums[j];
        int result = 0;
        while(i < j){//i==j则跳出循环了
            if(preSum == postSum){
                if (preSum >= result )
                    result = preSum;
                preSum += nums[++i];
            }else if(preSum < postSum){
                preSum += nums[++i];
            }else{
                postSum += nums[--j];
            }
        }
        System.out.println(result);
    }


}
