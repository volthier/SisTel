package br.gov.cultura.DitelAdm.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.gov.cultura.DitelAdm.model.CadastroChip;
import br.gov.cultura.DitelAdm.repository.Chips;
import br.gov.cultura.DitelAdm.repository.filtro.CadastroFiltroPesquisa;

@Service
public class CadastroChipService {

	@Autowired
	private Chips chips;

	public void salvar(CadastroChip chip) {
		chips.save(chip);

	}

	public void excluir(Long id) {
		// TODO Auto-generated method stub
		chips.delete(id);
	}

	public List<CadastroChip> filtrar(CadastroFiltroPesquisa filtro) {
		String nrseries = filtro.getNrseries() == null ? "%" : filtro.getNrseries();
		return chips.findByNrserieContaining(nrseries);

	}
}
