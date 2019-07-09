package br.gov.cultura.DitelAdm.model.faturasV3;
// Generated 29/08/2016 10:12:50 by Hibernate Tools 4.3.4.Final

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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import br.gov.cultura.DitelAdm.model.AlocacaoFatura;

/**
 * Fatura MODEL: Padrão FEBRABAN v3
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "fatura", catalog = "dbditel")
public class Fatura implements java.io.Serializable {

	private Integer idFatura;
	@JsonIgnore
	private Cliente cliente;
	private String numFatura;
	private Date dataEmissao;
	private String indConta;
	private String mesRef;
	private Date dataVenc;
	private String codBarra;
	private String codCobranca;
	private String descriCobranca;
	private String bancoCobranca;
	private String agenciaCobranca;
	private String ccCobranca;
	private String fisco;
	private Date dataGeraArquivo;
	private String versaoFormato;
	private String campoLivreOp;
	private boolean gerada;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Set<Trailler> traillers = new HashSet(0);
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Set<Enderecos> enderecoses = new HashSet(0);
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Set<Notafiscal> notafiscals = new HashSet(0);
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Set<Resumo> resumos = new HashSet(0);
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Set<Planos> planoses = new HashSet(0);
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Set<Ajustes> ajusteses = new HashSet(0);
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Set<Descontos> descontoses = new HashSet(0);
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Set<Chamadas> chamadases = new HashSet(0);
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Set<Servicos> servicoses = new HashSet(0);
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@JsonIgnore
	private Set<AlocacaoFatura> alocacaoFaturas = new HashSet(0);


	public Fatura() {
	}

	public Fatura(Cliente cliente, String numFatura, Date dataEmissao, String indConta, String mesRef, Date dataVenc,
			String codCobranca, String descriCobranca, Date dataGeraArquivo, String versaoFormato) {
		this.cliente = cliente;
		this.numFatura = numFatura;
		this.dataEmissao = dataEmissao;
		this.indConta = indConta;
		this.mesRef = mesRef;
		this.dataVenc = dataVenc;
		this.codCobranca = codCobranca;
		this.descriCobranca = descriCobranca;
		this.dataGeraArquivo = dataGeraArquivo;
		this.versaoFormato = versaoFormato;
	}

	public Fatura(Cliente cliente, String numFatura, Date dataEmissao, String indConta, String mesRef, Date dataVenc,
			String codBarra, String codCobranca, String descriCobranca, String bancoCobranca, String agenciaCobranca,
			String ccCobranca, String fisco, Date dataGeraArquivo, String versaoFormato, String campoLivreOp,
			Set<Trailler> traillers, Set<Enderecos> enderecoses, Set<Notafiscal> notafiscals, Set<Resumo> resumos, Set<Servicos> servicoses, Set<Chamadas> chamadases, Set<Ajustes> ajusteses, Set<Descontos> descontoses,
			Set<Planos> planoses, Set<AlocacaoFatura> alocacaoFaturas) {
		this.cliente = cliente;
		this.numFatura = numFatura;
		this.dataEmissao = dataEmissao;
		this.indConta = indConta;
		this.mesRef = mesRef;
		this.dataVenc = dataVenc;
		this.codBarra = codBarra;
		this.codCobranca = codCobranca;
		this.descriCobranca = descriCobranca;
		this.bancoCobranca = bancoCobranca;
		this.agenciaCobranca = agenciaCobranca;
		this.ccCobranca = ccCobranca;
		this.fisco = fisco;
		this.dataGeraArquivo = dataGeraArquivo;
		this.versaoFormato = versaoFormato;
		this.campoLivreOp = campoLivreOp;
		this.traillers = traillers;
		this.enderecoses = enderecoses;
		this.notafiscals = notafiscals;
		this.resumos = resumos;
		this.servicoses = servicoses;
		this.chamadases = chamadases;
		this.ajusteses = ajusteses;
		this.descontoses = descontoses;
		this.planoses = planoses;
		this.alocacaoFaturas = alocacaoFaturas;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id_fatura", unique = true, nullable = false)
	public Integer getIdFatura() {
		return this.idFatura;
	}

	public void setIdFatura(Integer idFatura) {
		this.idFatura = idFatura;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cliente_id_cliente", nullable = false)
	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Column(name = "numFatura", nullable = false)
	public String getNumFatura() {
		return this.numFatura;
	}

	public void setNumFatura(String numFatura) {
		this.numFatura = numFatura;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dataEmissao", nullable = false, length = 8)
	public Date getDataEmissao() {
		return this.dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	@Column(name = "indConta", nullable = false, length = 25)
	public String getIndConta() {
		return this.indConta;
	}

	public void setIndConta(String indConta) {
		this.indConta = indConta;
	}

	@Column(name = "mesRef", nullable = false, length = 6)
	public String getMesRef() {
		return this.mesRef;
	}

	public void setMesRef(String mesRef) {
		this.mesRef = mesRef;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dataVenc", nullable = false, length = 8)
	public Date getDataVenc() {
		return this.dataVenc;
	}

	public void setDataVenc(Date dataVenc) {
		this.dataVenc = dataVenc;
	}

	@Column(name = "codBarra", length = 50)
	public String getCodBarra() {
		return this.codBarra;
	}

	public void setCodBarra(String codBarra) {
		this.codBarra = codBarra;
	}

	@Column(name = "codCobranca", nullable = false, length = 2)
	public String getCodCobranca() {
		return this.codCobranca;
	}

	public void setCodCobranca(String codCobranca) {
		this.codCobranca = codCobranca;
	}

	@Column(name = "descriCobranca", nullable = false, length = 20)
	public String getDescriCobranca() {
		return this.descriCobranca;
	}

	public void setDescriCobranca(String descriCobranca) {
		this.descriCobranca = descriCobranca;
	}

	@Column(name = "bancoCobranca", length = 4)
	public String getBancoCobranca() {
		return this.bancoCobranca;
	}

	public void setBancoCobranca(String bancoCobranca) {
		this.bancoCobranca = bancoCobranca;
	}

	@Column(name = "agenciaCobranca", length = 4)
	public String getAgenciaCobranca() {
		return this.agenciaCobranca;
	}

	public void setAgenciaCobranca(String agenciaCobranca) {
		this.agenciaCobranca = agenciaCobranca;
	}

	@Column(name = "ccCobranca", length = 10)
	public String getCcCobranca() {
		return this.ccCobranca;
	}

	public void setCcCobranca(String ccCobranca) {
		this.ccCobranca = ccCobranca;
	}

	@Column(name = "fisco", length = 35)
	public String getFisco() {
		return this.fisco;
	}

	public void setFisco(String fisco) {
		this.fisco = fisco;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dataGeraArquivo", nullable = false, length = 8)
	public Date getDataGeraArquivo() {
		return this.dataGeraArquivo;
	}

	public void setDataGeraArquivo(Date dataGeraArquivo) {
		this.dataGeraArquivo = dataGeraArquivo;
	}

	@Column(name = "versaoFormato", nullable = false, length = 4)
	public String getVersaoFormato() {
		return this.versaoFormato;
	}

	public void setVersaoFormato(String versaoFormato) {
		this.versaoFormato = versaoFormato;
	}

	@Column(name = "campoLivreOp", length = 25)
	public String getCampoLivreOp() {
		return this.campoLivreOp;
	}

	public void setCampoLivreOp(String campoLivreOp) {
		this.campoLivreOp = campoLivreOp;
	}
	
	@Column(name = "gerada", nullable = false, columnDefinition="boolean default false")
	public boolean getGerada() {
		return this.gerada;
	}

	public void setGerada(boolean gerada) {
		this.gerada = gerada;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fatura")
	public Set<Trailler> getTraillers() {
		return this.traillers;
	}

	public void setTraillers(Set<Trailler> traillers) {
		this.traillers = traillers;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fatura")
	public Set<Enderecos> getEnderecoses() {
		return this.enderecoses;
	}

	public void setEnderecoses(Set<Enderecos> enderecoses) {
		this.enderecoses = enderecoses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fatura")
	public Set<Notafiscal> getNotafiscals() {
		return this.notafiscals;
	}

	public void setNotafiscals(Set<Notafiscal> notafiscals) {
		this.notafiscals = notafiscals;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fatura")
	public Set<Resumo> getResumos() {
		return this.resumos;
	}

	public void setResumos(Set<Resumo> resumos) {
		this.resumos = resumos;
	}
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fatura")
	public Set<Planos> getPlanoses() {
		return this.planoses;
	}

	public void setPlanoses(Set<Planos> planoses) {
		this.planoses = planoses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fatura")
	public Set<Servicos> getServicoses() {
		return this.servicoses;
	}

	public void setServicoses(Set<Servicos> servicoses) {
		this.servicoses = servicoses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fatura")
	public Set<Chamadas> getChamadases() {
		return this.chamadases;
	}

	public void setChamadases(Set<Chamadas> chamadases) {
		this.chamadases = chamadases;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fatura")
	public Set<Ajustes> getAjusteses() {
		return this.ajusteses;
	}

	public void setAjusteses(Set<Ajustes> ajusteses) {
		this.ajusteses = ajusteses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fatura")
	public Set<Descontos> getDescontoses() {
		return this.descontoses;
	}

	public void setDescontoses(Set<Descontos> descontoses) {
		this.descontoses = descontoses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fatura")
	public Set<AlocacaoFatura> getAlocacaoFaturas() {
		return this.alocacaoFaturas;
	}

	public void setAlocacaoFaturas(Set<AlocacaoFatura> alocacaoFaturas) {
		this.alocacaoFaturas = alocacaoFaturas;
	}
}
