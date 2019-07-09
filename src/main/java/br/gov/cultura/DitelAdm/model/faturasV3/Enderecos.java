package br.gov.cultura.DitelAdm.model.faturasV3;
// Generated 29/08/2016 10:12:50 by Hibernate Tools 4.3.4.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Enderecos MODEL: Padrão FEBRABAN v3
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "enderecos", catalog = "dbditel")
public class Enderecos implements java.io.Serializable {

	private String idEnderecos;
	@JsonIgnore
	private Fatura fatura;
	private String tipoEndereco;
	private String cnlRecEnd;
	private String nomeLocalEnd;
	private String ufLocal;
	private String endereco;
	private String numero;
	private String complemento;
	private String bairro;
	private String campoLivreOp;

	public Enderecos() {
	}

	public Enderecos(String idEnderecos, Fatura fatura) {
		this.idEnderecos = idEnderecos;
		this.fatura = fatura;
	}

	public Enderecos(String idEnderecos, Fatura fatura, String tipoEndereco, String cnlRecEnd, String nomeLocalEnd,
			String ufLocal, String endereco, String numero, String complemento, String bairro, String campoLivreOp) {
		this.idEnderecos = idEnderecos;
		this.fatura = fatura;
		this.tipoEndereco = tipoEndereco;
		this.cnlRecEnd = cnlRecEnd;
		this.nomeLocalEnd = nomeLocalEnd;
		this.ufLocal = ufLocal;
		this.endereco = endereco;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.campoLivreOp = campoLivreOp;
	}

	@Id
	@Column(name = "id_enderecos", unique = true, nullable = false, length = 18)
	public String getIdEnderecos() {
		return this.idEnderecos;
	}

	public void setIdEnderecos(String idEnderecos) {
		this.idEnderecos = idEnderecos;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fatura_id_fatura", nullable = false)
	public Fatura getFatura() {
		return this.fatura;
	}

	public void setFatura(Fatura fatura) {
		this.fatura = fatura;
	}

	@Column(name = "tipoEndereco", length = 5)
	public String getTipoEndereco() {
		return this.tipoEndereco;
	}

	public void setTipoEndereco(String tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}

	@Column(name = "cnlRecEnd", length = 5)
	public String getCnlRecEnd() {
		return this.cnlRecEnd;
	}

	public void setCnlRecEnd(String cnlRecEnd) {
		this.cnlRecEnd = cnlRecEnd;
	}

	@Column(name = "nomeLocalEnd", length = 15)
	public String getNomeLocalEnd() {
		return this.nomeLocalEnd;
	}

	public void setNomeLocalEnd(String nomeLocalEnd) {
		this.nomeLocalEnd = nomeLocalEnd;
	}

	@Column(name = "ufLocal", length = 2)
	public String getUfLocal() {
		return this.ufLocal;
	}

	public void setUfLocal(String ufLocal) {
		this.ufLocal = ufLocal;
	}

	@Column(name = "endereco", length = 30)
	public String getEndereco() {
		return this.endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	@Column(name = "numero", length = 5)
	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Column(name = "complemento", length = 8)
	public String getComplemento() {
		return this.complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	@Column(name = "bairro", length = 10)
	public String getBairro() {
		return this.bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	@Column(name = "campoLivreOp", length = 25)
	public String getCampoLivreOp() {
		return this.campoLivreOp;
	}

	public void setCampoLivreOp(String campoLivreOp) {
		this.campoLivreOp = campoLivreOp;
	}

}
