package br.gov.cultura.DitelAdm.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.ws.rs.core.StreamingOutput;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.cultura.DitelAdm.model.dtos.FaturaArquivoDTO;
import br.gov.cultura.DitelAdm.model.dtos.RelatorioGraficoClienteDTO;
import br.gov.cultura.DitelAdm.model.dtos.RelatorioGraficoFaturaDTO;
import br.gov.cultura.DitelAdm.model.dtos.RelatorioGraficoOperadoraDTO;
import br.gov.cultura.DitelAdm.model.faturasV3.Chamadas;
import br.gov.cultura.DitelAdm.model.faturasV3.Cliente;
import br.gov.cultura.DitelAdm.model.faturasV3.Enderecos;
import br.gov.cultura.DitelAdm.model.faturasV3.Fatura;
import br.gov.cultura.DitelAdm.model.faturasV3.Operadora;
import br.gov.cultura.DitelAdm.model.faturasV3.Planos;
import br.gov.cultura.DitelAdm.model.faturasV3.Resumo;
import br.gov.cultura.DitelAdm.model.faturasV3.Trailler;
import br.gov.cultura.DitelAdm.repository.Faturas.Ajustesas;
import br.gov.cultura.DitelAdm.repository.Faturas.CategoriasAjustes;
import br.gov.cultura.DitelAdm.repository.Faturas.CategoriasChamadas;
import br.gov.cultura.DitelAdm.repository.Faturas.CategoriasDescontos;
import br.gov.cultura.DitelAdm.repository.Faturas.CategoriasPlanos;
import br.gov.cultura.DitelAdm.repository.Faturas.CategoriasServicos;
import br.gov.cultura.DitelAdm.repository.Faturas.Chamadasas;
import br.gov.cultura.DitelAdm.repository.Faturas.Clientes;
import br.gov.cultura.DitelAdm.repository.Faturas.Descontosas;
import br.gov.cultura.DitelAdm.repository.Faturas.Enderecosas;
import br.gov.cultura.DitelAdm.repository.Faturas.Faturas;
import br.gov.cultura.DitelAdm.repository.Faturas.NotasFiscais;
import br.gov.cultura.DitelAdm.repository.Faturas.Operadoras;
import br.gov.cultura.DitelAdm.repository.Faturas.Planosas;
import br.gov.cultura.DitelAdm.repository.Faturas.Resumos;
import br.gov.cultura.DitelAdm.repository.Faturas.Servicosas;
import br.gov.cultura.DitelAdm.repository.Faturas.Traillers;

@Service
@Transactional
public class FaturaService {

	@Autowired
	private Operadoras operadoras;

	@Autowired
	private Clientes clientes;

	@Autowired
	private Faturas faturas;

	@Autowired
	private Resumos resumos;

	@Autowired
	private Enderecosas enderecos;

	@Autowired
	private Chamadasas chamadas;

	@Autowired
	private CategoriasChamadas categoriasChamadas;

	@Autowired
	private Servicosas servicosas;

	@Autowired
	private CategoriasServicos categoriasServicos;

	@Autowired
	private Descontosas descontosas;

	@Autowired
	private CategoriasDescontos categoriasDescontos;
	
	@Autowired
	private Planosas planosas;
	
	@Autowired
	private CategoriasPlanos categoriasPlanos;
	
	@Autowired
	private Ajustesas ajustesas;
	
	@Autowired
	private CategoriasAjustes categoriasAjustes;

	@Autowired
	private NotasFiscais notasFiscais;
	
	@Autowired
	private Traillers traillers;
	
	public void salvarOperadora(FaturaArquivoDTO faturaArquivoDTO) {
		operadoras.saveAndFlush(faturaArquivoDTO.getOperadora());
	}

	public void salvarCliente(FaturaArquivoDTO faturaArquivoDTO) {
		clientes.saveAndFlush(faturaArquivoDTO.getCliente());
	}

	public void salvarFatura(FaturaArquivoDTO faturaArquivoDTO) {
		faturas.saveAndFlush(faturaArquivoDTO.getFatura());
	}

	public void salvarResumo(FaturaArquivoDTO faturaArquivoDTO) {
		resumos.save(faturaArquivoDTO.getResumo());
	}

	public void salvarEnderecos(FaturaArquivoDTO faturaArquivoDTO) {
		enderecos.save(faturaArquivoDTO.getEnderecos());
	}

	public void salvarChamadas(FaturaArquivoDTO faturaArquivoDTO) {
		chamadas.save(faturaArquivoDTO.getChamadas());
	}

	public void salvarCategoriasChamadas(FaturaArquivoDTO faturaArquivoDTO) {
		categoriasChamadas.save(faturaArquivoDTO.getCategoriaChamadas());
	}

	public void salvarServicos(FaturaArquivoDTO faturaArquivoDTO) {
		servicosas.save(faturaArquivoDTO.getServicos());
	}

	public void salvarCategoriaServicos(FaturaArquivoDTO faturaArquivoDTO) {
		categoriasServicos.save(faturaArquivoDTO.getCategoriaServicos());
	}

	public void salvarDescontos(FaturaArquivoDTO faturaArquivoDTO) {
		descontosas.save(faturaArquivoDTO.getDescontos());
	}

	public void salvarCategoriasDescontos(FaturaArquivoDTO faturaArquivoDTO) {
		categoriasDescontos.save(faturaArquivoDTO.getCategoriaDescontos());
	}

