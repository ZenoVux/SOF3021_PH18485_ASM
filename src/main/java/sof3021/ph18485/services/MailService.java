package sof3021.ph18485.services;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Service
public class MailService {
	
	@Autowired
	private JavaMailSender sender;
	private List<MimeMessage> mimeMessages = new ArrayList<>();

	public void push(String to, String subject, String body) {
		MailModel mail = new MailModel(to, subject, body);
		this.push(mail);
	}

	public void push(MailModel mail) {
		try {
			MimeMessage mimeMessage = sender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			helper.setFrom(mail.getForm());
			helper.setTo(mail.getTo());
			helper.setSubject(mail.getSubject());
			helper.setText(mail.getBody(), true);
			helper.setReplyTo(mail.getForm());
			for (String email : mail.getCc()) {
				helper.addCc(email);
			}
			for (String email : mail.getBcc()) {
				helper.addBcc(email);
			}
			for (File file : mail.getFiles()) {
				helper.addAttachment(file.getName(), file);
			}
			this.mimeMessages.add(mimeMessage);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	@Scheduled(fixedDelay = 1000)
	public void run() {
		while (!this.mimeMessages.isEmpty()) {
			MimeMessage message = this.mimeMessages.remove(0);
			try {
				sender.send(message);
				System.out.println("Send mail success");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
