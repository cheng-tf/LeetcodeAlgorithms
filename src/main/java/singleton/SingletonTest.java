package singleton;

import org.junit.Test;

public class SingletonTest {
    @Test
    public void test(){
        //懒汉式、饿汉式
        Singleton singleton = Singleton.getInstance();
        Singleton singleton1 = Singleton.getInstance();
        System.out.println("singleton==singleton1 ? " +  (singleton == singleton1) ); //true
        //双重检查延迟加载
        DualIfSingleton instance = DualIfSingleton.getInstance();
        DualIfSingleton instance2 = DualIfSingleton.getInstance();
        System.out.println("instance==instance2 ? " +  (instance == instance2) ); //true
        //静态内部类实现
        NestedSingleton nestedSingleton = NestedSingleton.getInstance();
        NestedSingleton nestedSingleton2 = NestedSingleton.getInstance();
        System.out.println("nestedSingleton == nestedSingleton2 " + (nestedSingleton == nestedSingleton2));//true
        //枚举方式实现
        EnumSingleton enumSingleton = EnumSingleton.INSTANCE;
        EnumSingleton enumSingleton2 = EnumSingleton.INSTANCE;
        System.out.println("enumSingleton = enumSingleton2 ?" + (enumSingleton==enumSingleton2));//true
        System.out.println("enumSingleton2 = " + enumSingleton2.toString());
    }
}
