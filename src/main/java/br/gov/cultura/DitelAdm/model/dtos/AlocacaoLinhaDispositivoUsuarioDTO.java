package br.gov.cultura.DitelAdm.model.dtos;

import java.util.Date;

import br.gov.cultura.DitelAdm.model.Dispositivo;
import br.gov.cultura.DitelAdm.model.Usuario;
import br.gov.cultura.DitelAdm.model.Linha;

public class AlocacaoLinhaDispositivoUsuarioDTO {
	
	private Integer idDispositivo;
	private Integer idAlocacaoUsuarioLinha;
	private Date dtDevolucao;
	private Date dtRecebido;
	private Integer idLinha;
	private Integer idUsuario;
	private Dispositivo dispositivo;
	private Usuario usuario;
	private Linha linha;
	
	public AlocacaoLinhaDispositivoUsuarioDTO() {
	}
	
	public AlocacaoLinhaDispositivoUsuarioDTO(Integer idDispositivo, Integer idAlocacaoUsuarioLinha, 
			Date dtDevolucao, Date dtRecebido, Integer idLinha, Integer idUsuario){
		
		this.idDispositivo = idDispositivo;
		this.idAlocacaoUsuarioLinha = idAlocacaoUsuarioLinha;
		this.dtDevolucao = dtDevolucao;		
		this.dtRecebido = dtRecebido;
		this.idLinha = idLinha;
		this.idUsuario = idUsuario;
	}
	
	public Integer getIdDispositivo() {
		return idDispositivo;
	}

	public void setIdDispositivo(Integer idDispositivo) {
		this.idDispositivo = idDispositivo;
	}

	public Integer getIdAlocacaoUsuarioLinha() {
		return idAlocacaoUsuarioLinha;
	}

	public void setIdAlocacaoUsuarioLinha(Integer idAlocacaoUsuarioLinha) {
		this.idAlocacaoUsuarioLinha = idAlocacaoUsuarioLinha;
	}

	public Date getDtDevolucao() {
		return dtDevolucao;
	}

	public void setDtDevolucao(Date dtDevolucao) {
		this.dtDevolucao = dtDevolucao;
	}

	public Date getDtRecebido() {
		return dtRecebido;
	}

	public void setDtRecebido(Date dtRecebido) {
		this.dtRecebido = dtRecebido;
	}

	public Integer getIdLinha() {
		return idLinha;
	}

	public void setIdLinha(Integer idLinha) {
		this.idLinha = idLinha;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Dispositivo getDispositivo() {
		return dispositivo;
	}

	public void setDispositivo(Dispositivo dispositivo) {
		this.dispositivo = dispositivo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Linha getLinha() {
		return linha;
	}

	public void setLinha(Linha linha) {
		this.linha = linha;
	}

}
