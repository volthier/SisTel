package br.gov.cultura.DitelAdm.model.faturasV3;
// Generated 29/08/2016 10:12:50 by Hibernate Tools 4.3.4.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.math.RoundingMode;
import java.text.DecimalFormat;
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
 * Chamadas MODEL: Padrão FEBRABAN v3
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "chamadas", catalog = "dbditel")
public class Chamadas implements java.io.Serializable {

	private Integer idChamadas;
	@JsonIgnore
	private Categoriachamada categoriachamada;
	@JsonIgnore
	private Fatura fatura;
	@JsonIgnore
	private Resumo resumo;
	@JsonIgnore
	private Linha linha;
	private String numRecursoChamada;
	private String numTelefoneChamado;
	private Date dataLigacao;
	private Date horaLigacao;
	private int cnlAreaLocalUso;
	private int cnlLocalDestino;
	private String nomeLocalDestino;
	private String ufTelDestino;
	private String codNacInt;
	private String codCsp;
	private String nomeOpCsp;
	private Integer codOpRoaming;
	private String opTerminalVincDestino;
	private Date duracaoLigacao;
	private int icms;
	private float valLigImp;
	private float valLigSemImp;
	private int tipoNf;
	private String numNf;
	private String tipoChamada;
	private String grupoHoraTarif;
	private String desHoraTarif;
	private Integer degrauLigacao;
	private String campoLivreOp;

	public Chamadas() {
	}

	public Chamadas(Categoriachamada categoriachamada, Resumo resumo, String numRecursoChamada, String numTelefoneChamado, Date dataLigacao,
			Date horaLigacao, int cnlAreaLocalUso, int cnlLocalDestino, String nomeLocalDestino, String ufTelDestino,
			String codNacInt, Date duracaoLigacao, int icms, float valLigImp, float valLigSemImp, int tipoNf,
			String numNf, String tipoChamada) {
		this.categoriachamada = categoriachamada;
		this.resumo = resumo;
		this.numRecursoChamada = numRecursoChamada;
		this.numTelefoneChamado = numTelefoneChamado;
		this.dataLigacao = dataLigacao;
		this.horaLigacao = horaLigacao;
		this.cnlAreaLocalUso = cnlAreaLocalUso;
		this.cnlLocalDestino = cnlLocalDestino;
		this.nomeLocalDestino = nomeLocalDestino;
		this.ufTelDestino = ufTelDestino;
		this.codNacInt = codNacInt;
		this.duracaoLigacao = duracaoLigacao;
		this.icms = icms;
		this.valLigImp = valLigImp;
		this.valLigSemImp = valLigSemImp;
		this.tipoNf = tipoNf;
		this.numNf = numNf;
		this.tipoChamada = tipoChamada;
	}

