package ReentrantLockDemo;

public class UnReentrantLock {

	public static void main(String[] args) {
		UnReentrantLock unReentrantLock = new UnReentrantLock();
		unReentrantLock.lock();
		System.out.println("第一次进入");
		unReentrantLock.lock();
		System.out.println("第二次进入");
		unReentrantLock.unlock();
		unReentrantLock.unlock();
		System.out.println("结束了");

	}
	
	private boolean isLocked = false;
	
	public void lock() {
		while(isLocked) {
			//wait();
		}
		isLocked = true;
	}
	
	public void unlock() {
		isLocked = true;
		//notify();
	}
}
