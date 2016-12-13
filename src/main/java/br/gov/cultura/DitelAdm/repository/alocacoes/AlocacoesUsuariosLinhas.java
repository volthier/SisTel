package br.gov.cultura.DitelAdm.repository.alocacoes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.cultura.DitelAdm.model.AlocacaoUsuarioLinha;
import br.gov.cultura.DitelAdm.model.Linha;

public interface AlocacoesUsuariosLinhas extends JpaRepository<AlocacaoUsuarioLinha, Integer> {

	public AlocacaoUsuarioLinha findByIdAlocacaoUsuarioLinha(Integer idAlocacaoUsuarioLinha);
	
	@Query("select a from AlocacaoUsuarioLinha a where linha = ?1")
	public List<AlocacaoUsuarioLinha> getAlocacaoByLinhda(Linha linha);
	
	public List<AlocacaoUsuarioLinha> findByLinha(Linha linha);
}