package br.gov.cultura.DitelAdm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.gov.cultura.DitelAdm.email.Mailer;
import br.gov.cultura.DitelAdm.model.dtos.AlocacaoLinhaDispositivoUsuarioDTO;
import br.gov.cultura.DitelAdm.service.PendenciaService;

@Controller
@RequestMapping
public class PendenciaController {
	
	private static final String CADASTRO_VIEW = "Pendencia";
	
	@Autowired
	private PendenciaService pendenciaService;
	
	@Autowired
	private Mailer mailer;
	
	@RequestMapping("/pendencia")
	public ModelAndView pendencia() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		List<AlocacaoLinhaDispositivoUsuarioDTO> lista =  pendenciaService.listaPendencia();
		mv.addObject("pendencia",lista);		
		return mv;
	}
		
	@RequestMapping(value="/email/{id}", method = RequestMethod.POST)
	public String enviarEmailProcesso(@PathVariable("id") @RequestBody String id) {
		mailer.enviar(Integer.parseInt(id));
	    return "redirect:/pendencia";
	}
	
}