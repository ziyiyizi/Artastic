package com.javaee.artastic.Artastic.jms;

import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

@Service("producer")
public class Producer {
	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;
	
	public void sendMessage(Destination destination, final String message) {
		jmsMessagingTemplate.convertAndSend(destination, message);
	}
	
	@JmsListener(destination="out.queue")
	public void consumerMessage(String text) {
		System.out.println("从out接收到" + text);
	}
}
