package br.gov.cultura.DitelAdm.model;
// Generated 17/08/2016 19:31:54 by Hibernate Tools 4.3.4.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Chip generated by hbm2java
 */
@Entity
@Table(name = "chip", catalog = "diteladmdev")
public class Chip implements java.io.Serializable {

	private Integer idChip;
	private String numeroSerieChip;
	private String tipoChip;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Set<AlocacaoLinhaChip> alocacaoLinhaChips = new HashSet(0);

	public Chip() {
	}

	public Chip(String numeroSerieChip, String tipoChip, Set<AlocacaoLinhaChip> alocacaoLinhaChips) {
		this.numeroSerieChip = numeroSerieChip;
		this.tipoChip = tipoChip;
		this.alocacaoLinhaChips = alocacaoLinhaChips;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idChip", unique = true, nullable = false)
	public Integer getIdChip() {
		return this.idChip;
	}

	public void setIdChip(Integer idChip) {
		this.idChip = idChip;
	}

	@Column(name = "numeroSerieChip")
	@NotEmpty(message="Numero de serie e obrigatório!")
	public String getNumeroSerieChip() {
		return this.numeroSerieChip;
	}

	public void setNumeroSerieChip(String numeroSerieChip) {
		this.numeroSerieChip = numeroSerieChip;
	}

	@Column(name = "tipoChip")
	/*@Enumerated(EnumType.STRING)*/
	public String getTipoChip() {
		return this.tipoChip;
	}

	public void setTipoChip(String tipoChip) {
		this.tipoChip = tipoChip;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "chip")
	public Set<AlocacaoLinhaChip> getAlocacaoLinhaChips() {
		return this.alocacaoLinhaChips;
	}

	public void setAlocacaoLinhaChips(Set<AlocacaoLinhaChip> alocacaoLinhaChips) {
		this.alocacaoLinhaChips = alocacaoLinhaChips;
	}

}
