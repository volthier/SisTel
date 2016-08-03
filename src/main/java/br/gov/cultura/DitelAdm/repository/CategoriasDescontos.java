package br.gov.cultura.DitelAdm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.cultura.DitelAdm.modelo.Categoriadesconto;

public interface CategoriasDescontos extends JpaRepository<Categoriadesconto, Long>{

	public List<Categoriadesconto> findByCodCatDescontoContaining(String codCatDesconto);
}