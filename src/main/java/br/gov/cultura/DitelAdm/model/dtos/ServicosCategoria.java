package br.gov.cultura.DitelAdm.model.dtos;

import br.gov.cultura.DitelAdm.model.faturasV3.Categoriaservico;

public class ServicosCategoria {
	
	private Integer quantidade;
	
	private Float tarifa;
	
	private Float valorTotal;
	
	private Float valorCobrado;
	
	private Float dados;
	
	private Categoriaservico categoria;
	
	public ServicosCategoria() {
		this.setQuantidade(0);
		this.setDados(new Float(0));
		this.setTarifa(new Float(0));
		this.setValorCobrado(new Float(0));
		this.setValorTotal(new Float(0));
	}
	

	public Integer getQuantidade() {
		return quantidade;
	}

	public ServicosCategoria setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
		return this;
	}

	public Float getTarifa() {
		return tarifa;
	}

	public ServicosCategoria setTarifa(Float tarifa) {
		this.tarifa = tarifa;
		return this;
	}

	public Float getValorTotal() {
		return valorTotal;
	}

	public ServicosCategoria setValorTotal(Float valorTotal) {
		this.valorTotal = valorTotal;
		return this;
	}

	public Float getValorCobrado() {
		return valorCobrado;
	}

	public ServicosCategoria setValorCobrado(Float valorCobrado) {
		this.valorCobrado = valorCobrado;
		return this;
	}

	public Float getDados() {
		return dados;
	}

	public ServicosCategoria setDados(Float dados) {
		this.dados = dados;
		return this;
	}
	
	public Categoriaservico getCategoria() {
		return this.categoria;
	}
	
	public ServicosCategoria setCategoria(Categoriaservico categoria) {
		this.categoria = categoria;
		return this;
	}
}
