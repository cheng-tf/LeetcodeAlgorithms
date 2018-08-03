package ReentrantLockDemo;

import java.util.concurrent.atomic.AtomicReference;

public class NotReentrantLock {
    //原子引用类
    private AtomicReference<Thread> atomicReference = new AtomicReference<Thread>();

    public void lock() {
        //采用CAS操作完成
        while(!atomicReference.compareAndSet(null, Thread.currentThread())) {};
    }

    public void unlock() {
        while(!atomicReference.compareAndSet(Thread.currentThread(),null)) {};
    }

    //测试
    public static void main(String[] args) {
        NotReentrantLock notReentrantLock = new NotReentrantLock();
        notReentrantLock.lock();
        System.out.println("进入第一层");
        notReentrantLock.lock();//一直停留在这里，自旋
        System.out.println("进入第二层");
        notReentrantLock.unlock();
        notReentrantLock.unlock();
        System.out.println("结束了");
    }

}