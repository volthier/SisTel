package br.gov.cultura.DitelAdm.controller;

import java.rmi.RemoteException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.gov.cultura.DitelAdm.email.Mailer;
import br.gov.cultura.DitelAdm.model.AlocacaoUsuarioLinha;
import br.gov.cultura.DitelAdm.model.dtos.AlocacaoLinhaDispositivoUsuarioDTO;
import br.gov.cultura.DitelAdm.service.AlocacaoService;
import br.gov.cultura.DitelAdm.service.PendenciaService;
import br.gov.cultura.DitelAdm.ws.SeiClient;
import br.gov.cultura.DitelAdm.wsdl.RetornoGeracaoProcedimento;

@Controller
@RequestMapping
public class PendenciaController {
	
	private static final String CADASTRO_VIEW = "Pendencia";
	@Autowired
	private AlocacaoService alocacaoService;
	@Autowired
	private PendenciaService pendenciaService;
	
	@Autowired
	private Mailer mailer;
	@Autowired
	private SeiClient sei;
	
	@RequestMapping("/pendencia")
	public ModelAndView pendencia() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		List<AlocacaoLinhaDispositivoUsuarioDTO> lista =  pendenciaService.listaPendencia();
		mv.addObject("pendencia",lista);		
		return mv;
	}
		
	@RequestMapping(value="/email/{id}", method = RequestMethod.POST)
	public String enviarEmailProcesso(@PathVariable("id") @RequestBody String id, RedirectAttributes attributes) throws RemoteException {
		AlocacaoUsuarioLinha alocacaoUsuarioLinha = alocacaoService.getAlocacaoUsuarioLinha(Integer.parseInt(id)); 
		if(alocacaoUsuarioLinha.getNumeroExternoProcessoSei()!=null){
			mailer.enviar(Integer.parseInt(id));
			attributes.addFlashAttribute("mensagem", "E-mail encaminhado com sucesso!");
		    return "redirect:/pendencia";
		}
		RetornoGeracaoProcedimento processo = sei.gerarProcedimentoAlocacao();
		alocacaoUsuarioLinha.setNumeroProcessoSei(processo.getIdProcedimento());
		alocacaoUsuarioLinha.setNumeroExternoProcessoSei(processo.getProcedimentoFormatado());
		alocacaoUsuarioLinha.setLinkAcessoSei(processo.getLinkAcesso());
		alocacaoService.salvar(alocacaoUsuarioLinha);
		mailer.enviar(Integer.parseInt(id));
		attributes.addFlashAttribute("mensagem", "E-mail encaminhado com sucesso!");
	    return "redirect:/pendencia";
	}
	
}