package com.javaee.artastic.Artastic.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Service
public class MailService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Value("${mail.fromMail.addr}")
	private String fromMail;
	
	public void sendSimpleMail(String toMail, String subject, String content) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(fromMail);
		message.setTo(toMail);
		message.setSubject(subject);
		message.setText(content);
		try {
			mailSender.send(message);
			logger.info("message has been sent");
			
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			// TODO: handle exception
		}
	}
	
	public void sendHtmlMail(String toMail, String subject, String htmlPage, Context context) {
		MimeMessage message = mailSender.createMimeMessage();

	    try {
	        //true表示需要创建一个multipart message
	        MimeMessageHelper helper = new MimeMessageHelper(message, true);
	        String emailContent = templateEngine.process(htmlPage, context);
	        helper.setFrom(fromMail);
	        helper.setTo(toMail);
	        helper.setSubject(subject);
	        helper.setText(emailContent, true);
	        
	        mailSender.send(message);
	        logger.info("html邮件发送成功");
	    } catch (MessagingException e) {
	        logger.error("发送html邮件时发生异常", e);
	    }
	}
}
