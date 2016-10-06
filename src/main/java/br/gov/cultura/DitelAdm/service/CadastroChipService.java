package br.gov.cultura.DitelAdm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.cultura.DitelAdm.model.Chip;
import br.gov.cultura.DitelAdm.model.Dispositivo;
import br.gov.cultura.DitelAdm.repository.Chips;

@Service
public class CadastroChipService {

	@Autowired
	private Chips chips;

	public void salvar(Chip chip) {
		chips.save(chip);
	}

	public void excluir(Integer id) {
		// TODO Auto-generated method stub
		chips.delete(id);
	}

	
	public List<Chip> getIdChip() {
		return chips.findAll();
	}
	public List<Chip> listarChipDisponivel(){
		return chips.findByNumeroSerieChip();
	}
}
