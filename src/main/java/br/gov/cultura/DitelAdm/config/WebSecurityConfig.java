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
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		auth.ldapAuthentication()
			.userSearchFilter("(sAMAccountName={0})")
			.userSearchBase("OU=CGTI,OU=Usuarios,OU=Sede")
				.groupSearchFilter("(member={0})")
				.groupSearchBase("OU=Seguranca,OU=Grupos,OU=Sede")
				.contextSource()
				.url("ldap://10.0.0.173:389/DC=minc,DC=intra")
				.managerDn("CN="+System.getenv("USERLDAP")+",OU=CGTI,OU=Usuarios,OU=Sede,DC=minc,DC=intra")
				.managerPassword(System.getenv("PASSLDAP"));
	}
}