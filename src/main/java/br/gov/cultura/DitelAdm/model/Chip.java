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

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * Chip MODEL
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "chip", catalog = "dbditel")
public class Chip implements java.io.Serializable {

	private Integer idChip;
	private String numeroSerieChip;
	private String tipoChip;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@JsonManagedReference
	private Set<Alocacao> alocacaos = new HashSet(0);

	public Chip() {
	}

	public Chip(String numeroSerieChip, String tipoChip) {
		this.numeroSerieChip = numeroSerieChip;
		this.tipoChip = tipoChip;
	}

	public Chip(String numeroSerieChip, String tipoChip, Set<Alocacao> alocacaos) {
		this.numeroSerieChip = numeroSerieChip;
		this.tipoChip = tipoChip;
		this.alocacaos = alocacaos;
	}
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_chip", unique = true, nullable = false)
	public Integer getIdChip() {
		return this.idChip;
	}

	public void setIdChip(Integer idChip) {
		this.idChip = idChip;
	}
	@Column(name = "numero_serie_chip", nullable = false)
	@NotEmpty(message="Numero de serie e obrigat�rio!")
	public String getNumeroSerieChip() {
		return this.numeroSerieChip;
	}

	public void setNumeroSerieChip(String numeroSerieChip) {
		this.numeroSerieChip = numeroSerieChip;
	}
	@Column(name = "tipo_chip", nullable = false)
	@NotBlank(message="Selecione Tipo!")
	public String getTipoChip() {
		return this.tipoChip;
	}

	public void setTipoChip(String tipoChip) {
		this.tipoChip = tipoChip;
	}
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "chip")
	public Set<Alocacao> getAlocacaos() {
		return this.alocacaos;
	}

	public void setAlocacaos(Set<Alocacao> alocacaos) {
		this.alocacaos = alocacaos;
	}

}
