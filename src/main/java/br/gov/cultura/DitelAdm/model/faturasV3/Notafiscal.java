package br.gov.cultura.DitelAdm.model.faturasV3;
// Generated 29/08/2016 10:12:50 by Hibernate Tools 4.3.4.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Notafiscal MODEL: Padrão FEBRABAN v3
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "nota_fiscal", catalog = "dbditel")
public class Notafiscal implements java.io.Serializable {

	private String numNf;
	private Fatura fatura;
	private float valorNfimp;
	private String tipoNf;
	private String campoLivreOp;

	public Notafiscal() {
	}

	public Notafiscal(String numNf, Fatura fatura, float valorNfimp, String tipoNf) {
		this.numNf = numNf;
		this.fatura = fatura;
		this.valorNfimp = valorNfimp;
		this.tipoNf = tipoNf;
	}

	public Notafiscal(String numNf, Fatura fatura, float valorNfimp, String tipoNf, String campoLivreOp) {
		this.numNf = numNf;
		this.fatura = fatura;
		this.valorNfimp = valorNfimp;
		this.tipoNf = tipoNf;
		this.campoLivreOp = campoLivreOp;
	}

	@Id

	@Column(name = "numNF", unique = true, nullable = false, length = 12)
	public String getNumNf() {
		return this.numNf;
	}

	public void setNumNf(String numNf) {
		this.numNf = numNf;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fatura_id_fatura", nullable = false)
	public Fatura getFatura() {
		return this.fatura;
	}

	public void setFatura(Fatura fatura) {
		this.fatura = fatura;
	}

	@Column(name = "valorNFImp", nullable = false, precision = 12, scale = 0)
	public float getValorNfimp() {
		return this.valorNfimp;
	}

	public void setValorNfimp(float valorNfimp) {
		this.valorNfimp = valorNfimp;
	}

	@Column(name = "tipoNF", nullable = false, length = 1)
	public String getTipoNf() {
		return this.tipoNf;
	}

	public void setTipoNf(String tipoNf) {
		this.tipoNf = tipoNf;
	}

	@Column(name = "campoLivreOp", length = 25)
	public String getCampoLivreOp() {
		return this.campoLivreOp;
	}

	public void setCampoLivreOp(String campoLivreOp) {
		this.campoLivreOp = campoLivreOp;
	}

}
