package br.gov.cultura.DitelAdm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.cultura.DitelAdm.model.Linha;

public interface Linhas extends JpaRepository<Linha, Integer>{
	
	public List<Linha> findByIdLinha(Integer idLinha);

}
