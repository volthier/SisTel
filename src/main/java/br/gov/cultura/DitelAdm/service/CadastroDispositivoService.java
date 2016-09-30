package br.gov.cultura.DitelAdm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import br.gov.cultura.DitelAdm.model.Dispositivo;
import br.gov.cultura.DitelAdm.repository.Dispositivos;

@Service
public class CadastroDispositivoService {

	@Autowired
	private Dispositivos dispositivos;

	public void salvar(Dispositivo cadastroDispositivo) {
		try {
			dispositivos.save(cadastroDispositivo);
		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("Formato de data invalido!");
		}
	}

	public void excluir(Integer idDispositivo){
		dispositivos.delete(idDispositivo);
	}
	
	public List<Dispositivo> getIdDispositivo() {
		return dispositivos.findAll();
	}
	
	public List<Dispositivo> obterDispositivosDisponiveis() {
		
		return dispositivos.obterDispositivosDisponiveis();
	}
}
