package br.gov.cultura.DitelAdm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.gov.cultura.DitelAdm.model.AlocacaoLinhaDispositivo;
import br.gov.cultura.DitelAdm.model.AlocacaoUsuarioLinha;
import br.gov.cultura.DitelAdm.service.AlocacaoService;

@Controller
@RequestMapping
public class PendenciaController {
	
	@Autowired
	private AlocacaoService alocacaoService;
		
	@RequestMapping("/pendencia")
	public ModelAndView pendencia() {
		ModelAndView mv = new ModelAndView("Pendencia");
/*		List<AlocacaoLinhaDispositivo> linhaDispo = alocacaoService.getIdAlocacaoLinhaDispositivo();
		List<AlocacaoUsuarioLinha> linhaUsuario = alocacaoService.getIdAlocacaoUsuarioLinha();
		
		mv.addObject("pendenciaLinhadispositivo",linhaDispo);
		mv.addObject("pendenciaUsuarioLinha",linhaUsuario);*/
		
/*		List<AlocacaoUsuarioLinha> linhaDispo =  alocacaoService.getAlocacaoUsuarioLinhaList();
		mv.addObject("pendencia",linhaDispo);*/
		return mv;
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Integer id, RedirectAttributes attributes) {
		alocacaoService.excluir(id);
		attributes.addFlashAttribute("mensagem", "Fornecimento CANCELADO com sucesso!");
		return "redirect:/inicio";
	}

}
