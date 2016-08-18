/*package br.gov.cultura.DitelAdm.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import org.hibernate.validator.constraints.NotEmpty;

import br.gov.cultura.DitelAdm.model.enums.TipoChip;

@Entity
public class CadastroChip {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotEmpty(message="Numero de serie e obrigatório!")
	private String nrserie;
	
	@Enumerated(EnumType.STRING)
	private TipoChip tipo;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNrserie() {
		return nrserie;
	}

	public void setNrserie(String nrserie) {
		this.nrserie = nrserie;
	}

	public TipoChip getTipo() {
		return tipo;
	}

	public void setTipo(TipoChip tipo) {
		this.tipo = tipo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CadastroChip other = (CadastroChip) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
}

*/