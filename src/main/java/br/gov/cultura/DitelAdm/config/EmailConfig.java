package br.gov.cultura.DitelAdm.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableAsync;

import br.gov.cultura.DitelAdm.email.Mailer;


@Configuration
@ComponentScan(basePackageClasses = Mailer.class)
@EnableAsync
public class EmailConfig {

	@Autowired
	Environment env;
	
	@Bean
	public JavaMailSender emailSender(){
		JavaMailSenderImpl emailSender = new JavaMailSenderImpl();
		emailSender.setHost(env.getRequiredProperty("email.host"));
		emailSender.setPort(Integer.parseInt(env.getRequiredProperty("email.port")));
		
		Properties emailProperties = new Properties();
		emailProperties.put("mail.transport.protocol", env.getRequiredProperty("email.protocol"));
		emailProperties.put("mail.smtp.auth", Boolean.parseBoolean(env.getRequiredProperty("email.smtp.aut")));
		emailProperties.put("mail.smtp.starttls.enable", Boolean.parseBoolean(env.getRequiredProperty("email.tls.enable")));
		emailProperties.put("mail.debug", Boolean.parseBoolean(env.getRequiredProperty("email.debug")));
		emailProperties.put("mail.smtp.connectiontimeout", Integer.parseInt(env.getRequiredProperty("email.timeout")));
		
		return emailSender;
	}
}
