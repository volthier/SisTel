package br.gov.cultura.DitelAdm.model.faturasV3;
// Generated 29/08/2016 10:12:50 by Hibernate Tools 4.3.4.Final

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

/**
 * Trailler MODEL: Padrão FEBRABAN v3
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "trailler", catalog = "dbditel")
public class Trailler implements java.io.Serializable {

	private Integer idTrailler;
	@JsonIgnore
	private Fatura fatura;
	private int quanReg10;
	private int quanReg20;
	private int quanReg30;
	private int quanReg40;
	private int quanReg50;
	private int quanReg60;
	private int quanReg70;
	private int quanReg80;
	private int quanTotalReg;
	private float valTotal;
	private float valTotal10;
	private float valTotal30;
	private float valTotal40;
	private float valTotal50;
	private float valTotal60;
	private float valTotal70;
	private float valTotal80;
	private char sinTotalReg50;
	private char sinTotalReg70;
	private String campoLivreOp;

	public Trailler() {
	}

	public Trailler(Fatura fatura, int quanReg10, int quanReg20, int quanReg30, int quanReg40, int quanReg50,
			int quanReg60, int quanReg70, int quanReg80, int quanTotalReg, float valTotal, float valTotal10,
			float valTotal30, float valTotal40, float valTotal50, float valTotal60, float valTotal70, float valTotal80,
			char sinTotalReg50, char sinTotalReg70) {
		this.fatura = fatura;
		this.quanReg10 = quanReg10;
		this.quanReg20 = quanReg20;
		this.quanReg30 = quanReg30;
		this.quanReg40 = quanReg40;
		this.quanReg50 = quanReg50;
		this.quanReg60 = quanReg60;
		this.quanReg70 = quanReg70;
		this.quanReg80 = quanReg80;
		this.quanTotalReg = quanTotalReg;
		this.valTotal = valTotal;
		this.valTotal10 = valTotal10;
		this.valTotal30 = valTotal30;
		this.valTotal40 = valTotal40;
		this.valTotal50 = valTotal50;
		this.valTotal60 = valTotal60;
		this.valTotal70 = valTotal70;
		this.valTotal80 = valTotal80;
		this.sinTotalReg50 = sinTotalReg50;
		this.sinTotalReg70 = sinTotalReg70;
	}

	public Trailler(Fatura fatura, int quanReg10, int quanReg20, int quanReg30, int quanReg40, int quanReg50,
			int quanReg60, int quanReg70, int quanReg80, int quanTotalReg, float valTotal, float valTotal10,
			float valTotal30, float valTotal40, float valTotal50, float valTotal60, float valTotal70, float valTotal80,
			char sinTotalReg50, char sinTotalReg70, String campoLivreOp) {
		this.fatura = fatura;
		this.quanReg10 = quanReg10;
		this.quanReg20 = quanReg20;
		this.quanReg30 = quanReg30;
		this.quanReg40 = quanReg40;
		this.quanReg50 = quanReg50;
		this.quanReg60 = quanReg60;
		this.quanReg70 = quanReg70;
		this.quanReg80 = quanReg80;
		this.quanTotalReg = quanTotalReg;
		this.valTotal = valTotal;
		this.valTotal10 = valTotal10;
		this.valTotal30 = valTotal30;
		this.valTotal40 = valTotal40;
		this.valTotal50 = valTotal50;
		this.valTotal60 = valTotal60;
		this.valTotal70 = valTotal70;
		this.valTotal80 = valTotal80;
		this.sinTotalReg50 = sinTotalReg50;
		this.sinTotalReg70 = sinTotalReg70;
		this.campoLivreOp = campoLivreOp;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id_trailler", unique = true, nullable = false)
	public Integer getIdTrailler() {
		return this.idTrailler;
	}

	public void setIdTrailler(Integer idTrailler) {
		this.idTrailler = idTrailler;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fatura_id_fatura", nullable = false)
	public Fatura getFatura() {
		return this.fatura;
	}

	public void setFatura(Fatura fatura) {
		this.fatura = fatura;
	}

	@Column(name = "quanReg10", nullable = false)
	public int getQuanReg10() {
		return this.quanReg10;
	}

	public void setQuanReg10(int quanReg10) {
		this.quanReg10 = quanReg10;
	}

	@Column(name = "quanReg20", nullable = false)
	public int getQuanReg20() {
		return this.quanReg20;
	}

	public void setQuanReg20(int quanReg20) {
		this.quanReg20 = quanReg20;
	}

	@Column(name = "quanReg30", nullable = false)
	public int getQuanReg30() {
		return this.quanReg30;
	}

	public void setQuanReg30(int quanReg30) {
		this.quanReg30 = quanReg30;
	}

	@Column(name = "quanReg40", nullable = false)
	public int getQuanReg40() {
		return this.quanReg40;
	}

	public void setQuanReg40(int quanReg40) {
		this.quanReg40 = quanReg40;
	}

	@Column(name = "quanReg50", nullable = false)
	public int getQuanReg50() {
		return this.quanReg50;
	}

	public void setQuanReg50(int quanReg50) {
		this.quanReg50 = quanReg50;
	}

	@Column(name = "quanReg60", nullable = false)
	public int getQuanReg60() {
		return this.quanReg60;
	}

	public void setQuanReg60(int quanReg60) {
		this.quanReg60 = quanReg60;
	}

	@Column(name = "quanReg70", nullable = false)
	public int getQuanReg70() {
		return this.quanReg70;
	}

	public void setQuanReg70(int quanReg70) {
		this.quanReg70 = quanReg70;
	}

	@Column(name = "quanReg80", nullable = false)
	public int getQuanReg80() {
		return this.quanReg80;
	}

	public void setQuanReg80(int quanReg80) {
		this.quanReg80 = quanReg80;
	}

	@Column(name = "quanTotalReg", nullable = false)
	public int getQuanTotalReg() {
		return this.quanTotalReg;
	}

	public void setQuanTotalReg(int quanTotalReg) {
		this.quanTotalReg = quanTotalReg;
	}

	@Column(name = "valTotal", nullable = false, precision = 12, scale = 0)
	public float getValTotal() {
		return this.valTotal;
	}

	public void setValTotal(float valTotal) {
		this.valTotal = valTotal;
	}

	@Column(name = "valTotal10", nullable = false, precision = 12, scale = 0)
	public float getValTotal10() {
		return this.valTotal10;
	}

	public void setValTotal10(float valTotal10) {
		this.valTotal10 = valTotal10;
	}

	@Column(name = "valTotal30", nullable = false, precision = 12, scale = 0)
	public float getValTotal30() {
		return this.valTotal30;
	}

	public void setValTotal30(float valTotal30) {
		this.valTotal30 = valTotal30;
	}

	@Column(name = "valTotal40", nullable = false, precision = 12, scale = 0)
	public float getValTotal40() {
		return this.valTotal40;
	}

	public void setValTotal40(float valTotal40) {
		this.valTotal40 = valTotal40;
	}

	@Column(name = "valTotal50", nullable = false, precision = 12, scale = 0)
	public float getValTotal50() {
		return this.valTotal50;
	}

	public void setValTotal50(float valTotal50) {
		this.valTotal50 = valTotal50;
	}

	@Column(name = "valTotal60", nullable = false, precision = 12, scale = 0)
	public float getValTotal60() {
		return this.valTotal60;
	}

	public void setValTotal60(float valTotal60) {
		this.valTotal60 = valTotal60;
	}

	@Column(name = "valTotal70", nullable = false, precision = 12, scale = 0)
	public float getValTotal70() {
		return this.valTotal70;
	}

	public void setValTotal70(float valTotal70) {
		this.valTotal70 = valTotal70;
	}

	@Column(name = "valTotal80", nullable = false, precision = 12, scale = 0)
	public float getValTotal80() {
		return this.valTotal80;
	}

	public void setValTotal80(float valTotal80) {
		this.valTotal80 = valTotal80;
	}

	@Column(name = "sinTotalReg50", nullable = false, length = 1)
	public char getSinTotalReg50() {
		return this.sinTotalReg50;
	}

	public void setSinTotalReg50(char sinTotalReg50) {
		this.sinTotalReg50 = sinTotalReg50;
	}

	@Column(name = "sinTotalReg70", nullable = false, length = 1)
	public char getSinTotalReg70() {
		return this.sinTotalReg70;
	}

	public void setSinTotalReg70(char sinTotalReg70) {
		this.sinTotalReg70 = sinTotalReg70;
	}

	@Column(name = "campoLivreOp", length = 25)
	public String getCampoLivreOp() {
		return this.campoLivreOp;
	}

	public void setCampoLivreOp(String campoLivreOp) {
		this.campoLivreOp = campoLivreOp;
	}

}
