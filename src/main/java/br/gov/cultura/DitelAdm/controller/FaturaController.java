package br.gov.cultura.DitelAdm.controller;

import java.io.ByteArrayOutputStream;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
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
import br.gov.cultura.DitelAdm.model.Usuario;
import br.gov.cultura.DitelAdm.model.dtos.CalculadorDTO;
import br.gov.cultura.DitelAdm.model.dtos.FaturaArquivoDTO;
import br.gov.cultura.DitelAdm.model.dtos.ServicosCategoria;
import br.gov.cultura.DitelAdm.model.faturasV3.Chamadas;
import br.gov.cultura.DitelAdm.model.faturasV3.Fatura;
import br.gov.cultura.DitelAdm.model.faturasV3.Planos;
import br.gov.cultura.DitelAdm.model.faturasV3.Resumo;
import br.gov.cultura.DitelAdm.model.faturasV3.Servicos;
import br.gov.cultura.DitelAdm.service.AlocacaoService;
import br.gov.cultura.DitelAdm.service.CadastroUsuarioService;
import br.gov.cultura.DitelAdm.service.ChamadasService;
import br.gov.cultura.DitelAdm.service.FaturaService;
import br.gov.cultura.DitelAdm.service.PlanoService;
import br.gov.cultura.DitelAdm.service.ResumoService;
import br.gov.cultura.DitelAdm.service.ServicoService;
import br.gov.cultura.DitelAdm.ws.SeiClient;

/**
 * Controlador responsavel pela funcionalidade de GERAR e ENVIAR faturamento
 * registrado na base de dados através do FaturaUploadController
 */
@Controller
@RequestMapping("/fatura")
public class FaturaController {

	private final String CADASTRO_VIEW = "ExecutarFatura";

