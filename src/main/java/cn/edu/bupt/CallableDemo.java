package cn.edu.bupt;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class CallableDemo {
    public static void main(String[] args) throws Exception{
        Callable task = new Callable() {
            public Object call() throws Exception {
                return "Hello Callable!";
            }
        };
        //包装转换成Runnable
        FutureTask<String> futureTask = new FutureTask<String>(task);
        new Thread(futureTask).start();
        System.out.println("futureTask = " + futureTask.get());

        Runnable target = new Runnable() {
            public void run() {
                System.out.println(" Hello Runnable! ");
            }
        };
        new Thread(target).start();

        new Thread(){
            public void run(){
                System.out.println(" 继承Thread创建线程");
            }
        }.start();





//        final MonitorObject mo = new MonitorObject();
//        final MonitorObject mo2 = new MonitorObject();
//        for(int i = 0;i<4;i++){
//            new Thread(new Runnable() {
//                public void run() {
//                    for(int i = 0;i<10;i++)
//                        System.out.println(Thread.currentThread().getName()+";"+"mo.addAndGetNum() = " + mo.addAndGetNum());
//                }
//            }).start();
//        }
//        for(int i = 0;i<4;i++){
//            new Thread(new Runnable() {
//                public void run() {
//                    for(int i = 0;i<10;i++)
//                        System.out.println(Thread.currentThread().getName()+";"+"mo2.addAndGetNum() = " + mo2.addAndGetNum());
//                }
//            }).start();
//        }
//
//
//
////        SingletonByEnum singleton = SingletonByEnum.INSTANCE;
////        singleton.setName("Enum");
////        System.out.println("Name = " + singleton.getName());
//
//
////        Singleton s = Singleton.getInstance();
////        System.out.println(s.getString());
////        SingletonEnum se = SingletonEnum.Instance1;
////        System.out.println("se.getInt() = " +se.getInt());
////        System.out.println("args = [" + se.getString() + "]");
////        SingletonEnum se3 = SingletonEnum.Instance3;
////        System.out.println("args = [" + se3.getString() + "]");
////        TimeUnit minute = TimeUnit.MINUTES;
////
//
//        MyTimeUnit day = MyTimeUnit.DAYS;
//        day.setValue(11);
//        System.out.println("day.getValue() = " + day.getValue());
    }

}
