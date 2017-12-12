package br.gov.cultura.DitelAdm.model.dtos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "fatura")
public class RelatorioGraficoFaturaDTO {
	
	private Integer idFatura;
	private String mesRef;
	private Date dataVenc;
	private String cnLocalidade;
	private float valTotal;
	
	public RelatorioGraficoFaturaDTO() {
	}

	public RelatorioGraficoFaturaDTO(Integer idFatura, String cnLocalidade, String mesRef, Date dataVenc, float valTotal) {
		super();
		this.idFatura = idFatura;
		this.cnLocalidade = cnLocalidade;
		this.mesRef = mesRef;
		this.dataVenc = dataVenc;
		this.valTotal = valTotal;
	}

	public Integer getIdFatura() {
		return idFatura;
	}

	public void setIdFatura(Integer idFatura) {
		this.idFatura = idFatura;
	}

	public String getCnLocalidade(){
		return cnLocalidade;
	}
	
	public void setCnLocalidade(String cnLocalidade){
		this.cnLocalidade = cnLocalidade;
	}
	
	public String getMesRef() {
		return mesRef;
	}

	public void setMesRef(String mesRef) {
		this.mesRef = mesRef;
	}

	public Date getDataVenc() {
		return dataVenc;
	}

	public void setDataVenc(Date dataVenc) {
		this.dataVenc = dataVenc;
	}

	public float getValTotal() {
		return valTotal;
	}

	public void setValTotal(float valTotal) {
		this.valTotal = valTotal;
	}
	
	
}
