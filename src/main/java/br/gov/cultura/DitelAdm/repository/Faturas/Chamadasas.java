package br.gov.cultura.DitelAdm.repository.Faturas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.cultura.DitelAdm.model.faturasV3.Chamadas;



public interface Chamadasas extends JpaRepository<Chamadas, Long>{

	public List<Chamadas> findByIdContaining(String id);
}