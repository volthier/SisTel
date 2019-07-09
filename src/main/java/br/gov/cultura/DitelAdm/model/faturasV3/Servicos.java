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

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.gov.cultura.DitelAdm.model.Linha;

/**
 * Servicos MODEL: Padrão FEBRABAN v3
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "servicos", catalog = "dbditel")
public class Servicos implements java.io.Serializable {

	private Integer idServicos;
	@JsonIgnore
	private Categoriaservico categoriaservico;
	@JsonIgnore
	private Fatura fatura;
	@JsonIgnore
	private Resumo resumo;
	@JsonIgnore
	private Linha linha;
	private Date dataServico;
	private Date horaServico;
	private Integer cnlAreaLocalUso;
	private String codNacInt;
	private String numRecursoServico;
	private String numTelDestino;
	private Integer codOpRoaming;
	private String opTerminalVincDestino;
	private int quantUtil;
	private String unidadeServico;
	private float valServImp;
	private float valServ;
	private int tipoNf;
	private String numNf;
	private String campoLivreOp;

	public Servicos() {
	}

	public Servicos(Categoriaservico categoriaservico, Fatura fatura, Resumo resumo, Linha linha, Date dataServico, Date horaServico, int quantUtil,
			String unidadeServico, float valServImp, float valServ, int tipoNf, String numNf) {
		this.categoriaservico = categoriaservico;
		this.fatura = fatura;
		this.resumo = resumo;
		this.linha = linha;
		this.dataServico = dataServico;
		this.horaServico = horaServico;
		this.quantUtil = quantUtil;
		this.unidadeServico = unidadeServico;
		this.valServImp = valServImp;
		this.valServ = valServ;
		this.tipoNf = tipoNf;
		this.numNf = numNf;
	}

	public Servicos(Categoriaservico categoriaservico, Fatura fatura, Resumo resumo, Linha linha, Date dataServico, Date horaServico,
			Integer cnlAreaLocalUso, String codNacInt, String numRecursoServico, String numTelDestino, Integer codOpRoaming,
			String opTerminalVincDestino, int quantUtil, String unidadeServico, float valServImp, float valServ,
			int tipoNf, String numNf, String campoLivreOp) {
		this.categoriaservico = categoriaservico;
		this.fatura = fatura;
		this.resumo = resumo;
		this.linha = linha;
		this.dataServico = dataServico;
		this.horaServico = horaServico;
		this.cnlAreaLocalUso = cnlAreaLocalUso;
		this.codNacInt = codNacInt;
		this.numRecursoServico = numRecursoServico;
		this.numTelDestino = numTelDestino;
		this.codOpRoaming = codOpRoaming;
		this.opTerminalVincDestino = opTerminalVincDestino;
		this.quantUtil = quantUtil;
		this.unidadeServico = unidadeServico;
		this.valServImp = valServImp;
		this.valServ = valServ;
		this.tipoNf = tipoNf;
		this.numNf = numNf;
		this.campoLivreOp = campoLivreOp;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id_servicos", unique = true, nullable = false)
	public Integer getIdServicos() {
		return this.idServicos;
	}

	public void setIdServicos(Integer idServicos) {
		this.idServicos = idServicos;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoriaServico_id_catServico", nullable = false)
	public Categoriaservico getCategoriaservico() {
		return this.categoriaservico;
	}

	public void setCategoriaservico(Categoriaservico categoriaservico) {
		this.categoriaservico = categoriaservico;
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
	@Column(name = "dataServico", nullable = false, length = 10)
	public Date getDataServico() {
		return this.dataServico;
	}

	public void setDataServico(Date dataServico) {
		this.dataServico = dataServico;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "horaServico", length = 19)
	public Date getHoraServico() {
		return this.horaServico;
	}

	public void setHoraServico(Date horaServico) {
		this.horaServico = horaServico;
	}

	@Column(name = "cnlAreaLocalUso")
	public Integer getCnlAreaLocalUso() {
		return this.cnlAreaLocalUso;
	}

	public void setCnlAreaLocalUso(Integer cnlAreaLocalUso) {
		this.cnlAreaLocalUso = cnlAreaLocalUso;
	}

	@Column(name = "codNacInt", length = 2)
	public String getCodNacInt() {
		return this.codNacInt;
	}

	public void setCodNacInt(String codNacInt) {
		this.codNacInt = codNacInt;
	}
	
	@Column(name = "num_recurso_linha", nullable = false, length = 17)
	public String getNumRecursoServico() {
		return numRecursoServico;
	}

	public void setNumRecursoServico(String numRecursoServico) {
		this.numRecursoServico = numRecursoServico;
	}

	@Column(name = "numTelDestino", length = 17)
	public String getNumTelDestino() {
		return this.numTelDestino;
	}

	public void setNumTelDestino(String numTelDestino) {
		this.numTelDestino = numTelDestino;
	}

	@Column(name = "codOpRoaming")
	public Integer getCodOpRoaming() {
		return this.codOpRoaming;
	}

	public void setCodOpRoaming(Integer codOpRoaming) {
		this.codOpRoaming = codOpRoaming;
	}

	@Column(name = "opTerminalVincDestino", length = 3)
	public String getOpTerminalVincDestino() {
		return this.opTerminalVincDestino;
	}

	public void setOpTerminalVincDestino(String opTerminalVincDestino) {
		this.opTerminalVincDestino = opTerminalVincDestino;
	}

	@Column(name = "quantUtil", nullable = false)
	public int getQuantUtil() {
		return this.quantUtil;
	}

	public void setQuantUtil(int quantUtil) {
		this.quantUtil = quantUtil;
	}

	@Column(name = "unidadeServico", nullable = false, length = 2)
	public String getUnidadeServico() {
		return this.unidadeServico;
	}

	public void setUnidadeServico(String unidadeServico) {
		this.unidadeServico = unidadeServico;
	}

	@Column(name = "valServImp", nullable = false, precision = 12, scale = 0)
	public float getValServImp() {
		return this.valServImp;
	}

	public void setValServImp(float valServImp) {
		this.valServImp = valServImp;
	}

	@Column(name = "valServ", nullable = false, precision = 12, scale = 0)
	public float getValServ() {
		return this.valServ;
	}

	public void setValServ(float valServ) {
		this.valServ = valServ;
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

}
