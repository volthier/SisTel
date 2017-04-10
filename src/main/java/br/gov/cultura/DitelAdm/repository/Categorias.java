package br.gov.cultura.DitelAdm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.cultura.DitelAdm.model.Categoria;

public interface Categorias extends JpaRepository<Categoria, Integer>{
	
	public Categoria findByIdCategoria(Integer idCategoria);
	

}
