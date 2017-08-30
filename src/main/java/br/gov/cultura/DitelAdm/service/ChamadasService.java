package br.gov.cultura.DitelAdm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.cultura.DitelAdm.model.Linha;
import br.gov.cultura.DitelAdm.model.faturasV3.Chamadas;
import br.gov.cultura.DitelAdm.model.faturasV3.Fatura;
import br.gov.cultura.DitelAdm.repository.Faturas.Chamadasas;

@Service
@Transactional
public class ChamadasService {

	@Autowired
	private Chamadasas chamadasRepository;
	
	public List<Chamadas> getChamadasFatura(Fatura fatura) {
		return chamadasRepository.findByFatura(fatura);
	}
	public List<Chamadas> getChamadasFaturaLinha(Fatura fatura, Linha linha) {
		return chamadasRepository.findChamadasFaturaLinha(fatura, linha);
	}
	

}
