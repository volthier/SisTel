package br.gov.cultura.DitelAdm.controller;

import javax.transaction.Transactional;

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

import br.gov.cultura.DitelAdm.model.Contrato;
import br.gov.cultura.DitelAdm.repository.filtro.CadastroFiltroPesquisa;
import br.gov.cultura.DitelAdm.service.ContratoService;

@Controller
@Transactional
@RequestMapping("/contratos")
public class ContratoController {
	
	private static final String CADASTRO_VIEW = "CadastroContrato";
	
	@Autowired
	private ContratoService contratoService;
	
	@RequestMapping("/novo")
	public ModelAndView novo(@ModelAttribute("filtro")CadastroFiltroPesquisa filtro){
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Contrato());
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Contrato contrato, Errors errors, RedirectAttributes attributes){
		if(errors.hasErrors()){
	 		return CADASTRO_VIEW;
	 	}
		try{
			contratoService.salvar(contrato);
			attributes.addFlashAttribute("mensagem","Contrato cadastrado com sucesso!");
			return "redirect:/contratos/novo";		
		}catch(IllegalArgumentException e) {
			errors.rejectValue("dataVencimento", null, e.getMessage());
			return CADASTRO_VIEW;
		}
	}

	@RequestMapping(value="{id}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Integer id, RedirectAttributes attributes){
		contratoService.excluir(id);
		attributes.addFlashAttribute("mensagem","Cadastro do Contrato removido com sucesso!");
		return "redirect:/consulta";
		}	
	
	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") Contrato contrato){
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(contrato);
		return mv;
	}
	
}