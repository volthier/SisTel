package br.gov.cultura.DitelAdm.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.cultura.DitelAdm.model.CadastroPessoa;
import br.gov.cultura.DitelAdm.repository.Pessoas;
import br.gov.cultura.DitelAdm.repository.filtro.CadastroFiltroPesquisa;

@Service
public class CadastroPessoaService {

	@Autowired
	private Pessoas pessoas;

	public void salvar(CadastroPessoa pessoa) {
		pessoas.save(pessoa);

	}

	public void excluir(Long id) {
		// TODO Auto-generated method stub
		pessoas.delete(id);
	}

	public List<CadastroPessoa> filtrar(CadastroFiltroPesquisa filtro) {
		String nome = filtro.getNome() == null ? "%" : filtro.getNome();
		return pessoas.findByNomeContaining(nome);

	}
}
