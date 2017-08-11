package br.gov.cultura.DitelAdm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.cultura.DitelAdm.model.Linha;
import br.gov.cultura.DitelAdm.model.faturasV3.Fatura;
import br.gov.cultura.DitelAdm.model.faturasV3.Resumo;
import br.gov.cultura.DitelAdm.model.faturasV3.Servicos;
import br.gov.cultura.DitelAdm.repository.Faturas.Servicosas;

@Service
public class ServicoService {

	@Autowired
	private Servicosas servicoRepository;
	
	public List<Servicos> getServicosFatura(Fatura fatura){
		return servicoRepository.findByFatura(fatura);
	}
	public List<Servicos> getServicosResumo(Resumo resumo) {
		return servicoRepository.getServicosAgrupadosResumo(resumo);
	}
	
	public List<Servicos> getServicosFaturaLinha(Fatura fatura, Linha linha) {
		return servicoRepository.findServicosFaturaLinha(fatura,linha);
	}
}