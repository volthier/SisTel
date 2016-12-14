package br.gov.cultura.DitelAdm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.cultura.DitelAdm.model.Usuario;
import br.gov.cultura.DitelAdm.repository.Usuarios;

@Service
public class CadastroUsuarioService {

	@Autowired
	private Usuarios usuarios;

	public void salvar(Usuario usuario) {
		usuarios.save(usuario);
	}

	public void excluir(Integer id) {
		usuarios.delete(id);
	}
	public List<Usuario> getIdUsuario() {
		return usuarios.findAll();
	}
	
	public List<Usuario> getId() {
		return usuarios.findOne();
	}
}