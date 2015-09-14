package com.iktz.thread;

public class Thread014LockClass {

	/**
		--------
		********
		--------
		-*****-------
		***
		********
		--------

	 * @param args
	 */
	public static void main(String[] args) {
		new Thread014LockClass().init();
	}
	
	private void init(){
		/**
		 * 静态方法中不能new内部类的实例对象
		 * 
		 * 因为内部类不能访问
		 * 
		 */
		final Outputer ot = new Outputer();
		
		new Thread(new Runnable(){
			public void run() {

				while(true){
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ot.output("----------------------------------------");
				}
			}
		}).start();
		
		new Thread(new Runnable(){
			public void run() {
				while(true){
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Outputer.output3("****************************************");
				}
			}
		}).start();
				
	}
	static class Outputer{
		
		public void output(String name){
			
			synchronized (Outputer.class) {//this 本类对象
				int len = name.length();
				for(int i=0;i<len;i++){
					System.out.print(name.charAt(i));
				}
				System.out.println();
			}
		}
		
		public synchronized void output2(String name){
			int len = name.length();
			for(int i=0;i<len;i++){
				System.out.print(name.charAt(i));
			}
			System.out.println();
		}
		
		public static synchronized void output3(String name){
			int len = name.length();
			for(int i=0;i<len;i++){
				System.out.print(name.charAt(i));
			}
			System.out.println();
		} 
	}
}
