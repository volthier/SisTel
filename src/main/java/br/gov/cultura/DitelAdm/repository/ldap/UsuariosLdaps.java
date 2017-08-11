package br.gov.cultura.DitelAdm.repository.ldap;

import java.util.List;

import br.gov.cultura.DitelAdm.model.ldap.UsuarioLdap;

public interface UsuariosLdaps{
	
	public List<UsuarioLdap> findAll();
}	
