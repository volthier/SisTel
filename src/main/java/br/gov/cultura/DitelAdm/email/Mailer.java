package br.gov.cultura.DitelAdm.email;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import br.gov.cultura.DitelAdm.model.Alocacao;
import br.gov.cultura.DitelAdm.model.DocumentoSei;
import br.gov.cultura.DitelAdm.model.dtos.FaturaArquivoDTO;
import br.gov.cultura.DitelAdm.model.faturasV3.Fatura;
import br.gov.cultura.DitelAdm.service.AlocacaoService;

@Component
public class Mailer {

	public static Logger logger = LoggerFactory.getLogger(Mailer.class);

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private TemplateEngine thymeleaf;

	@Autowired
	private AlocacaoService alocacaoService;

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
	public void enviarTermo(Integer id,DocumentoSei documento) throws IOException, ParseException, Exception {

		Alocacao alocacao = alocacaoService.getAlocacao(id);
		
		Context context = new Context();
		context.setVariable("dto", alocacao);
		context.setVariable("doc", documento);
		context.setVariable("logo", "logo");

		try {
			String email = thymeleaf.process("email/EmailTermoResponsabilidade", context);
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			helper.setFrom("ditel@cultura.gov.br");
			helper.setTo(alocacao.getUsuario().getEmailUsuario());
			helper.setSubject("SISTEL - Termo de Responsabilidade");
			helper.setText(email, true);
			helper.addInline("logo", new ClassPathResource("../miminium/img/logo-sistel-horizontal-branca.png"));

			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			logger.error("Erro ao enviar o e-mail!", e);
		}

	}
	@Async
	public void enviarAtestoFatura(List<FaturaArquivoDTO> fatura ) throws IOException, ParseException, Exception {

			
		Context context = new Context();
		context.setVariable("faturas", fatura);

		try {
			String email = thymeleaf.process("email/EmailAtestoFatura", context);
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			helper.setFrom("ditel@cultura.gov.br");
			helper.setTo(fatura.get(0).getAlocacao().getUsuario().getEmailUsuario());
			helper.setSubject("Telefonia - Fatura Telefônica para Atesto!");
			helper.setText(email, true);

			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			logger.error("Erro ao enviar o e-mail!", e);
		}

	}
	
}
