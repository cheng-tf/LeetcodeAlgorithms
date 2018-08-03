package singleton;


/**
 * 延迟加载模式，俗称懒汉式
 * 在多线程情况下，存在线程安全问题；只适用于单线程
 */
public class Singleton {
    private static volatile Singleton instance = null;
    private Singleton(){}
    public static Singleton getInstance(){
        if(instance == null)
            instance = new Singleton();
        return instance;
    }
}

/**
 * 饿汉式：饥渴难耐
 * 不存在同步安全问题，在启动的时候创建实例，实例一直存在，浪费存储空间；
 * 利用的是：静构造器的类加载与初始化时线程安全的。
 * 一般考察较少
 */
//public class Singleton {
//    private static volatile Singleton instance = new Singleton();
//    private Singleton(){}
//    public static Singleton getInstance(){
//        return instance;
//    }
//}
