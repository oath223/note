package com.iktz.thread;

public class Thread011Create {

	public static void main(String[] args) throws Exception{
/*		Thread t = new Thread(){
			@Override
			public void run() {
				for(int i=0;i<300;i++){
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+"  "+i);//或取当前线程
				}
			}
		};
		t.start();*/
		
		Thread t2 = new Thread(new Runnable() {
			
			public void run() {
				for(int i=0;i<300;i++){
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+"  "+i);//或取当前线程
				}
			}
		},"testthread");
		t2.start();
	}
}
