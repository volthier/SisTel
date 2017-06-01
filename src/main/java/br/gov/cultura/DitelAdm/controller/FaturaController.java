package br.gov.cultura.DitelAdm.controller;

import java.io.ByteArrayOutputStream;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import br.gov.cultura.DitelAdm.email.Mailer;
import br.gov.cultura.DitelAdm.model.Alocacao;
import br.gov.cultura.DitelAdm.model.AlocacaoFatura;
import br.gov.cultura.DitelAdm.model.LimiteAtesto;
import br.gov.cultura.DitelAdm.model.Linha;
import br.gov.cultura.DitelAdm.model.dtos.CalculadorDTO;
import br.gov.cultura.DitelAdm.model.dtos.ServicosCategoria;
import br.gov.cultura.DitelAdm.model.faturasV3.Chamadas;
import br.gov.cultura.DitelAdm.model.faturasV3.Fatura;
import br.gov.cultura.DitelAdm.model.faturasV3.Planos;
import br.gov.cultura.DitelAdm.model.faturasV3.Resumo;
import br.gov.cultura.DitelAdm.model.faturasV3.Servicos;
import br.gov.cultura.DitelAdm.service.AlocacaoService;
import br.gov.cultura.DitelAdm.service.ChamadasService;
import br.gov.cultura.DitelAdm.service.FaturaService;
import br.gov.cultura.DitelAdm.service.PlanoService;
import br.gov.cultura.DitelAdm.service.ResumoService;
import br.gov.cultura.DitelAdm.service.ServicoService;
import br.gov.cultura.DitelAdm.ws.SeiClient;
import br.gov.cultura.DitelAdm.wsdl.Usuario;

@Controller
@RequestMapping("/fatura")
public class FaturaController {

	private final String CADASTRO_VIEW = "ExecutarFatura";

	@Autowired
	private Mailer mailer;

	@Autowired
	private LocaleResolver locale;

	@Autowired
	private TemplateEngine tempEngine;

	@Autowired
	private AlocacaoService alocacaoService;

	@Autowired
	private FaturaService faturaService;

	@Autowired
	private ResumoService resumoService;

	@Autowired
	private ChamadasService chamadaService;

	@Autowired
	private PlanoService planoService;

	@Autowired
	private ServicoService servicoService;

	@Autowired
	private SeiClient sei;

