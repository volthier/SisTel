package br.gov.cultura.DitelAdm.repository.Faturas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.cultura.DitelAdm.model.faturasV3.Ajustes;

public interface Ajustesas extends JpaRepository<Ajustes, Long>{

	public List<Ajustes> findByIdAjustes(Integer idAjustes);
}