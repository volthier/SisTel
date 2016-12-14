package br.gov.cultura.DitelAdm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import br.gov.cultura.DitelAdm.ws.SeiClient;

@Configuration
public class SeiConfiguration {
	
	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		
		marshaller.setContextPath("br.gov.cultura.DitelAdm.sei.wsdl");
		
		return marshaller;
	}
	
	@Bean
	public SeiClient seiClient(Jaxb2Marshaller marshaller) {
		SeiClient client = new SeiClient();
		
		client.setDefaultUri("");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		
		return client;
	}

}
