package ThreadValves;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo2 extends Thread{

    private CountDownLatch cdl ;
    public CountDownLatchDemo2(String name,CountDownLatch cdl) {
        super(name);
        this.cdl = cdl;
    }

    @Override
    public void run() {
        try {
            cdl.await();
            for (int i = 1; i <= 50; i++) {
                System.out.println("" + this.getName() + "-----" + i);
                // 当i为30时，该线程就会把CPU时间让掉，让其他或者自己的线程执行（也就是谁先抢到谁执行）
                if (i == 30) {
                    this.yield();
                }
            }
        } catch (InterruptedException e) {
            cdl.countDown();
        }
    }



}