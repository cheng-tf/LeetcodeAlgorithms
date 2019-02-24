package jvm.schedule;

import org.junit.Test;

import java.io.IOException;
import java.util.Timer;

public class TimerDemo {
    @Test
    public void test() throws IOException {
        Timer timer = new Timer();
        timer.schedule(new MyTimerTask(), 1000, 5000);//5秒中执行一次
        timer.schedule(new MyTimerTask2(),2000,3000);//3秒执行一次
        timer.schedule(new MyTimerTask3(),3000,4000);//4秒执行一次
        System.in.read();
    }
}
