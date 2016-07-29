package br.gov.cultura.DitelAdm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.cultura.DitelAdm.modelo.Cliente;



public interface Clientes extends JpaRepository<Cliente, Long>{

	public List<Cliente> findByNomeContaining(String nome);
}