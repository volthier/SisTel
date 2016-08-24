package br.gov.cultura.DitelAdm.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.gov.cultura.DitelAdm.Service.CadastroUsuarioService;
import br.gov.cultura.DitelAdm.model.Usuario;


@Controller
@RequestMapping("/usuarios")
public class UsuarioController extends UrlController {
	
	private static final String CADASTRO_VIEW = "CadastroUsuario";
 	
	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;
	
	@RequestMapping("/novo")
	public ModelAndView nova(){
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Usuario());
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Usuario usuario, Errors errors, RedirectAttributes attributes){
				
		if(errors.hasErrors()){
	 		return CADASTRO_VIEW;
	 	}
		try {
			cadastroUsuarioService.salvar(usuario);
			attributes.addFlashAttribute("mensagem","Pessoa cadastrada com sucesso!");
			return "redirect:/usuarios/novo";		
		} catch (IllegalArgumentException e) {
			errors.rejectValue("dataVencimento", null, e.getMessage());
			return CADASTRO_VIEW;
 		}
		
		
	}
	@RequestMapping(value="{id}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long id, RedirectAttributes attributes){
		cadastroUsuarioService.excluir(id);
		attributes.addFlashAttribute("mensagem","Cadastrado removido com sucesso!");
		return "redirect:/inicio";
		}	
	
	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") Usuario usuario){
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(usuario);
				return mv;
	}
	

}