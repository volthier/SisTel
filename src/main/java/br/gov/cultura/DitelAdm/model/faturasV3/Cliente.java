package br.gov.cultura.DitelAdm.model.faturasV3;
// Generated 29/08/2016 10:12:50 by Hibernate Tools 4.3.4.Final

import static javax.persistence.GenerationType.IDENTITY;

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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Cliente MODEL: Padrão FEBRABAN v3
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "cliente", catalog = "dbditel")
public class Cliente implements java.io.Serializable {

	private Integer idCliente;
	private String codCliente;
	@JsonIgnore
	private Operadora operadora;
	private String nome;
	private String cnpj;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Set<Fatura> faturas = new HashSet(0);

	public Cliente() {
	}

	public Cliente(Integer idCliente, String codCliente, Operadora operadora, String nome, String cnpj) {
		this.idCliente = idCliente;
		this.codCliente = codCliente;
		this.operadora = operadora;
		this.nome = nome;
		this.cnpj = cnpj;
	}

	public Cliente(Integer idCliente, String codCliente, Operadora operadora, String nome, String cnpj, Set<Fatura> faturas) {
		this.idCliente = idCliente;
		this.codCliente = codCliente;
		this.operadora = operadora;
		this.nome = nome;
		this.cnpj = cnpj;
		this.faturas = faturas;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id_cliente", unique = true, nullable = false)
	public Integer getIdCliente() {
		return this.idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	
	@Column(name = "codCliente", nullable = false, length = 15)
	public String getCodCliente() {
		return this.codCliente;
	}

	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "operadora_id_operadora", nullable = false)
	public Operadora getOperadora() {
		return this.operadora;
	}

	public void setOperadora(Operadora operadora) {
		this.operadora = operadora;
	}

	@Column(name = "nome", nullable = false, length = 30)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "cnpj", nullable = false, length = 15)
	public String getCnpj() {
		return this.cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "cliente")
	public Set<Fatura> getFaturas() {
		return this.faturas;
	}

	public void setFaturas(Set<Fatura> faturas) {
		this.faturas = faturas;
	}

}
