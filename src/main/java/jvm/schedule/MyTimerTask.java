package jvm.schedule;

import java.util.TimerTask;

public class MyTimerTask extends TimerTask {
    @Override
    public void run() {
        System.out.println("TimerTask...");
    }
}
