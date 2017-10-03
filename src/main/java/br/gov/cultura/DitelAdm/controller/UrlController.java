package br.gov.cultura.DitelAdm.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.gov.cultura.DitelAdm.model.Alocacao;
import br.gov.cultura.DitelAdm.model.Dispositivo;
import br.gov.cultura.DitelAdm.repository.Dispositivos;
import br.gov.cultura.DitelAdm.service.AlocacaoService;

@Controller
@RequestMapping
public class UrlController {
	
	@Autowired
	private AlocacaoService alocacaoService;
	
	@Autowired
	private Dispositivos dispositivos;

@RequestMapping("/login")
public ModelAndView login(@RequestParam(value = "error",required = false) String error,
@RequestParam(value = "/logout", required = false) String logout,RedirectAttributes attributes) {
	
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

@RequestMapping("/inicio")
	public ModelAndView inicio(){

		ModelAndView mv = new ModelAndView("TelaInicio");
		
		List<Dispositivo> disp = dispositivos.findAll();
		List<Alocacao> lista = alocacaoService.getIdAlocacao();
	
		//Lista de alocados Devolvidos
		List<Alocacao> dto = lista.stream().filter(p-> Objects.nonNull(p.getDtDevolucao()) && p.getDtRecebido() !=null).collect(Collectors.toList());
	
		//Lista de alocados habilitados
		List<Alocacao> dto1 = lista.stream().filter( p -> Objects.nonNull(p.getDtRecebido()) && p.getDtDevolucao()==null).collect(Collectors.toList());
		
		List<Dispositivo> celular = new ArrayList<Dispositivo>();
		List<Dispositivo> tablet = new ArrayList<Dispositivo>();
		List<Dispositivo> fixo = new ArrayList<Dispositivo>();
		List<Dispositivo> modem = new ArrayList<Dispositivo>();
			
				for (Iterator<Dispositivo> d = disp.listIterator(); d.hasNext();) {
					Dispositivo dis = d.next();
					
					if(dis.getTipoDispositivo().equals("Celular")){
						celular.add(dis);
					}else if(dis.getTipoDispositivo().equals("Tablet")){
						tablet.add(dis);
					}else if(dis.getTipoDispositivo().equals("Modem")){
						modem.add(dis);
					}else if(dis.getTipoDispositivo().equals("Fixo")){
						fixo.add(dis);
					}
					
					for (Iterator<Alocacao> a = dto1.listIterator(); a.hasNext();) {
						Alocacao aloc = a.next();
						if (dis.getIdDispositivo().equals(aloc.getDispositivo().getIdDispositivo())) {
							d.remove();
						}
						celular.remove(aloc.getDispositivo());
						modem.remove(aloc.getDispositivo());
						tablet.remove(aloc.getDispositivo());
						fixo.remove(aloc.getDispositivo());
				}
			}
		
		//lista de total alocados
		mv.addObject("alocacaoTotal",lista);
		
		//lista de total alocados Habilitados
		mv.addObject("devolvidosTotal",dto);
		
		//Lista de alocados habilitados
		mv.addObject("habilitadosTotal",dto1);
		
		//Celulares no estoque
		mv.addObject("celulares", celular);
		
		//Tablets no estoque
		mv.addObject("tablets", tablet);
		
		//Modens no estoque
		mv.addObject("modens", modem);
		
		//Fixo no estoque
		mv.addObject("fixos", fixo);
	
		return mv;
	}
	
}