package br.gov.cultura.DitelAdm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
	public @ResponseBody ModelAndView executarFatura(HttpServletRequest request) {
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
						alocacaoSei.setNumeroProcessoSei("123456");
						alocacaoSei.setRessarcimento((valorTotal > Float.parseFloat(limiteAtesto.getValorLimite())) ? true : false);
						alocacaoSei.setAlocacaoUsuarioLinha(alocacaoUsuarioLinha);
						alocacaoSei.setResumo(resumo);
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
	
	public float valorTotal(List<Chamadas> chamadas, List<Servicos> servicos, List<Planos> planos) {
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
}