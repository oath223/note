package com.iktz.thread.a01ThreadPoll;

import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/**
 * 实现读与读不排斥，读与写排斥
 * @author John
 *
 */
public class ReadWriteLockTest {

	public static void main(String[] args) {
		
		final Queue3 q3 = new Queue3();
		
		for(int i=0;i<3;i++){
			new Thread(){
				public void run(){
					while(true){
						q3.get();
					}
				}
			}.start();
		}
		
		new Thread(){
			public void run(){
				while(true){
					q3.put(new Random().nextInt(10000));
				}
			}
		}.start();
	}
}

class Queue3{
	
	private Object data = null;
	private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	/*
	 * 代码如果没有加锁，有可能在写的过程中，别的线程去写了
	 * 
	 * 
	 */
	public void get(){
		//rwl.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + " be ready to read data!");
			Thread.sleep((long)(Math.random()*1000));
			System.out.println(Thread.currentThread().getName() + "have read data :" + data);			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			//rwl.readLock().unlock();
		}
	}
	
	public void put(Object data){
		//rwl.writeLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + " be ready to write data!");					
			Thread.sleep((long)(Math.random()*1000));
			this.data = data;		
			System.out.println(Thread.currentThread().getName() + " have write data: " + data);					
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			//rwl.writeLock().unlock();
		}
	}
}