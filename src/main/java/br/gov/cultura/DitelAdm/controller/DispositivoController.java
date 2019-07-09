package br.gov.cultura.DitelAdm.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.gov.cultura.DitelAdm.model.Dispositivo;
import br.gov.cultura.DitelAdm.model.LimiteAtesto;
import br.gov.cultura.DitelAdm.repository.filtro.FiltroPesquisa;
import br.gov.cultura.DitelAdm.service.CadastroDispositivoService;
import br.gov.cultura.DitelAdm.service.LimiteAtestoService;
import br.gov.cultura.DitelAdm.service.ldap.ConsultaLdapService;

@Controller
@RequestMapping("/dispositivos")
public class DispositivoController {

	private static final String CADASTRO_VIEW = "CadastroDispositivo";

	@Autowired
	private CadastroDispositivoService cadastroDispositivoService;

	@Autowired
	private LimiteAtestoService limiteAtestoService;

	@Autowired
	private ConsultaLdapService ldap;

	@RequestMapping("/novo")
	public ModelAndView novo(@ModelAttribute("filtro") FiltroPesquisa filtro) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		List<Dispositivo> todosDispositivos = cadastroDispositivoService.getIdDispositivo();
		List<LimiteAtesto> limiteAtesto = limiteAtestoService.getLimitesAtesto();
		mv.addObject("dispositivos", todosDispositivos);
		mv.addObject("limiteAtesto", limiteAtesto);
		mv.addObject(new Dispositivo());

		ldap.usuarioInfos(mv);
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Dispositivo cadastroDispositivo, Errors errors, RedirectAttributes attributes) {

		if (errors.hasErrors()) {
			attributes.addFlashAttribute("messageErro","Erro no cadastrado!");
			return "redirect:/dispositivos/novo";
		}
		else {
			cadastroDispositivoService.salvar(cadastroDispositivo);
			attributes.addFlashAttribute("mensagem", "Dispositivo cadastrado com sucesso!");
			return "redirect:/dispositivos/novo";
		} 

	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Integer id, RedirectAttributes attributes) {
		cadastroDispositivoService.excluir(id);
		attributes.addFlashAttribute("mensagem", "Cadastro do dispositivo removido com sucesso!");
		return "redirect:/consultas/dispositivos";
	}

	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") Dispositivo dispositivos) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(dispositivos);
		ldap.usuarioInfos(mv);
		return mv;
	}

	// DropDownMenu Atributos
	@ModelAttribute("tipoDispositivoMap")
	public Map<String, String> populateTipoDispositivoMap() throws MalformedURLException, IOException {

		Map<String, String> tipoDispositivoMap = new HashMap<String, String>();
		tipoDispositivoMap.put("Fixo", "Fixo");
		tipoDispositivoMap.put("Celular", "Celular");
		tipoDispositivoMap.put("Tablet", "Tablet");
		tipoDispositivoMap.put("Modem", "Modem");
		// tipoDispositivoMap.put("NoteBook","NoteBook");
		return tipoDispositivoMap;

	}
}
