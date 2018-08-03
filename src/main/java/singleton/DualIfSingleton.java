package singleton;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 创建一个字节数组，充当锁对象
 */
public class DualIfSingleton {
    //双重判断，这是最基本的方法
    /*
    在getInstance中做了两次null检查，确保了只有第一次调用单例的时候才会做同步，这样也是线程安全的，同时避免了每次都同步的性能损耗。
    */
    private static volatile DualIfSingleton instance = null;
    private static byte[] lock = new byte[1];
    private DualIfSingleton(){}
    public static DualIfSingleton getInstance(){
        if(instance == null){
            synchronized (lock){
                if(instance == null)
                    instance = new DualIfSingleton();
            }
        }
        return instance;
    }
}

/**
 * 无论instance有没有被创建都要加锁，加锁是一个十分耗时的操作。
 */
//public class OneIfSingleton {
//    //双重判断，这是最基本的方法
//    private static volatile OneIfSingleton instance = null;
//    private static byte[] lock = new byte[1];
//    private OneIfSingleton(){}
//    public static OneIfSingleton getInstance(){
//            synchronized (lock){
//                if(instance == null)
//                    instance = new OneIfSingleton();
//            }
//        return instance;
//    }
//}

/**
 * 利用ReentrantLock可重入锁
 */
//public class DualIfSingleton {
//    //双重判断，这是最基本的方法
//    private static volatile DualIfSingleton instance = null;
//    private static ReentrantLock lock = new ReentrantLock();
//    private DualIfSingleton(){}
//    public static DualIfSingleton getInstance(){
//        if(instance == null){
//            lock.lock();
//                if(instance == null)
//                    instance = new DualIfSingleton();
//             lock.unlock();
//        }
//        return instance;
//    }
//}
