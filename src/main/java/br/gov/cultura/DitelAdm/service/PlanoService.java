package br.gov.cultura.DitelAdm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.cultura.DitelAdm.model.faturasV3.Fatura;
import br.gov.cultura.DitelAdm.model.faturasV3.Planos;
import br.gov.cultura.DitelAdm.model.faturasV3.Resumo;
import br.gov.cultura.DitelAdm.repository.Faturas.Planosas;

@Service
public class PlanoService {

	@Autowired
	private Planosas planoRepository;
	
	public List<Planos> getPlanoResumo(Resumo resumo) {
		return planoRepository.findByResumo(resumo);
	}
}