package ThreadValves;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

public class ThreadValvesDemo {

    @Test
    public void test() {
        CountDownLatch cdl = new CountDownLatch(1);
        CountDownLatchDemo yt1 = new CountDownLatchDemo("张三",cdl);
        CountDownLatchDemo yt2 = new CountDownLatchDemo("李四",cdl);
        yt1.start();
        yt2.start();
        cdl.countDown();//发出开始信号：线程一起开始运行
    }
}
