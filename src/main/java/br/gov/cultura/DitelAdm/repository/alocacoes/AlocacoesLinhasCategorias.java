package br.gov.cultura.DitelAdm.repository.alocacoes;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.cultura.DitelAdm.model.AlocacaoLinhaCategoria;

public interface AlocacoesLinhasCategorias extends JpaRepository<AlocacaoLinhaCategoria, Integer>{
	
	public AlocacaoLinhaCategoria findByIdAlocacaoLinhaCategoria (Integer idAlocacaoLinhaCategoria);

}
