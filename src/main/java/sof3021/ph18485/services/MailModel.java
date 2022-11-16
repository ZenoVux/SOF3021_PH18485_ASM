package sof3021.ph18485.services;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MailModel {
	private String form = "Vũ Văn Luân <luanvu0702@gmail.com>";
	private String to;
	private String subject;
	private String body;
	private List<String> cc = new ArrayList<>();
	private List<String> bcc = new ArrayList<>();
	private List<File> files = new ArrayList<>();
	
	public MailModel(String to, String subject, String body) {
		super();
		this.to = to;
		this.subject = subject;
		this.body = body;
	}
	
}
