package br.gov.cultura.DitelAdm.repository.Faturas;

import br.gov.cultura.DitelAdm.model.faturasV3.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;



public interface Clientes extends JpaRepository<Cliente, Long>{

	public List<Cliente> findByNomeContaining(String nome);
}
