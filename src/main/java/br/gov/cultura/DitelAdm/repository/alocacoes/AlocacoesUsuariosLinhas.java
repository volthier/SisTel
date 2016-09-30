package br.gov.cultura.DitelAdm.repository.alocacoes;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.cultura.DitelAdm.model.AlocacaoUsuarioLinha;

public interface AlocacoesUsuariosLinhas extends JpaRepository<AlocacaoUsuarioLinha, Integer> {

	public AlocacaoUsuarioLinha findByIdAlocacaoUsuarioLinha(Integer idAlocacaoUsuarioLinha);
}