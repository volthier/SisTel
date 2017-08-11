package br.gov.cultura.DitelAdm.repository.alocacoes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.cultura.DitelAdm.model.Alocacao;
import br.gov.cultura.DitelAdm.model.DocumentoSei;

public interface DocumentosSei extends JpaRepository<DocumentoSei, Integer>{

	List<DocumentoSei> findByAlocacao(Alocacao alocacao);
}
