package br.gov.cultura.DitelAdm.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.gov.cultura.DitelAdm.model.dtos.AlocacaoLinhaDispositivoUsuarioDTO;
import br.gov.cultura.DitelAdm.service.AlocacaoService;
import br.gov.cultura.DitelAdm.service.CadastroDispositivoService;
import br.gov.cultura.DitelAdm.service.CadastroUsuarioService;

@Controller
@RequestMapping
public class PendenciaController {
	
	@Autowired
	private AlocacaoService alocacaoService;
	
	@Autowired
	private CadastroDispositivoService cadastroDispositivoService;
	
	@Autowired
	private CadastroUsuarioService cadastroUsuarioService; 
	
		
	@RequestMapping("/pendencia")
	public ModelAndView pendencia() {
		ModelAndView mv = new ModelAndView("Pendencia");
		
	    List<Object[]> linhaDispo =  alocacaoService.getAlocacaoUsuarioLinhaList();
	    
	    List<AlocacaoLinhaDispositivoUsuarioDTO> lista = new ArrayList<AlocacaoLinhaDispositivoUsuarioDTO>();
	    for (Object[] item : linhaDispo) {
	    	AlocacaoLinhaDispositivoUsuarioDTO alocacao = 
	    			new AlocacaoLinhaDispositivoUsuarioDTO(item[0],item[1],item[2],item[3],item[4],item[5]);
	    	lista.add(alocacao);
	    }
	    
	    System.out.println(lista);
	    
/*		List<Dispositivo> dispositivo =  cadastroDispositivoService.getIdDispositivo();
		List<Usuario> usuario = cadastroUsuarioService.getIdUsuario();*/
		

		mv.addObject("pendencia",lista);
		
		
		return mv;
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Integer id, RedirectAttributes attributes) {
		alocacaoService.excluir(id);
		attributes.addFlashAttribute("mensagem", "Fornecimento CANCELADO com sucesso!");
		return "redirect:/inicio";
	}

}
