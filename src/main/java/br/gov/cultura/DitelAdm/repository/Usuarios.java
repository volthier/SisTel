package br.gov.cultura.DitelAdm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.cultura.DitelAdm.model.Usuario;

public interface Usuarios extends JpaRepository<Usuario, Integer>{
	
	public List<Usuario> findByIdUsuario(Integer idUsuario);

	@Query("select u.idUsuario, u.nomeUsuario,u.cargoUsuario,"
			+ "u.cpfUsuario,u.lotacaoUsuario, u.limiteAtesto.dasAtesto,"
			+ "u.limiteAtesto.valorLimite from Usuario u")
	public List<Usuario> findOne();
}
