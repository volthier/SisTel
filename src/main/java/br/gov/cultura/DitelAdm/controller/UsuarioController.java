package br.gov.cultura.DitelAdm.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.gov.cultura.DitelAdm.model.LimiteAtesto;
import br.gov.cultura.DitelAdm.model.Usuario;
import br.gov.cultura.DitelAdm.service.CadastroUsuarioService;
import br.gov.cultura.DitelAdm.service.LimiteAtestoService;
import br.gov.cultura.DitelAdm.ws.SeiClient;
import br.gov.cultura.DitelAdm.wsdl.Unidade;

@Controller
@Transactional
@RequestMapping("/usuarios")
public class UsuarioController extends UrlController {

	private static final String CADASTRO_VIEW = "CadastroUsuario";

	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;
	@Autowired
	private LimiteAtestoService limiteAtestoService;
	@Autowired
	private SeiClient sei;

	@RequestMapping("/novo")
	public ModelAndView nova() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		List<LimiteAtesto> limiteAtesto = limiteAtestoService.getLimitesAtesto();
		mv.addObject("limiteAtesto", limiteAtesto);
		List<Unidade> uni = Arrays.asList(sei.listarUnidades());
		uni.sort((u1, u2) -> u1.getDescricao().compareTo(u2.getDescricao()));
		mv.addObject("listaUnidadeSei", uni);
		
		mv.addObject(new Usuario());
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Usuario usuario,BindingResult bindingResult, HttpServletRequest servletRequest, HttpServletResponse servletResponse, Errors errors, RedirectAttributes attributes) {

		if (errors.hasErrors()) {
			return CADASTRO_VIEW;
		}
		try {
			List<Unidade> uni = Arrays.asList(sei.listarUnidades());
			for (Unidade item : uni) {
				if (item.getIdUnidade().equals(usuario.getLotacaoIdUsuario())) {
					
					usuario.setDescricaoUsuario(item.getDescricao());
					usuario.setLotacaoUsuario(item.getSigla());
				}
			}
			cadastroUsuarioService.salvar(usuario);
			attributes.addFlashAttribute("mensagem", "Registro alterado com sucesso!");
			return "redirect:/usuarios/novo";
		} catch (IllegalArgumentException e) {
			errors.rejectValue("dataVencimento", null, e.getMessage());
			return CADASTRO_VIEW;
		}
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Integer id, RedirectAttributes attributes) {
		cadastroUsuarioService.excluir(id);
		attributes.addFlashAttribute("mensagem", "Cadastrado do usuário removido com sucesso!");
		return "redirect:/consultas/usuarios";
	}

	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") Usuario usuario) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		List<LimiteAtesto> limiteAtesto = limiteAtestoService.getLimitesAtesto();
		List<Unidade> uni = Arrays.asList(sei.listarUnidades());
		uni.sort((u1, u2) -> u1.getDescricao().compareTo(u2.getDescricao()));
		mv.addObject("limiteAtesto", limiteAtesto);
		mv.addObject("listaUnidadeSei", uni);
		mv.addObject(usuario);
		return mv;
	}
}