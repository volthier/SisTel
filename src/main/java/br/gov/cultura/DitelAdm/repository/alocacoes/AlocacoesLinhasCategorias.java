package br.gov.cultura.DitelAdm.repository.alocacoes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.cultura.DitelAdm.model.AlocacaoLinhaCategoria;

public interface AlocacoesLinhasCategorias extends JpaRepository<AlocacaoLinhaCategoria, Integer>{
	
	public List<AlocacaoLinhaCategoria> findByIdAlocacaoLinhaCategoria (Integer idAlocacaoLinhaCategoria);

}