	public void salvarCategoriasPlanos(FaturaArquivoDTO faturaArquivoDTO){
		categoriasPlanos.save(faturaArquivoDTO.getCategoriaPlano());
	}
	public void SalvarPlanos(FaturaArquivoDTO faturaArquivoDTO){
		List<Planos> planos = faturaArquivoDTO.getPlanos();
		for (Planos plano : planos) {
			planosas.save(plano);
		}
	}
	public void salvarAjustes(FaturaArquivoDTO faturaArquivoDTO){
		ajustesas.save(faturaArquivoDTO.getAjustes());
	}
	public void salvarCategoriasAjustes(FaturaArquivoDTO faturaArquivoDTO){
		categoriasAjustes.save(faturaArquivoDTO.getCategoriaAjuste());
	}
	public void salvarNotaFiscal(FaturaArquivoDTO faturaArquivoDTO){
		notasFiscais.save(faturaArquivoDTO.getNotaFiscal());
	}
	public void salvarTrailler(FaturaArquivoDTO faturaArquivoDTO){
		traillers.save(faturaArquivoDTO.getTrailler());
	}
	public Fatura salvarFaturaGerada(Fatura fatura){
		return faturas.saveAndFlush(fatura);
	}
	
	public Operadora retornarOperadoraCNPJ(String cnpj, int codOperadora){
		return operadoras.findByCnpjAndCodOperadora(cnpj,codOperadora);
	}
	
	public Cliente retornarCliente(String codCliente, String cnpj){
		return clientes.findByCodClienteAndCnpj(codCliente, cnpj);
	}
	public List<Cliente> retornarClientesIguais(String codCliente, String cnpj){
		return clientes.findByCodClienteAndCnpjContaining(codCliente, cnpj);
	}
	
	public List<Operadora> getOperadoras(){
		return operadoras.findAll();
	}
	
	public List<RelatorioGraficoOperadoraDTO> getFaturasGrafico(){
		List<Operadora> operadora = operadoras.findAll();
		List<RelatorioGraficoOperadoraDTO> lista = new ArrayList<>();
		for (Operadora op : operadora) {
			List<RelatorioGraficoClienteDTO> cliente = new ArrayList<>();
			RelatorioGraficoOperadoraDTO relatorio = new RelatorioGraficoOperadoraDTO();
			relatorio.setIdOperadora(op.getIdOperadora());
			relatorio.setNomeOperadora(op.getNome());
			relatorio.setUfOperadora(op.getUf());
			relatorio.setCnpjOperadora(op.getCnpj());
			for(Cliente cl :op.getClientes()){
				List<RelatorioGraficoFaturaDTO> fatura = new ArrayList<>();
				RelatorioGraficoClienteDTO relatorioCliente = new RelatorioGraficoClienteDTO();
				relatorioCliente.setIdCliente(cl.getIdCliente());
				relatorioCliente.setCnpjCliente(cl.getCnpj());
				relatorioCliente.setCodCliente(cl.getCodCliente());
				relatorioCliente.setNomeCliente(cl.getNome());
				for(Fatura fa : cl.getFaturas()){
					RelatorioGraficoFaturaDTO relatorioFatura = new RelatorioGraficoFaturaDTO();
					relatorioFatura.setIdFatura(fa.getIdFatura());
					relatorioFatura.setMesRef(fa.getMesRef());
					relatorioFatura.setDataVenc(fa.getDataVenc());
					
					while (relatorioFatura.getCnLocalidade() == null) {

						for (Chamadas cha : fa.getChamadases()) {
							if (relatorioFatura.getCnLocalidade() == null) {
								relatorioFatura.setCnLocalidade(cha.getNumRecursoChamada().trim().substring(0, 2));
							}
						}

						for (Resumo resumo : fa.getResumos()) {
							if (relatorioFatura.getCnLocalidade() == null) {
								relatorioFatura.setCnLocalidade(String.valueOf(resumo.getCnl()));
							}
						}

						for (Planos plano : fa.getPlanoses()) {
							if (relatorioFatura.getCnLocalidade() == null) {
								relatorioFatura
										.setCnLocalidade(String.valueOf(plano.getNumRecursoPlanos().substring(0, 2)));
							}
						}
					}
						
							for (Trailler tr : fa.getTraillers()){
								relatorioFatura.setValTotal(tr.getValTotal());
								fatura.add(relatorioFatura);
							};
				}
				relatorioCliente.setRelatorioGraficoFaturaDTO(fatura);
				cliente.add(relatorioCliente);
			}
			relatorio.setRelatorioGraficoClienteDTO(cliente);
			lista.add(relatorio);	
		}
		return lista;
	}
	
	public List<RelatorioGraficoOperadoraDTO> getFaturasGraficoMensal(){
		List<RelatorioGraficoOperadoraDTO> lista = getFaturasGrafico();
		return lista;
	}
	
	public List<Fatura> getFaturas() {
		return faturas.findAll();
	}
		
	public Fatura getFatura(Integer id) {
		return faturas.findByIdFatura(id);
	}
	
	public List<Fatura> getFaturasNaoGeradas() {
		return faturas.findFaturasGeradas(Boolean.FALSE);
	}
	
	public List<Fatura> getFaturasGeradas() {
		return faturas.findFaturasGeradas(Boolean.TRUE);
	}
}
