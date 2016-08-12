package br.gov.cultura.DitelAdm.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

import br.gov.cultura.DitelAdm.model.enums.VinculoCadastroPessoa;
import br.gov.cultura.DitelAdm.model.enums.VinculoCadastroPessoaCargo;
import br.gov.cultura.DitelAdm.model.enums.VinculoCadastroPessoaDAS;

@Entity
public class CadastroPessoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@CPF
	@NotNull(message="CPF e obrigatório!")
	private String cpf;
	
	@NotEmpty(message="Descrição e obrigatório!")
	@Size(max = 100, message = "Maximo de 100 caracteres para descrição!")
	private String nome;
	
	@NotNull
	private long matricula;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private VinculoCadastroPessoa vinculo;
	
	@NotNull(message = "Campo Setor obrigatório!")
	private String setor;

	@Enumerated(EnumType.STRING)
	private VinculoCadastroPessoaCargo cargo;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private VinculoCadastroPessoaDAS das;
	
	@NotEmpty(message = "Campo Email obrigatório!")
	@Size(max = 120,message="Campo Email excede tamanho!")
	private String email;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public long getMatricula() {
		return matricula;
	}
	public void setMatricula(long matricula) {
		this.matricula = matricula;
	}
	public VinculoCadastroPessoa getVinculo() {
		return vinculo;
	}
	public void setVinculo(VinculoCadastroPessoa vinculo) {
		this.vinculo = vinculo;
	}
	public String getSetor() {
		return setor;
	}
	public void setSetor(String setor) {
		this.setor = setor;
	}
	public VinculoCadastroPessoaDAS getDas() {
		return das;
	}
	public void setDas(VinculoCadastroPessoaDAS das) {
		this.das = das;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public VinculoCadastroPessoaCargo getCargo() {
		return cargo;
	}
	public void setCargo(VinculoCadastroPessoaCargo cargo) {
		this.cargo = cargo;
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
		CadastroPessoa other = (CadastroPessoa) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
