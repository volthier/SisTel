package br.gov.cultura.DitelAdm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.cultura.DitelAdm.model.faturasV3.Planos;

public interface Planosas extends JpaRepository<Planos, Long> {

	public List<Planos> findByTipoContaining(String Tipo);
}
