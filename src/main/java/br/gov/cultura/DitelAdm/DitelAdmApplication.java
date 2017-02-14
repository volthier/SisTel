package br.gov.cultura.DitelAdm;

import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.util.Locale;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import br.gov.cultura.DitelAdm.controller.FaturaUploadController;
import br.gov.cultura.DitelAdm.ws.SeiClient;

@SpringBootApplication
public class DitelAdmApplication {

	public static void main(String[] args) {
		SpringApplication.run(DitelAdmApplication.class, args);
	}
	
	
	@Bean
	CommandLineRunner init(SeiClient client) {
		return (args) -> {
			if(!Files.exists(Paths.get(FaturaUploadController.ROOT), LinkOption.NOFOLLOW_LINKS))
				Files.createDirectory(Paths.get(FaturaUploadController.ROOT));
		};
	}
	
	@Bean
	public LocaleResolver localeResolver(){
		return new FixedLocaleResolver(new Locale("pt","BR"));
	}
}
