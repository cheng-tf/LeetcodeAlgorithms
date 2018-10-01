package VolatileProblem;

import org.junit.Test;

public class VolatileDemo {

    /**
     * 验证目的：验证volatile的使用
     *
     * 主要是：容易忽略对象锁和类锁。
     *  主要是锁都是一个，则没问题
     *  但是如果是对象锁，则都不同。
     */

    volatile boolean flag = false;
    public synchronized void  execute(){
        if(flag){
            return;
        }
        System.out.println("启动成功");
        flag = true;
        return;
    }

    @Test
    public void test(){
       final VolatileDemo object = new VolatileDemo();
        for(int i  = 0;i < 100;i++){
//        final VolatileDemo object = new VolatileDemo();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    object.execute();
                }
            }).start();
        }
    }

}
