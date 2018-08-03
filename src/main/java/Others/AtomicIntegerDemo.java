package Others;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicIntegerDemo {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(100);
        System.out.println(atomicInteger.get());
        atomicInteger.decrementAndGet();

        Integer i = new Integer(10);
        AtomicStampedReference<Integer> re = new AtomicStampedReference<Integer>(10, 0);
        re.compareAndSet(10, 55, 0, 4);
        System.out.println(re.getStamp());
    }
}
