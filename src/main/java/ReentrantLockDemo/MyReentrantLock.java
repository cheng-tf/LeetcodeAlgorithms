package ReentrantLockDemo;

import java.util.concurrent.atomic.AtomicInteger;

public class MyReentrantLock {

	boolean isLocked = false;
	AtomicInteger count = new AtomicInteger(0);
	Thread lockedByThread = null;
	
	public void lock() {
		while(isLocked && Thread.currentThread() != lockedByThread) {
			//锁已经被占，且占领的线程不是本线程就阻塞；否则不需要阻塞(锁没有被占或占领的线程就是当前线程)
			//wait();
		}
		isLocked = true;
		count.incrementAndGet();
		lockedByThread = Thread.currentThread();//注意：一个锁只要是被锁定isLocked为，那么只能被一个线程所拥有
	}
	
	public void unlock() {
		if(Thread.currentThread() == lockedByThread) {
			//只要是进入的线程是获取锁的线程，计数减1，只有计数值为0时，释放锁
			count.decrementAndGet();
			if(count.get() == 0) {//
				isLocked = false;
				lockedByThread = null;
//				notify();
			}
		}
	}
	
	
	public static void main(String[] args) {
		MyReentrantLock myReentrantLock = new MyReentrantLock();
		myReentrantLock.lock();
		System.out.println("第一次进入");
		myReentrantLock.lock();
		System.out.println("第二次进入");
		myReentrantLock.unlock();
		myReentrantLock.unlock();
		System.out.println("结束了");
	}

}
