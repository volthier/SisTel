package br.gov.cultura.DitelAdm.repository.Faturas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.cultura.DitelAdm.model.Linha;
import br.gov.cultura.DitelAdm.model.faturasV3.Chamadas;
import br.gov.cultura.DitelAdm.model.faturasV3.Fatura;
import br.gov.cultura.DitelAdm.model.faturasV3.Servicos;



public interface Chamadasas extends JpaRepository<Chamadas, Long>{

	public List<Chamadas> findByIdChamadas(Integer idChamadas);
	
	public List<Chamadas> findByFatura( Fatura fatura);
	
	@Query("select r from Chamadas r where fatura = ?1 and linha = ?2")
	public List<Chamadas> findChamadasFaturaLinha(Fatura fatura, Linha linha);
}