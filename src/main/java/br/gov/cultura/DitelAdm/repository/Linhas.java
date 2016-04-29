package br.gov.cultura.DitelAdm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.cultura.DitelAdm.model.CadastroLinha;

public interface Linhas extends JpaRepository<CadastroLinha, Long>{
	
	public List<CadastroLinha> findByNlinhaContaining(String nlinha);

}
