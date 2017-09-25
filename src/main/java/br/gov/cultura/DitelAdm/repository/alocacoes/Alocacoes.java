package br.gov.cultura.DitelAdm.repository.alocacoes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.cultura.DitelAdm.model.Alocacao;
import br.gov.cultura.DitelAdm.model.Linha;
import br.gov.cultura.DitelAdm.model.Usuario;

public interface Alocacoes extends JpaRepository<Alocacao, Integer> {
	
	@Query("select a from Alocacao a where linha = ?1")
	public List<Alocacao> getAlocacaoByLinha(Linha linha);	
	
	@Query("select a from Alocacao a WHERE a.dtDevolucao IS NOT NULL")
	public List<Alocacao> getAlocacaoDevolucao();
	
	@Query("select a from Alocacao a where usuario = ?1")
	public List<Alocacao> getAlocacoesUsuario(Usuario usuario);
	
	public List<Alocacao> findByUsuario(List<Usuario> usuario);
}
