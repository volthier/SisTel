package br.gov.cultura.DitelAdm.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.maven.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import br.gov.cultura.DitelAdm.model.Alocacao;
import br.gov.cultura.DitelAdm.model.AlocacaoFatura;
import br.gov.cultura.DitelAdm.model.LimiteAtesto;
import br.gov.cultura.DitelAdm.model.Linha;
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
	private LocaleResolver locale;

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

						float valorTotal = this.valorTotal(chamadas, servicos, planos);

						AlocacaoFatura alocacaoFatura = new AlocacaoFatura();

						if (valorTotal > limiteAtesto.getValorLimite()) {
							alocacaoFatura.setRessarcimento(true);
							sei.enviarMemorando(alocacao, gerarMemorando(request));

							sei.enviarFatura(alocacao.getIdAlocacao(), alocacao.getAlocacaoSei().getNumeroProcessoSei(),
									gerarPdfFatura(request, fatura, alocacao));

						} else {
							alocacaoFatura.setRessarcimento(false);
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
			@PathVariable("alocacao") int idAlocacao, HttpServletRequest request) {

		ModelAndView mv = new ModelAndView("Resumo");

		Fatura fatura = faturaService.getFatura(idFatura);
		Alocacao alocacao = alocacaoService.getAlocacao(idAlocacao);

		List<Resumo> resumos = resumoService.getResumoFaturaLinha(fatura, alocacao.getLinha());
		List<Chamadas> chamadas = chamadaService.getChamadaResumo(resumos.get(0));
		List<Planos> planos = planoService.getPlanoResumo(resumos.get(0));
		List<Servicos> servicos = servicoService.getServicosResumo(resumos.get(0));
		List<ServicosCategoria> servicosPorCategoria = new ArrayList<ServicosCategoria>();
		ServicosCategoria servicoCategoria = null;

		for (Servicos servico : servicos) {
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

		mv.addObject("resumo", resumos.get(0));
		mv.addObject("chamadas", chamadas);
		mv.addObject("planos", planos);
		mv.addObject("servicos", servicosPorCategoria);

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

	private byte[] gerarPdfFatura(HttpServletRequest request, Fatura fatura, Alocacao alocacao) throws Exception {
		View view = this.viewResolver.resolveViewName("FaturaLinhas", locale.resolveLocale(request));
		MockHttpServletResponse mockResp = new MockHttpServletResponse();
		ModelAndView mv = this.gerarResumo(fatura.getIdFatura(), alocacao.getIdAlocacao(), request);

		view = this.viewResolver.resolveViewName("Resumo", locale.resolveLocale(request));
		view.render(mv.getModelMap(), request, mockResp);

		Document document = new Document(PageSize.A4, 2, 2, 10, 10);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PdfWriter writer = PdfWriter.getInstance(document, baos);

		document.open();
		XMLWorkerHelper.getInstance().parseXHtml(writer, document,
				new ByteArrayInputStream(mockResp.getContentAsByteArray()));
		document.close();

		return baos.toByteArray();
	}

	private byte[] gerarMemorando(HttpServletRequest request) throws Exception {
		View view = this.viewResolver.resolveViewName("MemorandoFaturaTelefonica", locale.resolveLocale(request));
		MockHttpServletResponse mockResp = new MockHttpServletResponse();
		view.render(new ModelAndView().getModelMap(), request, mockResp);

		return mockResp.getContentAsByteArray();
	}
}