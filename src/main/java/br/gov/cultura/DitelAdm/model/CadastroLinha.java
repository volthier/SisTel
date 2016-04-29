package br.gov.cultura.DitelAdm.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class CadastroLinha {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotEmpty(message="Numero da Linha e obrigatório!")
	private String nlinha;
	
	@Enumerated(EnumType.STRING)
	private VinculoCadastroLinhaPlano plano;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNlinha() {
		return nlinha;
	}

	public void setNlinha(String nlinha) {
		this.nlinha = nlinha;
	}

	public VinculoCadastroLinhaPlano getPlano() {
		return plano;
	}

	public void setPlano(VinculoCadastroLinhaPlano plano) {
		this.plano = plano;
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
		CadastroLinha other = (CadastroLinha) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
}

