package br.gov.cultura.DitelAdm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping
public class UrlController {

/*@RequestMapping("/login")
public ModelAndView login(){
	ModelAndView mv = new ModelAndView("Login");
	return mv;
}*/
	
@RequestMapping("/login")
public ModelAndView login(@RequestParam(value = "error",required = false) String error,
@RequestParam(value = "/logout",	required = false) String logout) {
	
	ModelAndView mv = new ModelAndView("Login");
	if (error != null) {
		mv.addObject("error", " Credencial Inválida.");
	}

	if (logout != null) {
		mv.addObject("message", "Logged out do SisTel concluido.");
	}
	return mv;
}

	@RequestMapping("/inicio")
	public ModelAndView inicio(){
		ModelAndView mv = new ModelAndView("TelaInicio");	
		return mv;
	}
	
}
