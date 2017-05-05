package br.gov.cultura.DitelAdm.controller;

import java.rmi.RemoteException;
import java.time.LocalDate;
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
import br.gov.cultura.DitelAdm.model.Alocacao;
import br.gov.cultura.DitelAdm.model.AlocacaoSei;
import br.gov.cultura.DitelAdm.service.AlocacaoService;
import br.gov.cultura.DitelAdm.ws.SeiClient;
import br.gov.cultura.DitelAdm.wsdl.RetornoConsultaProcedimento;
import br.gov.cultura.DitelAdm.wsdl.RetornoGeracaoProcedimento;

@Controller
@RequestMapping
public class PendenciaController {
	
	private static final String CADASTRO_VIEW = "Pendencia";
	@Autowired
	private AlocacaoService alocacaoService;

	@Autowired
	private Mailer mailer;
	@Autowired
	private SeiClient sei;
	
	@RequestMapping("/pendencia")
	public ModelAndView pendencia() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		List<Alocacao> lista =  alocacaoService.getIdAlocacao();
		mv.addObject("pendencia",lista);		
		return mv;
	}
		
	@RequestMapping(value="/email/{id}", method = RequestMethod.POST)
	public String enviarEmailProcesso(@PathVariable("id") @RequestBody String id, Alocacao alocacao, AlocacaoSei alocacaoSei, RedirectAttributes attributes) throws RemoteException {

		alocacao = alocacaoService.getAlocacao(Integer.parseInt(id)); 
		if(alocacao.getAlocacaoSei()!=null){
			mailer.enviar(Integer.parseInt(id));
			attributes.addFlashAttribute("mensagem", "E-mail encaminhado com sucesso!");
		    return "redirect:/pendencia";
		}
		if(alocacao.getAlocacaoSei()==null){
		
		RetornoGeracaoProcedimento processo = sei.gerarProcedimentoAlocacao();
		
		RetornoConsultaProcedimento consulta = sei.consutaProcessoSei(processo.getIdProcedimento());
		
		
		alocacaoSei.setNumeroProcessoSei(processo.getIdProcedimento());
		alocacaoSei.setNumeroExternoProcessoSei(processo.getProcedimentoFormatado());
		alocacaoSei.setLinkAcessoSei(processo.getLinkAcesso());
		alocacaoSei.setDtAberturaProcesso();
		
		alocacaoService.salvar(alocacaoSei);
		alocacao.setAlocacaoSei(alocacaoSei);
		
		alocacaoService.salvar(alocacao);
		mailer.enviar(Integer.parseInt(id));
		attributes.addFlashAttribute("mensagem", "E-mail encaminhado com sucesso!");
	}
		return "redirect:/pendencia";
}
}