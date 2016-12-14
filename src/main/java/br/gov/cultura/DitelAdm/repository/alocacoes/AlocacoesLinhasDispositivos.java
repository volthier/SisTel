package br.gov.cultura.DitelAdm.repository.alocacoes;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.cultura.DitelAdm.model.AlocacaoLinhaDispositivo;

public interface AlocacoesLinhasDispositivos extends JpaRepository<AlocacaoLinhaDispositivo, Integer> {
	
	public AlocacaoLinhaDispositivo findByIdAlocacaoLinhaDispositivo(Integer idAlocacaoLinhaDispositivo);
	
}
