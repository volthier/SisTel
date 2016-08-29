package br.gov.cultura.DitelAdm.service;
/*package br.gov.cultura.DitelAdm.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.gov.cultura.DitelAdm.model.Alocacao;
import br.gov.cultura.DitelAdm.repository.alocacoes.Alocacoes;
import br.gov.cultura.DitelAdm.repository.alocacoes.AlocacoesLinhasChips;
import br.gov.cultura.DitelAdm.repository.filtro.CadastroFiltroPesquisa;

@Service
public class AlocacaoService {

	@Autowired
	private static Alocacoes alocacoes;
	
	@Autowired
	private static AlocacoesLinhasChips alocacoesLinhasChips;

	public static void salvar(Alocacao alocacao) {
		alocacoes.save(alocacao);
	}

	public void excluir(Long id) {
		alocacoes.delete(id);
	}
	
	

	public List<Alocacao> filtrar(CadastroFiltroPesquisa filtro){
		String id = filtro.getId() == null ? "%" : filtro.getId();
		return alocacoes.findByIdContaining(id);
	}
}
*/