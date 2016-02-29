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
import br.gov.cultura.DitelAdm.Service.CadastroPessoaService;
import br.gov.cultura.DitelAdm.model.CadastroPessoa;

@Controller
@RequestMapping("/pessoas")
public class PessoaController extends UrlController {
	
	private static final String CADASTRO_VIEW = "CadastroPessoa";
 	
	@Autowired
	private CadastroPessoaService cadastroPessoaService;
	
	@RequestMapping("/nova")
	public ModelAndView nova(){
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new CadastroPessoa());
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated CadastroPessoa cadastropessoa, Errors errors, RedirectAttributes attributes){
				
		if(errors.hasErrors()){
	 		return CADASTRO_VIEW;
	 	}
		try {
			cadastroPessoaService.salvar(cadastropessoa);
			attributes.addFlashAttribute("mensagem","Pessoa cadastrada com sucesso!");
			return "redirect:/pessoas/nova";		
		} catch (IllegalArgumentException e) {
			errors.rejectValue("dataVencimento", null, e.getMessage());
			return CADASTRO_VIEW;
 		}
		
		
	}
	@RequestMapping(value="{id}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long id, RedirectAttributes attributes){
		cadastroPessoaService.excluir(id);
		attributes.addFlashAttribute("mensagem","Cadastrado removido com sucesso!");
		return "redirect:/inicio";
		}	
	
	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") CadastroPessoa Pessoas){
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(Pessoas);
				return mv;
	}
	

}