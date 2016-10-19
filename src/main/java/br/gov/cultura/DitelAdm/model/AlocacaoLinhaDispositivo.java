package br.gov.cultura.DitelAdm.model;
// Generated 24/08/2016 14:33:52 by Hibernate Tools 4.3.4.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * AlocacaoLinhaDispositivo generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "alocacao_linha_dispositivo", catalog = "diteladmdev")
public class AlocacaoLinhaDispositivo implements java.io.Serializable {

	private Integer idAlocacaoLinhaDispositivo;
	@JsonBackReference
	private Dispositivo dispositivo;
	@JsonBackReference
	private Linha linha;
	private Date dtDevolucao;
	private Date dtRecebimento;

	public AlocacaoLinhaDispositivo() {
	}

	public AlocacaoLinhaDispositivo(Dispositivo dispositivo, Linha linha, Date dtRecebimento) {
		this.dispositivo = dispositivo;
		this.linha = linha;
		this.dtRecebimento = dtRecebimento;
	}

	public AlocacaoLinhaDispositivo(Dispositivo dispositivo, Linha linha, Date dtDevolucao, Date dtRecebimento) {
		this.dispositivo = dispositivo;
		this.linha = linha;
		this.dtDevolucao = dtDevolucao;
		this.dtRecebimento = dtRecebimento;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id_alocacao_linha_dispositivo", unique = true, nullable = false)
	public Integer getIdAlocacaoLinhaDispositivo() {
		return this.idAlocacaoLinhaDispositivo;
	}

	public void setIdAlocacaoLinhaDispositivo(Integer idAlocacaoLinhaDispositivo) {
		this.idAlocacaoLinhaDispositivo = idAlocacaoLinhaDispositivo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dispositivo_id_dispositivo", nullable = false)
	public Dispositivo getDispositivo() {
		return this.dispositivo;
	}

	public void setDispositivo(Dispositivo dispositivo) {
		this.dispositivo = dispositivo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "linha_idlinha", nullable = false)
	public Linha getLinha() {
		return this.linha;
	}

	public void setLinha(Linha linha) {
		this.linha = linha;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_devolucao", length = 19)
	public Date getDtDevolucao() {
		return this.dtDevolucao;
	}

	public void setDtDevolucao(Date dtDevolucao) {
		this.dtDevolucao = dtDevolucao;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_recebimento", nullable = false, length = 19)
	public Date getDtRecebimento() {
		return this.dtRecebimento;
	}

	public void setDtRecebimento(Date dtRecebimento) {
		this.dtRecebimento = dtRecebimento;
	}

}
