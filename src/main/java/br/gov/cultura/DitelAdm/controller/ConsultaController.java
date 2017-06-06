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
@RequestMapping("/consulta")
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

	@RequestMapping
	public ModelAndView pesquisar(@ModelAttribute("filtro") CadastroFiltroPesquisa filtro) {
		List<Dispositivo> todosDispositivos = cadastroDispositivoService.getIdDispositivo();
		ModelAndView mv = new ModelAndView("PesquisaDispositivos");
		mv.addObject("dispositivos", todosDispositivos);
		List<Usuario> todosUsuarios = cadastroUsuarioService.getIdUsuario();
		mv.addObject("usuarios", todosUsuarios);
		List<Chip> todosChips = cadastroChipService.getIdChip();
		mv.addObject("chips", todosChips);
		List<Linha> todasLinhas = cadastroLinhaService.getIdLinha();
		mv.addObject("linhas", todasLinhas);
		List<Categoria> todasCategorias = CadastroCategoriaService.getIdCategoria();
		mv.addObject("categorias",todasCategorias);
		List<LimiteAtesto> todosOsLimites = limiteAtestoService.getLimitesAtesto();
		mv.addObject("limites",todosOsLimites);
		return mv;
	}	
	
}
