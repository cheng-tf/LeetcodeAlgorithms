package ThreadValves;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

public class ThreadValvesDemo {

    @Test
    public void test() {
        CountDownLatch cdl = new CountDownLatch(1);
        CountDownLatchDemo2 yt1 = new CountDownLatchDemo2("张三",cdl);
        CountDownLatchDemo2 yt2 = new CountDownLatchDemo2("李四",cdl);
        yt1.start();
        yt2.start();
        cdl.countDown();//发出开始信号：线程一起开始运行
    }
}
