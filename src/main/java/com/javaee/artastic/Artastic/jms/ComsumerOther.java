package com.javaee.artastic.Artastic.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class ComsumerOther {
	@JmsListener(destination="mytest.queue")
	@SendTo("out.queue")
	public String receiveQueue(String text) {
		System.out.println("Other收到" + text);
		return "return message " + text;
	}
}
