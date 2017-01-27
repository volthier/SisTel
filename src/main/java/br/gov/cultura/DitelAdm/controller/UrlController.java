package br.gov.cultura.DitelAdm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


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
		ModelAndView mv = new ModelAndView("TelaInicio");	
		return mv;
	}
	
}
