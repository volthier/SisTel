package br.gov.cultura.DitelAdm.repository.Faturas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.cultura.DitelAdm.model.faturasV3.Planos;
import br.gov.cultura.DitelAdm.model.faturasV3.Resumo;

public interface Planosas extends JpaRepository<Planos, Long> {

	public List<Planos> findByTipoContaining(String Tipo);
	
	public List<Planos> findByResumo( Resumo resumo );
}
