package ThreadValves;

import java.util.Arrays;

public class ReentrantLockDemo {
    public static void main(String[] args) {
        int[] a = {1,2};
        Arrays.sort(a);
        Arrays.sort(new int[]{10,11,13});
        aaa(a);
    }

    private static void aaa(int[] a) {
        aaa(a);
    }
}
