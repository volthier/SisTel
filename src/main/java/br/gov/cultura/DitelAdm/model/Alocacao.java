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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Alocação: tabela de registro de movimentação dos Serviços de telefonia
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "alocacao", catalog = "dbditel")
public class Alocacao implements java.io.Serializable {

	private Integer idAlocacao;
	@JsonBackReference
	private Dispositivo dispositivo;
	@JsonBackReference
	private Linha linha;
	@JsonBackReference
	private Categoria categoria;
	@JsonBackReference
	private Chip chip;
	@JsonBackReference
	private AlocacaoSei alocacaoSei;
	@JsonBackReference
	private Usuario usuario;
	private Date dtRecebido;
	private Date dtDevolucao;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Set<AlocacaoFatura> alocacaoFaturas = new HashSet(0);
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Set<DocumentoSei> documentoSeis = new HashSet(0);

	public Alocacao() {
	}

	public Alocacao(Integer idAlocacao, Linha linha, Categoria categoria, Usuario usuario, Date dtRecebido) {
		this.idAlocacao = idAlocacao;
		this.linha = linha;
		this.categoria = categoria;
		this.usuario = usuario;
		this.dtRecebido = dtRecebido;
	}

	public Alocacao(Integer idAlocacao, Dispositivo dispositivo, Linha linha, Categoria categoria, Chip chip,
			AlocacaoSei alocacaoSei, Usuario usuario, Date dtRecebido, Date dtDevolucao,
			Set<AlocacaoFatura> alocacaoFaturas,Set<DocumentoSei> documentoSeis) {
		super();
		this.idAlocacao = idAlocacao;
		this.dispositivo = dispositivo;
		this.linha = linha;
		this.categoria = categoria;
		this.chip = chip;
		this.alocacaoSei = alocacaoSei;
		this.usuario = usuario;
		this.dtRecebido = dtRecebido;
		this.dtDevolucao = dtDevolucao;
		this.alocacaoFaturas = alocacaoFaturas;
		this.documentoSeis = documentoSeis;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_alocacao", unique = true, nullable = false)
	public Integer getIdAlocacao() {
		return this.idAlocacao;
	}

	public void setIdAlocacao(Integer idAlocacao) {
		this.idAlocacao = idAlocacao;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_dispositivo")
	public Dispositivo getDispositivo() {
		return this.dispositivo;
	}

	public void setDispositivo(Dispositivo dispositivo) {
		this.dispositivo = dispositivo;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_linha", nullable = false)
	public Linha getLinha() {
		return this.linha;
	}

	public void setLinha(Linha linha) {
		this.linha = linha;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_categoria")
	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_chip")
	public Chip getChip() {
		return this.chip;
	}

	public void setChip(Chip chip) {
		this.chip = chip;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_alocacao_sei")
	public AlocacaoSei getAlocacaoSei() {
		return this.alocacaoSei;
	}

	public void setAlocacaoSei(AlocacaoSei alocacaoSei) {
		this.alocacaoSei = alocacaoSei;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_usuario", nullable = false)
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_recebido", nullable = false, length = 19)
	public Date getDtRecebido() {
		return this.dtRecebido;
	}

	public void setDtRecebido(Date dtRecebido) {
		this.dtRecebido = dtRecebido;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_devolucao", length = 19)
	public Date getDtDevolucao() {
		return this.dtDevolucao;
	}

	public void setDtDevolucao(Date dtDevolucao) {
		this.dtDevolucao = dtDevolucao;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "alocacao")
	public Set<AlocacaoFatura> getAlocacaoFaturas() {
		return alocacaoFaturas;
	}

	public void setAlocacaoFaturas(Set<AlocacaoFatura> alocacaoFaturas) {
		this.alocacaoFaturas = alocacaoFaturas;
	}
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "alocacao")
	public Set<DocumentoSei> getDocumentoSeis() {
		return documentoSeis;
	}

	public void setDocumentoSeis(Set<DocumentoSei> documentoSeis) {
		this.documentoSeis = documentoSeis;
	}
	
}
