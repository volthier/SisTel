package br.gov.cultura.DitelAdm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import br.gov.cultura.DitelAdm.ws.SeiClient;

@Configuration
public class SeiConfiguration {
	
	@Bean
	public SeiClient seiClient() {
		SeiClient client = new SeiClient();		
		return client;
	}

}
