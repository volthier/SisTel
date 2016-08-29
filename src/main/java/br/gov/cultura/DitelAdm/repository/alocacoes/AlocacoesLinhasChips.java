package br.gov.cultura.DitelAdm.repository.alocacoes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.cultura.DitelAdm.model.AlocacaoLinhaChip;

public interface AlocacoesLinhasChips extends JpaRepository<AlocacaoLinhaChip, Integer> {
	
	public List<AlocacaoLinhaChip> findByIdAlocacaoLinhaChip(Integer idAlocacaoLinhaChip);

}
