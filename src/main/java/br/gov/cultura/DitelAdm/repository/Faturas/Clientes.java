package br.gov.cultura.DitelAdm.repository.Faturas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.cultura.DitelAdm.model.faturasV3.Cliente;



public interface Clientes extends JpaRepository<Cliente, Long>{

	public List<Cliente> findByNomeContaining(String nome);
}