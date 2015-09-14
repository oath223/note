package com.iktz.thread.a01ThreadPoll;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Ts03Lock {

	
	
	
	public static void main(String[] args) {
		
	}

	static class Outputer{
		Lock lock = new ReentrantLock();
		public void output(String name){
			int len = name.length();
			try{
				lock.lock();
				
				for(int i=0;i<len;i++){
					System.out.print(name.charAt(i));
				}
				System.out.println();
			}finally{
				lock.unlock();
			}
		}
	} 
}
