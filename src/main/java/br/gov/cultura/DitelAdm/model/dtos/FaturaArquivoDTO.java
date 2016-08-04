package br.gov.cultura.DitelAdm.model.dtos;

import java.util.List;

import br.gov.cultura.DitelAdm.modelo.Ajustes;
import br.gov.cultura.DitelAdm.modelo.Categoriaajuste;
import br.gov.cultura.DitelAdm.modelo.Categoriachamada;
import br.gov.cultura.DitelAdm.modelo.Categoriadesconto;
import br.gov.cultura.DitelAdm.modelo.Categoriaplano;
import br.gov.cultura.DitelAdm.modelo.Categoriaservico;
import br.gov.cultura.DitelAdm.modelo.Chamadas;
import br.gov.cultura.DitelAdm.modelo.Cliente;
import br.gov.cultura.DitelAdm.modelo.Descontos;
import br.gov.cultura.DitelAdm.modelo.Enderecos;
import br.gov.cultura.DitelAdm.modelo.Fatura;
import br.gov.cultura.DitelAdm.modelo.Operadora;
import br.gov.cultura.DitelAdm.modelo.Planos;
import br.gov.cultura.DitelAdm.modelo.Resumo;
import br.gov.cultura.DitelAdm.modelo.Servicos;

public class FaturaArquivoDTO {

	private Operadora operadora;
	private Cliente cliente;
	private Fatura fatura;
	private List<Resumo> resumo;
	private List<Enderecos> enderecos;
	private List<Chamadas> chamadas;
	private List<Categoriachamada> categoriaChamadas;
	private List<Servicos> servicos;
	private List<Categoriaservico> categoriaServicos;
	private List<Descontos> descontos;
	private List<Categoriadesconto> categoriaDescontos;
	private List<Planos> planos;
	private List<Categoriaplano> categoriaPlano;
	private List<Ajustes> ajustes;
	private List<Categoriaajuste> categoriaAjuste;

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

	public List<Enderecos> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Enderecos> enderecos) {
		this.enderecos = enderecos;
	}

	public List<Chamadas> getChamadas() {
		return chamadas;
	}

	public void setChamadas(List<Chamadas> chamadas) {
		this.chamadas = chamadas;
	}

	public List<Categoriachamada> getCategoriaChamadas() {
		return categoriaChamadas;
	}

	public void setCategoriaChamadas(List<Categoriachamada> categoriaChamadas) {
		this.categoriaChamadas = categoriaChamadas;
	}

	public List<Servicos> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servicos> servicos) {
		this.servicos = servicos;
	}

	public List<Categoriaservico> getCategoriaServicos() {
		return categoriaServicos;
	}

	public void setCategoriaServicos(List<Categoriaservico> categoriaServicos) {
		this.categoriaServicos = categoriaServicos;
	}

	public List<Descontos> getDescontos() {
		return descontos;
	}

	public void setDescontos(List<Descontos> descontos) {
		this.descontos = descontos;
	}

	public List<Categoriadesconto> getCategoriaDescontos() {
		return categoriaDescontos;
	}

	public void setCategoriaDescontos(List<Categoriadesconto> categoriaDescontos) {
		this.categoriaDescontos = categoriaDescontos;
	}

	public List<Planos> getPlanos() {
		return planos;
	}

	public void setPlanos(List<Planos> planos) {
		this.planos = planos;
	}

	public List<Categoriaplano> getCategoriaPlano() {
		return categoriaPlano;
	}

	public void setCategoriaPlano(List<Categoriaplano> categoriaPlano) {
		this.categoriaPlano = categoriaPlano;
	}

	public List<Ajustes> getAjustes() {
		return ajustes;
	}

	public void setAjustes(List<Ajustes> ajustes) {
		this.ajustes = ajustes;
	}

	public List<Categoriaajuste> getCategoriaAjuste() {
		return categoriaAjuste;
	}

	public void setCategoriaAjuste(List<Categoriaajuste> categoriaAjuste) {
		this.categoriaAjuste = categoriaAjuste;
	}

}
