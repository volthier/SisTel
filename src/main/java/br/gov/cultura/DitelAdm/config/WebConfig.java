/*package br.gov.cultura.DitelAdm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import br.gov.cultura.DitelAdm.service.LimiteAtestoFormatter;

@Configuration
	
	@ComponentScan(value = {"br.gov.cultura.DitelAdm" })
	public class WebConfig extends WebMvcConfigurerAdapter {


	    //Formatters

	    @Autowired //Without autowire, this solution may not work
	    private LimiteAtestoFormatter limiteAtestoFormatter;

	    @Override
	    public void addFormatters(FormatterRegistry registry) {
	        registry.addFormatter(limiteAtestoFormatter);
	    }
	}

*/