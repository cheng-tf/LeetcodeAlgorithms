package BiShiAlgorithms;

import java.util.Scanner;

public class Alibaba_20180813_TestAlgorithm {


    public static void main(String[] args) {
        Scanner scanner  = new Scanner(System.in);
        int n = Integer.valueOf(scanner.nextLine());
        String[] points = new String[n];
        for(int i = 0;i<n;i++)
            points[i] = scanner.nextLine();

        int[] distances = new int[n+1];
        String[] xy = points[0].split(",");
        int x1 = Integer.valueOf(xy[0]);
        int y1 = Integer.valueOf(xy[1]);
        distances[0] = x1+y1;
        for(int i = 1;i<n;i++){
            String[] xy2 = points[i].split(",");
            int x2 = Integer.valueOf(xy2[0]);
            int y2 = Integer.valueOf(xy2[1]);
            distances[i] = Math.abs(x2-x1)+ Math.abs(y2-y1);
            x1 = x2;
            y1 = y2;
        }
        String[] xy3 = points[n-1].split(",");
        int x3 = Integer.valueOf(xy3[0]);
        int y3 = Integer.valueOf(xy3[1]);
        distances[n] = x3+y3;
        int sum = 0;
//        System.out.println(Arrays.toString(distances));
        for(int d :distances)
            sum += d;
        System.out.println(sum);
    }

}
