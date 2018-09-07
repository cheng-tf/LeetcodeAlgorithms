package BiShiAlgorithms;


        import java.util.Arrays;
        import java.util.LinkedList;
        import java.util.Scanner;

public class KeDaXunFei_0907 {

    public class Main {
    }
//        public static void main2(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int num = Integer.parseInt(scanner.nextLine());
//        int[] studNums = new int[num];
//        int[] averages = new int[num];
//        String[] scores = new String[num];
//        for(int i = 0;i < num;i++){
//            String line = scanner.nextLine();
//            String[] strs = line.split(" ");
//            studNums[i] = Integer.parseInt(strs[0]);
//            averages[i] = Integer.parseInt(strs[1]);
//            scores[i] = scanner.nextLine();
//        }
//        int[] results = new int[num];
//        for(int k = 0;k < num;k++) {
//                String[] score = scores[k].split(" ");
//                int[] s = new int[score.length];
//                int sum = 0;
//                for (int i = 0; i < score.length; i++) {
//                    s[i] = Integer.parseInt(score[i]);
//                    sum += s[i];//总分
//                }
//                Arrays.sort(s);
//                int d = s.length * averages[k]-sum;
//                int count = 0;
//                for(int i = 0;i < s.length;i++){
//                    if(d <= 0 ){
//                        results[k] = count;
//                        break;
//                    }
//                    d +=  s[i]-100;
//                    count++;
//                }
//            }
//        for(int result : results)
//            System.out.println(result);
//        }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str1 = scanner.nextLine();
        int num = Integer.parseInt(str1);
        String str = scanner.nextLine();
        String[] nums = str.split(" ");
        int[] ns = new int[num];
        for(int i = 0;i < ns.length;i++){
            ns[i] = Integer.parseInt(nums[i]);
        }
        int result = 0;
        int i = 0,j = i+1;
        int count = 0;
        while( i < num-1 ){
            if(ns[i] == -1) continue;
            if(ns[j] == -1) j++;
            int pre = ns[i];
            while(j < num-1 && pre > ns[j]&&ns[j]!=-1){//-1表示已经被杀
                pre = ns[j];
                ns[j] = -1;
                j++;
                count++;
            }
            i++;
            if(i == num-1){
                result++;
                i = 0;
                if(count == 0)
                    break;
                count=0;
            }
        }
        System.out.println(result);
    }

}
