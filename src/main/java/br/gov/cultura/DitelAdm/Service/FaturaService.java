package br.gov.cultura.DitelAdm.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.cultura.DitelAdm.model.dtos.FaturaArquivoDTO;
import br.gov.cultura.DitelAdm.repository.CategoriasChamadas;
import br.gov.cultura.DitelAdm.repository.CategoriasDescontos;
import br.gov.cultura.DitelAdm.repository.CategoriasPlanos;
import br.gov.cultura.DitelAdm.repository.CategoriasServicos;
import br.gov.cultura.DitelAdm.repository.Chamadasas;
import br.gov.cultura.DitelAdm.repository.Clientes;
import br.gov.cultura.DitelAdm.repository.Descontosas;
import br.gov.cultura.DitelAdm.repository.Enderecosas;
import br.gov.cultura.DitelAdm.repository.Faturas;
import br.gov.cultura.DitelAdm.repository.Operadoras;
import br.gov.cultura.DitelAdm.repository.Planosas;
import br.gov.cultura.DitelAdm.repository.Resumos;
import br.gov.cultura.DitelAdm.repository.Servicosas;

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

	public void salvarOp(FaturaArquivoDTO faturaArquivoDTO) {
		operadoras.save(faturaArquivoDTO.getOperadora());
	}

	public void salvarCliente(FaturaArquivoDTO faturaArquivoDTO) {
		clientes.save(faturaArquivoDTO.getCliente());
	}

	public void salvarFat(FaturaArquivoDTO faturaArquivoDTO) {
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
		planosas.save(faturaArquivoDTO.getPlanos());
	}

}
