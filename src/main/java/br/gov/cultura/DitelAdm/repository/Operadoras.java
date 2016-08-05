package br.gov.cultura.DitelAdm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.cultura.DitelAdm.modelo.Operadora;

public interface Operadoras extends JpaRepository<Operadora, Long>{

	public List<Operadora> findByCodOperadora(int nome);
}
