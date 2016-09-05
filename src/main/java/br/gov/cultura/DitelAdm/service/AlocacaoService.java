package br.gov.cultura.DitelAdm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.cultura.DitelAdm.model.AlocacaoLinhaCategoria;
import br.gov.cultura.DitelAdm.model.AlocacaoLinhaChip;
import br.gov.cultura.DitelAdm.model.AlocacaoLinhaDispositivo;
import br.gov.cultura.DitelAdm.model.AlocacaoUsuarioLinha;
import br.gov.cultura.DitelAdm.repository.alocacoes.AlocacoesLinhasCategorias;
import br.gov.cultura.DitelAdm.repository.alocacoes.AlocacoesLinhasChips;
import br.gov.cultura.DitelAdm.repository.alocacoes.AlocacoesLinhasDispositivos;
import br.gov.cultura.DitelAdm.repository.alocacoes.AlocacoesUsuariosLinhas;

@Service
public class AlocacaoService {

	@Autowired
	private AlocacoesLinhasCategorias alocacoesLinhasCategorias;

	@Autowired
	private AlocacoesLinhasChips alocacoesLinhasChips;

	@Autowired
	private AlocacoesLinhasDispositivos alocacoesLinhasDispositivos;

	@Autowired
	private AlocacoesUsuariosLinhas alocacoesUsuariosLinhas;

	public void salvar(AlocacaoLinhaCategoria alocacaoLinhaCategoria) {
		alocacoesLinhasCategorias.save(alocacaoLinhaCategoria);
	}

	public void salvar(AlocacaoLinhaChip alocacaoLinhaChip) {
		alocacoesLinhasChips.save(alocacaoLinhaChip);
	}

	public void salvar(AlocacaoLinhaDispositivo alocacaoLinhaDispositivo) {
		alocacoesLinhasDispositivos.save(alocacaoLinhaDispositivo);
	}

	public void salvar(AlocacaoUsuarioLinha alocacaoUsuarioLinha) {
		alocacoesUsuariosLinhas.save(alocacaoUsuarioLinha);
	}

	public void excluir(Integer id) {
		alocacoesLinhasCategorias.delete(id);
		alocacoesLinhasChips.delete(id);
		alocacoesLinhasDispositivos.delete(id);
		alocacoesUsuariosLinhas.delete(id);
	}

}