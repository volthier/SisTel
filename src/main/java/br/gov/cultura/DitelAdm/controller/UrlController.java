package br.gov.cultura.DitelAdm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/inicio")
public class UrlController {

	@RequestMapping
	public ModelAndView inicio(){
		ModelAndView mv = new ModelAndView("TelaInicio");	
		return mv;
	}	
	
}
