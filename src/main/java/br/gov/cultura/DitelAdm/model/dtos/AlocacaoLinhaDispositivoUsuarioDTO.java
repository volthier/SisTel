package br.gov.cultura.DitelAdm.model.dtos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.gov.cultura.DitelAdm.model.Dispositivo;
import br.gov.cultura.DitelAdm.model.Usuario;

public class AlocacaoLinhaDispositivoUsuarioDTO {
	
	private Integer idDispositivo;
	private Integer idAlocacaoUsuarioLinha;
	private Date dtDevolucao;
	private Date dtRecebido;
	private Integer idLinha;
	private Integer idUsuario;
	private Dispositivo dispositivo;
	private Usuario usuario;
	private SimpleDateFormat df;
	
	public AlocacaoLinhaDispositivoUsuarioDTO() {
	}
	
	public AlocacaoLinhaDispositivoUsuarioDTO(Object idDispositivo, Object idAlocacaoUsuarioLinha, 
			Object dtDevolucao, Object dtRecebido, Object idLinha, Object idUsuario){
		
		df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.idDispositivo = Integer.parseInt(idDispositivo.toString());
		this.idAlocacaoUsuarioLinha = Integer.parseInt(idAlocacaoUsuarioLinha.toString());
		if(dtDevolucao != null){
			try {
				this.dtDevolucao = df.parse(dtDevolucao.toString().substring(0, 19));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(dtRecebido != null){
			try {
				this.dtRecebido = df.parse(dtRecebido.toString().substring(0, 19));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		this.idLinha = Integer.parseInt(idLinha.toString());
		this.idUsuario = Integer.parseInt(idUsuario.toString());
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

	public SimpleDateFormat getDf() {
		return df;
	}

	public void setDf(SimpleDateFormat df) {
		this.df = df;
	}

}