	public Chamadas(Categoriachamada categoriachamada, Fatura fatura, Resumo resumo, Linha linha, String numRecursoChamada, String numTelefoneChamado, Date dataLigacao,
			Date horaLigacao, int cnlAreaLocalUso, int cnlLocalDestino, String nomeLocalDestino, String ufTelDestino,
			String codNacInt, String codCsp, String nomeOpCsp, Integer codOpRoaming, String opTerminalVincDestino,
			Date duracaoLigacao, int icms, float valLigImp, float valLigSemImp, int tipoNf, String numNf,
			String tipoChamada, String grupoHoraTarif, String desHoraTarif, Integer degrauLigacao,
			String campoLivreOp) {
		this.categoriachamada = categoriachamada;
		this.fatura = fatura;
		this.resumo = resumo;
		this.linha = linha;
		this.numRecursoChamada = numRecursoChamada;
		this.numTelefoneChamado = numTelefoneChamado;
		this.dataLigacao = dataLigacao;
		this.horaLigacao = horaLigacao;
		this.cnlAreaLocalUso = cnlAreaLocalUso;
		this.cnlLocalDestino = cnlLocalDestino;
		this.nomeLocalDestino = nomeLocalDestino;
		this.ufTelDestino = ufTelDestino;
		this.codNacInt = codNacInt;
		this.codCsp = codCsp;
		this.nomeOpCsp = nomeOpCsp;
		this.codOpRoaming = codOpRoaming;
		this.opTerminalVincDestino = opTerminalVincDestino;
		this.duracaoLigacao = duracaoLigacao;
		this.icms = icms;
		this.valLigImp = valLigImp;
		this.valLigSemImp = valLigSemImp;
		this.tipoNf = tipoNf;
		this.numNf = numNf;
		this.tipoChamada = tipoChamada;
		this.grupoHoraTarif = grupoHoraTarif;
		this.desHoraTarif = desHoraTarif;
		this.degrauLigacao = degrauLigacao;
		this.campoLivreOp = campoLivreOp;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id_chamadas", unique = true, nullable = false)
	public Integer getIdChamadas() {
		return this.idChamadas;
	}

	public void setIdChamadas(Integer idChamadas) {
		this.idChamadas = idChamadas;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoriaChamada_id_categoriaChamada", nullable = false)
	public Categoriachamada getCategoriachamada() {
		return this.categoriachamada;
	}

	public void setCategoriachamada(Categoriachamada categoriachamada) {
		this.categoriachamada = categoriachamada;
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

	@Column(name = "num_recurso_linha", nullable = false, length = 17)
	public String getNumRecursoChamada() {
		return numRecursoChamada;
	}

	public void setNumRecursoChamada(String numRecursoChamada) {
		this.numRecursoChamada = numRecursoChamada;
	}

	@Column(name = "numTelefoneChamado", nullable = false, length = 17)
	public String getNumTelefoneChamado() {
		return this.numTelefoneChamado;
	}

	public void setNumTelefoneChamado(String numTelefoneChamado) {
		this.numTelefoneChamado = numTelefoneChamado;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dataLigacao", nullable = false, length = 10)
	public Date getDataLigacao() {
		return this.dataLigacao;
	}

	public void setDataLigacao(Date dataLigacao) {
		this.dataLigacao = dataLigacao;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "horaLigacao", nullable = false, length = 19)
	public Date getHoraLigacao() {
		return this.horaLigacao;
	}

	public void setHoraLigacao(Date horaLigacao) {
		this.horaLigacao = horaLigacao;
	}

	@Column(name = "cnlAreaLocalUso", nullable = false)
	public int getCnlAreaLocalUso() {
		return this.cnlAreaLocalUso;
	}

	public void setCnlAreaLocalUso(int cnlAreaLocalUso) {
		this.cnlAreaLocalUso = cnlAreaLocalUso;
	}

	@Column(name = "cnlLocalDestino", nullable = false)
	public int getCnlLocalDestino() {
		return this.cnlLocalDestino;
	}

	public void setCnlLocalDestino(int cnlLocalDestino) {
		this.cnlLocalDestino = cnlLocalDestino;
	}

	@Column(name = "nomeLocalDestino", nullable = false, length = 25)
	public String getNomeLocalDestino() {
		return this.nomeLocalDestino;
	}

	public void setNomeLocalDestino(String nomeLocalDestino) {
		this.nomeLocalDestino = nomeLocalDestino;
	}

	@Column(name = "ufTelDestino", nullable = false, length = 2)
	public String getUfTelDestino() {
		return this.ufTelDestino;
	}

	public void setUfTelDestino(String ufTelDestino) {
		this.ufTelDestino = ufTelDestino;
	}

	@Column(name = "codNacInt", nullable = false, length = 2)
	public String getCodNacInt() {
		return this.codNacInt;
	}

	public void setCodNacInt(String codNacInt) {
		this.codNacInt = codNacInt;
	}

	@Column(name = "codCsp", length = 2)
	public String getCodCsp() {
		return this.codCsp;
	}

	public void setCodCsp(String codCsp) {
		this.codCsp = codCsp;
	}

	@Column(name = "nomeOpCsp", length = 20)
	public String getNomeOpCsp() {
		return this.nomeOpCsp;
	}

	public void setNomeOpCsp(String nomeOpCsp) {
		this.nomeOpCsp = nomeOpCsp;
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

	@Temporal(TemporalType.TIME)
	@Column(name = "duracaoLigacao", nullable = false, length = 19)
	public Date getDuracaoLigacao() {
		return this.duracaoLigacao;
	}

	public void setDuracaoLigacao(Date duracaoLigacao) {
		this.duracaoLigacao = duracaoLigacao;
	}

	@Column(name = "icms", nullable = false)
	public int getIcms() {
		return this.icms;
	}

	public void setIcms(int icms) {
		this.icms = icms;
	}

	@Column(name = "valLigImp", nullable = false, precision = 12, scale = 0)
	public float getValLigImp() {
		return this.valLigImp;
	}

	public void setValLigImp(float valLigImp) {
		this.valLigImp = valLigImp;
	}

	@Column(name = "valLigSemImp", nullable = false, precision = 12, scale = 0)
	public float getValLigSemImp() {
		return this.valLigSemImp;
	}

	public void setValLigSemImp(float valLigSemImp) {
		this.valLigSemImp = valLigSemImp;
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

	@Column(name = "tipoChamada", nullable = false, length = 1)
	public String getTipoChamada() {
		return this.tipoChamada;
	}

	public void setTipoChamada(String tipoChamada) {
		this.tipoChamada = tipoChamada;
	}

	@Column(name = "grupoHoraTarif", length = 1)
	public String getGrupoHoraTarif() {
		return this.grupoHoraTarif;
	}

	public void setGrupoHoraTarif(String grupoHoraTarif) {
		this.grupoHoraTarif = grupoHoraTarif;
	}

	@Column(name = "desHoraTarif", length = 15)
	public String getDesHoraTarif() {
		return this.desHoraTarif;
	}

	public void setDesHoraTarif(String desHoraTarif) {
		this.desHoraTarif = desHoraTarif;
	}

	@Column(name = "degrauLigacao")
	public Integer getDegrauLigacao() {
		return this.degrauLigacao;
	}

	public void setDegrauLigacao(Integer degrauLigacao) {
		this.degrauLigacao = degrauLigacao;
	}

	@Column(name = "campoLivreOp", length = 25)
	public String getCampoLivreOp() {
		return this.campoLivreOp;
	}

	public void setCampoLivreOp(String campoLivreOp) {
		this.campoLivreOp = campoLivreOp;
	}
	
	public String dataFormatada()
	{
		SimpleDateFormat format = new SimpleDateFormat("dd/MM");
		return format.format(this.getDataLigacao());
	}
	
	public String tarifa() {
		
		DecimalFormat decimalFormatFinal = new DecimalFormat("##.##");
		decimalFormatFinal.setRoundingMode(RoundingMode.HALF_EVEN);
		float hora,minuto,segundo;
		
		hora = ((float)this.getDuracaoLigacao().getHours())*60;
		minuto = (float)this.getDuracaoLigacao().getMinutes();
		segundo = ((float)this.getDuracaoLigacao().getSeconds())/60;
		
		float retorno = (this.getValLigImp() / (hora+minuto+segundo));
	
		return decimalFormatFinal.format(retorno);
	}
}
