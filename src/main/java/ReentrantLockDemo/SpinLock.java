package ReentrantLockDemo;

import java.util.concurrent.atomic.AtomicReference;

public class SpinLock {
//	利用计数器实现的自旋锁是一个可重入锁
	public static void main(String[] args) {
		SpinLock spinLock = new SpinLock();
		spinLock.lock();
		System.out.println("进入第一层锁");
		spinLock.lock();
		System.out.println("进入第二层锁");
		spinLock.unlock();
		spinLock.unlock();
		System.out.println("结束");
	}

	private AtomicReference<Thread> owner = new AtomicReference<Thread>();
	private int count = 0;

	public void lock() {
		Thread current = Thread.currentThread();
		if (current == owner.get()) {
			count++;
			return;
		}
		while (!owner.compareAndSet(null, current)) {}
	}

	public void unlock() {
		Thread current = Thread.currentThread();
		if (current == owner.get()) {
			if (count != 0) {
				count--;
			} else {
				owner.compareAndSet(current, null);
			}
		}
	}
}
