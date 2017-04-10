package br.gov.cultura.DitelAdm.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableAsync;

import br.gov.cultura.DitelAdm.email.Mailer;


@Configuration
@ComponentScan(basePackageClasses = Mailer.class)
@EnableAsync
public class EmailConfig {

	@Bean
	public JavaMailSender emailSender(){
		JavaMailSenderImpl emailSender = new JavaMailSenderImpl();
		emailSender.setHost(System.getenv("ENVMAILHOST"));
		emailSender.setPort(Integer.parseInt(System.getenv("ENVMAILPORT")));
		
		Properties emailProperties = new Properties();
		emailProperties.put("mail.transport.protocol", System.getenv("ENVMAILPROTOCOL"));
		emailProperties.put("mail.smtp.auth", Boolean.parseBoolean(System.getenv("ENVSMTPAUTH")));
		emailProperties.put("mail.smtp.starttls.enable", Boolean.parseBoolean(System.getenv("ENVTLSENABLE")));
		emailProperties.put("mail.debug", Boolean.parseBoolean(System.getenv("ENVMAILDEBUG")));
		emailProperties.put("mail.smtp.connectiontimeout", Integer.parseInt(System.getenv("ENVMAILTIMEOUT")));
		
		return emailSender;
	}
}
