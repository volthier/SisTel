package br.gov.cultura.DitelAdm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.cultura.DitelAdm.model.Linha;

public interface Linhas extends JpaRepository<Linha, Integer>{
	
	public List<Linha> findByIdLinha(Integer idLinha);
	
	@Query("select l.numeroLinha, l.idLinha from AlocacaoUsuarioLinha al inner join al.linha l where al.idLinha =:l.idLinha")
	public List<Linha> findByNumeroLinha();

}
