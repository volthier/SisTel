package br.gov.cultura.DitelAdm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.cultura.DitelAdm.model.LimiteAtesto;

public interface LimitesAtestos extends JpaRepository<LimiteAtesto, Integer> {

	public List<LimiteAtesto> findByIdLimiteAtesto(Integer idLimiteAtesto);

}
