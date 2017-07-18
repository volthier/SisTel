package br.gov.cultura.DitelAdm.controller;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.gov.cultura.DitelAdm.model.Alocacao;
import br.gov.cultura.DitelAdm.repository.filtro.CadastroFiltroPesquisa;
import br.gov.cultura.DitelAdm.service.AlocacaoService;



@Controller
@RequestMapping
public class UrlController {

@Autowired
private AlocacaoService alocacaoService;

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

		ModelAndView mv = new ModelAndView("TelaInicio");
		
/*		List<Alocacao> lista = alocacaoService.getIdAlocacao();
	
		//Lista de alocados Devolvidos
		Stream<Alocacao> dto = lista.stream().filter(p-> Objects.nonNull(p.getDtDevolucao()) && p.getDtRecebido() !=null);
	
		
		//Lista de alocados habilitados
		Stream<Alocacao> dto1 = lista.stream().filter( p -> Objects.nonNull(p.getDtRecebido()) && p.getDtDevolucao()==null);
		
		//lista de total alocados
		mv.addObject("alocacaoTotal",lista);
		
		//lista de total alocados Habilitados
		mv.addObject("devolvidosTotal",dto);
		
		//Lista de alocados habilitados
		mv.addObject("habilitadosTotal",dto1);

		mv.addObject("pendencia",lista);
		*/
		return mv;
	}
	
}