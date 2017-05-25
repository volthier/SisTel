package br.gov.cultura.DitelAdm.controller;

import java.io.IOException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import br.gov.cultura.DitelAdm.email.Mailer;
import br.gov.cultura.DitelAdm.model.Alocacao;
import br.gov.cultura.DitelAdm.model.AlocacaoSei;
import br.gov.cultura.DitelAdm.model.DocumentoSei;
import br.gov.cultura.DitelAdm.service.AlocacaoService;
import br.gov.cultura.DitelAdm.ws.SeiClient;
import br.gov.cultura.DitelAdm.wsdl.RetornoConsultaProcedimento;
import br.gov.cultura.DitelAdm.wsdl.RetornoGeracaoProcedimento;
import br.gov.cultura.DitelAdm.wsdl.Usuario;

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

	@Autowired
	private TemplateEngine tempEngine;
	
	@Autowired
	private LocaleResolver locale;
	
	@RequestMapping("/pendencia")
	public ModelAndView pendencia() throws RemoteException {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		List<Alocacao> lista = alocacaoService.getIdAlocacao();
		
		List<br.gov.cultura.DitelAdm.model.Usuario> usuarioErrorSei = new ArrayList<br.gov.cultura.DitelAdm.model.Usuario>();

		for (Alocacao alocacao : lista) {
			
			List<DocumentoSei> documento = alocacaoService.getDocumentoSeiListaAlocacao(alocacao);
			documento.forEach(doc -> {
				try {
					sei.consultarAssinatura(doc);
				} catch (RemoteException | ParseException e) {

					System.out.println(e + "Essa parada aqui mano aferição dos documentos!!!");
					e.printStackTrace();
				} catch (InterruptedException e) {
					System.out.println(e + "Essa parada aqui mano espera error!!!");// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			
			if (alocacao.getDtDevolucao() == null) {
				Usuario usuarioSei = sei.ValidaUsuarioUnidade(alocacao);
				if (usuarioSei == null) {
					if (!usuarioErrorSei.contains(alocacao.getUsuario())) {

						usuarioErrorSei.add(alocacao.getUsuario());
					}
				}
			}
		}
		
		mv.addObject("erroUnidade", usuarioErrorSei);
		mv.addObject("pendencia", lista);
		return mv;
	}

	@RequestMapping(value = "/email/{id}", method = RequestMethod.POST)
	public String enviarEmailProcesso(@PathVariable("id") @RequestBody String id, Alocacao alocacao,
			AlocacaoSei alocacaoSei, HttpServletRequest request,RedirectAttributes attributes) throws NumberFormatException, IOException, Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		alocacao = alocacaoService.getAlocacao(Integer.parseInt(id));
		String cpf = alocacao.getUsuario().getCpfUsuario();
		

		if (alocacao.getAlocacaoSei() != null) {
			RetornoConsultaProcedimento processo = sei
					.consutaProcessoSei(alocacao.getAlocacaoSei().getNumeroExternoProcessoSei());

			if (processo.getAndamentoConclusao() == null) {
				mailer.enviarTermo(Integer.parseInt(id), gerarTermoResponsabilidade(new DocumentoSei(),request,alocacao));
				alocacao.getDocumentoSeis();
				attributes.addFlashAttribute("mensagem", "E-mail encaminhado com sucesso!");

				return "redirect:/pendencia";

			}
			alocacao.getAlocacaoSei()
					.setDtEncerramentoProcesso(sdf.parse(processo.getAndamentoConclusao().getDataHora()));
			alocacaoService.salvar(alocacao);
			alocacao.setAlocacaoSei(null);
		}
		if (alocacao.getAlocacaoSei() == null) {

			List<Alocacao> listaConferencia = alocacaoService.getIdAlocacao();
			listaConferencia.remove(alocacao);
			int i = 0;
			while (i == 0) {

				Alocacao alocacaoVerificaProcesso = listaConferencia.stream().filter(
						lc -> lc != null && lc.getUsuario().getCpfUsuario().equals(cpf) && lc.getAlocacaoSei() != null)
						.findFirst().orElse(null);

				if (alocacaoVerificaProcesso != null) {

					RetornoConsultaProcedimento processo = sei.consutaProcessoSei(
							alocacaoVerificaProcesso.getAlocacaoSei().getNumeroExternoProcessoSei());

					if (processo != null) {

						if (processo.getAndamentoConclusao() == null) {
							alocacao.setAlocacaoSei(alocacaoVerificaProcesso.getAlocacaoSei());
							alocacaoService.salvar(alocacao);
							mailer.enviarTermo(Integer.parseInt(id), gerarTermoResponsabilidade(new DocumentoSei(),request,alocacao));
							attributes.addFlashAttribute("mensagem", "E-mail encaminhado com sucesso!");
							i = 1;
						} else if (processo.getAndamentoConclusao() != null) {
							alocacaoVerificaProcesso.getAlocacaoSei().setDtEncerramentoProcesso(
									sdf.parse(processo.getAndamentoConclusao().getDataHora()));
							alocacaoService.salvar(alocacaoVerificaProcesso);
						}
					}
				} else if (alocacaoVerificaProcesso == null) {
					i = 1;
					RetornoGeracaoProcedimento processoNovo = sei.gerarProcedimentoAlocacao();
					RetornoConsultaProcedimento consulta = sei
							.consutaProcessoSei(processoNovo.getProcedimentoFormatado());
					alocacaoSei.setNumeroProcessoSei(processoNovo.getIdProcedimento());
					alocacaoSei.setNumeroExternoProcessoSei(processoNovo.getProcedimentoFormatado());
					alocacaoSei.setLinkAcessoSei(processoNovo.getLinkAcesso());
					alocacaoSei.setDtAberturaProcesso(sdf.parse(consulta.getAndamentoGeracao().getDataHora()));
					alocacaoService.salvar(alocacaoSei);
					alocacao.setAlocacaoSei(alocacaoSei);
					alocacaoService.salvar(alocacao);					
					mailer.enviarTermo(Integer.parseInt(id),gerarTermoResponsabilidade(new DocumentoSei(),request,alocacao));
					attributes.addFlashAttribute("mensagem", "E-mail encaminhado com sucesso!");
				}
			}
		}
		return "redirect:/pendencia";
	}
	private DocumentoSei gerarTermoResponsabilidade(DocumentoSei documento, HttpServletRequest request,Alocacao alocacao) throws Exception {
		
		Context context = new Context();
		context.setVariable("dto",alocacao);
		context.setLocale(locale.resolveLocale(request));
		String template = tempEngine.process("/documentos/TermoResponsabilidadeCelular", context);
		
		
		
		/*View view = viewResolver.resolveViewName(template, locale.resolveLocale(request));
		
		MockHttpServletResponse mockResp = new MockHttpServletResponse();
		view.render(new ModelAndView().getModelMap(), request, mockResp);
		return mockResp.getContentAsByteArray();*/
		byte[] termo = template.getBytes();
		documento = sei.enviarTermoResponsabilidade(alocacao, termo);
		
		return documento;
	}	
}