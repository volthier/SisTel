package br.gov.cultura.DitelAdm.repository.Faturas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.cultura.DitelAdm.model.faturasV3.Fatura;



public interface Faturas extends JpaRepository<Fatura, Long>{

	public List<Fatura> findByCodBarraContaining(String codBarra);
	
	public Fatura findByIdFatura(Integer id);
	
	@Query("select f from Fatura f where f.gerada = ?1")
	public List<Fatura> findFaturasGeradas(boolean gerada);
}