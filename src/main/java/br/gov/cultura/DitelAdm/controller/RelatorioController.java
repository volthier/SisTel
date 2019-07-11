package br.gov.cultura.DitelAdm.controller;

import br.gov.cultura.DitelAdm.model.*;
import br.gov.cultura.DitelAdm.service.*;
import br.gov.cultura.DitelAdm.ws.SeiClient;
import br.gov.cultura.DitelAdm.wsdl.Unidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/relatorio")
public class RelatorioController {

	@Autowired
	private SeiClient sei;

	@Autowired
	private CadastroDispositivoService cadastroDispositivoService;

	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;

	@Autowired
	private CadastroChipService cadastroChipService;

	@Autowired
	private CadastroLinhaService cadastroLinhaService;

	@Autowired
	private AlocacaoService alocacaoservice;

	@RequestMapping
	public ModelAndView relatorios(){
		ModelAndView mv = new ModelAndView("Relatorio");
		List<Unidade> uni = Arrays.asList(sei.listarUnidades());
		mv.addObject("lista",uni);
		return mv;
	}

	@RequestMapping(value="/usuarios",method = RequestMethod.GET)
	@ResponseBody
	public List<Usuario> getUsuarios(){
		return cadastroUsuarioService.getIdUsuario();
	}

	@RequestMapping(value="/linhas",method = RequestMethod.GET)
	@ResponseBody
	public List<Linha> getLinhas(){
		return cadastroLinhaService.getIdLinha();
	}

	@RequestMapping(value="/dispositivos",method = RequestMethod.GET)
	@ResponseBody
	public List<Dispositivo> getDispositivos(){
		return cadastroDispositivoService.getIdDispositivo();
	}

	@RequestMapping(value="/chips",method = RequestMethod.GET)
	@ResponseBody
	public List<Chip> getChips(){
		return cadastroChipService.getIdChip();
	}

	@RequestMapping(value="/alocacao",method = RequestMethod.GET)
	@ResponseBody
	public List<Alocacao> getAlocacao(){
		return alocacaoservice.getIdAlocacao();
	}

}
