package br.gov.cultura.DitelAdm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.cultura.DitelAdm.modelo.Ajustes;

public interface Ajustesas extends JpaRepository<Ajustes, Long>{

	public List<Ajustes> findByIdAjustes(Integer idAjustes);
}