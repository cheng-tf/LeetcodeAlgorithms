package singleton;



/**
  * 这种方法略微高级，面试的时候可以讲讲
 */
public class NestedSingleton {
    private NestedSingleton(){}//构造函数私有化
    public static NestedSingleton getInstance(){
        return SingletonHolder.instance;
    }

    //类级内部类(内部类分为类级内部类和对象级内部类
    private static class SingletonHolder{//静态内部类
        private SingletonHolder(){}
        private static NestedSingleton instance = new NestedSingleton();
    }
}
