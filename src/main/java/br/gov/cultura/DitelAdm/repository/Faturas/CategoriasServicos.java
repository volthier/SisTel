package br.gov.cultura.DitelAdm.repository.Faturas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.cultura.DitelAdm.model.faturasV3.Categoriaservico;

public interface CategoriasServicos extends JpaRepository<Categoriaservico, Long>{

	public List<Categoriaservico> findByCodCatServicoContaining(String codCatServico);
}