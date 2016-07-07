package br.gov.cultura.DitelAdm.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.cultura.DitelAdm.model.dtos.FaturaArquivoDTO;
import br.gov.cultura.DitelAdm.repository.Operadoras;

@Service
public class FaturaService {
	
	@Autowired
	private Operadoras operadoras;

	public void salvarOp(FaturaArquivoDTO faturaArquivoDTO) {
		operadoras.save(faturaArquivoDTO.getOperadora());
		
	}

	
}
