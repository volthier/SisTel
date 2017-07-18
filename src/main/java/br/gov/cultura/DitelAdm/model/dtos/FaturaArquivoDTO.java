package br.gov.cultura.DitelAdm.model.dtos;

import java.util.Date;
import java.util.List;

import br.gov.cultura.DitelAdm.model.Alocacao;
import br.gov.cultura.DitelAdm.model.faturasV3.Ajustes;
import br.gov.cultura.DitelAdm.model.faturasV3.Categoriaajuste;
import br.gov.cultura.DitelAdm.model.faturasV3.Categoriachamada;
import br.gov.cultura.DitelAdm.model.faturasV3.Categoriadesconto;
import br.gov.cultura.DitelAdm.model.faturasV3.Categoriaplano;
import br.gov.cultura.DitelAdm.model.faturasV3.Categoriaservico;
import br.gov.cultura.DitelAdm.model.faturasV3.Chamadas;
import br.gov.cultura.DitelAdm.model.faturasV3.Cliente;
import br.gov.cultura.DitelAdm.model.faturasV3.Descontos;
import br.gov.cultura.DitelAdm.model.faturasV3.Enderecos;
import br.gov.cultura.DitelAdm.model.faturasV3.Fatura;
import br.gov.cultura.DitelAdm.model.faturasV3.Notafiscal;
import br.gov.cultura.DitelAdm.model.faturasV3.Operadora;
import br.gov.cultura.DitelAdm.model.faturasV3.Planos;
import br.gov.cultura.DitelAdm.model.faturasV3.Resumo;
import br.gov.cultura.DitelAdm.model.faturasV3.Servicos;
import br.gov.cultura.DitelAdm.model.faturasV3.Trailler;

public class FaturaArquivoDTO {

	private Alocacao alocacao;
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
	private List<Notafiscal> notaFiscal;
	private List<Trailler> trailler;
	private List<ServicosCategoria> servicosCategoria;
	private float valorTotal;
	private float valorContratoPlano;
	private Date totalHorasChamadas;
	
		
	public Date getTotalHorasChamadas() {
		return totalHorasChamadas;
	}

	public void setTotalHorasChamadas(Date totalHorasChamadas) {
		this.totalHorasChamadas = totalHorasChamadas;
	}

	public float getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}

	public float getValorContratoPlano() {
		return valorContratoPlano;
	}

	public void setValorContratoPlano(float valorContratoPlano) {
		this.valorContratoPlano = valorContratoPlano;
	}

	public Alocacao getAlocacao() {
		return alocacao;
	}

	public void setAlocacao(Alocacao alocacao) {
		this.alocacao = alocacao;
	}

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

	public List<Notafiscal> getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(List<Notafiscal> notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	public List<Trailler> getTrailler() {
		return trailler;
	}

	public void setTrailler(List<Trailler> trailler) {
		this.trailler = trailler;
	}

	public List<ServicosCategoria> getServicosCategoria() {
		return servicosCategoria;
	}

	public void setServicosCategoria(List<ServicosCategoria> servicosCategoria) {
		this.servicosCategoria = servicosCategoria;
	}
	
	
}
