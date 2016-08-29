package br.gov.cultura.DitelAdm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/*import br.gov.cultura.DitelAdm.model.Alocacao;*/
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
@RequestMapping("/alocacoes")
public class AlocacaoController {

	private final String CADASTRO_VIEW = "AlocacaoDisponibilizar";

/*	@Autowired
	private AlocacaoService alocacaoService;*/
	@Autowired
	private CadastroDispositivoService cadastroDispositivoService;
	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;
	@Autowired
	private CadastroChipService cadastroChipService;
	@Autowired
	private CadastroLinhaService cadastroLinhaService;

	@RequestMapping("/disponibilizar")
	public @ResponseBody ModelAndView novo(@ModelAttribute("filtro") CadastroFiltroPesquisa filtro) {
		List<Dispositivo> todosDispositivos = cadastroDispositivoService.filtrar(filtro);
		ModelAndView mv = new ModelAndView("AlocacaoDisponibilizar");
		/*mv.addObject(new Alocacao());*/
		
		mv.addObject("dispositivos", todosDispositivos);
		List<Usuario> todosUsuarios = cadastroUsuarioService.filtrar(filtro);
		mv.addObject("usuarios", todosUsuarios);
		List<Chip> todosChips = cadastroChipService.filtrar(filtro);
		mv.addObject("chips", todosChips);
		List<Linha> todasLinhas = cadastroLinhaService.filtrar(filtro);
		mv.addObject("linhas", todasLinhas);

		return mv;
	}

/*	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Alocacao alocacao, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return CADASTRO_VIEW;
		}
		try {

			AlocacaoService.salvar(alocacao);
			attributes.addFlashAttribute("mensagem", "Registrado vinculo!");
			return "redirect:/alocacao/disponibilizar";
		} catch (IllegalArgumentException e) {

			errors.rejectValue("dataVencimento", null, e.getMessage());
			return CADASTRO_VIEW;
		}
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long id, RedirectAttributes attributes) {
		alocacaoService.excluir(id);
		attributes.addFlashAttribute("mensagem", "Fornecimento CANCELADO com sucesso!");
		return "redirect:/inicio";
	}*/
}