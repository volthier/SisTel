package br.gov.cultura.DitelAdm.repository.ldap;

import java.util.Collection;

import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsContextMapper {
	
	UserDetails mapUserFromContext(DirContextOperations ctx, String username, Collection<GrantedAuthority> authorities);
	
	void mapUserToContext(UserDetails user, DirContextAdapter ctx);
}
