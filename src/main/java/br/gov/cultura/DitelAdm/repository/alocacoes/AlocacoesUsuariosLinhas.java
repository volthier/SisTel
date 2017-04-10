package br.gov.cultura.DitelAdm.repository.alocacoes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.cultura.DitelAdm.model.AlocacaoUsuarioLinha;
import br.gov.cultura.DitelAdm.model.Linha;

public interface AlocacoesUsuariosLinhas extends JpaRepository<AlocacaoUsuarioLinha, Integer> {

	public AlocacaoUsuarioLinha findByIdAlocacaoUsuarioLinha(Integer idAlocacaoUsuarioLinha);
	
	@Query(value="SELECT "
						+ "alocacaoLinhaDispositivo.dispositivo_id_dispositivo,"
						+ " alocacaoUsuarioLinha.*,"
						+ " alocacaoLinhaCategoria.categoria_id_categoria "
			   + "FROM dbditel.alocacao_linha_dispositivo AS alocacaoLinhaDispositivo,"
			   + "     dbditel.alocacao_usuario_linha AS alocacaoUsuarioLinha,"
			   + "     dbditel.alocacao_linha_categoria AS alocacaoLinhaCategoria "
			   + "WHERE alocacaoLinhaDispositivo.id_alocacao_linha_dispositivo = alocacaoUsuarioLinha.id_alocacao_usuario_linha"
			   + " AND  alocacaoLinhaDispositivo.id_alocacao_linha_dispositivo = alocacaoLinhaCategoria.id_alocacao_linha_categoria", nativeQuery = true)
	public List<Object[]> getAlocacoesUsuarios ();
	
	@Query("select a from AlocacaoUsuarioLinha a where linha = ?1")
	public List<AlocacaoUsuarioLinha> getAlocacaoByLinha(Linha linha);
}