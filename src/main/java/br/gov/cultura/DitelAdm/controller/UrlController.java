package br.gov.cultura.DitelAdm.controller;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import br.gov.cultura.DitelAdm.Service.CadastroDispositivoService;
import br.gov.cultura.DitelAdm.Service.CadastroPessoaService;
import br.gov.cultura.DitelAdm.model.CadastroChipTipo;
import br.gov.cultura.DitelAdm.model.CadastroDispositivo;
import br.gov.cultura.DitelAdm.model.CadastroPessoa;
import br.gov.cultura.DitelAdm.model.DispositivoTipo;
import br.gov.cultura.DitelAdm.model.StatusCadastro;
import br.gov.cultura.DitelAdm.model.VinculoCadastroPessoa;
import br.gov.cultura.DitelAdm.model.VinculoCadastroPessoaCargo;
import br.gov.cultura.DitelAdm.model.VinculoCadastroPessoaDAS;
import br.gov.cultura.DitelAdm.repository.filtro.CadastroFiltroPesquisa;

@Controller
@RequestMapping("/inicio")
public class UrlController {
	
	@Autowired
	private CadastroDispositivoService cadastroDispositivoService;
	
	@Autowired
	private CadastroPessoaService cadastroPessoaService;
		
	@RequestMapping
	public ModelAndView pesquisar(@ModelAttribute("filtro")CadastroFiltroPesquisa filtro){
		List<CadastroDispositivo> todosDispositivos = cadastroDispositivoService.filtrar(filtro); 
		ModelAndView mv = new ModelAndView("PesquisaDispositivos");
		mv.addObject("dispositivos", todosDispositivos);
		List<CadastroPessoa> todasPessoas = cadastroPessoaService.filtrar(filtro);
		mv.addObject("pessoas", todasPessoas);
		return mv;
	}		
	

	
	
	@ModelAttribute("todosStatusCadastro")
	public List<StatusCadastro> todosStatusCadastro(){
		return Arrays.asList(StatusCadastro.values());
	}
	
	@ModelAttribute("todosDispositivoTipo")
	public List<DispositivoTipo> todosDispositivoTipo(){
		return Arrays.asList(DispositivoTipo.values());
	}
	
	@ModelAttribute("todosVinculoCadastroPessoa")
	public List<VinculoCadastroPessoa> todosVinculoCadastroPessoa(){
		return Arrays.asList(VinculoCadastroPessoa.values());
	}

	@ModelAttribute("todosVinculoCadastroPessoaDAS")
	public List<VinculoCadastroPessoaDAS> todosVinculoCadastroPessoaDAS(){
		return Arrays.asList(VinculoCadastroPessoaDAS.values());
	}
	@ModelAttribute("todosVinculoCadastroPessoaCargo")
	public List<VinculoCadastroPessoaCargo> todosVinculoCadastroPessoaCargo(){
		return Arrays.asList(VinculoCadastroPessoaCargo.values());
	}
	@ModelAttribute("todosCadastroChipTipo")
	public List<CadastroChipTipo> todosCadastroChipTipo(){
		return Arrays.asList(CadastroChipTipo.values());
	}
	
}
