package br.gov.cultura.DitelAdm.controller;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import br.gov.cultura.DitelAdm.Service.CadastroChipService;
import br.gov.cultura.DitelAdm.Service.CadastroDispositivoService;
import br.gov.cultura.DitelAdm.Service.CadastroLinhaService;
import br.gov.cultura.DitelAdm.Service.CadastroPessoaService;

import br.gov.cultura.DitelAdm.model.CadastroChip;
import br.gov.cultura.DitelAdm.model.CadastroDispositivo;
import br.gov.cultura.DitelAdm.model.CadastroLinha;
import br.gov.cultura.DitelAdm.model.CadastroPessoa;

import br.gov.cultura.DitelAdm.model.VinculoCadastroChipTipo;
import br.gov.cultura.DitelAdm.model.VinculoDispositivoTipo;
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
	
	@Autowired
	private CadastroChipService cadastroChipService;
	
	@Autowired
	private CadastroLinhaService cadastroLinhaService;
		
	@RequestMapping
	public ModelAndView pesquisar(@ModelAttribute("filtro")CadastroFiltroPesquisa filtro){
		List<CadastroDispositivo> todosDispositivos = cadastroDispositivoService.filtrar(filtro); 
		ModelAndView mv = new ModelAndView("TelaInicio");
		String json = new Gson().toJson(mv); 
		mv.addObject("dispositivos", todosDispositivos);
		mv.addObject("sugestion", json);
		System.out.println(json);
		List<CadastroPessoa> todasPessoas = cadastroPessoaService.filtrar(filtro);
		json = new Gson().toJson(todasPessoas);
		mv.addObject("pessoas", todasPessoas);
		mv.addObject("sugestion", json);
		System.out.println(json);
		List<CadastroChip> todosChips = cadastroChipService.filtrar(filtro);
		json = new Gson().toJson(todosChips);
		mv.addObject("chips", todosChips);
		mv.addObject("sugestion", json);
		System.out.println(json);
		List<CadastroLinha> todasLinhas = cadastroLinhaService.filtrar(filtro);
		json = new Gson().toJson(todasLinhas);
		mv.addObject("linhas", todasLinhas);
		mv.addObject("sugestion", json);
		System.out.println(json);		
		
		return mv;
	}	

	@ModelAttribute("todosStatusCadastro")
	public List<StatusCadastro> todosStatusCadastro(){
		return Arrays.asList(StatusCadastro.values());
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
	public List<VinculoCadastroChipTipo> todosCadastroChipTipo(){
		return Arrays.asList(VinculoCadastroChipTipo.values());
	}
	
	@ModelAttribute("todosDispositivoTipo")
	public List<VinculoDispositivoTipo> todosDispositivoTipo(){
		return Arrays.asList(VinculoDispositivoTipo.values());
	}
	
}
