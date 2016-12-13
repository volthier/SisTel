package br.gov.cultura.DitelAdm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.cultura.DitelAdm.model.faturasV3.Chamadas;
import br.gov.cultura.DitelAdm.model.faturasV3.Resumo;
import br.gov.cultura.DitelAdm.repository.Faturas.Chamadasas;

@Service
public class ChamadasService {

	@Autowired
	private Chamadasas chamadasRepository;
	
	public List<Chamadas> getChamadaResumo(Resumo resumo) {
		return chamadasRepository.findByResumo(resumo);
	}

}
