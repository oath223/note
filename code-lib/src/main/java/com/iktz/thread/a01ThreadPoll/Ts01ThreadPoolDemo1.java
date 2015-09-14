package com.iktz.thread.a01ThreadPoll;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Ts01ThreadPoolDemo1 {
	public static void main(String[] args) {
		test02();
	}
	public static void test01(){

		// ExecutorService tp = Executors.newFixedThreadPool(3);//创建固定大小的线程池
		// ExecutorService tp = Executors.newCachedThreadPool();//创建缓存线程池

		ExecutorService tp = Executors.newSingleThreadExecutor();// 单线程，线程死掉后重新启动

		/**
		 * newFixedThreadPool(int nThreads)
		 * 
		 * 创建一个固定的线程池，参数为线程的个数（一起来就创建3个线程）
		 */

		// RunnableImpl rmp =new RunnableImpl(0);
		// tp.execute(rmp);
		// tp.execute(rmp);
		//

		// 像线程池中加入10个任务
		// 可以向池中加入n个任务，但同时只有3个线程提供服务，因为线程池中只有3个任务。

		for (int i = 1; i <= 100; i++) {
			final int task = i;
			tp.execute(new RunnableImpl(task));
		}

		tp.shutdown();

		System.out.println("---- end task -----");
	}
	public static void test02() {
		//
//		RunnableImpl rmp =new RunnableImpl(0);
		//param1：
		//		:时间
		//		：单位 TimeUnit
		/*Executors.newScheduledThreadPool(3).schedule(
				new Runnable() {
					public void run() {
						System.out.println("bombing");						
					}
				}, 10, TimeUnit.SECONDS);
		*/
		
		//间隔固定频率
		Executors.newScheduledThreadPool(3).scheduleAtFixedRate(
				new Runnable() {
					public void run() {
						System.out.println("bombing");						
					}
				}, 10, 2,TimeUnit.SECONDS);
		
		int i = 0;
		while(true){
			System.out.println(i++);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}

class RunnableImpl implements Runnable {

	int task = 0;

	public RunnableImpl(int task) {
		this.task = task;
	}

	public void run() {
		for (int i = 1; i <= 10; i++) {
			System.out.println(Thread.currentThread().getName() + "  >>>   " + i + "  task  >> " + task);
		}

	}
}