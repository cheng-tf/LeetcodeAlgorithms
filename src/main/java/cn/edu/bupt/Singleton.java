package cn.edu.bupt;

import java.util.concurrent.locks.ReentrantLock;

public class Singleton {
/*    //最简单的饿汉式：不存在线程安全问题
    private Singleton(){}
    private static Singleton instance = new Singleton();
    public static Singleton getInstance(){
        return instance;
    }*/
/*    // 饿汉式的变种(完全等价):都是静态的，在类加载的时候实例化
    private Singleton(){}
    private static Singleton instance = null;
    static{
        instance = new Singleton();
    }
    public static Singleton getInstance(){
        return instance;
    }*/
    // 懒汉式：存在线程安全问题
/*    // 最好的实现方式：加锁双重检查判断
    private Singleton(){}
    private static Singleton instance = null;
    private static byte[] lock = new byte[1];//利用1个字节数组当做对象锁
    public static Singleton getInstance(){
        if(instance == null){
            synchronized (lock){
                if(instance == null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }*/
//    或者使用ReentrantLock锁
    private Singleton(){}
    private static Singleton instance = null;
    private static ReentrantLock lock = new ReentrantLock(false);
    public static Singleton getInstance(){
        if(instance == null){//第一次判断的目的：防止执行获取锁的操作
            lock.lock();
            if(instance == null){//第二次判断的目的：再次判断instance是否实例化
                instance = new Singleton();
            }
            lock.unlock();
        }
        return instance;
    }
    //其他方法
    public String getString(){
        return "Hello World!";
    }
//    枚举Enum方法

}
