package br.gov.cultura.DitelAdm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.cultura.DitelAdm.model.Contrato;

public interface Contratos extends JpaRepository<Contrato, Integer>  {

	public List<Contrato> findByIdContrato(Integer idContrato);
}
