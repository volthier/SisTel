package br.gov.cultura.DitelAdm.model.faturasV3;
// Generated 29/08/2016 10:12:50 by Hibernate Tools 4.3.4.Final

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

import br.gov.cultura.DitelAdm.model.Linha;

/**
 * Descontos MODEL: Padrão FEBRABAN v3
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "descontos", catalog = "dbditel")
public class Descontos implements java.io.Serializable {

	private Integer idDescontos;
	private Categoriadesconto categoriadesconto;
	private Fatura fatura;
	private Resumo resumo;
	private Linha linha;
	private Date dataInicio;
	private Date horaInicio;
	private String tipo;
	private Float baseCal;
	private int tipoNf;
	private String numNf;
	private Float percentual;
	private String sinal;
	private float valor;
	private Date dataFim;
	private Date horaFim;
	private String campoLivreOp;

	public Descontos() {
	}

	public Descontos(Categoriadesconto categoriadesconto, Resumo resumo, String tipo, int tipoNf, String numNf,
			String sinal, float valor) {
		this.categoriadesconto = categoriadesconto;
		this.resumo = resumo;
		this.tipo = tipo;
		this.tipoNf = tipoNf;
		this.numNf = numNf;
		this.sinal = sinal;
		this.valor = valor;
	}

	public Descontos(Categoriadesconto categoriadesconto, Fatura fatura, Resumo resumo, Linha linha, Date dataInicio, Date horaInicio, String tipo,
			Float baseCal, int tipoNf, String numNf, Float percentual, String sinal, float valor, Date dataFim,
			Date horaFim, String campoLivreOp) {
		this.categoriadesconto = categoriadesconto;
		this.fatura = fatura;
		this.resumo = resumo;
		this.linha = linha;
		this.dataInicio = dataInicio;
		this.horaInicio = horaInicio;
		this.tipo = tipo;
		this.baseCal = baseCal;
		this.tipoNf = tipoNf;
		this.numNf = numNf;
		this.percentual = percentual;
		this.sinal = sinal;
		this.valor = valor;
		this.dataFim = dataFim;
		this.horaFim = horaFim;
		this.campoLivreOp = campoLivreOp;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id_descontos", unique = true, nullable = false)
	public Integer getIdDescontos() {
		return this.idDescontos;
	}

	public void setIdDescontos(Integer idDescontos) {
		this.idDescontos = idDescontos;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoriaDesconto_id_catDesconto", nullable = false)
	public Categoriadesconto getCategoriadesconto() {
		return this.categoriadesconto;
	}

	public void setCategoriadesconto(Categoriadesconto categoriadesconto) {
		this.categoriadesconto = categoriadesconto;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_fatura", nullable = false)
	public Fatura getFatura() {
		return this.fatura;
	}

	public void setFatura(Fatura fatura) {
		this.fatura = fatura;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_resumo")
	public Resumo getResumo() {
		return this.resumo;
	}

	public void setResumo(Resumo resumo) {
		this.resumo = resumo;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_linha")
	public Linha getLinha() {
		return this.linha;
	}

	public void setLinha(Linha linha) {
		this.linha = linha;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "dataInicio", length = 10)
	public Date getDataInicio() {
		return this.dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "horaInicio", length = 19)
	public Date getHoraInicio() {
		return this.horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	@Column(name = "tipo", nullable = false, length = 1)
	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Column(name = "baseCal", precision = 12, scale = 0)
	public Float getBaseCal() {
		return this.baseCal;
	}

	public void setBaseCal(Float baseCal) {
		this.baseCal = baseCal;
	}

	@Column(name = "tipoNF", nullable = false)
	public int getTipoNf() {
		return this.tipoNf;
	}

	public void setTipoNf(int tipoNf) {
		this.tipoNf = tipoNf;
	}

	@Column(name = "numNF", nullable = false, length = 12)
	public String getNumNf() {
		return this.numNf;
	}

	public void setNumNf(String numNf) {
		this.numNf = numNf;
	}

	@Column(name = "percentual", precision = 12, scale = 0)
	public Float getPercentual() {
		return this.percentual;
	}

	public void setPercentual(Float percentual) {
		this.percentual = percentual;
	}

	@Column(name = "sinal", nullable = false, length = 1)
	public String getSinal() {
		return this.sinal;
	}

	public void setSinal(String sinal) {
		this.sinal = sinal;
	}

	@Column(name = "valor", nullable = false, precision = 12, scale = 0)
	public float getValor() {
		return this.valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dataFim", length = 10)
	public Date getDataFim() {
		return this.dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "horaFim", length = 19)
	public Date getHoraFim() {
		return this.horaFim;
	}

	public void setHoraFim(Date horaFim) {
		this.horaFim = horaFim;
	}

	@Column(name = "campoLivreOp", length = 25)
	public String getCampoLivreOp() {
		return this.campoLivreOp;
	}

	public void setCampoLivreOp(String campoLivreOp) {
		this.campoLivreOp = campoLivreOp;
	}

}
