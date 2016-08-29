package br.gov.cultura.DitelAdm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.cultura.DitelAdm.model.dtos.FaturaArquivoDTO;
import br.gov.cultura.DitelAdm.model.faturasV3.Planos;
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
		operadoras.save(faturaArquivoDTO.getOperadora());
	}

	public void salvarCliente(FaturaArquivoDTO faturaArquivoDTO) {
		clientes.save(faturaArquivoDTO.getCliente());
	}

	public void salvarFatura(FaturaArquivoDTO faturaArquivoDTO) {
		faturas.save(faturaArquivoDTO.getFatura());
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
}
