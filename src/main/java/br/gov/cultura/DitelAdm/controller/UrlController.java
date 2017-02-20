package br.gov.cultura.DitelAdm.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.gov.cultura.DitelAdm.model.dtos.AlocacaoLinhaDispositivoUsuarioDTO;
import br.gov.cultura.DitelAdm.repository.filtro.CadastroFiltroPesquisa;
import br.gov.cultura.DitelAdm.service.AlocacaoService;
import br.gov.cultura.DitelAdm.service.CadastroDispositivoService;
import br.gov.cultura.DitelAdm.service.CadastroLinhaService;
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

@Autowired
private CadastroLinhaService cadastroLinhaService;

@RequestMapping("/login")
public ModelAndView login(@RequestParam(value = "error",required = false) String error,
@RequestParam(value = "/logout",	required = false) String logout,RedirectAttributes attributes) {
	
	ModelAndView mv = new ModelAndView("Login");
	if (error != null) {
		attributes.addFlashAttribute("mensagem", " Credencial Inválida.");
		mv.addObject(attributes);
	}

	if (logout != null) {
		attributes.addFlashAttribute("messagem", "Logged out do SisTel concluido.");
	}
	return mv;
}

@RequestMapping("/passo-a-passo")
public ModelAndView passoApasso(@ModelAttribute("filtro") CadastroFiltroPesquisa filtro){
	ModelAndView mv = new ModelAndView("CadastroPassoAPasso");
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
			dto.setLinha(cadastroLinhaService.getLinhaById(dto.getIdLinha()));
		}
		
		/*Lista de alocados Devolvidos*/
		Stream<AlocacaoLinhaDispositivoUsuarioDTO> dto = lista.stream().filter( p -> Objects.nonNull(p.getDtDevolucao()) && p.getDtRecebido()!=null);
		
		/*Lista de alocados habilitados*/
		Stream<AlocacaoLinhaDispositivoUsuarioDTO> dto1 = lista.stream().filter( p -> Objects.nonNull(p.getDtRecebido()) && p.getDtDevolucao()==null);
		
		ModelAndView mv = new ModelAndView("TelaInicio");
		//lista de total alocados
		mv.addObject("alocacaoTotal",lista);
		//lista de total alocados Habilitados
		mv.addObject("devolvidosTotal",dto);
		/*Lista de alocados habilitados*/
		mv.addObject("habilitadosTotal",dto1);
		
		
		
		return mv;
	}
	
}
