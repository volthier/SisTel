package br.gov.cultura.DitelAdm.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.gov.cultura.DitelAdm.model.Dispositivo;
import br.gov.cultura.DitelAdm.repository.Dispositivos;
import br.gov.cultura.DitelAdm.repository.filtro.CadastroFiltroPesquisa;

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

	public void excluir(Long id){
		dispositivos.delete(id);
	}
	
	public List<Dispositivo> filtrar(CadastroFiltroPesquisa filtro){
		String modelo = filtro.getModelo() == null ? "%" : filtro.getModelo();
		return dispositivos.findByModeloDispositivoContaining(modelo);
	}

}
