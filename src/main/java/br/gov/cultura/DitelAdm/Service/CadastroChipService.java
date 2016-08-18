package br.gov.cultura.DitelAdm.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.cultura.DitelAdm.model.Chip;
import br.gov.cultura.DitelAdm.repository.Chips;
import br.gov.cultura.DitelAdm.repository.filtro.CadastroFiltroPesquisa;

@Service
public class CadastroChipService {

	@Autowired
	private Chips chips;

	public void salvar(Chip chip) {
		chips.save(chip);
	}

	public void excluir(Long id) {
		// TODO Auto-generated method stub
		chips.delete(id);
	}

	public List<Chip> filtrar(CadastroFiltroPesquisa filtro){
		String numeroSerieChip = filtro.getModelo() == null ? "%" : filtro.getModelo();
		return chips.findByNumeroSerieChipContaining(numeroSerieChip);
	}
}
