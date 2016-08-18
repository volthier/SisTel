package br.gov.cultura.DitelAdm.repository.Faturas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.cultura.DitelAdm.model.faturasV3.Servicos;



public interface Servicosas extends JpaRepository<Servicos, Long>{

	public List<Servicos> findByIdContaining(String id);
}