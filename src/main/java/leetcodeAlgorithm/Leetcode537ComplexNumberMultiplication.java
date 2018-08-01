package leetcodeAlgorithm;

import org.junit.Test;

public class Leetcode537ComplexNumberMultiplication {
    /*
    https://leetcode.com/problems/complex-number-multiplication/description/
Given two strings representing two complex numbers.
You need to return a string representing their multiplication. Note i*i = -1 according to the definition.
Example 1:
Input: "1+1i", "1+1i"
Output: "0+2i"
Explanation: (1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i, and you need convert it to the form of 0+2i.
Example 2:
Input: "1+-1i", "1+-1i"
Output: "0+-2i"
Explanation: (1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i, and you need convert it to the form of 0+-2i.
Note:
The input strings will not have extra blank.
The input strings will be given in the form of a+bi, where the integer a and b will both belong to the range of [-100, 100]. And the output should be also in this form.
     */

    /*
class Solution {
    public String complexNumberMultiply(String a, String b) {
        String[] ab = a.split("\\+");
        int a1 = Integer.parseInt(ab[0]);
        int b1 = Integer.parseInt(ab[1].substring(0,ab[1].length()-1));
        String[] ab2 = b.split("\\+");
        int a2 = Integer.parseInt(ab2[0]);
        int b2 = Integer.parseInt(ab2[1].substring(0,ab2[1].length()-1));
        //三次实数乘法实现复数相乘
        int C1 = Math.multiplyExact(a1,b2);
        int C2 = Math.multiplyExact(b1,a2);
        int C3 = Math.multiplyExact(a1+b1,a2-b2);
        return C3+C1-C2+"+"+(C1+C2)+"i";
    }
}
*/
/*
class Solution {
    public String complexNumberMultiply(String a, String b)
    {
        String x[] = a.split("\\+");
        String y[] = b.split("\\+");
        int a_real = Integer.parseInt(x[0]);
        int a_img = Integer.parseInt(x[1].substring(0, x[1].length()-1));
        int b_real = Integer.parseInt(y[0]);
        int b_img = Integer.parseInt(y[1].substring(0, y[1].length()-1));
        return (a_real * b_real - a_img * b_img) + "+" + (a_real * b_img + a_img * b_real) + "i";
    }
}
*/
    class Solution {
        public String complexNumberMultiply(String a, String b) {
            int plusA = a.indexOf("+");
            int plusB = b.indexOf("+");
            int nA = a.length();
            int nB = b.length();

            int aReal = Integer.valueOf(a.substring(0, plusA));
            int bReal = Integer.valueOf(b.substring(0, plusB));

            int aImg = Integer.valueOf(a.substring(plusA + 1, nA - 1));
            int bImg = Integer.valueOf(b.substring(plusB + 1, nB - 1));

            int real = aReal * bReal + -1 * aImg * bImg;
            int img = aReal * bImg + bReal * aImg;

            return real + "+" + img + "i";

        }
    }

    @Test
    public  void solutionTest() {
        String a = "1+1i";
        String b = "1+-2i";
        System.out.println("("+a+")*("+b+") = " + new Solution().complexNumberMultiply(a,b));
    }

}
