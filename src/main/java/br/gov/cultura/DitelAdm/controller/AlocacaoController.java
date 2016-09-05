package br.gov.cultura.DitelAdm.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.gov.cultura.DitelAdm.model.AlocacaoLinhaCategoria;
import br.gov.cultura.DitelAdm.model.AlocacaoLinhaChip;
import br.gov.cultura.DitelAdm.model.AlocacaoLinhaDispositivo;
import br.gov.cultura.DitelAdm.model.AlocacaoUsuarioLinha;
import br.gov.cultura.DitelAdm.model.Chip;
import br.gov.cultura.DitelAdm.model.Dispositivo;
import br.gov.cultura.DitelAdm.model.Linha;
import br.gov.cultura.DitelAdm.model.Usuario;
import br.gov.cultura.DitelAdm.model.Categoria;
import br.gov.cultura.DitelAdm.repository.filtro.CadastroFiltroPesquisa;
import br.gov.cultura.DitelAdm.service.AlocacaoService;
import br.gov.cultura.DitelAdm.service.CadastroCategoriaService;
import br.gov.cultura.DitelAdm.service.CadastroChipService;
import br.gov.cultura.DitelAdm.service.CadastroDispositivoService;
import br.gov.cultura.DitelAdm.service.CadastroLinhaService;
import br.gov.cultura.DitelAdm.service.CadastroUsuarioService;

@Controller
@RequestMapping("/alocacoes")
public class AlocacaoController {

	private final String CADASTRO_VIEW = "AlocacaoDisponibilizar";

	@Autowired
	private AlocacaoService alocacaoService;
	@Autowired
	private CadastroDispositivoService cadastroDispositivoService;
	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;
	@Autowired
	private CadastroChipService cadastroChipService;
	@Autowired
	private CadastroLinhaService cadastroLinhaService;
	@Autowired
	private CadastroCategoriaService cadastroCategoriaService;

	@RequestMapping("/disponibilizar")
	public @ResponseBody ModelAndView novo(@ModelAttribute("filtro") CadastroFiltroPesquisa filtro) {
		ModelAndView mv = new ModelAndView("AlocacaoDisponibilizar");
		mv.addObject(new AlocacaoUsuarioLinha());
		mv.addObject(new AlocacaoLinhaChip());
		mv.addObject(new AlocacaoLinhaDispositivo());
		mv.addObject(new AlocacaoLinhaCategoria());
		List<Dispositivo> todosDispositivos = cadastroDispositivoService.getIdDispositivo();
		mv.addObject("dispositivos", todosDispositivos);
		List<Usuario> todosUsuarios = cadastroUsuarioService.getIdUsuario();
		mv.addObject("usuarios", todosUsuarios);
		List<Chip> todosChips = cadastroChipService.getIdChip();
		mv.addObject("chips", todosChips);
		List<Linha> todasLinhas = cadastroLinhaService.getIdLinha();
		mv.addObject("linhas", todasLinhas);
		List<Categoria> todasCategorias = cadastroCategoriaService.getIdCategoria();
		mv.addObject("categorias", todasCategorias);

		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated AlocacaoLinhaCategoria alocacaoLinhaCategoria,
			@Validated AlocacaoLinhaChip alocacaoLinhaChip,
			@Validated AlocacaoLinhaDispositivo alocacaoLinhaDispositivo,
			@Validated AlocacaoUsuarioLinha alocacaoUsuarioLinha, Errors errors, RedirectAttributes attributes,
			HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ParseException {

/*		String dataRecebido = servletRequest.getParameter("dataRecebido");
		Date date = new SimpleDateFormat("yy/MM/dd HH:mm:ss").parse(dataRecebido);

		alocacaoLinhaChip.setDtRecebimento(date);
		alocacaoUsuarioLinha.setDtRecebido(date);
		alocacaoLinhaDispositivo.setDtRecebimento(date);
		alocacaoLinhaCategoria.setDtRecebimento(date);
*/
		try {
			alocacaoService.salvar(alocacaoLinhaChip);
			alocacaoService.salvar(alocacaoLinhaDispositivo);
			alocacaoService.salvar(alocacaoUsuarioLinha);
			alocacaoService.salvar(alocacaoLinhaCategoria);
			attributes.addFlashAttribute("mensagem", "Registrado vinculo!");
			return "redirect:/alocacoes/disponibilizar";
		} catch (IllegalArgumentException e) {
			errors.rejectValue("dataRecebido", null, e.getMessage());
			return CADASTRO_VIEW;
		}
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Integer id, RedirectAttributes attributes) {
		alocacaoService.excluir(id);
		attributes.addFlashAttribute("mensagem", "Fornecimento CANCELADO com sucesso!");
		return "redirect:/inicio";
	}

}