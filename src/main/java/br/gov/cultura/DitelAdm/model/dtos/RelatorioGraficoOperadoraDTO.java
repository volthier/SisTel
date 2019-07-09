package br.gov.cultura.DitelAdm.model.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "operadora")
public class RelatorioGraficoOperadoraDTO {

	private Integer idOperadora;
	private String nomeOperadora;
	private String ufOperadora;
	private String cnpjOperadora;
	@JsonProperty("cliente")
	private List<RelatorioGraficoClienteDTO> relatorioGraficoClienteDTO; 
	
	public RelatorioGraficoOperadoraDTO() {
	}

	public RelatorioGraficoOperadoraDTO(Integer idOperadora, String nomeOperadora, String ufOperadora,
			String cnpjOperadora, List<RelatorioGraficoClienteDTO> relatorioGraficoClienteDTO) {
		super();
		this.idOperadora = idOperadora;
		this.nomeOperadora = nomeOperadora;
		this.ufOperadora = ufOperadora;
		this.cnpjOperadora = cnpjOperadora;
		this.relatorioGraficoClienteDTO = relatorioGraficoClienteDTO;
	}

	public Integer getIdOperadora() {
		return idOperadora;
	}

	public void setIdOperadora(Integer idOperadora) {
		this.idOperadora = idOperadora;
	}

	public String getNomeOperadora() {
		return nomeOperadora;
	}

	public void setNomeOperadora(String nomeOperadora) {
		this.nomeOperadora = nomeOperadora;
	}

	public String getUfOperadora() {
		return ufOperadora;
	}

	public void setUfOperadora(String ufOperadora) {
		this.ufOperadora = ufOperadora;
	}

	public String getCnpjOperadora() {
		return cnpjOperadora;
	}

	public void setCnpjOperadora(String cnpjOperadora) {
		this.cnpjOperadora = cnpjOperadora;
	}

	public List<RelatorioGraficoClienteDTO> getRelatorioGraficoClienteDTO() {
		return relatorioGraficoClienteDTO;
	}

	public void setRelatorioGraficoClienteDTO(List<RelatorioGraficoClienteDTO> relatorioGraficoClienteDTO) {
		this.relatorioGraficoClienteDTO = relatorioGraficoClienteDTO;
	}

}
