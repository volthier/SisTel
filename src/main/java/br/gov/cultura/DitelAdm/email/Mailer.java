package br.gov.cultura.DitelAdm.email;

import java.io.IOException;
import java.text.ParseException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import br.gov.cultura.DitelAdm.model.Alocacao;
import br.gov.cultura.DitelAdm.service.AlocacaoService;
import br.gov.cultura.DitelAdm.ws.SeiClient;

@Component
public class Mailer {

	public static Logger logger = LoggerFactory.getLogger(Mailer.class);

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private TemplateEngine thymeleaf;

	@Autowired
	private AlocacaoService alocacaoService;
	@Autowired
	private SeiClient sei;

	@Async
	public void enviar(Integer id) {

		Alocacao alocacao = alocacaoService.getAlocacao(id);
		
		Context context = new Context();
		context.setVariable("dto", alocacao);

		try {
			String email = thymeleaf.process("email/EmailProcessoAberto", context);
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			helper.setFrom("ditel@cultura.gov.br");
			helper.setTo(alocacao.getUsuario().getEmailUsuario());
			helper.setSubject("Telefonia - Processo no SEI");
			helper.setText(email, true);

			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			logger.error("Erro ao enviar o e-mail!", e);
		}

	}
	@Async
	public void enviarTermo(Integer id, byte[] bs) throws IOException, ParseException, Exception {

		Alocacao alocacao = alocacaoService.getAlocacao(id);
		sei.enviarTermoResponsabilidade(alocacao, bs);
		
		Context context = new Context();
		context.setVariable("dto", alocacao);

		try {
			String email = thymeleaf.process("email/EmailProcessoAberto", context);
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			helper.setFrom("ditel@cultura.gov.br");
			helper.setTo(alocacao.getUsuario().getEmailUsuario());
			helper.setSubject("Telefonia - Processo no SEI");
			helper.setText(email, true);

			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			logger.error("Erro ao enviar o e-mail!", e);
		}

	}
	
}
