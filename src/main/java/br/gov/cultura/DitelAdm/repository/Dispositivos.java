package br.gov.cultura.DitelAdm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.cultura.DitelAdm.model.Dispositivo;

public interface Dispositivos extends JpaRepository<Dispositivo, Integer> {
	
	public List<Dispositivo> findByIdDispositivo(Integer idDispositivo); 
	
	@Query("select l from Dispositivo l inner join l.alocacaoLinhaDispositivos al WHERE al.dtDevolucao IS NULL")
	public List<Dispositivo> findByNumeroSerieDispositivo();
}
