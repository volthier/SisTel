package br.gov.cultura.DitelAdm.repository.Faturas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.cultura.DitelAdm.model.faturasV3.Resumo;
import br.gov.cultura.DitelAdm.model.faturasV3.Servicos;



public interface Servicosas extends JpaRepository<Servicos, Long>{

	public List<Servicos> findByIdServicos(Integer idServicos);
	
	public List<Servicos> findByResumo(Resumo resumo);
	
	@Query("select s from Servicos s where s.resumo = ? order by s.categoriaservico")
	public List<Servicos> getServicosAgrupadosResumo(Resumo resumo);
}