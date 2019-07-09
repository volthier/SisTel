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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Operadora MODEL: Padrão FEBRABAN v3
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "operadora", catalog = "dbditel")
public class Operadora implements java.io.Serializable {

	private Integer idOperadora; 
	private int codOperadora;
	private String nome;
	private String cnpj;
	private String uf;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Set<Cliente> clientes = new HashSet(0);

	public Operadora() {
	}

	public Operadora(Integer idOperadora, int codOperadora, String nome, String cnpj, String uf) {
		this.idOperadora = idOperadora;
		this.codOperadora = codOperadora;
		this.nome = nome;
		this.cnpj = cnpj;
		this.uf = uf;
	}

	public Operadora(Integer idOperadora, int codOperadora, String nome, String cnpj, String uf, Set<Cliente> clientes) {
		this.idOperadora= idOperadora;
		this.codOperadora = codOperadora;
		this.nome = nome;
		this.cnpj = cnpj;
		this.uf = uf;
		this.clientes = clientes;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id_operadora", unique = true, nullable = false)
	public Integer getIdOperadora() {
		return this.idOperadora;
	}

	public void setIdOperadora(Integer idOperadora) {
		this.idOperadora = idOperadora;
	}
	
	@Column(name = "cod_operadora", nullable = false)
	public int getCodOperadora() {
		return this.codOperadora;
	}

	public void setCodOperadora(int codOperadora) {
		this.codOperadora = codOperadora;
	}

	@Column(name = "nome", nullable = false, length = 15)
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

	@Column(name = "uf", nullable = false, length = 2)
	public String getUf() {
		return this.uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "operadora")
	public Set<Cliente> getClientes() {
		return this.clientes;
	}

	public void setClientes(Set<Cliente> clientes) {
		this.clientes = clientes;
	}

}
