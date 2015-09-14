package com.iktz.thread.a01ThreadPoll;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Ts02CallableAndFuture {

	
	public static void test01(){
		
		ExecutorService st = Executors.newSingleThreadExecutor();
//		st.execute(null);//如果不用返回结果
		Future<String> future = st.submit(new Callable<String>() {
			public String call() throws Exception {
				Thread.sleep(2000);
				return "hello";
			}
		});
		
		System.out.println("wait ");
		try {
			System.out.println("result: >> "+future.get(1,TimeUnit.SECONDS));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void test02(){
		ExecutorService executor = Executors.newFixedThreadPool(10);
		CompletionService<String> cs = new ExecutorCompletionService<String>(executor);
		for(int i=1;i<=10;i++){
			final int seq = i;
			cs.submit(new Callable<String>() {
				public String call() throws Exception {
					Thread.sleep(new Random().nextInt(5000));
					return "第 "+ seq+"个任务执行返回值";
				}
			});
		}
		
		//获取执行结果
		for(int i=0;i<10;i++){
			try {
				System.out.println(cs.take().get());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		test02();
	}
	
}
