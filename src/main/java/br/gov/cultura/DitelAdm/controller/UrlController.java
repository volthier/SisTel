package br.gov.cultura.DitelAdm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping
public class UrlController {

	@RequestMapping("/login")
	public ModelAndView login(){
		ModelAndView mv = new ModelAndView("Login");	
		return mv;
	}
	
	@RequestMapping("/inicio")
	public ModelAndView inicio(){
		ModelAndView mv = new ModelAndView("TelaInicio");	
		return mv;
	}
	
	
	
}
