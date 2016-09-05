package br.gov.cultura.DitelAdm.controller;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
@RequestMapping("/menu-usuario")
public class UsuarioMenuDispositivosController {
	
	@Autowired
	private CadastroDispositivoService cadastroDispositivoService;
	
	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;
	
	@Autowired
	private CadastroChipService cadastroChipService;
	
	@Autowired
	private CadastroLinhaService cadastroLinhaService;
		
	@RequestMapping("/dispositivo")
	public ModelAndView pesquisar(@ModelAttribute("filtro")CadastroFiltroPesquisa filtro){
		List<Dispositivo> todosDispositivos = cadastroDispositivoService.getIdDispositivo();
		ModelAndView mv = new ModelAndView("UsuarioMenuDispositivo");
		 
		
		mv.addObject("dispositivos", todosDispositivos);
		
		
		List<Usuario> todosUsuarios = cadastroUsuarioService.getIdUsuario();
		
		mv.addObject("usuarios", todosUsuarios);
		
		
		List<Chip> todosChips = cadastroChipService.getIdChip();
		
		mv.addObject("chips", todosChips);
		
		
		List<Linha> todasLinhas = cadastroLinhaService.getIdLinha();
		
		mv.addObject("linhas", todasLinhas);
		
				
		
		
		return mv;
	}	
	
}