	@Autowired
	private ViewResolver viewResolver;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody ModelAndView executarFatura() throws RemoteException {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		List<Alocacao> alocacaoList = alocacaoService.getIdAlocacao();
		List<br.gov.cultura.DitelAdm.model.Usuario> usuarioErrorSei = new ArrayList<br.gov.cultura.DitelAdm.model.Usuario>();

		for (Alocacao alocacao : alocacaoList) {
			if (alocacao.getDtDevolucao() == null) {
				Usuario usuarioSei = sei.ValidaUsuarioUnidade(alocacao);
				if (usuarioSei == null) {
					if (!usuarioErrorSei.contains(alocacao.getUsuario())) {

						usuarioErrorSei.add(alocacao.getUsuario());
					}
				}
			}
		}
		/** Correção de medida de vizualização **/
		List<Fatura> faturasNaoGeradas = faturaService.getFaturasNaoGeradas();
		List<AlocacaoFatura> faturasGeradas = alocacaoService.getIdAlocacaoFatura();
		mv.addObject("faturasNaoGeradas", faturasNaoGeradas);
		mv.addObject("faturasGeradas", faturasGeradas);
		mv.addObject("erroUnidade", usuarioErrorSei);
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ModelAndView executarFatura(HttpServletRequest request, Model model) throws Exception {
		ModelAndView mv = new ModelAndView("FaturaLinhas");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		SimpleDateFormat sdt = new SimpleDateFormat("HH:mm:ss");
		CalculadorDTO cal = new CalculadorDTO();
		List<ServicosCategoria> servicosPorCategoria = new ArrayList<ServicosCategoria>();
		ServicosCategoria servicoCategoria = null;

		Integer idFatura = Integer.parseInt(request.getParameter("fatura"));

		Fatura fatura = faturaService.getFatura(idFatura);

		List<Resumo> resumos = resumoService.getResumoFatura(fatura);
		List<AlocacaoFatura> alocacoesFaturas = new ArrayList<AlocacaoFatura>();

		for (Resumo resumo : resumos) {
			Linha linha = resumo.getLinha();
			if (linha != null) {
				List<Alocacao> alocacoesUsuarioLinha = alocacaoService.getAlocacaoUsuarioLinha(linha);
				if (!alocacoesUsuarioLinha.isEmpty()) {
					for (Alocacao alocacao : alocacoesUsuarioLinha) {
						LimiteAtesto limiteAtesto = alocacao.getUsuario().getLimiteAtesto();

						/**
						 * ADICIONAR AQUI TRATATIVA PARA DATA ALOCADA DA LINHA
						 */

						List<Chamadas> chamadas = chamadaService.getChamadaResumo(resumo);
						List<Planos> planos = planoService.getPlanoResumo(resumo);
						List<Servicos> servicos = servicoService.getServicosResumo(resumo);

						/**
						 * ADICIONAR AQUI TRATATIVA PARA DATA ALOCADA DA LINHA
						 */
						/** adicionar a tabela ajustes ao faturamento */

						for (Servicos servico : servicos) {

							/*
							 * String tempDate = servico.getDataServico() + " "
							 * + servico.getHoraServico(); Date datePeriodo =
							 * sdf.parse(tempDate);
							 * 
							 * if((datePeriodo.compareTo(alocacao.getDtRecebido(
							 * )) > 0 &&
							 * datePeriodo.compareTo(alocacao.getDtDevolucao())
							 * <0 )||
							 * (datePeriodo.compareTo(alocacao.getDtDevolucao())
							 * ==0 ||
							 * datePeriodo.compareTo(alocacao.getDtDevolucao())=
							 * =0))
							 */

							if (servicoCategoria == null) {
								servicoCategoria = new ServicosCategoria();
							} else if (servicoCategoria.getCategoria().getCodCatServico() != servico
									.getCategoriaservico().getCodCatServico()) {
								servicosPorCategoria.add(servicoCategoria);
								servicoCategoria = new ServicosCategoria();
							}
							servicoCategoria.setCategoria(servico.getCategoriaservico());
							if (servico.getUnidadeServico().equals("MB") || servico.getUnidadeServico().equals("KB"))
								servicoCategoria.setQuantidade(
										(servico.getUnidadeServico().equals("KB") ? servico.getQuantUtil() / 1000
												: servico.getQuantUtil()) + servicoCategoria.getQuantidade());
							else
								servicoCategoria.setQuantidade(servicoCategoria.getQuantidade() + 1);
							servicoCategoria.setTarifa(servico.getValServImp());
							servicoCategoria
									.setValorCobrado(servico.getValServImp() + servicoCategoria.getValorCobrado());
							servicoCategoria.setValorTotal(servico.getValServImp() + servicoCategoria.getValorTotal());
						}

						servicosPorCategoria.add(servicoCategoria);

						Planos plano = planos.get(0);

						if (resumo.getDataDesativ() != null) {
							long diasTotal = ((plano.getDataFimCiclo().getTime() - plano.getDataIniCiclo().getTime()
									+ 3600000) / 86400000L);
							long diasUtilizado = ((resumo.getDataDesativ().getTime() - plano.getDataIniCiclo().getTime()
									+ 3600000) / 86400000L);
							float valor = 4.9f;

							cal.setResultadoF((valor / diasTotal) * diasUtilizado);
							

						} else {
							cal.setResultadoF(4.9f);
							

						}

						float valorTotal = this.valorTotal(chamadas, servicos, planos) + cal.getResultadoF();

						// Troca de campos para enviar para o Template Resumo

						cal.setFloatA(cal.getResultadoF());
						cal.setFloatB(valorTotal);

						cal.setResultadoF(0);
						for(Chamadas chamada :chamadas){
							if(cal.getDataA()==null){
								cal.setResultadoF(chamada.getDuracaoLigacao().getTime());
								cal.setDataA(sdt.parse(sdt.format(cal.getResultadoF())));
							}else{
							cal.setResultadoF((cal.getDataA().getTime()+chamada.getDuracaoLigacao().getTime()));
										
							cal.setDataA(sdt.parse(sdt.format(cal.getResultadoF())));
						}
						}

						AlocacaoFatura alocacaoFatura = new AlocacaoFatura();

						if (valorTotal > limiteAtesto.getValorLimite()) {
							alocacaoFatura.setRessarcimento(true);
							sei.enviarMemorando(alocacao, gerarMemorando(request));

							sei.enviarFatura(alocacao.getIdAlocacao(), alocacao.getAlocacaoSei().getNumeroProcessoSei(),
									gerarPdfFatura(fatura, alocacao, cal, chamadas, planos, servicosPorCategoria,
											resumo, request));
							mailer.enviarAtestoFatura(alocacao, fatura);
						} else {
							alocacaoFatura.setRessarcimento(false);
							sei.enviarFatura(alocacao.getIdAlocacao(), alocacao.getAlocacaoSei().getNumeroProcessoSei(),
									gerarPdfFatura(fatura, alocacao, cal, chamadas, planos, servicosPorCategoria,
											resumo, request));
							mailer.enviarAtestoFatura(alocacao, fatura);
						}

						alocacaoFatura.setAlocacao(alocacao);
						alocacaoFatura.setResumo(resumo);

						alocacaoService.salvar(alocacaoFatura);
						alocacoesFaturas.add(alocacaoFatura);
					}
				}
			}
		}

		mv.addObject("alocacoesSei", alocacoesFaturas);
		mv.addObject("fatura", fatura);

		return mv;
	}

	@RequestMapping("/resumo/{fatura}/{alocacao}")
	public @ResponseBody ModelAndView gerarResumo(@PathVariable("fatura") int idFatura,
			@PathVariable("alocacao") int idAlocacao, HttpServletRequest request) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		SimpleDateFormat sdt = new SimpleDateFormat("HH:mm:ss");
		ModelAndView mv = new ModelAndView("ResumoTeste");
		CalculadorDTO cal = new CalculadorDTO();

		Fatura fatura = faturaService.getFatura(idFatura);
		Alocacao alocacao = alocacaoService.getAlocacao(idAlocacao);

		List<Resumo> resumos = resumoService.getResumoFaturaLinha(fatura, alocacao.getLinha());
		List<Chamadas> chamadas = chamadaService.getChamadaResumo(resumos.get(0));
		List<Planos> planos = planoService.getPlanoResumo(resumos.get(0));
		List<Servicos> servicos = servicoService.getServicosResumo(resumos.get(0));
		List<ServicosCategoria> servicosPorCategoria = new ArrayList<ServicosCategoria>();
		ServicosCategoria servicoCategoria = null;

		//

		Planos plano = planos.get(0);

		if (resumos.get(0).getDataDesativ() != null) {
			long diasTotal = ((plano.getDataFimCiclo().getTime() - plano.getDataIniCiclo().getTime() + 3600000)
					/ 86400000L);
			long diasUtilizado = ((resumos.get(0).getDataDesativ().getTime() - plano.getDataIniCiclo().getTime()
					+ 3600000) / 86400000L);
			float valor = 4.9f;

			cal.setResultadoF((valor / diasTotal) * diasUtilizado);

		} else {
			cal.setResultadoF(4.9f);

		}

		float valorTotal = this.valorTotal(chamadas, servicos, planos) + cal.getResultadoF();
		cal.setFloatA(cal.getResultadoF());
		cal.setFloatB(valorTotal);
		//
		cal.setResultadoF(0);
		for(Chamadas chamada :chamadas){
			if(cal.getDataA()==null){
				cal.setResultadoF(chamada.getDuracaoLigacao().getTime());
				cal.setDataA(sdt.parse(sdt.format(cal.getResultadoF())));
			}else{
			cal.setResultadoF((cal.getDataA().getTime()+chamada.getDuracaoLigacao().getTime()));
						
			cal.setDataA(sdt.parse(sdt.format(cal.getResultadoF())));
		}
		}

		for (Servicos servico : servicos) {

			/*
			 * String tempDate = servico.getDataServico() + " " +
			 * servico.getHoraServico(); Date datePeriodo = sdf.parse(tempDate);
			 * 
			 * if((datePeriodo.compareTo(alocacao.getDtRecebido()) > 0 &&
			 * datePeriodo.compareTo(alocacao.getDtDevolucao()) <0 )||
			 * (datePeriodo.compareTo(alocacao.getDtDevolucao())==0 ||
			 * datePeriodo.compareTo(alocacao.getDtDevolucao())==0))
			 */

			if (servicoCategoria == null) {
				servicoCategoria = new ServicosCategoria();
			} else if (servicoCategoria.getCategoria().getCodCatServico() != servico.getCategoriaservico()
					.getCodCatServico()) {
				servicosPorCategoria.add(servicoCategoria);
				servicoCategoria = new ServicosCategoria();
			}
			servicoCategoria.setCategoria(servico.getCategoriaservico());
			if (servico.getUnidadeServico().equals("MB") || servico.getUnidadeServico().equals("KB"))
				servicoCategoria.setQuantidade((servico.getUnidadeServico().equals("KB") ? servico.getQuantUtil() / 1000
						: servico.getQuantUtil()) + servicoCategoria.getQuantidade());
			else
				servicoCategoria.setQuantidade(servicoCategoria.getQuantidade() + 1);
			servicoCategoria.setTarifa(servico.getValServImp());
			servicoCategoria.setValorCobrado(servico.getValServImp() + servicoCategoria.getValorCobrado());
			servicoCategoria.setValorTotal(servico.getValServImp() + servicoCategoria.getValorTotal());
		}

		servicosPorCategoria.add(servicoCategoria);
		Planos planoDatas = new Planos();
		planoDatas.setDataIniCiclo(planos.get(0).getDataIniCiclo());
		planoDatas.setDataFimCiclo(planos.get(0).getDataFimCiclo());
		mv.addObject("planoData", planoDatas);
		mv.addObject("alocacao", alocacao);
		mv.addObject("resumo", resumos.get(0));
		mv.addObject("fatura", fatura);
		mv.addObject("chamadas", chamadas);
		mv.addObject("planos", planos);
		mv.addObject("servicos", servicosPorCategoria);
		mv.addObject("pacote", cal);

		return mv;
	}

