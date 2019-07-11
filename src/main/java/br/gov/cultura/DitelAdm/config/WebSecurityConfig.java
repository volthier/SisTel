package br.gov.cultura.DitelAdm.config;

import br.gov.cultura.DitelAdm.service.ldap.ConsultaLdapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private ConsultaLdapService ldap;

	@Autowired
    Environment env;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
		.antMatchers("/css/**", "/js/**", "/img/**", "/images/**", "/resources/**", "/webjars/**","/fonts/**","/miminium/**","/icon/**").permitAll();

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

//		UsuarioLdap user = ldap.findOne(SecurityContextHolder.getContext().getAuthentication().getPrincipal().getClass().getName());
//		Authentication authentication = user;
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.ldapAuthentication()
		.userSearchFilter(env.getRequiredProperty("ldap.user.search.filter"))
			.userSearchBase(env.getRequiredProperty("ldap.user.search.base"))
				.groupSearchFilter("(member={0})")
				.groupSearchBase(env.getRequiredProperty("ldap.user.search.base"))
				.contextSource()
				.url(env.getRequiredProperty("ldap.url"))
				.managerDn(env.getRequiredProperty("ldap.userDn"))
				.managerPassword(env.getRequiredProperty("ldap.passDn"));
	}
}
