package br.gov.cultura.DitelAdm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.cultura.DitelAdm.model.CadastroDispositivo;

public interface Dispositivos extends JpaRepository<CadastroDispositivo, Long> {
	
	public List<CadastroDispositivo> findByModeloContaining(String modelo); 

}
