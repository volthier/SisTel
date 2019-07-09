package br.gov.cultura.DitelAdm.model.faturasV3;
// Generated 13/09/2016 10:38:25 by Hibernate Tools 4.3.4.Final

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
import com.fasterxml.jackson.annotation.JsonIgnore;

import br.gov.cultura.DitelAdm.model.AlocacaoFatura;
import br.gov.cultura.DitelAdm.model.Linha;

/**
 * Resumo MODEL: Padrão FEBRABAN v3
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "resumo", catalog = "dbditel")
public class Resumo implements java.io.Serializable {

	private Integer idResumo;
	@JsonIgnore
	private Fatura fatura;
	@JsonIgnore
	private Linha linha;
	private String idUnico;
	private int cnl;
	private String numRecurso;
	private Date dataAtiv;
	private Date dataDesativ;
	private int quantRegCham;
	private float valorTotalRegChamadaImp;
	private int quantRegServ;
	private float valorTotalRegServImp;
	private String degrau;
	private int modServ;
	private String uniVelocidade;
	private float valorTotalContaRecursoImp;
	private float valorTotalImp;
	private String velocidade;
	private Date dataVenc;

	public Resumo() {
	}

	public Resumo(Fatura fatura, String idUnico, int cnl, String numRecurso, int quantRegCham,
			float valorTotalRegChamadaImp, int quantRegServ, float valorTotalRegServImp, int modServ,
			float valorTotalContaRecursoImp, float valorTotalImp, Date dataVenc) {
		this.fatura = fatura;
		this.idUnico = idUnico;
		this.cnl = cnl;
		this.numRecurso = numRecurso;
		this.quantRegCham = quantRegCham;
		this.valorTotalRegChamadaImp = valorTotalRegChamadaImp;
		this.quantRegServ = quantRegServ;
		this.valorTotalRegServImp = valorTotalRegServImp;
		this.modServ = modServ;
		this.valorTotalContaRecursoImp = valorTotalContaRecursoImp;
		this.valorTotalImp = valorTotalImp;
		this.dataVenc = dataVenc;
	}

	public Resumo(Fatura fatura, Linha linha,String idUnico, int cnl, String numRecurso, Date dataAtiv, Date dataDesativ,
			int quantRegCham, float valorTotalRegChamadaImp, int quantRegServ, float valorTotalRegServImp,
			String degrau, int modServ, String uniVelocidade, float valorTotalContaRecursoImp, float valorTotalImp,
			String velocidade, Date dataVenc) {
		this.fatura = fatura;
		this.linha = linha;
		this.idUnico = idUnico;
		this.cnl = cnl;
		this.numRecurso = numRecurso;
		this.dataAtiv = dataAtiv;
		this.dataDesativ = dataDesativ;
		this.quantRegCham = quantRegCham;
		this.valorTotalRegChamadaImp = valorTotalRegChamadaImp;
		this.quantRegServ = quantRegServ;
		this.valorTotalRegServImp = valorTotalRegServImp;
		this.degrau = degrau;
		this.modServ = modServ;
		this.uniVelocidade = uniVelocidade;
		this.valorTotalContaRecursoImp = valorTotalContaRecursoImp;
		this.valorTotalImp = valorTotalImp;
		this.velocidade = velocidade;
		this.dataVenc = dataVenc;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id_resumo", unique = true, nullable = false)
	public Integer getIdResumo() {
		return this.idResumo;
	}

	public void setIdResumo(Integer idResumo) {
		this.idResumo = idResumo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_fatura", nullable = false)
	public Fatura getFatura() {
		return this.fatura;
	}

	public void setFatura(Fatura fatura) {
		this.fatura = fatura;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_linha")
	public Linha getLinha() {
		return this.linha;
	}

	public void setLinha(Linha linha) {
		this.linha = linha;
	}

	@Column(name = "id_unico", nullable = false, length = 25)
	public String getIdUnico() {
		return this.idUnico;
	}

	public void setIdUnico(String idUnico) {
		this.idUnico = idUnico;
	}

	@Column(name = "cnl", nullable = false)
	public int getCnl() {
		return this.cnl;
	}

	public void setCnl(int cnl) {
		this.cnl = cnl;
	}

	@Column(name = "num_recurso", nullable = false, length = 20)
	public String getNumRecurso() {
		return this.numRecurso;
	}

	public void setNumRecurso(String numRecurso) {
		this.numRecurso = numRecurso;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "data_ativ", length = 10)
	public Date getDataAtiv() {
		return this.dataAtiv;
	}

	public void setDataAtiv(Date dataAtiv) {
		this.dataAtiv = dataAtiv;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "data_desativ", length = 10)
	public Date getDataDesativ() {
		return this.dataDesativ;
	}

	public void setDataDesativ(Date dataDesativ) {
		this.dataDesativ = dataDesativ;
	}

	@Column(name = "quant_reg_cham", nullable = false)
	public int getQuantRegCham() {
		return this.quantRegCham;
	}

	public void setQuantRegCham(int quantRegCham) {
		this.quantRegCham = quantRegCham;
	}

	@Column(name = "valor_total_reg_chamada_imp", nullable = false, precision = 12, scale = 0)
	public float getValorTotalRegChamadaImp() {
		return this.valorTotalRegChamadaImp;
	}

	public void setValorTotalRegChamadaImp(float valorTotalRegChamadaImp) {
		this.valorTotalRegChamadaImp = valorTotalRegChamadaImp;
	}

	@Column(name = "quant_reg_serv", nullable = false)
	public int getQuantRegServ() {
		return this.quantRegServ;
	}

	public void setQuantRegServ(int quantRegServ) {
		this.quantRegServ = quantRegServ;
	}

	@Column(name = "valor_total_reg_serv_imp", nullable = false, precision = 12, scale = 0)
	public float getValorTotalRegServImp() {
		return this.valorTotalRegServImp;
	}

	public void setValorTotalRegServImp(float valorTotalRegServImp) {
		this.valorTotalRegServImp = valorTotalRegServImp;
	}

	@Column(name = "degrau", length = 2)
	public String getDegrau() {
		return this.degrau;
	}

	public void setDegrau(String degrau) {
		this.degrau = degrau;
	}

	@Column(name = "mod_serv", nullable = false)
	public int getModServ() {
		return this.modServ;
	}

	public void setModServ(int modServ) {
		this.modServ = modServ;
	}

	@Column(name = "uni_velocidade", length = 4)
	public String getUniVelocidade() {
		return this.uniVelocidade;
	}

	public void setUniVelocidade(String uniVelocidade) {
		this.uniVelocidade = uniVelocidade;
	}

	@Column(name = "valor_total_conta_recurso_imp", nullable = false, precision = 12, scale = 0)
	public float getValorTotalContaRecursoImp() {
		return this.valorTotalContaRecursoImp;
	}

	public void setValorTotalContaRecursoImp(float valorTotalContaRecursoImp) {
		this.valorTotalContaRecursoImp = valorTotalContaRecursoImp;
	}

	@Column(name = "valor_total_imp", nullable = false, precision = 12, scale = 0)
	public float getValorTotalImp() {
		return this.valorTotalImp;
	}

	public void setValorTotalImp(float valorTotalImp) {
		this.valorTotalImp = valorTotalImp;
	}

	@Column(name = "velocidade", length = 5)
	public String getVelocidade() {
		return this.velocidade;
	}

	public void setVelocidade(String velocidade) {
		this.velocidade = velocidade;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "data_venc", nullable = false, length = 10)
	public Date getDataVenc() {
		return this.dataVenc;
	}

	public void setDataVenc(Date dataVenc) {
		this.dataVenc = dataVenc;
	}

}
