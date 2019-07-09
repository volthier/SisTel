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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.gov.cultura.DitelAdm.model.LimiteAtesto;
import br.gov.cultura.DitelAdm.repository.filtro.FiltroPesquisa;
import br.gov.cultura.DitelAdm.service.LimiteAtestoService;
import br.gov.cultura.DitelAdm.service.ldap.ConsultaLdapService;

@Controller
@RequestMapping("/limites-atesto")
public class LimiteAtestoController {

	private static final String CADASTRO_VIEW = "CadastroLimiteAtesto";

	@Autowired
	private LimiteAtestoService limiteAtestoService;

	@Autowired
	private ConsultaLdapService ldap;

	@RequestMapping("/novo")
	public ModelAndView novo(@ModelAttribute("filtro") FiltroPesquisa filtro) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		List<LimiteAtesto> limiteAtesto = limiteAtestoService.getLimitesAtesto();
		mv.addObject("limiteAtesto", limiteAtesto);
		mv.addObject(new LimiteAtesto());
		ldap.usuarioInfos(mv);
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated LimiteAtesto limiteAtesto, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			attributes.addFlashAttribute("messageErro","Erro no cadastrado!");
			return "redirect:/limites-atesto/novo";
		}
		else {
			limiteAtestoService.salvar(limiteAtesto);
			attributes.addFlashAttribute("mensagem", "Limite atesto cadastrado com sucesso!");
			return "redirect:/limites-atesto/novo";
		}
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Integer id, RedirectAttributes attributes) {
		limiteAtestoService.excluir(id);
		attributes.addFlashAttribute("mensagem", "Cadastro do limite atesto removido com sucesso!");
		return "redirect:/consultas/limites-atesto";
	}

	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") LimiteAtesto limiteAtesto) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(limiteAtesto);
		ldap.usuarioInfos(mv);
		return mv;
	}

}