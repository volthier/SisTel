package br.gov.cultura.DitelAdm.repository.filtro;

public class CadastroFiltroPesquisa {
	
	private String descricao;
	private String nome;
	private String modelo;
	private String nrseries;

	
	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNrseries() {
		return nrseries;
	}

	public void setNrseries(String nrseries) {
		this.nrseries = nrseries;
	}
}
