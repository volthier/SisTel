package br.gov.cultura.DitelAdm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.cultura.DitelAdm.modelo.Categoriaservico;

public interface CategoriasServicos extends JpaRepository<Categoriaservico, Long>{

	public List<Categoriaservico> findByCodCatServicoContaining(String codCatServico);
}