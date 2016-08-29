package br.gov.cultura.DitelAdm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.cultura.DitelAdm.model.Alocacao;
import br.gov.cultura.DitelAdm.model.AlocacaoLinhaChip;
import br.gov.cultura.DitelAdm.model.AlocacaoLinhaDispositivo;
import br.gov.cultura.DitelAdm.model.AlocacaoUsuarioLinha;
import br.gov.cultura.DitelAdm.repository.alocacoes.Alocacoes;
import br.gov.cultura.DitelAdm.repository.alocacoes.AlocacoesLinhasChips;
import br.gov.cultura.DitelAdm.repository.alocacoes.AlocacoesLinhasDispositivos;
import br.gov.cultura.DitelAdm.repository.alocacoes.AlocacoesUsuariosLinhas;

@Service
public class AlocacaoService {

	@Autowired
	private static Alocacoes alocacoes;
	
	@Autowired
	private static AlocacoesLinhasChips alocacoesLinhasChips;
	
	@Autowired
	private static AlocacoesLinhasDispositivos alocacoesLinhasDispositivos;
	
	@Autowired
	private static AlocacoesUsuariosLinhas alocacoesUsuariosLinhas;

	public static void salvar(Alocacao alocacao) {
		alocacoes.save(alocacao);
	}

	public void excluir(Integer id) {
		alocacoes.delete(id);
		alocacoesLinhasChips.delete(id);
		alocacoesLinhasDispositivos.delete(id);
		alocacoesUsuariosLinhas.delete(id);
	}
	
	public static void salvar(AlocacaoLinhaChip alocacaoLinhaChip) {
		alocacoesLinhasChips.save(alocacaoLinhaChip);
	}

	public static void salvar(AlocacaoLinhaDispositivo alocacaoLinhaDispositivo) {
		alocacoesLinhasDispositivos.save(alocacaoLinhaDispositivo);
	}

	
	public static void salvar(AlocacaoUsuarioLinha alocacaoUsuarioLinha) {
		alocacoesUsuariosLinhas.save(alocacaoUsuarioLinha);
	}

}
