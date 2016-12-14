package br.gov.cultura.DitelAdm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.cultura.DitelAdm.model.Chip;



public interface Chips extends JpaRepository<Chip, Integer>{
	
	public List<Chip> findByIdChip(Integer idChip);
	
	@Query("select l from Chip l inner join l.alocacaoLinhaChips al WHERE al.dtDevolucao IS NULL")
	public List<Chip> findByNumeroSerieChip();

}
