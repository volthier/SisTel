package br.gov.cultura.DitelAdm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.cultura.DitelAdm.model.CadastroPessoa;

public interface Pessoas extends JpaRepository<CadastroPessoa, Long>{
	
	public List<CadastroPessoa> findByNomeContaining(String nome);

}
