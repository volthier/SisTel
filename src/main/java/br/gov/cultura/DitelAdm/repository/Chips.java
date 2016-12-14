package br.gov.cultura.DitelAdm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.cultura.DitelAdm.model.Chip;



public interface Chips extends JpaRepository<Chip, Integer>{
	
	public List<Chip> findByIdChip(Integer idChip);

}
