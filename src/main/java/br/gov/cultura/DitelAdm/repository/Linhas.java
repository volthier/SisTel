package br.gov.cultura.DitelAdm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.cultura.DitelAdm.model.Linha;

public interface Linhas extends JpaRepository<Linha, Long>{
	
	public List<Linha> findByNumeroLinhaContaining(String numeroLinha);

}
