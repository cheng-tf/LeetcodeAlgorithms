package jvm.schedule;

import java.util.TimerTask;

public class MyTimerTask2 extends TimerTask {
    @Override
    public void run() {
        System.out.println("Timer Task2......");
    }
}
