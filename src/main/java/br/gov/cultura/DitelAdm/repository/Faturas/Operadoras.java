package br.gov.cultura.DitelAdm.repository.Faturas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.cultura.DitelAdm.model.faturasV3.Operadora;

public interface Operadoras extends JpaRepository<Operadora, Long>{

	public List<Operadora> findByCodOperadora(int nome);

	public Operadora findByCnpjAndCodOperadora(String cnpj, int codOperadora);
}
