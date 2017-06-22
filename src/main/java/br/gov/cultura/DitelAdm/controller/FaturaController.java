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
import br.gov.cultura.DitelAdm.service.CadastroLinhaService;
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
	private CadastroLinhaService linhaService;

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

		Integer idFatura = Integer.parseInt(request.getParameter("fatura"));

		Fatura fatura = faturaService.getFatura(idFatura);
		List<Planos> planosLista = planoService.getPlanoFatura(fatura);
		List<Resumo> resumosLista = resumoService.getResumoFatura(fatura);
		List<Servicos> servicosLista = servicoService.getServicosFatura(fatura);
		List<Chamadas> chamadasLista = chamadaService.getChamadasFatura(fatura);
		List<Linha> linhasRegistradas = linhaService.getIdLinha();
		
		List<AlocacaoFatura> alocacoesFaturas = new ArrayList<AlocacaoFatura>();
		List<Planos> planosVinculados = new ArrayList<Planos>();
		List<Servicos> servicosVinculados = new ArrayList<Servicos>();
		List<Chamadas> chamadasVinculados = new ArrayList<Chamadas>();
		List<Resumo> resumoVinculados = new ArrayList<Resumo>();

		List<ServicosCategoria> servicosPorCategoria = new ArrayList<ServicosCategoria>();
		ServicosCategoria servicoCategoria = null;
		
		for (Linha linha : linhasRegistradas){
			List<Alocacao> alocacoesUsuarioLinha = alocacaoService.getAlocacaoUsuarioLinha(linha);
			if (!alocacoesUsuarioLinha.isEmpty()) {
				for (Alocacao alocacao : alocacoesUsuarioLinha) {
					LimiteAtesto limiteAtesto = alocacao.getUsuario().getLimiteAtesto();				
			
			for(Planos plano : planosLista){
				if(linha.equals(plano.getLinha())){
					planosVinculados.add(plano);		
				}
			}

			int i;

			i=0;
			for(Chamadas chamada : chamadasLista){
				++i;
				if(linha.equals(chamada.getLinha())){
					if(cal.getDataA()==null){
						cal.setResultadoF(chamada.getDuracaoLigacao().getTime());
						cal.setDataA(sdt.parse(sdt.format(cal.getResultadoF())));
					}else{
					cal.setResultadoF((cal.getDataA().getTime()+chamada.getDuracaoLigacao().getTime()));
								
					cal.setDataA(sdt.parse(sdt.format(cal.getResultadoF())));
					}
					
					chamadasVinculados.add(chamada);
					}
				}
			
			cal.setResultadoF(0);
			
			if(chamadasVinculados.isEmpty() && (chamadasLista.size()==i)){
				chamadasVinculados = new ArrayList<Chamadas>();
				chamadasVinculados.add(new Chamadas());
				System.out.println("Passei aqui CHAMADAS");
			}
			
			i=0;
			for (Resumo resumo : resumosLista) {
				if(linha.equals(resumo.getLinha())){
					resumoVinculados.add(resumo);
				}
				
				if(!(resumoVinculados.isEmpty()) ){
				if (resumoVinculados.get(0).getDataDesativ() != null) {
					long diasTotal = ((planosVinculados.get(0).getDataFimCiclo().getTime() - planosVinculados.get(0).getDataIniCiclo().getTime() + 3600000)
							/ 86400000L);
					long diasUtilizado = ((resumoVinculados.get(0).getDataDesativ().getTime() - planosVinculados.get(0).getDataIniCiclo().getTime()
							+ 3600000) / 86400000L);
					float valor = 4.9f;

					cal.setResultadoF((valor / diasTotal) * diasUtilizado);

				} else {
					cal.setResultadoF(4.9f);

				}
				}else{
					cal.setResultadoF(4.9f);
				}
			}
			if(resumoVinculados.isEmpty() && (resumosLista.size()==i)){
				resumoVinculados = new ArrayList<Resumo>();
					resumoVinculados.add(new Resumo());
					cal.setResultadoF(4.9f);
					System.out.println("Passei aqui RESUMO");
				}
			i=0;
			for (Servicos servico : servicosLista) {
				++i;
				if(linha.equals(servico.getLinha())){
					servicosVinculados.add(servico);
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
				
			}
			servicosPorCategoria.add(servicoCategoria);
			
			if(servicosVinculados.isEmpty() && (servicosLista.size()==i)){
				servicosVinculados = new ArrayList<Servicos>();
				servicosVinculados.add(new Servicos());
				servicosPorCategoria = new ArrayList<ServicosCategoria>();
				servicosPorCategoria.add(new ServicosCategoria());
				System.out.println("Passei aqui SERVICOS");
			}
			
			float valorTotal = this.valorTotal(chamadasVinculados, servicosVinculados, planosVinculados) + cal.getResultadoF();

			cal.setFloatA(cal.getResultadoF());
			cal.setFloatB(valorTotal);

			cal.setResultadoF(0);
			
			AlocacaoFatura alocacaoFatura = new AlocacaoFatura();
		
			if (valorTotal > limiteAtesto.getValorLimite()) {
				alocacaoFatura.setRessarcimento(true);
				sei.enviarMemorando(alocacao, gerarMemorando(request));
				alocacaoFatura.setDocumentoSei(sei.enviarFatura(alocacao.getIdAlocacao(), alocacao.getAlocacaoSei().getNumeroProcessoSei(),
						gerarPdfFatura(fatura, alocacao, cal, chamadasVinculados, planosVinculados, servicosPorCategoria,
								resumoVinculados, request)));
				mailer.enviarAtestoFatura(alocacao, fatura);
				
			} else {
				alocacaoFatura.setRessarcimento(false);
				alocacaoFatura.setDocumentoSei(sei.enviarFatura(alocacao.getIdAlocacao(), alocacao.getAlocacaoSei().getNumeroProcessoSei(),
						gerarPdfFatura(fatura, alocacao, cal, chamadasVinculados, planosVinculados, servicosPorCategoria,
								resumoVinculados, request)));
				mailer.enviarAtestoFatura(alocacao, fatura);
			}
			
			alocacaoFatura.setAlocacao(alocacao);
			alocacaoFatura.setFatura(fatura);
			alocacaoService.salvar(alocacaoFatura);
			alocacoesFaturas.add(alocacaoFatura);			
			
			}
				cal = new CalculadorDTO();
				planosVinculados = new ArrayList<Planos>();
				servicosVinculados = new ArrayList<Servicos>();
				chamadasVinculados = new ArrayList<Chamadas>();
				resumoVinculados = new ArrayList<Resumo>();
				servicosPorCategoria = new ArrayList<ServicosCategoria>();
				servicosVinculados = new ArrayList<Servicos>();
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
		ModelAndView mv = new ModelAndView("Resumo");
		CalculadorDTO cal = new CalculadorDTO();

		Fatura fatura = faturaService.getFatura(idFatura);
		Alocacao alocacao = alocacaoService.getAlocacao(idAlocacao);

		List<Resumo> resumosLista = resumoService.getResumoFaturaLinha(fatura, alocacao.getLinha());
		List<Chamadas> chamadasLista = chamadaService.getChamadasFaturaLinha(fatura, alocacao.getLinha());
		List<Planos> planosLista = planoService.getPlanoFaturaLinha(fatura, alocacao.getLinha());
		List<Servicos> servicosLista = servicoService.getServicosFaturaLinha(fatura, alocacao.getLinha());

		List<Planos> planosVinculados = new ArrayList<Planos>();
		List<Servicos> servicosVinculados = new ArrayList<Servicos>();
		List<Chamadas> chamadasVinculados = new ArrayList<Chamadas>();
		List<Resumo> resumoVinculados = new ArrayList<Resumo>();
		List<ServicosCategoria> servicosPorCategoria = new ArrayList<ServicosCategoria>();
		ServicosCategoria servicoCategoria = null;
		int i;

		//PLANOS
		for(Planos plano : planosLista){
			if(alocacao.getLinha().equals(plano.getLinha())){
				planosVinculados.add(plano);		
			}
		}

		//CHAMADAS
		i=0;
		for(Chamadas chamada : chamadasLista){
			++i;
			if(alocacao.getLinha().equals(chamada.getLinha())){
				if(cal.getDataA()==null){
					cal.setResultadoF(chamada.getDuracaoLigacao().getTime());
					cal.setDataA(sdt.parse(sdt.format(cal.getResultadoF())));
				}else{
				cal.setResultadoF((cal.getDataA().getTime()+chamada.getDuracaoLigacao().getTime()));
							
				cal.setDataA(sdt.parse(sdt.format(cal.getResultadoF())));
				}
				
				chamadasVinculados.add(chamada);
				}
			}
		
		cal.setResultadoF(0);
		
		if(chamadasVinculados.isEmpty() && (chamadasLista.size()==i)){
			chamadasVinculados = new ArrayList<Chamadas>();
			chamadasVinculados.add(new Chamadas());
			System.out.println("Passei aqui CHAMADAS");
		}
		
		//RESUMO
		i=0;
		for (Resumo resumo : resumosLista) {
			if(alocacao.getLinha().equals(resumo.getLinha())){
				resumoVinculados.add(resumo);
			}
			
			if(!(resumoVinculados.isEmpty()) ){
			if (resumoVinculados.get(0).getDataDesativ() != null) {
				long diasTotal = ((planosVinculados.get(0).getDataFimCiclo().getTime() - planosVinculados.get(0).getDataIniCiclo().getTime() + 3600000)
						/ 86400000L);
				long diasUtilizado = ((resumoVinculados.get(0).getDataDesativ().getTime() - planosVinculados.get(0).getDataIniCiclo().getTime()
						+ 3600000) / 86400000L);
				float valor = 4.9f;

				cal.setResultadoF((valor / diasTotal) * diasUtilizado);

			} else {
				cal.setResultadoF(4.9f);

			}
			}else{
				cal.setResultadoF(4.9f);
			}
		}
		if(resumoVinculados.isEmpty() && (resumosLista.size()==i)){
			resumoVinculados = new ArrayList<Resumo>();
				resumoVinculados.add(new Resumo());
				cal.setResultadoF(4.9f);
				System.out.println("Passei aqui RESUMO");
			}
		
		//SERVIÇOS
		i=0;
		for (Servicos servico : servicosLista) {
			++i;
			if(alocacao.getLinha().equals(servico.getLinha())){
				servicosVinculados.add(servico);
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
			
		}
		servicosPorCategoria.add(servicoCategoria);
		
		if(servicosVinculados.isEmpty() && (servicosLista.size()==i)){
			servicosVinculados = new ArrayList<Servicos>();
			servicosVinculados.add(new Servicos());
			servicosPorCategoria = new ArrayList<ServicosCategoria>();
			servicosPorCategoria.add(new ServicosCategoria());
			System.out.println("Passei aqui SERVICOS");
		}
		
		//Calculador de Totais
		float valorTotal = this.valorTotal(chamadasVinculados, servicosVinculados, planosVinculados) + cal.getResultadoF();

		cal.setFloatA(cal.getResultadoF());
		cal.setFloatB(valorTotal);

		cal.setResultadoF(0);
		
		Planos planoDatas = new Planos();
		planoDatas.setDataIniCiclo(planosVinculados.get(0).getDataIniCiclo());
		planoDatas.setDataFimCiclo(planosVinculados.get(0).getDataFimCiclo());
		mv.addObject("alocacao", alocacao);
		mv.addObject("fatura", fatura);
		mv.addObject("pacote", cal);
		mv.addObject("chamadas", chamadasVinculados);
		mv.addObject("planos", planosVinculados);
		mv.addObject("planoData", planoDatas);
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

	private byte[] gerarPdfFatura(Fatura fatura, Alocacao alocacao, CalculadorDTO cal, List<Chamadas> chamadas,
			List<Planos> planos, List<ServicosCategoria> servicosPorCategoria, List<Resumo> resumo,
			HttpServletRequest request) throws Exception {
		Planos planoDatas = new Planos();
		planoDatas.setDataIniCiclo(planos.get(0).getDataIniCiclo());
		planoDatas.setDataFimCiclo(planos.get(0).getDataFimCiclo());
		Context context = new Context();
		context.setVariable("alocacao", alocacao);
		context.setVariable("fatura", fatura);
		context.setVariable("pacote", cal);
		context.setVariable("resumos", resumo);
		context.setVariable("chamadas", chamadas);
		context.setVariable("planos", planos);
		context.setVariable("planoData", planoDatas);
		context.setVariable("servicos", servicosPorCategoria);

		context.setLocale(locale.resolveLocale(request));
		String template = tempEngine.process("Resumo", context);

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