package br.gov.cultura.DitelAdm.repository.Faturas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.cultura.DitelAdm.model.faturasV3.Cliente;
import br.gov.cultura.DitelAdm.model.faturasV3.Operadora;



public interface Clientes extends JpaRepository<Cliente, Long>{

	public List<Cliente> findByNomeContaining(String nome);
	
	public Cliente findByCodClienteAndCnpj(String codCliente, String cnpj);
	
	public List<Cliente> findByCodClienteAndCnpjContaining(String codCliente, String cnpj);
}