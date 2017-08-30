package br.gov.cultura.DitelAdm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.cultura.DitelAdm.model.Linha;
import br.gov.cultura.DitelAdm.model.faturasV3.Fatura;
import br.gov.cultura.DitelAdm.model.faturasV3.Planos;
import br.gov.cultura.DitelAdm.repository.Faturas.Planosas;

@Service
@Transactional
public class PlanoService {

	@Autowired
	private Planosas planoRepository;
	
	public List<Planos> getPlanoFatura(Fatura fatura) {
		return planoRepository.findByFatura(fatura);
	}
	
	public List<Planos> getPlanoFaturaLinha(Fatura fatura, Linha linha) {
		return planoRepository.findPlanosFaturaLinha(fatura,linha);
	}
	
}