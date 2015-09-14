package com.iktz.thread;

public class Thread021Test {

	public static void main(String[] args) {
		final Business b = new Business();
		new Thread(new Runnable(){
			public void run() {
				for(int i=1;i<=50;i++){
					b.sub(i);
 				}
			}
		}).start();
		
		for(int i=1;i<=50;i++){
			b.main(i);
		}
	}
}
class Business {
	public synchronized void sub(int i) {
		for (int j = 1; j <= 10; j++) {
			System.out.println("sub " + j + "   " + i);
		}
	}

	public synchronized void main(int i) {
		for (int j = 1; j <= 100; j++) {
			System.out.println("main " + j + "   " + i);
		}
	}
}
