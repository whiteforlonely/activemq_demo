package com.ake.activemq.demo;

public class TestMQ {

	public static void main(String[] args) {
		Producer producer = new Producer();
		producer.init();
		TestMQ testMQ = new TestMQ();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		new Thread(testMQ.new ProductorMq(producer)).start();
//		new Thread(testMQ.new ProductorMq(producer)).start();
//		new Thread(testMQ.new ProductorMq(producer)).start();
//		new Thread(testMQ.new ProductorMq(producer)).start();
//		new Thread(testMQ.new ProductorMq(producer)).start();
	}
	
	private class ProductorMq implements Runnable{
		
		Producer producer ;
		
		public ProductorMq(Producer producer) {
			this.producer = producer;
		}
		
		@Override
		public void run() {
			while(true) {
				try {
					producer.sendMessage("Jaycekon-MQ");
					Thread.sleep(1000);
				}catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
