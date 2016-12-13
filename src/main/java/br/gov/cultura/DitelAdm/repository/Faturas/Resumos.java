package br.gov.cultura.DitelAdm.repository.Faturas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.cultura.DitelAdm.model.Linha;
import br.gov.cultura.DitelAdm.model.faturasV3.Fatura;
import br.gov.cultura.DitelAdm.model.faturasV3.Resumo;



public interface Resumos extends JpaRepository<Resumo, Long>{

	public List<Resumo> findByIdUnicoContaining(String idUnico);
	
	public List<Resumo> findByFatura(Fatura fatura);
	
	@Query("select r from Resumo r where fatura = ?1 and linha = ?2")
	public List<Resumo> findResumoFaturaLinha(Fatura fatura, Linha linha);
}