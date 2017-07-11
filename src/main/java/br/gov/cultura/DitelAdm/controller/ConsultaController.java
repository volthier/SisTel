package br.gov.cultura.DitelAdm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.gov.cultura.DitelAdm.model.Categoria;
import br.gov.cultura.DitelAdm.model.Chip;
import br.gov.cultura.DitelAdm.model.Dispositivo;
import br.gov.cultura.DitelAdm.model.LimiteAtesto;
import br.gov.cultura.DitelAdm.model.Linha;
import br.gov.cultura.DitelAdm.model.Usuario;
import br.gov.cultura.DitelAdm.repository.filtro.CadastroFiltroPesquisa;
import br.gov.cultura.DitelAdm.service.CadastroCategoriaService;
import br.gov.cultura.DitelAdm.service.CadastroChipService;
import br.gov.cultura.DitelAdm.service.CadastroDispositivoService;
import br.gov.cultura.DitelAdm.service.CadastroLinhaService;
import br.gov.cultura.DitelAdm.service.CadastroUsuarioService;
import br.gov.cultura.DitelAdm.service.LimiteAtestoService;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

	@Autowired
	private CadastroDispositivoService cadastroDispositivoService;

	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;

	@Autowired
	private CadastroChipService cadastroChipService;

	@Autowired
	private CadastroLinhaService cadastroLinhaService;

	@Autowired
	private CadastroCategoriaService CadastroCategoriaService;

	@Autowired
	private LimiteAtestoService limiteAtestoService;

	@RequestMapping("/dispositivos")
	public ModelAndView consultarDispositivo(@ModelAttribute("filtro") CadastroFiltroPesquisa filtro) {
		ModelAndView mv = new ModelAndView("PesquisaDispositivos");
		List<Dispositivo> todosDispositivos = cadastroDispositivoService.getIdDispositivo();
		mv.addObject("dispositivos", todosDispositivos);
		return mv;
		}
	
	@RequestMapping("/usuarios")
	public ModelAndView consultarUsuario(@ModelAttribute("filtro") CadastroFiltroPesquisa filtro) {
		ModelAndView mv = new ModelAndView("PesquisaDispositivos");
		List<Usuario> todosUsuarios = cadastroUsuarioService.getIdUsuario();
		mv.addObject("usuarios", todosUsuarios);
		return mv;
		}
	
	@RequestMapping("/chips")
	public ModelAndView consultarChip(@ModelAttribute("filtro") CadastroFiltroPesquisa filtro) {
		ModelAndView mv = new ModelAndView("PesquisaDispositivos");
		List<Chip> todosChips = cadastroChipService.getIdChip();
		mv.addObject("chips", todosChips);
		return mv;
		}
	
	@RequestMapping("/linhas")
	public ModelAndView consultarLinha(@ModelAttribute("filtro") CadastroFiltroPesquisa filtro) {
		ModelAndView mv = new ModelAndView("PesquisaDispositivos");
		List<Linha> todasLinhas = cadastroLinhaService.getIdLinha();
		mv.addObject("linhas", todasLinhas);
		return mv;
		}
	
	@RequestMapping("/categorias")
	public ModelAndView consultarCategoria(@ModelAttribute("filtro") CadastroFiltroPesquisa filtro) {
		ModelAndView mv = new ModelAndView("PesquisaDispositivos");
		List<Categoria> todasCategorias = CadastroCategoriaService.getIdCategoria();
		mv.addObject("categorias",todasCategorias);
		return mv;
		}
	
	@RequestMapping("/limites-atesto")
	public ModelAndView consultarLimiteAtesto(@ModelAttribute("filtro") CadastroFiltroPesquisa filtro) {
		ModelAndView mv = new ModelAndView("PesquisaDispositivos");
		List<LimiteAtesto> todosOsLimites = limiteAtestoService.getLimitesAtesto();
		mv.addObject("limites", todosOsLimites);
		return mv;
	}

}
