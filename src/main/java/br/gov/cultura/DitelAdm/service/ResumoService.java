package br.gov.cultura.DitelAdm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.cultura.DitelAdm.model.Linha;
import br.gov.cultura.DitelAdm.model.faturasV3.Fatura;
import br.gov.cultura.DitelAdm.model.faturasV3.Resumo;
import br.gov.cultura.DitelAdm.repository.Faturas.Resumos;

@Service
public class ResumoService {

	@Autowired
	private Resumos resumoRepository;
	
	public List<Resumo> getResumoFatura(Fatura fatura) {
		return resumoRepository.findByFatura(fatura);
	}
	
	public List<Resumo> getResumoFaturaLinha(Fatura fatura, Linha linha) {
		return resumoRepository.findResumoFaturaLinha(fatura, linha);
	}
}