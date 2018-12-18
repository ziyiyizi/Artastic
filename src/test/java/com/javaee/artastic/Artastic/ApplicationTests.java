package com.javaee.artastic.Artastic;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.javaee.artastic.Artastic.service.impl.MailService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
	@Autowired
	private MailService mailService;
	
	@Autowired
	private TemplateEngine templateEngine;
	
//	@Test
//	public void testSimpleMessage() throws Exception{
//		mailService.sendSimpleMail("myjasson@163.com", "testmail", "hello");
//	}
	
	@Test
	public void sendTemplateMail() throws Exception{
	  //创建邮件正文
		
	  Context context = new Context();
      context.setVariable("id", "1");
	  String emailContent = templateEngine.process("emailTemplate", context);

	  mailService.sendHtmlMail("xiajinlei@foxmail.com","主题：这是模板邮件", "emailTemplate", context);
	}
}
