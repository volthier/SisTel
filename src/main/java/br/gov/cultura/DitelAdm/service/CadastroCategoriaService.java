package br.gov.cultura.DitelAdm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.cultura.DitelAdm.model.Categoria;
import br.gov.cultura.DitelAdm.repository.Categorias;

@Service
@Transactional
public class CadastroCategoriaService {

	@Autowired
	private Categorias categorias;

	public void salvar(Categoria categoria) {
		categorias.save(categoria);
	}

	public void excluir(Integer id) {
		categorias.delete(id);
	}
	
	public List<Categoria> getIdCategoria() {
		return categorias.findAll();
	}
	
	public  Categoria getCadastroById(Integer idCategoria) {
		return categorias.findByIdCategoria(idCategoria);
	}
}
