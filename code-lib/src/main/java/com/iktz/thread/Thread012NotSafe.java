package com.iktz.thread;

public class Thread012NotSafe {

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
		new Thread012NotSafe().init();
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
					ot.output("--------");
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
					ot.output("********");
				}
			}
		}).start();
				
	}
	class Outputer{
		private void output(String name){
			int len = name.length();
			for(int i=0;i<len;i++){
				System.out.print(name.charAt(i));
			}
			System.out.println();
		} 
	}
}
