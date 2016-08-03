package br.gov.cultura.DitelAdm.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.gov.cultura.DitelAdm.modelo.Categoriachamada;

public interface CategoriasChamadas extends JpaRepository<Categoriachamada, Long>{

	public List<Categoriachamada> findByCodCatChamadaContaining(String codCatChamada);
}