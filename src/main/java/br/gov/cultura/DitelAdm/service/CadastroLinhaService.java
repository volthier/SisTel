package br.gov.cultura.DitelAdm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.cultura.DitelAdm.model.Linha;
import br.gov.cultura.DitelAdm.repository.Linhas;

@Service
public class CadastroLinhaService {

	@Autowired
	private Linhas linhas;

	public void salvar(Linha linha) {
		linhas.save(linha);
	}

	public void excluir(Integer id) {
		// TODO Auto-generated method stub
		linhas.delete(id);
	}

	public List<Linha> getIdLinha() {
		return linhas.findAll();
	}
}
