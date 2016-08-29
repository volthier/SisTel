package br.gov.cultura.DitelAdm.repository.alocacoes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.cultura.DitelAdm.model.Alocacao;

public interface Alocacoes extends JpaRepository<Alocacao, Integer>{
	
	public List<Alocacao> findById(Integer id);

}
