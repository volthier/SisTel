package br.gov.cultura.DitelAdm.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import br.gov.cultura.DitelAdm.model.AlocacaoSei;
import br.gov.cultura.DitelAdm.model.AlocacaoUsuarioLinha;
import br.gov.cultura.DitelAdm.model.LimiteAtesto;
import br.gov.cultura.DitelAdm.model.Linha;
import br.gov.cultura.DitelAdm.model.dtos.ServicosCategoria;
import br.gov.cultura.DitelAdm.model.faturasV3.Chamadas;
import br.gov.cultura.DitelAdm.model.faturasV3.Fatura;
import br.gov.cultura.DitelAdm.model.faturasV3.Planos;
import br.gov.cultura.DitelAdm.model.faturasV3.Resumo;
import br.gov.cultura.DitelAdm.model.faturasV3.Servicos;
import br.gov.cultura.DitelAdm.service.AlocacaoSeiService;
import br.gov.cultura.DitelAdm.service.AlocacaoUsuarioLinhaService;
import br.gov.cultura.DitelAdm.service.ChamadasService;
import br.gov.cultura.DitelAdm.service.FaturaService;
import br.gov.cultura.DitelAdm.service.PlanoService;
import br.gov.cultura.DitelAdm.service.ResumoService;
import br.gov.cultura.DitelAdm.service.ServicoService;
import br.gov.cultura.DitelAdm.service.UsuarioService;
import br.gov.cultura.DitelAdm.ws.SeiClient;

@Controller
@RequestMapping("/fatura")
public class FaturaController {

	private final String CADASTRO_VIEW = "ExecutarFatura";

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private AlocacaoUsuarioLinhaService alocacaoUsuarioLinhaService;
	
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
	private AlocacaoSeiService alocacaoSeiService;
	
	@Autowired
	private SeiClient sei;
	
	@Autowired
	private ViewResolver viewResolver;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody ModelAndView executarFatura() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		List<Fatura> faturasNaoGeradas = faturaService.getFaturasNaoGeradas();
		List<Fatura> faturasGeradas = faturaService.getFaturasGeradas();
		mv.addObject("faturasNaoGeradas", faturasNaoGeradas);
		mv.addObject("faturasGeradas", faturasGeradas);
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ModelAndView executarFatura(HttpServletRequest request, Model model) throws Exception {
		ModelAndView mv = new ModelAndView("FaturaLinhas");
		Integer idFatura = Integer.parseInt(request.getParameter("fatura"));
		Fatura fatura = faturaService.getFatura(idFatura);
		List<Resumo> resumos = resumoService.getResumoFatura(fatura);
		List<AlocacaoSei> alocacoesSei = new ArrayList<AlocacaoSei>();
		for (Resumo resumo : resumos) {
			Linha linha = resumo.getLinha();
			if (linha != null) {
				List<AlocacaoUsuarioLinha> alocacoesUsuarioLinha = alocacaoUsuarioLinhaService.getAlocacaoUsuarioLinha(linha);
				if (!alocacoesUsuarioLinha.isEmpty()) {
					for (AlocacaoUsuarioLinha alocacaoUsuarioLinha : alocacoesUsuarioLinha) {
						LimiteAtesto limiteAtesto = alocacaoUsuarioLinha.getUsuario().getLimiteAtesto();
						List<Chamadas> chamadas = chamadaService.getChamadaResumo(resumo);
						List<Planos> planos = planoService.getPlanoResumo(resumo);
						List<Servicos> servicos = servicoService.getServicosResumo(resumo);
						
						float valorTotal = this.valorTotal(chamadas, servicos, planos);
						
						AlocacaoSei alocacaoSei = new AlocacaoSei();
						if(valorTotal > Float.parseFloat(limiteAtesto.getValorLimite())){
							alocacaoSei.setRessarcimento(true);
							sei.enviarMemorando(alocacaoUsuarioLinha.getNumeroProcessoSei());
							sei.enviarFatura(alocacaoUsuarioLinha.getNumeroProcessoSei(), gerarPdfFatura(request, fatura, alocacaoUsuarioLinha));
						} else {
							alocacaoSei.setRessarcimento(false);
						}
						alocacaoSei.setAlocacaoUsuarioLinha(alocacaoUsuarioLinha);
						alocacaoSei.setResumo(resumo);
						
						alocacaoSeiService.salvar(alocacaoSei);
						alocacoesSei.add(alocacaoSei);
					}
				}
			}
		}

		mv.addObject("alocacoesSei", alocacoesSei);
		mv.addObject("fatura", fatura);
		
		return mv;
	}
	
	@RequestMapping("/resumo/{fatura}/{alocacao}")
	public @ResponseBody ModelAndView gerarResumo(@PathVariable("fatura") int idFatura, @PathVariable("alocacao") int idAlocacaoUsuarioLinha, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("Resumo");
		Fatura fatura = faturaService.getFatura(idFatura);
		AlocacaoUsuarioLinha alocacaoUsuarioLinha = alocacaoUsuarioLinhaService.getAlocacaoUsuarioLinha(idAlocacaoUsuarioLinha);
		List<Resumo> resumos = resumoService.getResumoFaturaLinha(fatura, alocacaoUsuarioLinha.getLinha());
		List<Chamadas> chamadas = chamadaService.getChamadaResumo(resumos.get(0));
		List<Planos> planos = planoService.getPlanoResumo(resumos.get(0));
		List<Servicos> servicos = servicoService.getServicosResumo(resumos.get(0));
		List<ServicosCategoria> servicosPorCategoria = new ArrayList<ServicosCategoria>();
		ServicosCategoria servicoCategoria = null;
		
		for (Servicos servico: servicos) {
			if (servicoCategoria == null) {
				servicoCategoria = new ServicosCategoria();
			} else if(servicoCategoria.getCategoria().getCodCatServico() != servico.getCategoriaservico().getCodCatServico()) {
				servicosPorCategoria.add(servicoCategoria);
				servicoCategoria = new ServicosCategoria();
			}
			servicoCategoria.setCategoria(servico.getCategoriaservico());
			if (servico.getUnidadeServico().equals("MB") || servico.getUnidadeServico().equals("KB"))
				servicoCategoria.setQuantidade((servico.getUnidadeServico().equals("KB") ? servico.getQuantUtil() / 1000 : servico.getQuantUtil()) + servicoCategoria.getQuantidade());
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
			for (Servicos servico: servicos) {
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
	
	private byte[] gerarPdfFatura(HttpServletRequest request, Fatura fatura, AlocacaoUsuarioLinha alocacaoUsuarioLinha) throws Exception{
		View view = this.viewResolver.resolveViewName("FaturaLinhas", Locale.US);
		MockHttpServletResponse mockResp = new MockHttpServletResponse();
		ModelAndView mv = this.gerarResumo(fatura.getIdFatura(), alocacaoUsuarioLinha.getIdAlocacaoUsuarioLinha(), request);
		
		view = this.viewResolver.resolveViewName("Resumo", Locale.US);
		view.render(mv.getModelMap(), request, mockResp);
		
		Document document = new Document(PageSize.A4,2,2,10,10);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PdfWriter writer = PdfWriter.getInstance(document, baos);
		
		document.open();
		XMLWorkerHelper.getInstance().parseXHtml(writer, document, new ByteArrayInputStream(mockResp.getContentAsByteArray()));
		document.close();
		
		return baos.toByteArray();
	}
}