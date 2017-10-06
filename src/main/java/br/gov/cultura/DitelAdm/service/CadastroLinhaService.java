package br.gov.cultura.DitelAdm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.gov.cultura.DitelAdm.model.Linha;
import br.gov.cultura.DitelAdm.repository.Linhas;
import br.gov.cultura.DitelAdm.repository.filtro.FiltroPesquisa;

@Service
@Transactional
public class CadastroLinhaService {

	@Autowired
	private Linhas linhas;

	public void salvar(Linha linha) {
		linhas.save(linha);
	}

	public void excluir(Integer id) {
		linhas.delete(id);
	}

	public List<Linha> getIdLinha() {
		return linhas.findAll();
	}
	
	public Linha getLinhaRegistrada(String numero) {
		return linhas.findByNumeroLinhaEquals(numero);
	}
	
	public List<Linha> listarLinhaDisponivel(){
		return linhas.findByNumeroLinha();
	}
	
	public Linha getLinhaById(Integer idLinha){
		return linhas.findByIdLinha(idLinha);
	}
	
	public List<Linha> filtroPesquisa(FiltroPesquisa filtro){
		String filtroPesquisa =  filtro.getNumeroSerie() == null ? "%" : filtro.getNumeroSerie();
		return linhas.findByNumeroLinhaContaining(filtroPesquisa);
	}
	
}