	private float valorTotal(List<Chamadas> chamadas, List<Servicos> servicos, List<Planos> planos) {
		float valorTotal = 0;
		if (!chamadas.isEmpty()) {
			for (Chamadas chamada : chamadas) {
				valorTotal += chamada.getValLigImp();
			}
		}

		if (!servicos.isEmpty()) {
			for (Servicos servico : servicos) {
				valorTotal += servico.getValServImp();
			}
		}

		if (!planos.isEmpty()) {
			for (Planos plano : planos) {
				valorTotal += plano.getValComImp();
			}
		}

		return valorTotal;
	}

	private byte[] gerarPdfFatura(Fatura fatura, Alocacao alocacao, CalculadorDTO cal, List<Chamadas> chamadas,
			List<Planos> planos, List<ServicosCategoria> servicosPorCategoria, Resumo resumo,
			HttpServletRequest request) throws Exception {
		Planos planoDatas = new Planos();
		planoDatas.setDataIniCiclo(planos.get(0).getDataIniCiclo());
		planoDatas.setDataFimCiclo(planos.get(0).getDataFimCiclo());
		Context context = new Context();
		context.setVariable("alocacao", alocacao);
		context.setVariable("fatura", fatura);
		context.setVariable("pacote", cal);
		context.setVariable("resumo", resumo);
		context.setVariable("chamadas", chamadas);
		context.setVariable("plano", planos);
		context.setVariable("planoData", planoDatas);
		context.setVariable("servicos", servicosPorCategoria);

		context.setLocale(locale.resolveLocale(request));
		String template = tempEngine.process("ResumoTeste", context);

		ITextRenderer renderer = new ITextRenderer();
		renderer.setDocumentFromString(template);
		renderer.layout();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		renderer.createPDF(baos);

		return baos.toByteArray();

	}

	private byte[] gerarMemorando(HttpServletRequest request) throws Exception {
		View view = this.viewResolver.resolveViewName("/documentos/memorandos/MemorandoFaturaTelefonica",
				locale.resolveLocale(request));
		MockHttpServletResponse mockResp = new MockHttpServletResponse();
		view.render(new ModelAndView().getModelMap(), request, mockResp);

		return mockResp.getContentAsByteArray();
	}
}