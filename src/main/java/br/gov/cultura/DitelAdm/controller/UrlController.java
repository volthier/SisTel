package br.gov.cultura.DitelAdm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import br.gov.cultura.DitelAdm.model.Chip;
import br.gov.cultura.DitelAdm.model.Dispositivo;
import br.gov.cultura.DitelAdm.model.Linha;
import br.gov.cultura.DitelAdm.model.Usuario;
import br.gov.cultura.DitelAdm.repository.filtro.CadastroFiltroPesquisa;
import br.gov.cultura.DitelAdm.service.CadastroChipService;
import br.gov.cultura.DitelAdm.service.CadastroDispositivoService;
import br.gov.cultura.DitelAdm.service.CadastroLinhaService;
import br.gov.cultura.DitelAdm.service.CadastroUsuarioService;


@Controller
@RequestMapping("/inicio")
public class UrlController {
	
	@Autowired
	private CadastroDispositivoService cadastroDispositivoService;
	
	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;
	
	@Autowired
	private CadastroChipService cadastroChipService;
	
	@Autowired
	private CadastroLinhaService cadastroLinhaService;
		
	@RequestMapping
	public ModelAndView pesquisar(@ModelAttribute("filtro")CadastroFiltroPesquisa filtro){
		List<Dispositivo> todosDispositivos = cadastroDispositivoService.filtrar(filtro); 
		ModelAndView mv = new ModelAndView("TelaInicio");
		String json = new Gson().toJson(mv); 
		json = new Gson().toJson(todosDispositivos);
		mv.addObject("dispositivos", todosDispositivos);
		mv.addObject("sugestion", json);
		System.out.println(json);
		List<Usuario> todosUsuarios = cadastroUsuarioService.filtrar(filtro);
		json = new Gson().toJson(todosUsuarios);
		mv.addObject("usuarios", todosUsuarios);
		mv.addObject("sugestion", json);
		System.out.println(json);
		List<Chip> todosChips = cadastroChipService.filtrar(filtro);
		json = new Gson().toJson(todosChips);
		mv.addObject("chips", todosChips);
		mv.addObject("sugestion", json);
		System.out.println(json);
		List<Linha> todasLinhas = cadastroLinhaService.filtrar(filtro);
		json = new Gson().toJson(todasLinhas);
		mv.addObject("linhas", todasLinhas);
		mv.addObject("sugestion", json);
		System.out.println(json);		
		DasLimitesBase dasLimitesBase = new DasLimitesBase();
		
		return mv;
	}	
	
}
