package br.gov.cultura.DitelAdm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.cultura.DitelAdm.modelo.Categoriaajuste;

public interface CategoriasAjustes extends JpaRepository<Categoriaajuste, Long>{

	public List<Categoriaajuste> findByCodCatAjusteContaining(String codCatAjuste);
}