package br.gov.cultura.DitelAdm.model.dtos;

import java.util.Date;

import br.gov.cultura.DitelAdm.model.Dispositivo;
import br.gov.cultura.DitelAdm.model.Usuario;

public class AlocacaoLinhaDispositivoUsuarioDTO {

	private Integer idDispositivo;
	private Integer idAlocacaoUsuarioLinha;
	private Date dtDevolucao;
	private Date dtRecebido;
	private Integer idLinha;
	private Dispositivo dispositivo;
	private Usuario usuario;
	
	public AlocacaoLinhaDispositivoUsuarioDTO() {
	}
	
	public AlocacaoLinhaDispositivoUsuarioDTO(Integer idDispositivo, Integer idAlocacaoUsuarioLinha, Date dtDevolucao, Date dtRecebido, Integer idLinha){
		this.idDispositivo = idDispositivo;
		this.idAlocacaoUsuarioLinha = idAlocacaoUsuarioLinha;
		this.dtDevolucao = dtDevolucao;
		this.dtRecebido = dtRecebido;
		this.idLinha = idLinha;
	}
	
}
