package br.gov.cultura.DitelAdm.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import br.gov.cultura.DitelAdm.Service.CadastroChipService;
import br.gov.cultura.DitelAdm.Service.CadastroDispositivoService;
import br.gov.cultura.DitelAdm.Service.CadastroLinhaService;
import br.gov.cultura.DitelAdm.Service.CadastroUsuarioService;
import br.gov.cultura.DitelAdm.model.Chip;
import br.gov.cultura.DitelAdm.model.Dispositivo;
import br.gov.cultura.DitelAdm.model.Linha;
import br.gov.cultura.DitelAdm.model.Usuario;
import br.gov.cultura.DitelAdm.repository.filtro.CadastroFiltroPesquisa;

@Controller
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

	@RequestMapping
	public ModelAndView pesquisar(@ModelAttribute("filtro") CadastroFiltroPesquisa filtro) {
		List<Dispositivo> todosDispositivos = cadastroDispositivoService.filtrar(filtro);
		ModelAndView mv = new ModelAndView("PesquisaDispositivos");
		mv.addObject("dispositivos", todosDispositivos);
		List<Usuario> todosUsuarios = cadastroUsuarioService.filtrar(filtro);
		mv.addObject("usuarios", todosUsuarios);
		List<Chip> todosChips = cadastroChipService.filtrar(filtro);
		mv.addObject("chips", todosChips);
		List<Linha> todasLinhas = cadastroLinhaService.filtrar(filtro);
		mv.addObject("linhas", todasLinhas);
		return mv;
	}

	/*@ModelAttribute("todosStatusCadastro")
	public List<StatusCadastro> todosStatusCadastro() {
		return Arrays.asList(StatusCadastro.values());
	}

	@ModelAttribute("todosVinculoCadastroPessoa")
	public List<VinculoCadastroPessoa> todosVinculoCadastroPessoa() {
		return Arrays.asList(VinculoCadastroPessoa.values());
	}

	@ModelAttribute("todosVinculoCadastroPessoaDAS")
	public List<VinculoCadastroPessoaDAS> todosVinculoCadastroPessoaDAS() {
		return Arrays.asList(VinculoCadastroPessoaDAS.values());
	}

	@ModelAttribute("todosVinculoCadastroPessoaCargo")
	public List<VinculoCadastroPessoaCargo> todosVinculoCadastroPessoaCargo() {
		return Arrays.asList(VinculoCadastroPessoaCargo.values());
	}

	@ModelAttribute("todosDispositivoTipo")
	public List<VinculoDispositivoTipo> todosDispositivoTipo() {
		return Arrays.asList(VinculoDispositivoTipo.values());
	}*/

}