	@Autowired
	private CadastroUsuarioService usuarioService;

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

/*		for (Alocacao alocacao : alocacaoList) {
			if (alocacao.getDtDevolucao() == null) {
				br.gov.cultura.DitelAdm.wsdl.Usuario usuarioSei = sei.ValidaUsuarioUnidade(alocacao);
				if (usuarioSei == null) {
					if (!usuarioErrorSei.contains(alocacao.getUsuario())) {

						usuarioErrorSei.add(alocacao.getUsuario());
					}
				}
			}
		}
*/
		// verificar
		List<Fatura> faturasNaoGeradas = faturaService.getFaturasNaoGeradas();
		List<AlocacaoFatura> faturasGeradas = alocacaoService.getIdAlocacaoFatura();
		mv.addObject("faturasNaoGeradas", faturasNaoGeradas);
		mv.addObject("faturasGeradas", faturasGeradas);
		/*mv.addObject("erroUnidade", usuarioErrorSei);*/
		return mv;
	}

	/** EXECUTA A FUNÇÃO DE GERAR FATURAS E ENVIO PARA SEI */
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ModelAndView executarFatura(HttpServletRequest request, Model model) throws Exception {
		ModelAndView mv = new ModelAndView("FaturaLinhas");
		SimpleDateFormat sdt = new SimpleDateFormat("HH:mm:ss");
		CalculadorDTO cal = new CalculadorDTO();

		Integer idFatura = Integer.parseInt(request.getParameter("fatura"));

		Fatura fatura = faturaService.getFatura(idFatura);
		List<Planos> planosLista = planoService.getPlanoFatura(fatura);
		List<Resumo> resumosLista = resumoService.getResumoFatura(fatura);
		List<Servicos> servicosLista = servicoService.getServicosFatura(fatura);
		List<Chamadas> chamadasLista = chamadaService.getChamadasFatura(fatura);
		List<Usuario> usuarioLista = usuarioService.getIdUsuario();

		List<Alocacao> alocacaoListaUsuario = new ArrayList<Alocacao>();
		List<AlocacaoFatura> alocacoesFaturas = new ArrayList<AlocacaoFatura>();
		List<Planos> planosVinculados = new ArrayList<Planos>();
		List<Servicos> servicosVinculados = new ArrayList<Servicos>();
		List<Chamadas> chamadasVinculados = new ArrayList<Chamadas>();
		List<Resumo> resumoVinculados = new ArrayList<Resumo>();
		List<ServicosCategoria> servicosPorCategoria = new ArrayList<ServicosCategoria>();
		ServicosCategoria servicoCategoria = null;
		List<FaturaArquivoDTO> faturaDTOLista = new ArrayList<FaturaArquivoDTO>();
		FaturaArquivoDTO faturaDTO = new FaturaArquivoDTO();

		int i;

		while (usuarioLista.size() != 0) {
			if (usuarioLista.size() != 0)
				for (Usuario usuario : usuarioLista) {
					faturaDTOLista = new ArrayList<FaturaArquivoDTO>();
					AlocacaoFatura alocacaoFatura = new AlocacaoFatura();
					;

					cal.setValorTotalAtesto(0);
					List<Alocacao> alocacaoRepasse = new ArrayList<Alocacao>();
					alocacaoListaUsuario = alocacaoService.getAlocacoesUsuario(usuario);
					usuarioLista.remove(usuario);
					if (!alocacaoListaUsuario.isEmpty()) {

						LimiteAtesto limiteAtesto = usuario.getLimiteAtesto();

						for (Alocacao alocacao : alocacaoListaUsuario) {

							/* PLANOS */
							for (Planos plano : planosLista) {
								if (alocacao.getLinha().equals(plano.getLinha())) {

									if (((plano.getDataIniCiclo().compareTo(alocacao.getDtRecebido()) > 0)
											|| (plano.getDataIniCiclo().compareTo(alocacao.getDtRecebido()) == 0)
											|| (alocacao.getDtRecebido().getMonth() == plano.getDataIniCiclo()
													.getMonth()
													&& alocacao.getDtRecebido().getYear() == plano.getDataIniCiclo()
															.getYear()))
											&& ((plano.getDataFimCiclo().compareTo(alocacao.getDtRecebido()) > 0)
													|| (plano.getDataFimCiclo()
															.compareTo(alocacao.getDtRecebido()) == 0)
													|| (alocacao.getDtRecebido().getMonth() == plano.getDataFimCiclo()
															.getMonth()
															&& alocacao.getDtRecebido().getYear() == plano
																	.getDataFimCiclo().getYear()))) {

										if (alocacao.getDtDevolucao() != null) {
											if ((plano.getDataIniCiclo().compareTo(alocacao.getDtDevolucao()) < 0
													|| plano.getDataIniCiclo()
															.compareTo(alocacao.getDtDevolucao()) == 0)
													&& (plano.getDataFimCiclo().compareTo(alocacao.getDtDevolucao()) < 0
															|| plano.getDataFimCiclo()
																	.compareTo(alocacao.getDtDevolucao()) == 0
															|| (plano.getDataFimCiclo().getMonth() == alocacao
																	.getDtDevolucao().getMonth()
																	&& plano.getDataFimCiclo().getYear() == alocacao
																			.getDtDevolucao().getYear()))) {
												planosVinculados.add(plano);
											}

										} else if (alocacao.getDtDevolucao() == null) {

											planosVinculados.add(plano);
										}
									} else if (((alocacao.getDtRecebido().getMonth() == plano.getDataIniCiclo()
											.getMonth()
											&& alocacao.getDtRecebido().getYear() == plano.getDataIniCiclo().getYear())
											|| (alocacao.getDtDevolucao().getMonth() == plano.getDataIniCiclo()
													.getMonth()
													&& alocacao.getDtDevolucao().getYear() == plano.getDataIniCiclo()
															.getYear()))
											|| ((alocacao.getDtRecebido().getMonth() == plano.getDataFimCiclo()
													.getMonth()
													&& alocacao.getDtRecebido().getYear() == plano.getDataFimCiclo()
															.getYear())
													|| (alocacao.getDtDevolucao().getMonth() == plano.getDataFimCiclo()
															.getMonth()
															&& alocacao.getDtDevolucao().getYear() == plano
																	.getDataFimCiclo().getYear()))) {
										planosVinculados.add(plano);
									}
								}
							}

							/* CHAMADAS */
							if (planosVinculados.size() != 0) {
								alocacaoRepasse.add(alocacao);
								faturaDTO.setPlanos(planosVinculados);
								faturaDTO.setAlocacao(alocacao);
								i = 0;
								for (Chamadas chamada : chamadasLista) {
									++i;
									if (alocacao.getLinha().equals(chamada.getLinha())) {
										if (((chamada.getDataLigacao().compareTo(alocacao.getDtRecebido()) > 0)
												|| (chamada.getDataLigacao().compareTo(alocacao.getDtRecebido()) == 0))
												&& ((chamada.getHoraLigacao().getHours() > alocacao.getDtRecebido()
														.getHours()
														|| chamada.getHoraLigacao().getHours() == alocacao
																.getDtRecebido().getHours())
														&& (chamada.getHoraLigacao().getMinutes() > alocacao
																.getDtRecebido().getMinutes()
																|| chamada.getHoraLigacao().getMinutes() == alocacao
																		.getDtRecebido().getMinutes())
														&& (chamada.getHoraLigacao().getSeconds() > alocacao
																.getDtRecebido().getSeconds()
																|| chamada.getHoraLigacao().getSeconds() == alocacao
																		.getDtRecebido().getSeconds()))) {
											if (alocacao.getDtDevolucao() != null) {
												if (((chamada.getDataLigacao().compareTo(alocacao.getDtDevolucao()) < 0)
														|| (chamada.getDataLigacao()
																.compareTo(alocacao.getDtDevolucao()) == 0))
														&& (chamada.getHoraLigacao()
																.compareTo(alocacao.getDtDevolucao()) < 0
																|| chamada.getHoraLigacao()
																		.compareTo(alocacao.getDtDevolucao()) == 0)

												) {

													if (cal.getDataA() == null) {
														cal.setResultadoF(chamada.getDuracaoLigacao().getTime());
														cal.setDataA(sdt.parse(sdt.format(cal.getResultadoF())));
														faturaDTO.setTotalHorasChamadas(cal.getDataA());
													} else {
														cal.setResultadoF((cal.getDataA().getTime()
																+ chamada.getDuracaoLigacao().getTime()));
														cal.setDataA(sdt.parse(sdt.format(cal.getResultadoF())));
														faturaDTO.setTotalHorasChamadas(cal.getDataA());

													}
													chamadasVinculados.add(chamada);
												}
											} else if (alocacao.getDtDevolucao() == null) {
												if (cal.getDataA() == null) {
													cal.setResultadoF(chamada.getDuracaoLigacao().getTime());
													cal.setDataA(sdt.parse(sdt.format(cal.getResultadoF())));
													faturaDTO.setTotalHorasChamadas(cal.getDataA());
												} else {
													cal.setResultadoF((cal.getDataA().getTime()
															+ chamada.getDuracaoLigacao().getTime()));
													cal.setDataA(sdt.parse(sdt.format(cal.getResultadoF())));
													faturaDTO.setTotalHorasChamadas(cal.getDataA());
												}

												chamadasVinculados.add(chamada);
											}
										}
									}
								}
								cal.setResultadoF(0);

								if (chamadasVinculados.isEmpty() && (chamadasLista.size() == i)) {
									chamadasVinculados = new ArrayList<Chamadas>();
									chamadasVinculados.add(new Chamadas());
								}

								if (!chamadasVinculados.isEmpty()) {
									chamadasVinculados.sort(Comparator.comparing(Chamadas::getDataLigacao)
											.thenComparing(Chamadas::getHoraLigacao));
								}

								faturaDTO.setChamadas(chamadasVinculados);
								/* RESUMO */
								i = 0;
								for (Resumo resumo : resumosLista) {
									if (alocacao.getLinha().equals(resumo.getLinha())) {
										resumoVinculados.add(resumo);
									}

									if (!(resumoVinculados.isEmpty())) {
										if (resumoVinculados.get(0).getDataDesativ() != null) {
											long diasTotal = ((planosVinculados.get(0).getDataFimCiclo().getTime()
													- planosVinculados.get(0).getDataIniCiclo().getTime() + 3600000)
													/ 86400000L);
											long diasUtilizado = ((resumoVinculados.get(0).getDataDesativ().getTime()
													- planosVinculados.get(0).getDataIniCiclo().getTime() + 3600000)
													/ 86400000L);
											float valor = 4.9f;

											cal.setResultadoF((valor / diasTotal) * diasUtilizado);

										} else {
											cal.setResultadoF(4.9f);

										}
									} else {
										cal.setResultadoF(4.9f);
									}
								}
								if (resumoVinculados.isEmpty() && (resumosLista.size() == i)) {
									resumoVinculados = new ArrayList<Resumo>();
									resumoVinculados.add(new Resumo());
									cal.setResultadoF(4.9f);
								}
								faturaDTO.setResumo(resumoVinculados);

								/* SERVICOS */
								i = 0;
								for (Servicos servico : servicosLista) {
									++i;
									if (alocacao.getLinha().equals(servico.getLinha())) {

										if (((servico.getDataServico().compareTo(alocacao.getDtRecebido()) > 0)
												|| (servico.getDataServico().compareTo(alocacao.getDtRecebido()) == 0))
												&& (((servico.getHoraServico().getHours() > alocacao.getDtRecebido()
														.getHours()
														|| servico.getHoraServico().getHours() == alocacao
																.getDtRecebido().getHours())
														&& (servico.getHoraServico().getMinutes() > alocacao
																.getDtRecebido().getMinutes()
																|| servico.getHoraServico().getMinutes() == alocacao
																		.getDtRecebido().getMinutes())
														&& (servico.getHoraServico().getSeconds() > alocacao
																.getDtRecebido().getSeconds()
																|| servico.getHoraServico().getSeconds() == alocacao
																		.getDtRecebido().getSeconds())))) {

											if (alocacao.getDtDevolucao() != null) {

												if (((servico.getDataServico().compareTo(alocacao.getDtDevolucao()) < 0)
														|| (servico.getDataServico()
																.compareTo(alocacao.getDtDevolucao()) == 0))
														&& ((servico.getHoraServico()
																.compareTo(alocacao.getDtDevolucao()) < 0)
																|| (servico.getHoraServico()
																		.compareTo(alocacao.getDtDevolucao()) == 0))) {

													servicosVinculados.add(servico);
													if (servicoCategoria == null) {
														servicoCategoria = new ServicosCategoria();
													} else if (servicoCategoria.getCategoria()
															.getCodCatServico() != servico.getCategoriaservico()
																	.getCodCatServico()) {
														servicosPorCategoria.add(servicoCategoria);
														servicoCategoria = new ServicosCategoria();
													}
													servicoCategoria.setCategoria(servico.getCategoriaservico());
													if (servico.getUnidadeServico().equals("MB")
															|| servico.getUnidadeServico().equals("KB")){
														servicoCategoria
																.setQuantidade((servico.getUnidadeServico().equals("KB")
																		? servico.getQuantUtil() / 1000
																		: servico.getQuantUtil())
																		+ servicoCategoria.getQuantidade());
													}else{
														servicoCategoria
																.setQuantidade(servicoCategoria.getQuantidade() + 1);
													servicoCategoria.setTarifa(servico.getValServImp());
													servicoCategoria.setValorCobrado(servico.getValServImp()
															+ servicoCategoria.getValorCobrado());
													servicoCategoria.setValorTotal(
															servico.getValServImp() + servicoCategoria.getValorTotal());
													}
												}
										} else if (alocacao.getDtDevolucao() == null) {

											servicosVinculados.add(servico);
											if (servicoCategoria == null) {
												servicoCategoria = new ServicosCategoria();
											} else if (servicoCategoria.getCategoria().getCodCatServico() != servico
													.getCategoriaservico().getCodCatServico()) {
												servicosPorCategoria.add(servicoCategoria);
												servicoCategoria = new ServicosCategoria();
											}
											servicoCategoria.setCategoria(servico.getCategoriaservico());
											if (servico.getUnidadeServico().equals("MB")
													|| servico.getUnidadeServico().equals("KB")) {
												servicoCategoria.setQuantidade((servico.getUnidadeServico().equals("KB")
														? servico.getQuantUtil() / 1000 : servico.getQuantUtil())
														+ servicoCategoria.getQuantidade());
											} else {
												servicoCategoria.setQuantidade(servicoCategoria.getQuantidade() + 1);
												servicoCategoria.setTarifa(servico.getValServImp());
												servicoCategoria.setValorCobrado(
														servico.getValServImp() + servicoCategoria.getValorCobrado());
												servicoCategoria.setValorTotal(
														servico.getValServImp() + servicoCategoria.getValorTotal());

											}
										}
											
									}
								}
								}
						
								servicosPorCategoria.add(servicoCategoria);
						if (servicosVinculados.isEmpty() && (servicosLista.size() == i)) {
							servicosVinculados = new ArrayList<Servicos>();
							servicosVinculados.add(new Servicos());
							servicosPorCategoria = new ArrayList<ServicosCategoria>();
							servicosPorCategoria.add(new ServicosCategoria());
						}

						faturaDTO.setServicosCategoria(servicosPorCategoria);

						/* Calculador de Totais */
						float valorTotal = this.valorTotal(chamadasVinculados, servicosVinculados, planosVinculados)
								+ cal.getResultadoF();

						faturaDTO.setValorContratoPlano(cal.getResultadoF());
						cal.setFloatA(cal.getResultadoF());

						faturaDTO.setValorTotal(valorTotal);
						cal.setFloatB(valorTotal);

						cal.setResultadoF(0);
						cal.setValorTotalAtesto(cal.getValorTotalAtesto() + valorTotal);

					}

							/* SALVA FATURA GERADA E INFORMAÇÕES */
							alocacaoFatura.setAlocacao(alocacao);
							alocacaoFatura.setFatura(fatura);
							alocacaoService.salvar(alocacaoFatura);
							alocacoesFaturas.add(alocacaoFatura);
							

					cal.setFloatA(0);
					cal.setFloatB(0);
					faturaDTOLista.add(faturaDTO);

					alocacaoFatura = new AlocacaoFatura();
					faturaDTO = new FaturaArquivoDTO();
					planosVinculados = new ArrayList<Planos>();
					servicosVinculados = new ArrayList<Servicos>();
					chamadasVinculados = new ArrayList<Chamadas>();
					resumoVinculados = new ArrayList<Resumo>();
					servicosPorCategoria = new ArrayList<ServicosCategoria>();
					servicosVinculados = new ArrayList<Servicos>();
				}

			/* ATESTOS E ENVIO PARA O SEI FATURA GERADA */
			if (cal.getValorTotalAtesto() > limiteAtesto.getValorLimite()) {
				for (AlocacaoFatura alocacaoFaturaRessarcimento : alocacoesFaturas) {
					alocacaoFaturaRessarcimento.setRessarcimento(true);
					alocacaoService.salvar(alocacaoFaturaRessarcimento);
					cal = new CalculadorDTO();
				}
				sei.enviarMemorando(alocacaoRepasse, gerarMemorando(request));

				// Condessar fatura aqui

				alocacaoFatura.setDocumentoSei(
						sei.enviarFaturasCompostas(alocacaoRepasse, gerarPdfFaturaComposta(faturaDTOLista, request)));

				// mailer.enviarAtestoFatura(alocacao, fatura);

				servicosPorCategoria = new ArrayList<ServicosCategoria>();

			}

		}
		if (usuarioLista.size() == 0) {

			break;
		}
	}

	}
		mv.addObject("alocacoesSei",alocacoesFaturas);
		mv.addObject("fatura",fatura);

	return mv;}

	/** CONSULTA FATURAS JÁ GERADAS E ENVIADAS */
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

		// PLANOS
		for (Planos plano : planosLista) {
			if (alocacao.getLinha().equals(plano.getLinha())) {
				planosVinculados.add(plano);
			}
		}

		// CHAMADAS
		i = 0;
		for (Chamadas chamada : chamadasLista) {
			++i;
			if (alocacao.getLinha().equals(chamada.getLinha())) {
				if (cal.getDataA() == null) {
					cal.setResultadoF(chamada.getDuracaoLigacao().getTime());
					cal.setDataA(sdt.parse(sdt.format(cal.getResultadoF())));
				} else {
					cal.setResultadoF((cal.getDataA().getTime() + chamada.getDuracaoLigacao().getTime()));

					cal.setDataA(sdt.parse(sdt.format(cal.getResultadoF())));
				}

				chamadasVinculados.add(chamada);
			}
		}

		cal.setResultadoF(0);

		if (chamadasVinculados.isEmpty() && (chamadasLista.size() == i)) {
			chamadasVinculados = new ArrayList<Chamadas>();
			chamadasVinculados.add(new Chamadas());
		}

		// RESUMO
		i = 0;
		for (Resumo resumo : resumosLista) {
			if (alocacao.getLinha().equals(resumo.getLinha())) {
				resumoVinculados.add(resumo);
			}

			if (!(resumoVinculados.isEmpty())) {
				if (resumoVinculados.get(0).getDataDesativ() != null) {
					long diasTotal = ((planosVinculados.get(0).getDataFimCiclo().getTime()
							- planosVinculados.get(0).getDataIniCiclo().getTime() + 3600000) / 86400000L);
					long diasUtilizado = ((resumoVinculados.get(0).getDataDesativ().getTime()
							- planosVinculados.get(0).getDataIniCiclo().getTime() + 3600000) / 86400000L);
					float valor = 4.9f;

					cal.setResultadoF((valor / diasTotal) * diasUtilizado);

				} else {
					cal.setResultadoF(4.9f);

				}
			} else {
				cal.setResultadoF(4.9f);
			}
		}
		if (resumoVinculados.isEmpty() && (resumosLista.size() == i)) {
			resumoVinculados = new ArrayList<Resumo>();
			resumoVinculados.add(new Resumo());
			cal.setResultadoF(4.9f);
		}

		// SERVIÇOS
		i = 0;
		for (Servicos servico : servicosLista) {
			++i;
			if (alocacao.getLinha().equals(servico.getLinha())) {
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
					servicoCategoria
							.setQuantidade((servico.getUnidadeServico().equals("KB") ? servico.getQuantUtil() / 1000
									: servico.getQuantUtil()) + servicoCategoria.getQuantidade());
				else
					servicoCategoria.setQuantidade(servicoCategoria.getQuantidade() + 1);
				servicoCategoria.setTarifa(servico.getValServImp());
				servicoCategoria.setValorCobrado(servico.getValServImp() + servicoCategoria.getValorCobrado());
				servicoCategoria.setValorTotal(servico.getValServImp() + servicoCategoria.getValorTotal());
			}

		}
		servicosPorCategoria.add(servicoCategoria);

		if (servicosVinculados.isEmpty() && (servicosLista.size() == i)) {
			servicosVinculados = new ArrayList<Servicos>();
			servicosVinculados.add(new Servicos());
			servicosPorCategoria = new ArrayList<ServicosCategoria>();
			servicosPorCategoria.add(new ServicosCategoria());
		}

		// Calculador de Totais
		float valorTotal = this.valorTotal(chamadasVinculados, servicosVinculados, planosVinculados)
				+ cal.getResultadoF();

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

	// CACULA VALORES DA FATURA
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

	// GERA PDF FATURA
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

	// FATURA COMPOSTA E AGREGADAS
	private byte[] gerarPdfFaturaComposta(List<FaturaArquivoDTO> faturaDTO, HttpServletRequest request)
			throws Exception {
		Context context = new Context();
		context.setVariable("fatura", faturaDTO);

		context.setLocale(locale.resolveLocale(request));
		String template = tempEngine.process("ResumoFaturaComposta", context);

		ITextRenderer renderer = new ITextRenderer();
		renderer.setDocumentFromString(template);

		renderer.layout();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		renderer.createPDF(baos);

		return baos.toByteArray();

	}

	// ENVIA MEMORANDO DE FATURAMENTO
	private byte[] gerarMemorando(HttpServletRequest request) throws Exception {
		View view = this.viewResolver.resolveViewName("/documentos/memorandos/MemorandoFaturaTelefonica",
				locale.resolveLocale(request));
		MockHttpServletResponse mockResp = new MockHttpServletResponse();
		view.render(new ModelAndView().getModelMap(), request, mockResp);

		return mockResp.getContentAsByteArray();
	}
}