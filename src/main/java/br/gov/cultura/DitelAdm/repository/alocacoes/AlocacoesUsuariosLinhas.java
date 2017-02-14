package br.gov.cultura.DitelAdm.repository.alocacoes;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.cultura.DitelAdm.model.AlocacaoUsuarioLinha;
import br.gov.cultura.DitelAdm.model.Linha;

public interface AlocacoesUsuariosLinhas extends JpaRepository<AlocacaoUsuarioLinha, Integer> {

	public AlocacaoUsuarioLinha findByIdAlocacaoUsuarioLinha(Integer idAlocacaoUsuarioLinha);
	
	@Query(value="SELECT alocacaoLinhaDispositivo.dispositivo_id_dispositivo, alocacaoUsuarioLinha.* "
			+ "     FROM diteladmdev.alocacao_linha_dispositivo AS alocacaoLinhaDispositivo ,"
			+ " 		 diteladmdev.alocacao_usuario_linha AS alocacaoUsuarioLinha "
			+ " WHERE alocacaoLinhaDispositivo.id_alocacao_linha_dispositivo = alocacaoUsuarioLinha.id_alocacao_usuario_linha", nativeQuery = true)
	public List<Object[]> getAlocacoesUsuarios ();
	
	@Query("select a from AlocacaoUsuarioLinha a where linha = ?1")
	public List<AlocacaoUsuarioLinha> getAlocacaoByLinha(Linha linha);
}