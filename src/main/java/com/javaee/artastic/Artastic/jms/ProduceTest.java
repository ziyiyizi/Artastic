package com.javaee.artastic.Artastic.jms;

import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProduceTest {
	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;
	
	public void sendMessage(Destination destination, final String message) {
		jmsMessagingTemplate.convertAndSend(destination, message);
	}
	
	@JmsListener(destination="producetest.queue")
	public void receiveQueue(String text) {
		System.out.println("producetest收到" + text);
	}
}
