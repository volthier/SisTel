package br.gov.cultura.DitelAdm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.cultura.DitelAdm.model.AlocacaoUsuarioLinha;
import br.gov.cultura.DitelAdm.model.Linha;
import br.gov.cultura.DitelAdm.repository.alocacoes.AlocacoesUsuariosLinhas;

@Service
public class AlocacaoUsuarioLinhaService {


	@Autowired
	private AlocacoesUsuariosLinhas alocacaoUsuarioLinhaRepository;
	
	public List<AlocacaoUsuarioLinha> getAlocacoesUsuarioLinha() {
		return alocacaoUsuarioLinhaRepository.findAll();
	}
	
	public AlocacaoUsuarioLinha getAlocacaoUsuarioLinha(int id) {
		return alocacaoUsuarioLinhaRepository.findOne(id);
	}
	
	public List<AlocacaoUsuarioLinha> getAlocacaoUsuarioLinha(Linha linha) {
		return alocacaoUsuarioLinhaRepository.getAlocacaoByLinhda(linha);
	}
}