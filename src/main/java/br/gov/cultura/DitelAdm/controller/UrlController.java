package br.gov.cultura.DitelAdm.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.gov.cultura.DitelAdm.model.dtos.AlocacaoLinhaDispositivoUsuarioDTO;
import br.gov.cultura.DitelAdm.service.AlocacaoService;
import br.gov.cultura.DitelAdm.service.CadastroDispositivoService;
import br.gov.cultura.DitelAdm.service.CadastroUsuarioService;



@Controller
@RequestMapping
public class UrlController {

@Autowired
private AlocacaoService alocacaoService;

@Autowired
private CadastroDispositivoService dispositivoService;

@Autowired
private CadastroUsuarioService cadastroUsuarioService;

@RequestMapping("/login")
public ModelAndView login(@RequestParam(value = "error",required = false) String error,
@RequestParam(value = "/logout",	required = false) String logout,RedirectAttributes attributes) {
	
	ModelAndView mv = new ModelAndView("Login");
	if (error != null) {
		attributes.addFlashAttribute("error", " Credencial Inválida.");
		mv.addObject(attributes);
	}

	if (logout != null) {
		mv.addObject("message", "Logged out do SisTel concluido.");
	}
	return mv;
}

	@RequestMapping("/inicio")
	public ModelAndView inicio(){
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
		
		for (AlocacaoLinhaDispositivoUsuarioDTO dto : lista) {
			dto.setDispositivo(dispositivoService.getDispositivoById(dto.getIdDispositivo()));
			dto.setUsuario(cadastroUsuarioService.getUsuarioById(dto.getIdUsuario()));
		}
		
		ModelAndView mv = new ModelAndView("TelaInicio");
		mv.addObject("alocacao",lista);
		return mv;
	}
	
}
