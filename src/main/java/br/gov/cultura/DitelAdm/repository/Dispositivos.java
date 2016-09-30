package br.gov.cultura.DitelAdm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.cultura.DitelAdm.model.Dispositivo;

public interface Dispositivos extends JpaRepository<Dispositivo, Integer> {
	
	public List<Dispositivo> findByIdDispositivo(Integer idDispositivo); 
	
//	@Query("select d.modeloDispositivo from Dispositivo d inner join d.alocacaoLinhaDispositivos al where al.iddispositivo = :idDispositivo and al.dtDevolucao NOT NULL")
//	public Dispositivo findByModeloDispositivo (Integer idDispositivo);
	
	@Query("select d.modeloDispositivo from Dispositivo d inner join d.alocacaoLinhaDispositivos al where al.dtDevolucao IS NOT NULL")
	public List<Dispositivo> obterDispositivosDisponiveis();
}
