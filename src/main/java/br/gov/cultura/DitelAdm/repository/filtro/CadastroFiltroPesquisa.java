package br.gov.cultura.DitelAdm.repository.filtro;

public class CadastroFiltroPesquisa {
	
	private String descricao;
	private String nome;
	private String modelo;
	private String numeroSerieChip;
	private String nlinha;
	private String id;
	private String marca;
	private String idChip;
	private String dasAtesto;
	
	
	public String getDasAtesto() {
		return dasAtesto;
	}

	public void setDasAtesto(String dasAtesto) {
		this.dasAtesto = dasAtesto;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNlinha() {
		return nlinha;
	}

	public void setNlinha(String nlinha) {
		this.nlinha = nlinha;
	}

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

	public String getNumeroSerieChip() {
		return numeroSerieChip;
	}

	public void setNumeroSerieChip(String numeroSerieChip) {
		this.numeroSerieChip = numeroSerieChip;
	}

	public String getIdChip() {
		return idChip;
	}

	public void setIdChip(String idChip) {
		this.idChip = idChip;
	}

}