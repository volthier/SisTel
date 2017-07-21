package br.gov.cultura.DitelAdm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    Environment env;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		.antMatchers("/css/**", "/js/**", "/img/**", "/images/**", "/resources/**", "/webjars/**","/fonts/**","/miminium/**").permitAll();
		
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
		
		http
		.authorizeRequests()
			.antMatchers("/login").anonymous()
			.anyRequest().authenticated()
			.and()
		.exceptionHandling()
			.accessDeniedPage("/inicio");
		
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		auth.ldapAuthentication()
		.userSearchFilter(System.getenv("USERSEARCHFILTERLDAP"))
			.userSearchBase(System.getenv("USERSEARCHBASELDAP"))
				.groupSearchFilter("(member={0})")
				.groupSearchBase(System.getenv("USERSEARCHBASELDAP"))
				.contextSource()
				.url(System.getenv("URLLDAP"))
				.managerDn(System.getenv("USERLDAPDN"))
				.managerPassword(System.getenv("PASSLDAP"));
	}
}