package br.gov.cultura.DitelAdm.model.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "cliente")
public class RelatorioGraficoClienteDTO {
	
	private Integer idCliente;
	private String codCliente;
	private String nomeCliente;
	private String cnpjCliente;
	@JsonProperty("fatura")
	private List<RelatorioGraficoFaturaDTO> relatorioGraficoFaturaDTO;
	
	public RelatorioGraficoClienteDTO() {
	}

	public RelatorioGraficoClienteDTO(Integer idCliente, String codCliente, String nomeCliente, String cnpjCliente,
			List<RelatorioGraficoFaturaDTO> relatorioGraficoFaturaDTO) {
		super();
		this.idCliente = idCliente;
		this.codCliente = codCliente;
		this.nomeCliente = nomeCliente;
		this.cnpjCliente = cnpjCliente;
		this.relatorioGraficoFaturaDTO = relatorioGraficoFaturaDTO;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getCnpjCliente() {
		return cnpjCliente;
	}

	public void setCnpjCliente(String cnpjCliente) {
		this.cnpjCliente = cnpjCliente;
	}

	public List<RelatorioGraficoFaturaDTO> getRelatorioGraficoFaturaDTO() {
		return relatorioGraficoFaturaDTO;
	}

	public void setRelatorioGraficoFaturaDTO(List<RelatorioGraficoFaturaDTO> relatorioGraficoFaturaDTO) {
		this.relatorioGraficoFaturaDTO = relatorioGraficoFaturaDTO;
	}	

}
