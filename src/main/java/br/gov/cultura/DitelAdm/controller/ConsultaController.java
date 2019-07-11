package br.gov.cultura.DitelAdm.controller;

import br.gov.cultura.DitelAdm.model.*;
import br.gov.cultura.DitelAdm.repository.filtro.FiltroPesquisa;
import br.gov.cultura.DitelAdm.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;

	@Autowired
	private CadastroDispositivoService cadastroDispositivoService;

	@Autowired
	private CadastroChipService cadastroChipService;

	@Autowired
	private CadastroLinhaService cadastroLinhaService;

	@Autowired
	private CadastroCategoriaService CadastroCategoriaService;

	@Autowired
	private LimiteAtestoService limiteAtestoService;

	@RequestMapping("/dispositivos")
	public ModelAndView consultarDispositivo(@ModelAttribute("filtro") FiltroPesquisa filtro) {
		List<Dispositivo> todosDispositivos = cadastroDispositivoService.filtroPesquisa(filtro);
		ModelAndView mv = new ModelAndView("PesquisaDispositivo");
		mv.addObject("dispositivos", todosDispositivos);
		return mv;
	}

	@RequestMapping("/usuarios")
	public ModelAndView consultarUsuario(@ModelAttribute("filtro") FiltroPesquisa filtro) {
		List<Usuario> todosUsuarios = cadastroUsuarioService.filtroPesquisa(filtro);
		ModelAndView mv = new ModelAndView("PesquisaUsuario");
		mv.addObject("usuarios", todosUsuarios);
		return mv;
	}

	@RequestMapping("/chips")
	public ModelAndView consultarChip(@ModelAttribute("filtro") FiltroPesquisa filtro) {
		List<Chip> todosChips = cadastroChipService.filtroPesquisa(filtro);
		ModelAndView mv = new ModelAndView("PesquisaChip");
		mv.addObject("chips", todosChips);
		return mv;
	}

	@RequestMapping("/linhas")
	public ModelAndView consultarLinha(@ModelAttribute("filtro") FiltroPesquisa filtro) {
		List<Linha> todasLinhas = cadastroLinhaService.filtroPesquisa(filtro);
		ModelAndView mv = new ModelAndView("PesquisaLinha");
		mv.addObject("linhas", todasLinhas);
		return mv;
	}

	@RequestMapping("/categorias")
	public ModelAndView consultarCategoria(@ModelAttribute("filtro") FiltroPesquisa filtro) {
		ModelAndView mv = new ModelAndView("PesquisaCategoria");
		List<Categoria> todasCategorias = CadastroCategoriaService.getIdCategoria();
		mv.addObject("categorias", todasCategorias);
		return mv;
	}

	@RequestMapping("/limites-atesto")
	public ModelAndView consultarLimiteAtesto(@ModelAttribute("filtro") FiltroPesquisa filtro) {
		ModelAndView mv = new ModelAndView("PesquisaLimiteAtesto");
		List<LimiteAtesto> todosOsLimites = limiteAtestoService.getLimitesAtesto();
		mv.addObject("limites", todosOsLimites);
		return mv;
	}

	@RequestMapping("/contratos")
	public ModelAndView consultarContrato(@ModelAttribute("filtro") FiltroPesquisa filtro) {
		ModelAndView mv = new ModelAndView("PesquisaContrato");
		return mv;
	}

}
