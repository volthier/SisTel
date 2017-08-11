package br.gov.cultura.DitelAdm.model;
// Generated 27/04/2017 16:07:37 by Hibernate Tools 4.0.0.Final

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

import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.gov.cultura.DitelAdm.model.faturasV3.Resumo;

/**
 * Linha MODEL
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "linha", catalog = "dbditel")
public class Linha implements java.io.Serializable {

	private Integer idLinha;
	private String numeroLinha;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@JsonManagedReference
	private Set<Alocacao> alocacaos = new HashSet(0);
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@JsonManagedReference
	private Set<Resumo> resumos = new HashSet(0);

	public Linha() {
	}

	public Linha(String numeroLinha) {
		this.numeroLinha = numeroLinha;
	}

	public Linha(String numeroLinha, Set<Alocacao> alocacaos, Set<Resumo> resumos) {
		this.numeroLinha = numeroLinha;
		this.alocacaos = alocacaos;
		this.resumos = resumos;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_linha", unique = true, nullable = false)
	public Integer getIdLinha() {
		return this.idLinha;
	}

	public void setIdLinha(Integer idLinha) {
		this.idLinha = idLinha;
	}
	@Column(name = "numero_linha", nullable = false, length = 17)
	public String getNumeroLinha() {
		return this.numeroLinha;
	}

	public void setNumeroLinha(String numeroLinha) {
		this.numeroLinha = numeroLinha;
	}
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "linha")
	public Set<Alocacao> getAlocacaos() {
		return this.alocacaos;
	}

	public void setAlocacaos(Set<Alocacao> alocacaos) {
		this.alocacaos = alocacaos;
	}
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "linha")
	public Set<Resumo> getResumos() {
		return this.resumos;
	}

	public void setResumos(Set<Resumo> resumos) {
		this.resumos = resumos;
	}

}
