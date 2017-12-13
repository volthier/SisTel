package br.gov.cultura.DitelAdm.controller;

import java.io.ByteArrayOutputStream;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
import br.gov.cultura.DitelAdm.service.ldap.ConsultaLdapService;
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
	
	@Autowired
	private ConsultaLdapService ldap;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody ModelAndView executarFatura() throws RemoteException {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);

		List<Fatura> faturasOperadorasNaoGeradas = faturaService.getFaturasNaoGeradas();
		List<Fatura> faturasOperadorasGeradas = faturaService.getFaturasGeradas();
		List<AlocacaoFatura> faturasGeradasLinhas = alocacaoService.getIdAlocacaoFatura();
		mv.addObject("faturasNaoGeradas", faturasOperadorasNaoGeradas);
		mv.addObject("faturasGeradasLinhas", faturasGeradasLinhas);
		mv.addObject("faturasGeradas",faturasOperadorasGeradas);
		
		ldap.usuarioInfos(mv);
		return mv;
	}

	/** EXECUTA A FUNÇÃO DE GERAR FATURAS E ENVIO PARA SEI */
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ModelAndView executarFatura(@RequestParam(value = "fatura") int[] idFatura,
			HttpServletRequest request, Model model) throws Exception {
		ModelAndView mv = new ModelAndView("FaturaLinhas");
		SimpleDateFormat sdt = new SimpleDateFormat("HH:mm:ss");
		CalculadorDTO cal = new CalculadorDTO();

		List<AlocacaoFatura> alocacoesFaturas = new ArrayList<AlocacaoFatura>();
		List<Fatura> faturaLista = new ArrayList<Fatura>();
		List<Usuario> usuarioLista = usuarioService.getIdUsuario();

		List<Alocacao> alocacaoListaUsuario = new ArrayList<>();

		List<Planos> planosVinculados = new ArrayList<Planos>();
		List<Servicos> servicosVinculados = new ArrayList<Servicos>();
		List<Chamadas> chamadasVinculados = new ArrayList<Chamadas>();
		List<Resumo> resumoVinculados = new ArrayList<Resumo>();
		List<ServicosCategoria> servicosPorCategoria = new ArrayList<ServicosCategoria>();
		ServicosCategoria servicoCategoria = null;
		List<FaturaArquivoDTO> faturaDTOLista = new ArrayList<FaturaArquivoDTO>();
		FaturaArquivoDTO faturaDTO = new FaturaArquivoDTO();

		int i, not;
		int usercounter = 0;

		not = 0;

		while (usuarioLista.size() != 0 && usercounter != usuarioLista.size()) {
			if (usuarioLista.size() != 0 && usercounter != usuarioLista.size()) {
				for (Usuario usuario : usuarioLista) {
					
					faturaDTOLista = new ArrayList<FaturaArquivoDTO>();
					AlocacaoFatura alocacaoFatura = new AlocacaoFatura();
					List<Alocacao> alocacaoRepasse = new ArrayList<Alocacao>();
					cal.setValorTotalAtesto(0);
					
					alocacaoListaUsuario = alocacaoService.getAlocacoesUsuario(usuario);
					usercounter++;

					if (!alocacaoListaUsuario.isEmpty()) {

						for (int count = 0; count < idFatura.length; count++) {

							Fatura fatura = faturaService.getFatura(idFatura[count]);
							List<Planos> planosLista = new ArrayList<Planos>(fatura.getPlanoses());
							List<Resumo> resumosLista = new ArrayList<Resumo>(fatura.getResumos());
							List<Servicos> servicosLista = new ArrayList<Servicos>(fatura.getServicoses());
							List<Chamadas> chamadasLista = new ArrayList<Chamadas>(fatura.getChamadases());

							for (Alocacao alocacao : alocacaoListaUsuario) {

								not = 0;

								Boolean validador = planosLista.stream()
										.filter(p -> p.getLinha() != null && p.getLinha().getNumeroLinha() != null
												&& p.getLinha().getNumeroLinha().trim() == alocacao.getLinha()
														.getNumeroLinha().trim())
										.findAny().isPresent();
								if (validador == false) {
									not += 1;
								}
								;

								validador = servicosLista.stream()
										.filter(p -> p.getLinha() != null && p.getLinha().getNumeroLinha() != null
												&& p.getLinha().getNumeroLinha().trim() == alocacao.getLinha()
														.getNumeroLinha().trim())
										.findAny().isPresent();
								if (validador == false) {
									not += 1;
								}
								;

								validador = chamadasLista.stream()
										.filter(p -> p.getLinha() != null && p.getLinha().getNumeroLinha() != null
												&& p.getLinha().getNumeroLinha().trim() == alocacao.getLinha()
														.getNumeroLinha().trim())
										.findAny().isPresent();
								if (validador == false) {
									not += 1;
								}
								;

								validador = resumosLista.stream()
										.filter(p -> p.getLinha() != null && p.getLinha().getNumeroLinha() != null
												&& p.getLinha().getNumeroLinha().trim() == alocacao.getLinha()
														.getNumeroLinha().trim())
										.findAny().isPresent();
								if (validador == false) {
									not += 1;
								}
								;

								/* PLANOS */

								validador = planosLista.stream()
										.filter(p -> p.getLinha() != null && p.getLinha().getNumeroLinha() != null
												&& p.getLinha().getNumeroLinha().trim() == alocacao.getLinha()
														.getNumeroLinha().trim())
										.findAny().isPresent();

								boolean valorPlano = false;

								if (validador == true) {

									valorPlano = true;
									for (Planos plano : planosLista) {
										if (alocacao.getLinha().equals(plano.getLinha())) {

											LocalDate alRecebimento, dataInicio, dataFim, alDevolucao;
											alDevolucao = null;

											dataInicio = LocalDate.parse(plano.getDataIniCiclo().toString());
											dataFim = LocalDate.parse(plano.getDataFimCiclo().toString());
											alRecebimento = LocalDate
													.parse(alocacao.getDtRecebido().toString().substring(0, 10));

											if (alocacao.getDtDevolucao() != null) {
												alDevolucao = LocalDate
														.parse(alocacao.getDtDevolucao().toString().substring(0, 10));
											}

											if ((dataInicio.compareTo(alRecebimento) > 0
													|| dataInicio.compareTo(alRecebimento) == 0)
													&& (dataFim.compareTo(alRecebimento) > 0
															|| dataFim.compareTo(alRecebimento) == 0)) {

												if (alocacao.getDtDevolucao() != null) {
													if ((dataInicio.compareTo(alDevolucao) < 0
															|| dataInicio.compareTo(alDevolucao) == 0)
															&& (dataFim.compareTo(alDevolucao) < 0
																	|| dataFim.compareTo(alDevolucao) == 0)) {

														planosVinculados.add(plano);
													}

												} else if (alocacao.getDtDevolucao() == null) {
													planosVinculados.add(plano);
												}
											} else if (((alRecebimento == dataInicio) || (alDevolucao == dataInicio))
													|| ((alRecebimento == dataFim) || (alDevolucao == dataFim))) {

												planosVinculados.add(plano);
											}
										}
									}
								}

								/* CHAMADAS */

								validador = chamadasLista.stream()
										.filter(p -> p.getLinha() != null && p.getLinha().getNumeroLinha() != null
												&& p.getLinha().getNumeroLinha().trim() == alocacao.getLinha()
														.getNumeroLinha().trim())
										.findAny().isPresent();

								i = 0;
								alocacaoRepasse.add(alocacao);
								faturaDTO.setAlocacao(alocacao);
								faturaDTO.setFatura(fatura);
								if (planosVinculados.size() != 0) {
									faturaDTO.setPlanos(planosVinculados);
								}

								if (validador == true) {

									for (Chamadas chamada : chamadasLista) {
										++i;
										if (alocacao.getLinha().equals(chamada.getLinha())) {

											LocalDateTime alRecebimento, data, alDevolucao;
											alDevolucao = null;

											String dataChamada = chamada.getDataLigacao().toString() + " "
													+ chamada.getHoraLigacao().toString();
											DateTimeFormatter formatter = DateTimeFormatter
													.ofPattern("yyyy-MM-dd HH:mm:ss");
											data = LocalDateTime.parse(dataChamada, formatter);
											alRecebimento = LocalDateTime.ofInstant(
													alocacao.getDtRecebido().toInstant(), ZoneId.of("GMT-3"));

											if (alocacao.getDtDevolucao() != null) {
												alDevolucao = LocalDateTime.ofInstant(
														alocacao.getDtDevolucao().toInstant(), ZoneId.of("GMT-3"));
											}

											if (data.compareTo(alRecebimento) > 0 || data.compareTo(alRecebimento) == 0) {

												if (alocacao.getDtDevolucao() != null) {

													if (data.compareTo(alDevolucao) < 0	|| data.compareTo(alDevolucao) == 0) {

														chamadasVinculados.add(chamada);
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
													}

												} else if (alDevolucao == null) {
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
								}
								cal.setResultadoF(0);

								if (!chamadasVinculados.isEmpty()) {
									chamadasVinculados.sort(Comparator.comparing(Chamadas::getDataLigacao)
											.thenComparing(Chamadas::getHoraLigacao));
								}
								
								if(!chamadasVinculados.isEmpty()){
								faturaDTO.setChamadas(chamadasVinculados);
								}

								/* RESUMO */

								validador = resumosLista.stream()
										.filter(p -> p.getLinha() != null && p.getLinha().getNumeroLinha() != null
												&& p.getLinha().getNumeroLinha().trim() == alocacao.getLinha()
														.getNumeroLinha().trim())
										.findAny().isPresent();

								if (validador == true) {
									valorPlano = false;
									
									for (Resumo resumo : resumosLista) {
										if (alocacao.getLinha().equals(resumo.getLinha())) {
								
											LocalDate alRecebimento, dataVencimento, alDevolucao;
											alDevolucao = null;

											dataVencimento = LocalDate.parse(resumo.getDataVenc().toString());
											alRecebimento = LocalDate.parse(alocacao.getDtRecebido().toString().substring(0, 10));
											
											if (alocacao.getDtDevolucao() != null) {
												alDevolucao = LocalDate
														.parse(alocacao.getDtDevolucao().toString().substring(0, 10));
											}
											
											if (dataVencimento.compareTo(alRecebimento) > 0
													|| dataVencimento.compareTo(alRecebimento) == 0) {

												if (alocacao.getDtDevolucao() != null) {
													if ((dataVencimento.compareTo(alDevolucao) < 0
															|| dataVencimento.compareTo(alDevolucao) == 0)
															&& (dataVencimento.compareTo(alDevolucao) < 0
																	|| dataVencimento.compareTo(alDevolucao) == 0)) {
														resumoVinculados.add(resumo);
													}

												} else if (alocacao.getDtDevolucao() == null) {
													resumoVinculados.add(resumo);
												}
											} else if ((alRecebimento == dataVencimento) || (alDevolucao == dataVencimento)) {

												resumoVinculados.add(resumo);
											}
										}
									};

									if (!resumoVinculados.isEmpty()
											&& (alocacao.getDispositivo() == null || !alocacao.getDispositivo().getTipoDispositivo().equals("Fixo"))) {
										if (resumoVinculados.get(0).getDataDesativ() != null) {
											long diasTotal = ((planosVinculados.get(0).getDataFimCiclo().getTime()
													- planosVinculados.get(0).getDataIniCiclo().getTime() + 3600000)
													/ 86400000L);
											long diasUtilizado = ((resumoVinculados.get(0).getDataDesativ().getTime()
													- planosVinculados.get(0).getDataIniCiclo().getTime() + 3600000)
													/ 86400000L);
											float valor = 4.9f;

											cal.setResultadoF((valor / diasTotal) * diasUtilizado);

										} else if ((alocacao.getDispositivo() == null || !alocacao.getDispositivo().getTipoDispositivo().equals("Fixo")) && (!planosVinculados.isEmpty())) {
											cal.setResultadoF(4.9f);

										}
									} else if ((alocacao.getDispositivo() == null || !alocacao.getDispositivo().getTipoDispositivo().equals("Fixo")) && (!planosVinculados.isEmpty())) {
										cal.setResultadoF(4.9f);
									}
								}

								faturaDTO.setResumo(resumoVinculados);

								/* SERVICOS */
								validador = servicosLista.stream()
										.filter(p -> p.getLinha() != null && p.getLinha().getNumeroLinha() != null
												&& p.getLinha().getNumeroLinha().trim() == alocacao.getLinha()
														.getNumeroLinha().trim())
										.findAny().isPresent();
								if (validador == true) {
									
									for (Servicos servico : servicosLista) {

										if (alocacao.getLinha().equals(servico.getLinha())) {

											LocalDateTime alRecebimento, data, alDevolucao;
											alDevolucao = null;

											String dataChamada = servico.getDataServico().toString() + " "
													+ servico.getHoraServico().toString();
											DateTimeFormatter formatter = DateTimeFormatter
													.ofPattern("yyyy-MM-dd HH:mm:ss");
											data = LocalDateTime.parse(dataChamada, formatter);
											alRecebimento = LocalDateTime.ofInstant(
													alocacao.getDtRecebido().toInstant(), ZoneId.of("GMT-3"));

											if (alocacao.getDtDevolucao() != null) {
												alDevolucao = LocalDateTime.ofInstant(
														alocacao.getDtDevolucao().toInstant(), ZoneId.of("GMT-3"));
											}

											if (data.compareTo(alRecebimento) > 0 || data.compareTo(alRecebimento) == 0) {

												if (alocacao.getDtDevolucao() != null) {

													if (data.compareTo(alDevolucao) < 0	|| data.compareTo(alDevolucao) == 0) {

														servicosVinculados.add(servico);

														if (servicoCategoria == null) {

															servicoCategoria = new ServicosCategoria();

														} else if (servicoCategoria.getCategoria().getCodCatServico() != servico.getCategoriaservico().getCodCatServico()) {

															servicosPorCategoria.add(servicoCategoria);
															servicoCategoria = new ServicosCategoria();

														}
														servicoCategoria.setCategoria(servico.getCategoriaservico());

														if (servico.getUnidadeServico().equals("MB") || servico.getUnidadeServico().equals("KB")) {

															servicoCategoria.setQuantidade((servico.getUnidadeServico().equals("KB")
																			? servico.getQuantUtil() / 1000
																			: servico.getQuantUtil())
																			+ servicoCategoria.getQuantidade());
														} else {
															servicoCategoria.setQuantidade(servicoCategoria.getQuantidade() + 1);
															servicoCategoria.setTarifa(servico.getValServImp());
															servicoCategoria.setValorCobrado(servico.getValServImp() + servicoCategoria.getValorCobrado());
															servicoCategoria.setValorTotal(servico.getValServImp() + servicoCategoria.getValorTotal());
														}
													}
												} else if (alocacao.getDtDevolucao() == null) {

													servicosVinculados.add(servico);
													
													if (servicoCategoria == null) {
														
														servicoCategoria = new ServicosCategoria();
														
													} else if (servicoCategoria.getCategoria().getCodCatServico() != servico.getCategoriaservico().getCodCatServico()) {
														servicosPorCategoria.add(servicoCategoria);
														servicoCategoria = new ServicosCategoria();
													}
													
													servicoCategoria.setCategoria(servico.getCategoriaservico());
													
													if (servico.getUnidadeServico().equals("MB") || servico.getUnidadeServico().equals("KB")) {
														servicoCategoria.setQuantidade((servico.getUnidadeServico().equals("KB")
																		? servico.getQuantUtil() / 1000
																		: servico.getQuantUtil())
																		+ servicoCategoria.getQuantidade());
													} else {
														servicoCategoria.setQuantidade(servicoCategoria.getQuantidade() + 1);
														servicoCategoria.setTarifa(servico.getValServImp());
														servicoCategoria.setValorCobrado(servico.getValServImp() + servicoCategoria.getValorCobrado());
														servicoCategoria.setValorTotal(servico.getValServImp() + servicoCategoria.getValorTotal());

													}
												}

											}
										}
									}
									servicosPorCategoria.add(servicoCategoria);
									
									faturaDTO.setServicosCategoria(servicosPorCategoria);


								}
								;

								/* Calculador de Totais */
								if (valorPlano == true && cal.getResultadoF() == 0) {
									cal.setResultadoF(4.9f);
								}
								float valorTotal = this.valorTotal(chamadasVinculados, servicosVinculados,
										planosVinculados) + cal.getResultadoF();

								faturaDTO.setValorContratoPlano(cal.getResultadoF());
								cal.setFloatA(cal.getResultadoF());

								if(alocacao.getDispositivo() != null){
									if (alocacao.getDispositivo().getTipoDispositivo().equals("Fixo")) {
										cal.setDoubleA(valorTotal);
									}
								}
								
								faturaDTO.setValorTotal(valorTotal);
								cal.setFloatB(valorTotal);

								cal.setResultadoF(0);
								cal.setValorTotalAtesto(cal.getValorTotalAtesto() + valorTotal);

								if (fatura.getChamadases() == null) {

									alocacaoFatura = new AlocacaoFatura();
									faturaDTO = new FaturaArquivoDTO();
									planosVinculados = new ArrayList<Planos>();
									servicosVinculados = new ArrayList<Servicos>();
									chamadasVinculados = new ArrayList<Chamadas>();
									resumoVinculados = new ArrayList<Resumo>();
									servicosPorCategoria = new ArrayList<ServicosCategoria>();
									servicosVinculados = new ArrayList<Servicos>();
									break;
								}

								/* SALVA FATURA GERADA E INFORMAÇÕES */
								if (not != 4) {

									if(faturaDTO.getValorTotal() != 0){
										alocacaoFatura.setAlocacao(alocacao);
										alocacaoFatura.setFatura(fatura);
										alocacaoService.salvar(alocacaoFatura);
										alocacoesFaturas.add(alocacaoFatura);
									faturaDTOLista.add(faturaDTO);
									}
									cal.setFloatA(0);
									cal.setFloatB(0);
									faturaLista.add(fatura);

								}

								alocacaoFatura = new AlocacaoFatura();
								faturaDTO = new FaturaArquivoDTO();
								planosVinculados = new ArrayList<Planos>();
								servicosVinculados = new ArrayList<Servicos>();
								chamadasVinculados = new ArrayList<Chamadas>();
								resumoVinculados = new ArrayList<Resumo>();
								servicosPorCategoria = new ArrayList<ServicosCategoria>();
								servicosVinculados = new ArrayList<Servicos>();
							}

							fatura.setGerada(true);
							faturaService.salvarFaturaGerada(fatura);
						}

						/* ATESTOS E ENVIO PARA O SEI FATURA GERADA */

						if (faturaDTOLista.size() != 0) {

							for (FaturaArquivoDTO fat : faturaDTOLista) {
								cal.setResultadoD(cal.getResultadoD() + fat.getValorTotal());
							}

							if ((cal.getResultadoD() - cal.getDoubleA()) > usuario.getLimiteAtesto().getValorLimite()) {

								for (AlocacaoFatura alocacaoFaturaRessarcimento : alocacoesFaturas) {
									
									if (alocacaoFaturaRessarcimento.getAlocacao().getDispositivo() == null || !alocacaoFaturaRessarcimento.getAlocacao().getDispositivo().getTipoDispositivo()
											.equals("Fixo")) {
										alocacaoFaturaRessarcimento.setRessarcimento(true);
										alocacaoService.salvar(alocacaoFaturaRessarcimento);
										cal = new CalculadorDTO();
									} else {
										alocacaoFaturaRessarcimento.setRessarcimento(false);
										alocacaoService.salvar(alocacaoFaturaRessarcimento);
										cal = new CalculadorDTO();
									}
								}
								sei.enviarMemorandoRessarcimento(alocacaoRepasse, gerarMemorandoRessarcimento(request,alocacaoRepasse,faturaDTOLista));
								alocacaoFatura.setDocumentoSei(sei.enviarFaturasCompostas(alocacaoRepasse,
										gerarPdfFaturaComposta(faturaDTOLista, request)));
								mailer.enviarAtestoFatura(faturaDTOLista);
								servicosPorCategoria = new ArrayList<ServicosCategoria>();

							} else {

								for (AlocacaoFatura alocacaoFaturaRessarcimento : alocacoesFaturas) {
									alocacaoFaturaRessarcimento.setRessarcimento(false);
									alocacaoService.salvar(alocacaoFaturaRessarcimento);
									cal = new CalculadorDTO();
								}

								sei.enviarMemorando(alocacaoRepasse, gerarMemorando(request,alocacaoRepasse,faturaDTOLista));
								alocacaoFatura.setDocumentoSei(sei.enviarFaturasCompostas(alocacaoRepasse,
										gerarPdfFaturaComposta(faturaDTOLista, request)));
								mailer.enviarAtestoFatura(faturaDTOLista);
								servicosPorCategoria = new ArrayList<ServicosCategoria>();

							}
						}

						cal.setDoubleA(0);
					
						if (usuarioLista.size() == 0) {

							break;
						}
					}
					if (usuarioLista.size() == 0) {

						break;
					}
					if(usercounter >= usuarioLista.size()){
						break;
					}
				}
			}
		}
		mv.addObject("alocacoesSei", alocacoesFaturas);
		mv.addObject("fatura", faturaLista);
		ldap.usuarioInfos(mv);

		return mv;
	}


	
	/** CONSULTA DE GESTÃO DAS FATURAS JÁ GERADAS E ENVIADAS */
	@RequestMapping("/{fatura}")
	public @ResponseBody ModelAndView gerarResumo(@PathVariable("fatura") int idFatura, HttpServletRequest request) throws ParseException {
		ModelAndView mv = new ModelAndView("/faturas/FaturaGerencial");
		Fatura fatura = faturaService.getFatura(idFatura);
		mv.addObject("fatura", fatura);
		return mv;
	}

	/** CONSULTA FATURAS JÁ GERADAS E ENVIADAS */
	@RequestMapping("/resumo/{fatura}/{alocacao}")
	public @ResponseBody ModelAndView gerarResumo(@PathVariable("fatura") int idFatura,
			@PathVariable("alocacao") int idAlocacao, HttpServletRequest request) throws ParseException {

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
		if (!planosLista.isEmpty())
			for (Planos plano : planosLista) {
				if (alocacao.getLinha().equals(plano.getLinha())) {
					
					LocalDate alRecebimento, dataInicio, dataFim, alDevolucao;
					alDevolucao = null;

					dataInicio = LocalDate.parse(plano.getDataIniCiclo().toString());
					dataFim = LocalDate.parse(plano.getDataFimCiclo().toString());
					alRecebimento = LocalDate
							.parse(alocacao.getDtRecebido().toString().substring(0, 10));

					if (alocacao.getDtDevolucao() != null) {
						alDevolucao = LocalDate
								.parse(alocacao.getDtDevolucao().toString().substring(0, 10));
					}
					
					
					if ((dataInicio.compareTo(alRecebimento) > 0
							|| dataInicio.compareTo(alRecebimento) == 0)
							&& (dataFim.compareTo(alRecebimento) > 0
									|| dataFim.compareTo(alRecebimento) == 0)) {

						if (alocacao.getDtDevolucao() != null) {
							if ((dataInicio.compareTo(alDevolucao) < 0
									|| dataInicio.compareTo(alDevolucao) == 0)
									&& (dataFim.compareTo(alDevolucao) < 0
											|| dataFim.compareTo(alDevolucao) == 0)) {

								planosVinculados.add(plano);
							}

						} else if (alocacao.getDtDevolucao() == null) {
							planosVinculados.add(plano);
						}
					} else if (((alRecebimento == dataInicio) || (alDevolucao == dataInicio))
							|| ((alRecebimento == dataFim) || (alDevolucao == dataFim))) {

						planosVinculados.add(plano);
					}
				}
			}

		// CHAMADAS
		i = 0;
		if (!chamadasLista.isEmpty())
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
		if (!resumosLista.isEmpty())
			for (Resumo resumo : resumosLista) {
				if (alocacao.getLinha().equals(resumo.getLinha())) {
					resumoVinculados.add(resumo);
				}

				if (!(resumoVinculados.isEmpty()) && !planosLista.isEmpty()) {
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
		if (!servicosLista.isEmpty())
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

		if (alocacao.getDispositivo().getTipoDispositivo().equals("Fixo")) {
			cal.setFloatB(cal.getFloatB() - 4.9f);
		}

		Planos planoDatas = new Planos();
		if (!planosLista.isEmpty()) {
			planoDatas.setDataIniCiclo(planosVinculados.get(0).getDataIniCiclo());
			planoDatas.setDataFimCiclo(planosVinculados.get(0).getDataFimCiclo());
		}
		mv.addObject("alocacao", alocacao);
		mv.addObject("fatura", fatura);
		mv.addObject("pacote", cal);
		mv.addObject("chamadas", chamadasVinculados);
		mv.addObject("planos", planosVinculados);
		mv.addObject("planoData", planoDatas);
		mv.addObject("servicos", servicosPorCategoria);
		ldap.usuarioInfos(mv);
		
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
	private byte[] gerarMemorando(HttpServletRequest request, List<Alocacao> alocacaoRepasse, List<FaturaArquivoDTO> faturaDTOLista) throws Exception {
		View view = this.viewResolver.resolveViewName("/documentos/memorandos/MemorandoFaturaTelefonica",
				locale.resolveLocale(request));
		MockHttpServletResponse mockResp = new MockHttpServletResponse();
		view.render(new ModelAndView().getModelMap().addAttribute("alocacaoRepasse",alocacaoRepasse).addAttribute("faturaLista", faturaDTOLista), request, mockResp);

		return mockResp.getContentAsByteArray();
	}
	
	private byte[] gerarMemorandoRessarcimento(HttpServletRequest request, List<Alocacao> alocacaoRepasse, List<FaturaArquivoDTO> faturaDTOLista) throws Exception {
		View view = this.viewResolver.resolveViewName("/documentos/memorandos/MemorandoRessarcimentoFaturaTelefonica",
				locale.resolveLocale(request));
		MockHttpServletResponse mockResp = new MockHttpServletResponse();
		view.render(new ModelAndView().getModelMap().addAttribute("alocacaoRepasse",alocacaoRepasse).addAttribute("faturaLista", faturaDTOLista), request, mockResp);

		return mockResp.getContentAsByteArray();
	}
}