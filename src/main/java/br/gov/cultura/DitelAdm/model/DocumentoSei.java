package br.gov.cultura.DitelAdm.model;
// Generated 27/04/2017 16:07:37 by Hibernate Tools 4.0.0.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * DocumentosSei MODEL
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "documento_sei", catalog = "dbditel")
public class DocumentoSei implements java.io.Serializable {

	private Integer idDocumentosSei;
	@JsonBackReference
	private AlocacaoSei alocacaoSei;
	@JsonBackReference
	private Set<Alocacao> alocacao;
	@JsonBackReference
	private AlocacaoFatura alocacaoFatura;
	private String documentosTipo;
	private String documentosLink;
	private String documentoIdSei;
	private String documentosNumero;
	private Date documentosDataGerado;
	private String blocoId;
	private boolean blocoDisponibilizado;
	private boolean blocoFinalizado;
	private String assinaturaNome;
	private String assinaturaCargo;
	private Date assinaturaHora;
	boolean assinaturaValida;
	
	public DocumentoSei() {
	}

	public DocumentoSei(AlocacaoSei alocacaoSei, Set<Alocacao> alocacao, AlocacaoFatura alocacaoFatura, String documentosTipo, String documentosLink, String documentoIdSei, String documentosNumero,
			Date documentosDataGerado, String blocoId, Boolean blocoDisponibilizado, Boolean blocoFinalizado, String assinaturaNome, String assinaturaCargo, Date assinaturaHora, boolean assinaturaValida) {
		this.alocacaoSei = alocacaoSei;
		this.alocacao = alocacao;
		this.alocacaoFatura = alocacaoFatura;
		this.documentosTipo = documentosTipo;
		this.documentosLink = documentosLink;
		this.documentoIdSei = documentoIdSei;
		this.documentosNumero = documentosNumero;
		this.documentosDataGerado = documentosDataGerado;
		this.blocoId = blocoId;
		this.blocoDisponibilizado = blocoDisponibilizado;
		this.blocoFinalizado = blocoFinalizado;
		this.assinaturaNome = assinaturaNome;
		this.assinaturaCargo = assinaturaCargo;
		this.assinaturaHora = assinaturaHora;
		this.assinaturaValida = assinaturaValida;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_documentos_sei", unique = true, nullable = false)
	public Integer getIdDocumentosSei() {
		return this.idDocumentosSei;
	}

	public void setIdDocumentosSei(Integer idDocumentosSei) {
		this.idDocumentosSei = idDocumentosSei;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_alocacao_sei", nullable = false)
	public AlocacaoSei getAlocacaoSei() {
		return this.alocacaoSei;
	}
	public void setAlocacaoSei(AlocacaoSei alocacaoSei) {
		this.alocacaoSei = alocacaoSei;
	}
	
	@ManyToMany(fetch = FetchType.EAGER, cascade= CascadeType.ALL)
	@JoinTable(name="documento_sei_alocacao", catalog = "dbditel", joinColumns = {
			@JoinColumn(name = "id_documentos_sei", nullable = false)}, inverseJoinColumns={
					@JoinColumn(name = "id_alocacao", nullable = false)
	})
	public Set<Alocacao> getAlocacao() {
		return alocacao;
	}

	public void setAlocacao(Set<Alocacao> alocacao) {
		this.alocacao = alocacao;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_alocacao_fatura")
	public AlocacaoFatura getAlocacaoFatura() {
		return alocacaoFatura;
	}

	public void setAlocacaoFatura(AlocacaoFatura alocacaoFatura) {
		this.alocacaoFatura = alocacaoFatura;
	}

	@Column(name = "documentos_tipo")
	public String getDocumentosTipo() {
		return this.documentosTipo;
	}

	public void setDocumentosTipo(String documentosTipo) {
		this.documentosTipo = documentosTipo;
	}
	@Column(name = "documentos_link")
	public String getDocumentosLink() {
		return this.documentosLink;
	}

	public void setDocumentosLink(String documentosLink) {
		this.documentosLink = documentosLink;
	}
	
	@Column(name = "documentos_id_sei")
	public String getDocumentoIdSei() {
		return documentoIdSei;
	}

	public void setDocumentoIdSei(String documentoIdSei) {
		this.documentoIdSei = documentoIdSei;
	}

	@Column(name = "documentos_numero")
	public String getDocumentosNumero() {
		return this.documentosNumero;
	}

	public void setDocumentosNumero(String documentosNumero) {
		this.documentosNumero = documentosNumero;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_recebido", nullable = false, length = 19)
	public Date getDocumentosDataGerado() {
		return this.documentosDataGerado;
	}

	public void setDocumentosDataGerado(Date documentosDataGerado) {
		this.documentosDataGerado = documentosDataGerado;
	}
	@Column(name = "id_bloco_assinatura")
	public String getBlocoId() {
		return blocoId;
	}

	public void setBlocoId(String blocoId) {
		this.blocoId = blocoId;
	}
	
	@Column(name = "bloco_assinatura_disponibilizado")
	public boolean isBlocoDisponibilizado() {
		return blocoDisponibilizado;
	}

	public void setBlocoDisponibilizado(boolean blocoDisponibilizado) {
		this.blocoDisponibilizado = blocoDisponibilizado;
	}
	@Column(name = "bloco_assinatura_finalizado")
	public boolean isBlocoFinalizado() {
		return blocoFinalizado;
	}

	public void setBlocoFinalizado(boolean blocoFinalizado) {
		this.blocoFinalizado = blocoFinalizado;
	}

	@Column(name = "assinatura_usuario_sei")
	public String getAssinaturaNome() {
		return assinaturaNome;
	}

	public void setAssinaturaNome(String assinaturaNome) {
		this.assinaturaNome = assinaturaNome;
	}
	@Column(name = "assinatura_usuario_cargo_sei")
	public String getAssinaturaCargo() {
		return assinaturaCargo;
	}

	public void setAssinaturaCargo(String assinaturaCargo) {
		this.assinaturaCargo = assinaturaCargo;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_assinatura")
	public Date getAssinaturaHora() {
		return assinaturaHora;
	}

	public void setAssinaturaHora(Date assinaturaHora) {
		this.assinaturaHora = assinaturaHora;
	}
	@Column(name = "assinatura_validador_flag")
	public boolean isAssinaturaValida() {
		return assinaturaValida;
	}
	public void setAssinaturaValida(boolean assinaturaValida) {
		this.assinaturaValida = assinaturaValida;
	}

}
