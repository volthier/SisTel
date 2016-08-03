package br.gov.cultura.DitelAdm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.cultura.DitelAdm.modelo.Planos;



public interface Planosas extends JpaRepository<Planos, Long>{

	public List<Planos> findByCodPlanoContaining(String codPlano);
}