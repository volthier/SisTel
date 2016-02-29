package br.gov.cultura.DitelAdm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.cultura.DitelAdm.model.CadastroChip;



public interface Chips extends JpaRepository<CadastroChip, Long>{
	
	public List<CadastroChip> findByNrserieContaining(String nrserie);

}
