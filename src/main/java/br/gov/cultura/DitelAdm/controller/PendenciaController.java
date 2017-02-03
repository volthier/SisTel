package br.gov.cultura.DitelAdm.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
	    
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    
	    for (Object[] item : linhaDispo) {
	    	Date dtDevolucao = null;
	    	Date dtRecebido = null;
	    	
			if(item[2] != null){
				try {
					dtDevolucao = df.parse(item[2].toString().substring(0, 19));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if(item[3] != null){
				try {
					dtRecebido = df.parse(item[3].toString().substring(0, 19));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
	    	
	    	AlocacaoLinhaDispositivoUsuarioDTO alocacao = 
	    			new AlocacaoLinhaDispositivoUsuarioDTO(
	    					Integer.parseInt(item[0].toString()),
	    					Integer.parseInt(item[1].toString()),
	    					dtDevolucao,
	    					dtRecebido,
	    					Integer.parseInt(item[4].toString()),
	    					Integer.parseInt(item[5].toString()));
	    	lista.add(alocacao);
	    }
	    
		

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
