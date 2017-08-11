package br.gov.cultura.DitelAdm.model;
// Generated 27/04/2017 16:07:37 by Hibernate Tools 4.0.0.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * AlocacaoSei MODEL: Alocações documentadas no SEI
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "alocacao_sei", catalog = "dbditel")
public class AlocacaoSei implements java.io.Serializable {

	private Integer idAlocacaoSei;
	private Date dtAberturaProcesso;
	private Date dtEncerramentoProcesso;
	private String linkAcessoSei;
	private String numeroExternoProcessoSei;
	private String numeroProcessoSei;
	@JsonManagedReference
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Set<DocumentoSei> documentoSeis = new HashSet(0);
	@JsonManagedReference
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Set<Alocacao> alocacaos = new HashSet(0);

	public AlocacaoSei() {
	}

	public AlocacaoSei(Date dtAberturaProcesso) {
		this.dtAberturaProcesso = dtAberturaProcesso;
	}

	public AlocacaoSei(Integer idAlocacaoSei, Date dtAberturaProcesso, Date dtEncerramentoProcesso,
			String linkAcessoSei, String numeroExternoProcessoSei, String numeroProcessoSei,
			Set<DocumentoSei> documentoSeis, Set<Alocacao> alocacaos) {
		super();
		this.idAlocacaoSei = idAlocacaoSei;
		this.dtAberturaProcesso = dtAberturaProcesso;
		this.dtEncerramentoProcesso = dtEncerramentoProcesso;
		this.linkAcessoSei = linkAcessoSei;
		this.numeroExternoProcessoSei = numeroExternoProcessoSei;
		this.numeroProcessoSei = numeroProcessoSei;
		this.documentoSeis = documentoSeis;
		this.alocacaos = alocacaos;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_alocacao_sei", unique = true, nullable = false)
	public Integer getIdAlocacaoSei() {
		return this.idAlocacaoSei;
	}

	public void setIdAlocacaoSei(Integer idAlocacaoSei) {
		this.idAlocacaoSei = idAlocacaoSei;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_abertura_processo", length = 19)
	public Date getDtAberturaProcesso() {
		return this.dtAberturaProcesso;
	}

	public void setDtAberturaProcesso(Date dtAberturaProcesso) {
		this.dtAberturaProcesso = dtAberturaProcesso;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_encerramento_processo", length = 19)
	public Date getDtEncerramentoProcesso() {
		return this.dtEncerramentoProcesso;
	}

	public void setDtEncerramentoProcesso(Date dtEncerramentoProcesso) {
		this.dtEncerramentoProcesso = dtEncerramentoProcesso;
	}
	@Column(name = "link_acesso_sei")
	public String getLinkAcessoSei() {
		return this.linkAcessoSei;
	}

	public void setLinkAcessoSei(String linkAcessoSei) {
		this.linkAcessoSei = linkAcessoSei;
	}
	@Column(name = "numero_externo_processo_sei")
	public String getNumeroExternoProcessoSei() {
		return this.numeroExternoProcessoSei;
	}

	public void setNumeroExternoProcessoSei(String numeroExternoProcessoSei) {
		this.numeroExternoProcessoSei = numeroExternoProcessoSei;
	}
	@Column(name = "numero_processo_sei")
	public String getNumeroProcessoSei() {
		return this.numeroProcessoSei;
	}

	public void setNumeroProcessoSei(String numeroProcessoSei) {
		this.numeroProcessoSei = numeroProcessoSei;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "alocacaoSei")
	public Set<Alocacao> getAlocacaos() {
		return alocacaos;
	}

	public void setAlocacaos(Set<Alocacao> alocacaos) {
		this.alocacaos = alocacaos;
	}
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "alocacaoSei")
	public Set<DocumentoSei> getDocumentoSeis() {
		return documentoSeis;
	}

	public void setDocumentoSeis(Set<DocumentoSei> documentoSeis) {
		this.documentoSeis = documentoSeis;
	}

}
