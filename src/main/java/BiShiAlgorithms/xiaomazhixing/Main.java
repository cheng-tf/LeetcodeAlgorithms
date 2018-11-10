package BiShiAlgorithms.xiaomazhixing;

import org.junit.Test;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        process();
    }


    public static void process() {
        Scanner scanner = new Scanner(System.in);
        String line1 = scanner.nextLine();
        String line2 = scanner.nextLine();
        String line3 = scanner.nextLine();
        Fenshu fenshu1 = parseFenshu(line1);
        Fenshu fenshu2 = parseFenshu(line3);
        Fenshu result = new Fenshu();
        if ("+".equals(line2)) {
            fenshu1 = fenshu1.transformToJia(fenshu1);
            fenshu2 = fenshu2.transformToJia(fenshu2);
            result.setFenmu(fenshu1.fenmu * fenshu2.fenmu);
            result.setFenzi(fenshu1.fenzi * fenshu2.fenmu + fenshu2.fenzi * fenshu1.fenmu);
            result = yuefen(result);
            result = result.transformToZhen(result);
            System.out.println(result.toString());
        } else if ("-".equals(line2)) {//没有验证，有bug
            fenshu1 = fenshu1.transformToJia(fenshu1);
            fenshu2 = fenshu2.transformToJia(fenshu2);
            result.setFenmu(fenshu1.fenmu * fenshu2.fenmu);
            result.setFenzi(fenshu1.fenzi * fenshu2.fenmu - fenshu2.fenzi * fenshu1.fenmu);
            result = yuefen(result);
            result = result.transformToZhen(result);
            System.out.println(result.toString());
        } else if ("*".equals(line2)) {//有bug
            fenshu1 = fenshu1.transformToJia(fenshu1);
            fenshu2 = fenshu2.transformToJia(fenshu2);
            result.setFenmu(fenshu1.fenmu * fenshu2.fenmu);
            result.setFenzi(fenshu1.fenzi * fenshu1.fenzi);
            result = yuefen(result);
            result = result.transformToZhen(result);
            System.out.println(result.toString());
        } else {//有bug
            fenshu1 = fenshu1.transformToJia(fenshu1);
            fenshu2 = fenshu2.transformToJia(fenshu2);
            result.setFenzi(fenshu1.fenzi * fenshu1.fenmu);
            result.setFenmu(fenshu1.fenmu * fenshu2.fenzi);
            result = yuefen(result);
            result = result.transformToZhen(result);
            System.out.println(result.toString());
        }
    }

    /**
     * 约分函数
     * @param fenshu
     * @return
     */
    public static Fenshu yuefen(Fenshu fenshu) {
        boolean isFu = fenshu.fenzi < 0;
        fenshu.setFenzi(Math.abs(fenshu.fenzi));
        int gcd1 = gcd(Math.abs(fenshu.fenzi), Math.abs(fenshu.fenmu));
        fenshu.setFenmu(fenshu.fenmu / gcd1);
        fenshu.setFenzi(fenshu.fenzi / gcd1);
        if (isFu)
            fenshu.setFenzi(-fenshu.fenzi);
        return fenshu;
    }

    /**
     * 将字符串转换成对象
     * @param str
     * @return
     */
    public static Fenshu parseFenshu(String str) {
        Fenshu fenshu = new Fenshu();
        String fenStr;
        if (str.contains(" ")) {
            String[] strs = str.split(" ");
            fenshu.setVal(Integer.parseInt(strs[0]));
            fenStr = strs[1];
        } else {
            fenStr = str;
        }
        String[] strs = fenStr.split("/");
        fenshu.setFenzi(Integer.parseInt(strs[0]));
        fenshu.setFenmu(Integer.parseInt(strs[1]));
        if (fenshu.val < 0) {
            fenshu.setFu(true);
            fenshu.setVal(-fenshu.val);
        }
        return fenshu;
    }


    /**
     * 求最大公约数
     * @param num1
     * @param num2
     * @return
     *
     */
    public static int gcd(int num1, int num2) {
        if (num1 < num2) {
            int temp = num1;
            num1 = num2;
            num2 = temp;
        }
        int remainder = num1 % num2;
        while (remainder != 0) {
            num1 = num2;
            num2 = remainder;
            remainder = num1 % num2;
        }
        return num2;
    }

    /**
     * 构造分数对象
     */
    public static class Fenshu {
        boolean isFu;
        int val;
        int fenzi;
        int fenmu;

        public Fenshu(int val, int fenzi, int fenmu, boolean isFu) {
            this.val = val;
            this.fenzi = fenzi;
            this.fenmu = fenmu;
            this.isFu = isFu;
        }

        public void setFenzi(int fenzi) {
            this.fenzi = fenzi;
        }

        public void setFenmu(int fenmu) {
            this.fenmu = fenmu;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public void setFu(boolean fu) {
            isFu = fu;
        }

        public Fenshu() {
        }

        /**
         * 转换成真分数
         * @param fenshu1
         * @return
         */
        public Fenshu transformToZhen(Fenshu fenshu1) {
            boolean isFu = fenshu1.fenzi < 0;
            if (isFu) {
                fenshu1.setFu(true);
                fenshu1.setFenzi(Math.abs(fenshu1.fenzi));
            }
            fenshu1.setVal(fenshu1.fenzi / fenshu1.fenmu);
            fenshu1.setFenzi(fenshu1.fenzi % fenshu1.fenmu);
            return fenshu1;
        }

        /**
         * 转换成假分数
         * @param fenshu1
         * @return
         */
        public Fenshu transformToJia(Fenshu fenshu1) {
            fenshu1.setFenzi(fenshu1.val * fenshu1.fenmu + fenshu1.fenzi);
            if (fenshu1.isFu) {
                fenshu1.setFenzi(-fenshu1.fenzi);
                fenshu1.setFu(false);
            }
            fenshu1.setVal(0);
            return fenshu1;
        }

        /**
         * 打印分数
         * @return
         */
        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (this.val != 0) {
                if (this.isFu)
                    sb.append("-");
                sb.append(this.val);
            }
            if (this.fenzi == 0) return sb.toString();
            sb.append(" ").append(this.fenzi).append("/").append(this.fenmu);
            return sb.toString();
        }

    }
}

