package br.gov.cultura.DitelAdm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.gov.cultura.DitelAdm.model.Alocacao;
import br.gov.cultura.DitelAdm.model.Chip;
import br.gov.cultura.DitelAdm.model.Dispositivo;
import br.gov.cultura.DitelAdm.model.Linha;
import br.gov.cultura.DitelAdm.model.Usuario;
import br.gov.cultura.DitelAdm.service.AlocacaoService;
import br.gov.cultura.DitelAdm.service.CadastroChipService;
import br.gov.cultura.DitelAdm.service.CadastroDispositivoService;
import br.gov.cultura.DitelAdm.service.CadastroLinhaService;
import br.gov.cultura.DitelAdm.service.CadastroUsuarioService;
import br.gov.cultura.DitelAdm.ws.SeiClient;

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
