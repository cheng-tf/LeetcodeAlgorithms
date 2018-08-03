package Others;


import java.util.Scanner;

public class ScannerDemo {

    public  static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()) {
            String str = scanner.next();
            System.out.println("Input Str : "+str);
            if(str.equals("exit")) {
                scanner.close();
                System.out.println("Scanner is closed!");
            }
        }

    }

}