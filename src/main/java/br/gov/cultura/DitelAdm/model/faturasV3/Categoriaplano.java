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

/**
 * Categoriaplano MODEL: Padrão FEBRABAN v3
 */
@Entity
@Table(name = "categoria_plano", catalog = "dbditel")
public class Categoriaplano implements java.io.Serializable {

	private Integer idCatPlano;
	private int codCatPlano;
	private String sigla;
	private String descricao;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Set<Planos> planoses = new HashSet(0);

	public Categoriaplano() {
	}

	public Categoriaplano(int codCatPlano, String sigla, String descricao) {
		this.codCatPlano = codCatPlano;
		this.sigla = sigla;
		this.descricao = descricao;
	}

	public Categoriaplano(int codCatPlano, String sigla, String descricao, Set<Planos> planoses) {
		this.codCatPlano = codCatPlano;
		this.sigla = sigla;
		this.descricao = descricao;
		this.planoses = planoses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id_catPlano", unique = true, nullable = false)
	public Integer getIdCatPlano() {
		return this.idCatPlano;
	}

	public void setIdCatPlano(Integer idCatPlano) {
		this.idCatPlano = idCatPlano;
	}

	@Column(name = "codCatPlano", nullable = false)
	public int getCodCatPlano() {
		return this.codCatPlano;
	}

	public void setCodCatPlano(int codCatPlano) {
		this.codCatPlano = codCatPlano;
	}

	@Column(name = "sigla", nullable = false, length = 3)
	public String getSigla() {
		return this.sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	@Column(name = "descricao", nullable = false, length = 25)
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "categoriaplano")
	public Set<Planos> getPlanoses() {
		return this.planoses;
	}

	public void setPlanoses(Set<Planos> planoses) {
		this.planoses = planoses;
	}

}
