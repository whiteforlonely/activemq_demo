package com.ake.activemq.demo;

import java.util.concurrent.atomic.AtomicInteger;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Comsumer {

	private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
	private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
	private static final String BROKEN_URL = ActiveMQConnection.DEFAULT_BROKER_URL;
	
	ConnectionFactory connectionFactory;
	Connection connection;
	Session session;
	
	ThreadLocal<MessageConsumer> threadLocal = new ThreadLocal<MessageConsumer>();
	
	AtomicInteger count = new AtomicInteger();
	
	public void init() {
		try {
			connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKEN_URL);
			connection = connectionFactory.createConnection();
			connection.start();
			session = connection.createSession(true, Session.SESSION_TRANSACTED);
			
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	public void getMessage(String disname) {
		try {
			Queue queue = session.createQueue(disname);
			MessageConsumer comsumer = null;
			
			if (threadLocal.get() != null) {
				comsumer = threadLocal.get();
			}else {
				comsumer = session.createConsumer(queue);
				threadLocal.set(comsumer);
			}
			
			while (true) {
				Thread.sleep(1000);
				TextMessage msg = (TextMessage) comsumer.receive();
				if (null != msg) {
//					msg.acknowledge();
					session.commit();
					System.out.println(Thread.currentThread().getName()+": Comsumer: i am com, comsume msg: "+msg.getText()+"---->"+count.getAndIncrement());
				}else {
					break;
				}
			}
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
