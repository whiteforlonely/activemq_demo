package com.ake.activemq.demo;

public class TestComsumer {

	public static void main(String[] args) {
		Comsumer comsumer = new Comsumer();
		comsumer.init();
		
		TestComsumer testComsumer = new TestComsumer();
		new Thread(testComsumer.new ComsumerMq(comsumer)).start();
//		new Thread(testComsumer.new ComsumerMq(comsumer)).start();
//		new Thread(testComsumer.new ComsumerMq(comsumer)).start();
//		new Thread(testComsumer.new ComsumerMq(comsumer)).start();
//		new Thread(testComsumer.new ComsumerMq(comsumer)).start();
	}
	
	
	private class ComsumerMq implements Runnable{
		
		Comsumer comsumer ;
		
		
		public ComsumerMq(Comsumer comsumer) {
			super();
			this.comsumer = comsumer;
		}


		@Override
		public void run() {
			while (true) {
				try {
					comsumer.getMessage("Jaycekon-MQ");
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
