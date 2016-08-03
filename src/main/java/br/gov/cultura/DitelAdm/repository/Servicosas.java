package br.gov.cultura.DitelAdm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.cultura.DitelAdm.modelo.Servicos;



public interface Servicosas extends JpaRepository<Servicos, Long>{

	public List<Servicos> findByIdContaining(String id);
}