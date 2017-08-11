package br.gov.cultura.DitelAdm.repository.Faturas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.cultura.DitelAdm.model.Linha;
import br.gov.cultura.DitelAdm.model.faturasV3.Fatura;
import br.gov.cultura.DitelAdm.model.faturasV3.Planos;

public interface Planosas extends JpaRepository<Planos, Long> {

	public List<Planos> findByTipoContaining(String Tipo);
	
	public List<Planos> findByFatura( Fatura fatura );
	
	@Query("select r from Planos r where fatura = ?1 and linha = ?2")
	public List<Planos> findPlanosFaturaLinha(Fatura fatura, Linha linha);
}
