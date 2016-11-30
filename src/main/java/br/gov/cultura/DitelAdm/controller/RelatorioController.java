package br.gov.cultura.DitelAdm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.gov.cultura.DitelAdm.model.AlocacaoLinhaCategoria;
import br.gov.cultura.DitelAdm.model.AlocacaoLinhaChip;
import br.gov.cultura.DitelAdm.model.AlocacaoLinhaDispositivo;
import br.gov.cultura.DitelAdm.model.AlocacaoUsuarioLinha;
import br.gov.cultura.DitelAdm.model.Chip;
import br.gov.cultura.DitelAdm.model.Dispositivo;
import br.gov.cultura.DitelAdm.model.Linha;
import br.gov.cultura.DitelAdm.model.Usuario;
import br.gov.cultura.DitelAdm.service.AlocacaoService;
import br.gov.cultura.DitelAdm.service.CadastroChipService;
import br.gov.cultura.DitelAdm.service.CadastroDispositivoService;
import br.gov.cultura.DitelAdm.service.CadastroLinhaService;
import br.gov.cultura.DitelAdm.service.CadastroUsuarioService;

@Controller
@RequestMapping("/relatorio")
public class RelatorioController {

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
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView relatorios(){
		ModelAndView mv = new ModelAndView("Relatorio");
		return mv;
	}
	
	@RequestMapping(value="/usuarios",headers = "Accept=application/json")
	public List<Usuario> getUsuarios(){
		return cadastroUsuarioService.getIdUsuario();
	}

	@RequestMapping(value="/linhas",headers = "Accept=application/json")
	public List<Linha> getLinhas(){
		return cadastroLinhaService.getIdLinha();
	}
	
	@RequestMapping(value="/dispositivos",headers = "Accept=application/json")
	public List<Dispositivo> getDispositivos(){
		return cadastroDispositivoService.getIdDispositivo();
	}
	
	@RequestMapping(value="/chips",method = RequestMethod.GET)
	@ResponseBody
	public List<Chip> getChips(){
		return cadastroChipService.getIdChip();
	}
	
	@RequestMapping("/alocacaoUsuarioLinha")
	public List<AlocacaoUsuarioLinha> getAUL(){ 
		return alocacaoservice.getIdAlocacaoUsuarioLinha();
	}
	@RequestMapping("/alocacaoLinhaDispositivo")
	public List<AlocacaoLinhaDispositivo> getALD(){ 
		return alocacaoservice.getIdAlocacaoLinhaDispositivo();
	}
	@RequestMapping("/alocacaoLinhaChip")
	public List<AlocacaoLinhaChip> getALC(){ 
		return alocacaoservice.getIdAlocacaoLinhaChip();
	}
	@RequestMapping("/alocacaoLinhaCategoria")
	public List<AlocacaoLinhaCategoria> getALCat(){ 
		return alocacaoservice.getIdAlocacaoLinhaCategoria();
	}

}
