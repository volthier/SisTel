package br.gov.cultura.DitelAdm.model;
// Generated 27/04/2017 16:07:37 by Hibernate Tools 4.0.0.Final

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.gov.cultura.DitelAdm.model.faturasV3.Fatura;

/**
 * AlocacaoFatura MODEL
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "alocacao_fatura", catalog = "dbditel")
public class AlocacaoFatura implements java.io.Serializable {

	private Integer idAlocacaoFatura;
	@JsonIgnore
	private Fatura fatura;
	@JsonIgnore
	private Alocacao alocacao;
	@JsonIgnore
	private Linha linha;
	@JsonIgnore
	private DocumentoSei documentoSei;
	private boolean ressarcimento;

	public AlocacaoFatura() {
	}

	public AlocacaoFatura(boolean ressarcimento, Fatura fatura, Alocacao alocacao, Linha linha, DocumentoSei documentoSei) {
		this.fatura = fatura;
		this.alocacao = alocacao;
		this.linha = linha;
		this.ressarcimento = ressarcimento;
		this.documentoSei = documentoSei;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_alocacao_fatura", unique = true, nullable = false)
	public Integer getIdAlocacaoFatura() {
		return this.idAlocacaoFatura;
	}

	public void setIdAlocacaoFatura(Integer idAlocacaoFatura) {
		this.idAlocacaoFatura = idAlocacaoFatura;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_fatura", nullable = false)
	public Fatura getFatura() {
		return this.fatura;
	}

	public void setFatura(Fatura fatura) {
		this.fatura = fatura;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_alocacao", nullable = false)
	public Alocacao getAlocacao() {
		return this.alocacao;
	}

	public void setAlocacao(Alocacao alocacao) {
		this.alocacao = alocacao;
	}
	
	@Column(name = "ressarcimento", nullable = false)
	public boolean isRessarcimento() {
		return this.ressarcimento;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_linha")
	public Linha getLinha() {
		return this.linha;
	}

	public void setLinha(Linha linha) {
		this.linha = linha;
	}
	
	public void setRessarcimento(boolean ressarcimento) {
		this.ressarcimento = ressarcimento;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_documentos_sei")
	public DocumentoSei getDocumentoSei() {
		return documentoSei;
	}

	public void setDocumentoSei(DocumentoSei documentoSei) {
		this.documentoSei = documentoSei;
	}
	
	

}
