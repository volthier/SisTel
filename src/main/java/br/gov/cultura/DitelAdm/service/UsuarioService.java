package br.gov.cultura.DitelAdm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.cultura.DitelAdm.model.Usuario;
import br.gov.cultura.DitelAdm.repository.Usuarios;

@Service
public class UsuarioService {

	@Autowired
	private static Usuarios usuarioRepository;
	
	public List<Usuario> getUsuarios() {
		return usuarioRepository.findAll();
	}

}
