package week7.threads;

import java.util.concurrent.locks.ReentrantLock;

public class TestSyncConsole extends Thread {
	
	private String name;
	private static ReentrantLock lock = new ReentrantLock();

	public static void main(String[] args){
		
		TestSyncConsole alpha = new TestSyncConsole("Thread A");
		alpha.start();
		
		TestSyncConsole beta = new TestSyncConsole("Thread B");
		beta.start();
		
	}
	
	public TestSyncConsole(String name){
		this.name = name;
	}
	
	public void run(){
		sum();
	}
	
	private synchronized void sum(){
		lock.lock();
		int int1 = SyncConsole.readInt(name + ": Get number 1?");
		int int2 = SyncConsole.readInt(name + ": Get number 2?");
		SyncConsole.println(name + ": the sum of " + int1 + " and " + int2 + " is " + (int1 + int2));
		lock.unlock();
	}

}
