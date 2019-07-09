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

import br.gov.cultura.DitelAdm.model.Categoria;
import br.gov.cultura.DitelAdm.repository.filtro.FiltroPesquisa;
import br.gov.cultura.DitelAdm.service.CadastroCategoriaService;
import br.gov.cultura.DitelAdm.service.ldap.ConsultaLdapService;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {
	
	private static final String CADASTRO_VIEW = "CadastroCategoria";
 	
	@Autowired
	private CadastroCategoriaService cadastroCategoriaService;
	
	@Autowired
	private ConsultaLdapService ldap;
	
	@RequestMapping("/nova")
	public ModelAndView novo(@ModelAttribute("filtro")FiltroPesquisa filtro){
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		List<Categoria> categoria = cadastroCategoriaService.getIdCategoria();
		mv.addObject("categorias", categoria);
		mv.addObject(new Categoria());
		ldap.usuarioInfos(mv);
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Categoria categoria, Errors errors, RedirectAttributes attributes){

		if(errors.hasErrors()){
			attributes.addFlashAttribute("messageErro","Erro no cadastrado!");
			return "redirect:/categorias/nova";	
	 	}else{
			cadastroCategoriaService.salvar(categoria);
			attributes.addFlashAttribute("mensagem","Categoria cadastrada com sucesso!");
			return "redirect:/categorias/nova";	
	 	}
		
	}
	@RequestMapping(value="{id}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Integer id, RedirectAttributes attributes){
		cadastroCategoriaService.excluir(id);
		attributes.addFlashAttribute("mensagem","Categoria removida com sucesso!");
		return "redirect:/consultas/categorias";
		}	
	
	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") Categoria categoria){
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(categoria);
		ldap.usuarioInfos(mv);
				return mv;
	}
	
}
