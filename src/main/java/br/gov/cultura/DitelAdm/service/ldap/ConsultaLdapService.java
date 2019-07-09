package br.gov.cultura.DitelAdm.service.ldap;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.gov.cultura.DitelAdm.model.ldap.UsuarioLdap;
import br.gov.cultura.DitelAdm.repository.ldap.UsuariosLdaps;

@Service
public class ConsultaLdapService implements UsuariosLdaps {

	@Autowired
	private LdapTemplate ldapTemplate;

	
	public List<UsuarioLdap> findAll() {
		List<UsuarioLdap> allUsers = ldapTemplate.find(
				query().filter(
						"(&(samAccountType=805306368)(mail=*)(sn=*)(!(userAccountControl:1.2.840.113556.1.4.803:=2)))"),
				UsuarioLdap.class);
		return allUsers;
	}
	
	public UsuarioLdap findOne(String cpf){
		UsuarioLdap user = ldapTemplate.findOne(query().where("cn").is(cpf), UsuarioLdap.class);
		return user;
	}

	public void setLdapTemplate(LdapTemplate ldapTemplate) {
		this.ldapTemplate = ldapTemplate;
	}
	
	@RequestMapping(value="usuarioInfos", method=RequestMethod.GET)
	public ModelAndView usuarioInfos(ModelAndView mv){
		 UsuarioLdap user = ldapTemplate.findOne(query().where("cn").is(SecurityContextHolder.getContext().getAuthentication().getName()), UsuarioLdap.class);
		 mv.addObject("usuarioInfos", user);
		 return mv;
	}
	
	public UsuarioLdap userInfoUploadFatura(){
		UsuarioLdap user = ldapTemplate.findOne(query().where("cn").is(SecurityContextHolder.getContext().getAuthentication().getName()), UsuarioLdap.class);
		return user;
	}
}
