package br.gov.cultura.DitelAdm.model.dtos;

import java.util.List;

import br.gov.cultura.DitelAdm.modelo.Cliente;
import br.gov.cultura.DitelAdm.modelo.Fatura;
import br.gov.cultura.DitelAdm.modelo.Operadora;
import br.gov.cultura.DitelAdm.modelo.Resumo;

public class FaturaArquivoDTO {

	private Operadora operadora;
	private Cliente cliente;
	private Fatura fatura;
	private List<Resumo> resumo;
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Operadora getOperadora() {
		return operadora;
	}

	public void setOperadora(Operadora operadora) {
		this.operadora = operadora;
	}

	public Fatura getFatura() {
		return fatura;
	}

	public void setFatura(Fatura fatura) {
		this.fatura = fatura;
	}

	public List<Resumo> getResumo() {
		return resumo;
	}

	public void setResumo(List<Resumo> resumo) {
		this.resumo = resumo;
	}

}
