package br.gov.cultura.DitelAdm.repository.alocacoes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.cultura.DitelAdm.model.AlocacaoLinhaChip;
import br.gov.cultura.DitelAdm.model.AlocacaoLinhaDispositivo;

public interface AlocacoesLinhasDispositivos extends JpaRepository<AlocacaoLinhaDispositivo, Integer> {
	
	public List<AlocacaoLinhaDispositivo> findByIdAlocacaoLinhaDispositivo(Integer idAlocacaoLinhaChip);

}
