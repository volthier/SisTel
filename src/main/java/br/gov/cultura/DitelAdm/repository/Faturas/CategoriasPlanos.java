package br.gov.cultura.DitelAdm.repository.Faturas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.cultura.DitelAdm.model.faturasV3.Categoriaplano;

public interface CategoriasPlanos extends JpaRepository<Categoriaplano, Long>{

	public List<Categoriaplano> findBySiglaContaining(String sigla);
}