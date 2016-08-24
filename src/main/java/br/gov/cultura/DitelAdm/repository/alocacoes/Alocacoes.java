package br.gov.cultura.DitelAdm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.cultura.DitelAdm.model.Alocacao;

public interface Alocacoes extends JpaRepository<Alocacao, Long>{
	
	public List<Alocacao> findByIdContaining(String id);

}
