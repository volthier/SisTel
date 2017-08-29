package br.gov.cultura.DitelAdm.controller;


import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

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

import br.gov.cultura.DitelAdm.model.Chip;
import br.gov.cultura.DitelAdm.service.CadastroChipService;


@Controller
@RequestMapping("/chips")
public class ChipController extends UrlController {
	
	private static final String CADASTRO_VIEW = "CadastroChip";
 	
	@Autowired
	private CadastroChipService cadastroChipService;
	
	@RequestMapping("/novo")
	public ModelAndView novo(){
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Chip());
		return mv;
		
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Chip cadastroChip, Errors errors, RedirectAttributes attributes){
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
	
	@RequestMapping(value="{idChip}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Integer idChip, RedirectAttributes attributes){
		cadastroChipService.excluir(idChip);
		attributes.addFlashAttribute("mensagem","Cadastrado do chip removido com sucesso!");
		return "redirect:/consultas/chips";
		}	
	
	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") Chip chips){
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(chips);
				return mv;
	}
	
	//DropDownMenu Atributos
	@ModelAttribute("tipoChipMap")
	public Map<String,String> populateTipoChipMap() throws MalformedURLException, IOException 
	{

	    Map<String,String> tipoChipMap = new HashMap<String,String> ();
	    tipoChipMap.put("SimCard","SimCard");
	    tipoChipMap.put("MicroSimCard","MicroSimCard");
	    tipoChipMap.put("NanoSimcard","NanoSimCard");
	    tipoChipMap.put("FlexSimCard","FlexSimCard");
	    return tipoChipMap;
	}
}