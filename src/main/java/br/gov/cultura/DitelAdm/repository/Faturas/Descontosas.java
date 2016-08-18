package br.gov.cultura.DitelAdm.repository.Faturas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.cultura.DitelAdm.model.faturasV3.Descontos;



public interface Descontosas extends JpaRepository<Descontos, Long>{

	public List<Descontos> findByIdContaining(String id);
}