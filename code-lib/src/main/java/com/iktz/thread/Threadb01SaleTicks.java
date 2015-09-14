package com.iktz.thread;


class Threadb01SaleTicks  implements Runnable{
//	private static int tick =100;
	//private int tick =100;
	/**
	 * 静态可以解决，但是不建议定义static，因为静态的生命周期太长。
	 */
	private int tick =100;
/*	public Threadb01SaleTicks(int tick) {
		this.tick = tick;
	}*/
	Object o = new Object();
	public void run(){
		while(true){
			synchronized(o){
				if(tick>0){
					try{
						Thread.sleep(10);
					}catch(Exception e){
						e.printStackTrace();
					}
					System.out.println(
							Thread.currentThread().getName()
							+"... ... sale : "+tick--
							);
				}
			}
		}
	}
	
	public static void main(String[] args) {

		Threadb01SaleTicks t1 = new Threadb01SaleTicks();

		new Thread (t1).start();
		new Thread (t1).start();
		new Thread (t1).start();
		new Thread (t1).start();
		
	}
}

