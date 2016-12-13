package br.gov.cultura.DitelAdm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.cultura.DitelAdm.model.AlocacaoSei;
import br.gov.cultura.DitelAdm.repository.alocacoes.AlocacoesSei;

@Service
public class AlocacaoSeiService {

	@Autowired
	private AlocacoesSei alocacaoSeiRepository;

}