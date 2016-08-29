package br.gov.cultura.DitelAdm.repository.alocacoes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.cultura.DitelAdm.model.AlocacaoUsuarioLinha;

public interface AlocacoesUsuariosLinhas extends JpaRepository<AlocacaoUsuarioLinha, Integer> {

	public List<AlocacaoUsuarioLinha> findByIdAlocacaoUsuarioLinha(Integer idAlocacaoUsuarioLinha);
}
