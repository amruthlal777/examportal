package com.exam.examMS.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
@Service
public class MailService {

	
	@Autowired
	private final JavaMailSender javaMailSender;
	
	@Async
	public void sendMail()
	{	
		try {
			
			MimeMessage message = javaMailSender.createMimeMessage();
			
			MimeMessageHelper helper = new MimeMessageHelper(message);
			
			helper.setText("mail sent successfully");
			helper.setSubject("mail sent successfully");
			helper.setTo("amruthlal777@examportalplus.com");
			helper.setFrom("amruthlalpk@examportalplus.com");
			
			javaMailSender.send(message);

		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
	}
}
