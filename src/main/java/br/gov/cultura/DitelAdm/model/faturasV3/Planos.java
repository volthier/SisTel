package br.gov.cultura.DitelAdm.model.faturasV3;
// Generated 29/08/2016 10:12:50 by Hibernate Tools 4.3.4.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.text.SimpleDateFormat;
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

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.gov.cultura.DitelAdm.model.Linha;

/**
 * Planos MODEL: Padrão FEBRABAN v3
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "planos", catalog = "dbditel")
public class Planos implements java.io.Serializable {

	private Integer idPlanos;
	@JsonIgnore
	private Categoriaplano categoriaplano;
	@JsonIgnore
	private Fatura fatura;
	@JsonIgnore
	private Linha linha;
	@JsonIgnore
	private Resumo resumo;
	private String idUnicoPlanos;
	private String numRecursoPlanos; 
	private Date dataIniCiclo;
	private Date dataFimCiclo;
	private String tipo;
	private float consumoMedido;
	private float consumoFranqueado;
	private String codPlano;
	private String descricaoPlano;
	private String planoscol;
	private float valComImp;
	private float valSemImp;
	private int tipoNf;
	private String numNf;
	private String campoLivreOp;

	public Planos() {
	}

	public Planos(Categoriaplano categoriaplano, Fatura fatura, Resumo resumo, Linha linha, Date dataIniCiclo, Date dataFimCiclo, String tipo,
			float consumoMedido, float consumoFranqueado, float valComImp, float valSemImp, int tipoNf, String numNf, String idUnicoPlanos, String numRecursoPlanos ) {
		this.categoriaplano = categoriaplano;
		this.fatura = fatura;
		this.resumo = resumo;
		this.linha = linha;
		this.dataIniCiclo = dataIniCiclo;
		this.dataFimCiclo = dataFimCiclo;
		this.tipo = tipo;
		this.consumoMedido = consumoMedido;
		this.consumoFranqueado = consumoFranqueado;
		this.valComImp = valComImp;
		this.valSemImp = valSemImp;
		this.tipoNf = tipoNf;
		this.numNf = numNf;
		this.idUnicoPlanos = idUnicoPlanos;
		this.numRecursoPlanos = numRecursoPlanos;
	}

	public Planos(Categoriaplano categoriaplano, Fatura fatura, Linha linha, Resumo resumo, Date dataIniCiclo, Date dataFimCiclo, String tipo,
			float consumoMedido, float consumoFranqueado, String codPlano, String descricaoPlano, String planoscol,
			float valComImp, float valSemImp, int tipoNf, String numNf, String campoLivreOp, String idUnicoPlanos, String numRecursoPlanos) {
		this.categoriaplano = categoriaplano;
		this.fatura = fatura;
		this.linha = linha;
		this.resumo = resumo;
		this.idUnicoPlanos = idUnicoPlanos;
		this.numRecursoPlanos = numRecursoPlanos;
		this.dataIniCiclo = dataIniCiclo;
		this.dataFimCiclo = dataFimCiclo;
		this.tipo = tipo;
		this.consumoMedido = consumoMedido;
		this.consumoFranqueado = consumoFranqueado;
		this.codPlano = codPlano;
		this.descricaoPlano = descricaoPlano;
		this.planoscol = planoscol;
		this.valComImp = valComImp;
		this.valSemImp = valSemImp;
		this.tipoNf = tipoNf;
		this.numNf = numNf;
		this.campoLivreOp = campoLivreOp;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id_planos", unique = true, nullable = false)
	public Integer getIdPlanos() {
		return this.idPlanos;
	}

	public void setIdPlanos(Integer idPlanos) {
		this.idPlanos = idPlanos;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoriaPlano_id_catPlano", nullable = false)
	public Categoriaplano getCategoriaplano() {
		return this.categoriaplano;
	}

	public void setCategoriaplano(Categoriaplano categoriaplano) {
		this.categoriaplano = categoriaplano;
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
	@Column(name = "dataIniCiclo", nullable = false, length = 10)
	public Date getDataIniCiclo() {
		return this.dataIniCiclo;
	}

	public void setDataIniCiclo(Date dataIniCiclo) {
		this.dataIniCiclo = dataIniCiclo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dataFimCiclo", nullable = false, length = 10)
	public Date getDataFimCiclo() {
		return this.dataFimCiclo;
	}

	public void setDataFimCiclo(Date dataFimCiclo) {
		this.dataFimCiclo = dataFimCiclo;
	}

	@Column(name = "tipo", nullable = false, length = 1)
	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Column(name = "consumoMedido", nullable = false, precision = 12, scale = 0)
	public float getConsumoMedido() {
		return this.consumoMedido;
	}

	public void setConsumoMedido(float consumoMedido) {
		this.consumoMedido = consumoMedido;
	}

	@Column(name = "consumoFranqueado", nullable = false, precision = 12, scale = 0)
	public float getConsumoFranqueado() {
		return this.consumoFranqueado;
	}

	public void setConsumoFranqueado(float consumoFranqueado) {
		this.consumoFranqueado = consumoFranqueado;
	}

	@Column(name = "codPlano", length = 5)
	public String getCodPlano() {
		return this.codPlano;
	}

	public void setCodPlano(String codPlano) {
		this.codPlano = codPlano;
	}

	@Column(name = "descricaoPlano", length = 25)
	public String getDescricaoPlano() {
		return this.descricaoPlano;
	}

	public void setDescricaoPlano(String descricaoPlano) {
		this.descricaoPlano = descricaoPlano;
	}

	@Column(name = "planoscol", length = 45)
	public String getPlanoscol() {
		return this.planoscol;
	}

	public void setPlanoscol(String planoscol) {
		this.planoscol = planoscol;
	}

	@Column(name = "valComImp", nullable = false, precision = 12, scale = 0)
	public float getValComImp() {
		return this.valComImp;
	}

	public void setValComImp(float valComImp) {
		this.valComImp = valComImp;
	}

	@Column(name = "valSemImp", nullable = false, precision = 12, scale = 0)
	public float getValSemImp() {
		return this.valSemImp;
	}

	public void setValSemImp(float valSemImp) {
		this.valSemImp = valSemImp;
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

	@Column(name = "campoLivreOp", length = 25)
	public String getCampoLivreOp() {
		return this.campoLivreOp;
	}

	public void setCampoLivreOp(String campoLivreOp) {
		this.campoLivreOp = campoLivreOp;
	}
	
	public String dataIniCicloFormatada() {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		return format.format(this.dataIniCiclo);
	}
	
	public String dataFimCicloFormatada() {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		return format.format(this.dataFimCiclo);
	}

	public String getIdUnicoPlanos() {
		return idUnicoPlanos;
	}

	public void setIdUnicoPlanos(String idUnicoPlanos) {
		this.idUnicoPlanos = idUnicoPlanos;
	}

	public String getNumRecursoPlanos() {
		return numRecursoPlanos;
	}

	public void setNumRecursoPlanos(String numRecursoPlanos) {
		this.numRecursoPlanos = numRecursoPlanos;
	}
	
	

}
