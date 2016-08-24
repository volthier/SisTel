package br.gov.cultura.DitelAdm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.cultura.DitelAdm.model.Dispositivo;

public interface Dispositivos extends JpaRepository<Dispositivo, Long> {
	
	public List<Dispositivo> findByModeloDispositivoContaining(String modeloDispositivo); 

}
