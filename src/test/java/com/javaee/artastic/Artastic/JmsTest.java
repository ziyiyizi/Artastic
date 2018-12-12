package com.javaee.artastic.Artastic;

import javax.jms.Destination;

import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.javaee.artastic.Artastic.jms.ProduceTest;
import com.javaee.artastic.Artastic.jms.Producer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JmsTest {
	@Autowired
	private ProduceTest producer;
	
	@Test
	public void contextLoads() throws InterruptedException{
		Destination destination = new ActiveMQQueue("producetest.queue");
		for(int i = 0; i < 10; i++) {
			producer.sendMessage(destination, "hello world!");
		}
	}
	
}
