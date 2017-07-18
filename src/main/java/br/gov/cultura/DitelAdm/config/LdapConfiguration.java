package br.gov.cultura.DitelAdm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.pool.factory.PoolingContextSource;

@Configuration
public class LdapConfiguration {
	@Autowired
    Environment env;
	
	@Bean
    public LdapContextSource contextSource () {
        LdapContextSource contextSource= new LdapContextSource();
        contextSource.setUrl(env.getRequiredProperty("ldap.url"));
        contextSource.setBase(env.getRequiredProperty("ldap.base"));
        contextSource.setUserDn(env.getRequiredProperty("ldap.userDn"));
        contextSource.setPassword(env.getRequiredProperty("ldap.passDn"));
        contextSource.setPooled(false);
        return contextSource;
    }

/*	@Bean
	public PoolingContextSource poolContext(){
		PoolingContextSource poolContext = new PoolingContextSource();
		poolContext.setTestOnBorrow(true);
		poolContext.setTestWhileIdle(true);
		return poolContext;
	}
	*/
    @Bean
    public LdapTemplate ldapTemplate() {
        LdapTemplate ldapTemplate = new LdapTemplate(contextSource());
        ldapTemplate.setIgnorePartialResultException(true);
        return ldapTemplate;
    }

}
