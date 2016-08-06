package br.gov.cultura.DitelAdm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.cultura.DitelAdm.modelo.Trailler;



public interface Traillers extends JpaRepository<Trailler, Long>{

	public List<Trailler> findById(int id);
}