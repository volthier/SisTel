package br.gov.cultura.DitelAdm.model.faturasV3;
// Generated 29/08/2016 10:12:50 by Hibernate Tools 4.3.4.Final

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Categoriadesconto MODEL: Padrão FEBRABAN v3
 */
@Entity
@Table(name = "categoriadesconto", catalog = "dbditel")
public class Categoriadesconto implements java.io.Serializable {

	private int idCatDesconto;
	private int codCatDesconto;
	private String sigla;
	private String descricao;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Set<Descontos> descontoses = new HashSet(0);

	public Categoriadesconto() {
	}

	public Categoriadesconto(int idCatDesconto, int codCatDesconto, String sigla, String descricao) {
		this.idCatDesconto = idCatDesconto;
		this.codCatDesconto = codCatDesconto;
		this.sigla = sigla;
		this.descricao = descricao;
	}

	public Categoriadesconto(int idCatDesconto, int codCatDesconto, String sigla, String descricao, Set<Descontos> descontoses) {
		this.idCatDesconto = idCatDesconto;
		this.codCatDesconto = codCatDesconto;
		this.sigla = sigla;
		this.descricao = descricao;
		this.descontoses = descontoses;
	}

	@Id

	@Column(name = "id_catDesconto", unique = true, nullable = false)
	public int getIdCatDesconto() {
		return this.idCatDesconto;
	}

	public void setIdCatDesconto(int idCatDesconto) {
		this.idCatDesconto = idCatDesconto;
	}

	@Column(name = "codCatDesconto", nullable = false)
	public int getCodCatDesconto() {
		return this.codCatDesconto;
	}

	public void setCodCatDesconto(int codCatDesconto) {
		this.codCatDesconto = codCatDesconto;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "categoriadesconto")
	public Set<Descontos> getDescontoses() {
		return this.descontoses;
	}

	public void setDescontoses(Set<Descontos> descontoses) {
		this.descontoses = descontoses;
	}

}
