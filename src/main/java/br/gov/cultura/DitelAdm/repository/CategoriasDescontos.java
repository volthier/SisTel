package br.gov.cultura.DitelAdm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.cultura.DitelAdm.model.faturasV3.Categoriadesconto;

public interface CategoriasDescontos extends JpaRepository<Categoriadesconto, Long>{

	public List<Categoriadesconto> findByCodCatDescontoContaining(String codCatDesconto);
}