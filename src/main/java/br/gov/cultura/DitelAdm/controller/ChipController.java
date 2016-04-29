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
import br.gov.cultura.DitelAdm.Service.CadastroChipService;
import br.gov.cultura.DitelAdm.model.CadastroChip;

@Controller
@RequestMapping("/chips")
public class ChipController extends UrlController {
	
	private static final String CADASTRO_VIEW = "CadastroChip";
 	
	@Autowired
	private CadastroChipService cadastroChipService;
	
	@RequestMapping("/novo")
	public ModelAndView novo(){
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new CadastroChip());
		return mv;
		
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated CadastroChip cadastroChip, Errors errors, RedirectAttributes attributes){
		//TODO: Salva no banco de dados
		
		if(errors.hasErrors()){
	 		return CADASTRO_VIEW;
	 	}
		try {
			cadastroChipService.salvar(cadastroChip);
			attributes.addFlashAttribute("mensagem","Dispositivo cadastrado com sucesso!");
			return "redirect:/chips/novo";		
		} catch (IllegalArgumentException e) {
			errors.rejectValue("dataVencimento", null, e.getMessage());
			return CADASTRO_VIEW;
 		}
		
	}
	
	@RequestMapping(value="{id}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long id, RedirectAttributes attributes){
		cadastroChipService.excluir(id);
		attributes.addFlashAttribute("mensagem","Cadastrado removido com sucesso!");
		return "redirect:/inicio";
		}	
	
	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") CadastroChip chips){
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(chips);
				return mv;
	}
}