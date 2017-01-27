package br.gov.cultura.DitelAdm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		.antMatchers("/css/**", "/js/**", "/images/**", "/resources/**", "/webjars/**","/fonts/**").permitAll();
		
		http
		.authorizeRequests()
			.antMatchers("/login").anonymous()
			.anyRequest().authenticated()
			.and()
		.formLogin()
			.loginPage("/login")
			.failureUrl("/login?error")
			.usernameParameter("username")
			.passwordParameter("password")
			.defaultSuccessUrl("/inicio",true)
			.and()
		.logout()
			.logoutSuccessUrl("/login");
			
			 
	}

	@Autowired
	// @ConfigurationProperties(prefix = "ldap")
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		auth.ldapAuthentication()
			.userSearchFilter("(sAMAccountName={0})")
			.userSearchBase("XX=,XX=,XX=")
				.groupSearchFilter("(member={0})")
				.groupSearchBase("XX=*****,XX=****,XX=*****")
				.contextSource()
				.url("ldap://000.000.000.000:389/XX=******,XX=******")
				.managerDn("XX=********,XX=*****,XX=******,XX=*****,XX=****,XX=******")
				.managerPassword("*******");
	}
}