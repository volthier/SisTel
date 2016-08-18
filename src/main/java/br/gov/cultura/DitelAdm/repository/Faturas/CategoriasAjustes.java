package br.gov.cultura.DitelAdm.repository.Faturas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.cultura.DitelAdm.model.faturasV3.Categoriaajuste;

public interface CategoriasAjustes extends JpaRepository<Categoriaajuste, Long>{

	public List<Categoriaajuste> findByCodCatAjusteContaining(String codCatAjuste);
}