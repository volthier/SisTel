package br.gov.cultura.DitelAdm.controller;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.gov.cultura.DitelAdm.model.Alocacao;
import br.gov.cultura.DitelAdm.model.Categoria;
import br.gov.cultura.DitelAdm.model.Chip;
import br.gov.cultura.DitelAdm.model.Dispositivo;
import br.gov.cultura.DitelAdm.model.DocumentoSei;
import br.gov.cultura.DitelAdm.model.LimiteAtesto;
import br.gov.cultura.DitelAdm.model.Linha;
import br.gov.cultura.DitelAdm.model.Usuario;
import br.gov.cultura.DitelAdm.model.ldap.UsuarioLdap;
import br.gov.cultura.DitelAdm.repository.Dispositivos;
import br.gov.cultura.DitelAdm.repository.filtro.FiltroPesquisa;
import br.gov.cultura.DitelAdm.service.AlocacaoService;
import br.gov.cultura.DitelAdm.service.CadastroCategoriaService;
import br.gov.cultura.DitelAdm.service.CadastroChipService;
import br.gov.cultura.DitelAdm.service.CadastroLinhaService;
import br.gov.cultura.DitelAdm.service.CadastroUsuarioService;
import br.gov.cultura.DitelAdm.service.LimiteAtestoService;
import br.gov.cultura.DitelAdm.service.ldap.ConsultaLdapService;
import br.gov.cultura.DitelAdm.ws.SeiClient;
import br.gov.cultura.DitelAdm.wsdl.RetornoConsultaProcedimento;
import br.gov.cultura.DitelAdm.wsdl.Unidade;

@Controller
@RequestMapping("/alocacoes")
public class AlocacaoController {
	
	@Autowired
	private Dispositivos dispositivos;

	@Autowired
	private AlocacaoService alocacaoService;
	
	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;
	
	@Autowired
	private LimiteAtestoService limiteAtestoService;
	
	@Autowired
	private CadastroChipService cadastroChipService;
	
	@Autowired
	private CadastroLinhaService cadastroLinhaService;
	
	@Autowired
	private CadastroCategoriaService cadastroCategoriaService;
	
	@Autowired
	private ConsultaLdapService ldap;
	
	@Autowired
	private SeiClient sei;

	@RequestMapping("/disponibilizar")
	public ModelAndView alocar(Usuario user, @ModelAttribute("filtro") FiltroPesquisa filtro) {
		ModelAndView mv = new ModelAndView("AlocacaoDisponibilizar");

		List<Unidade> uni = Arrays.asList(sei.listarUnidades());
		uni.sort((u1, u2) -> u1.getDescricao().compareTo(u2.getDescricao()));
		mv.addObject("listaUnidadeSei", uni);

		List<UsuarioLdap> todosUsuarios = ldap.findAll();
		todosUsuarios.sort((user1, user2) -> user1.getFullName().compareTo(user2.getFullName()));
		mv.addObject("usuarios", todosUsuarios);

		List<LimiteAtesto> limiteAtesto = limiteAtestoService.getLimitesAtesto();
		mv.addObject("limiteAtesto", limiteAtesto);

		List<Dispositivo> todosDispositivos = dispositivos.findAll();
		List<Dispositivo> todosDispositivosNaoDisponiveis = dispositivos.findByNumeroSerieDispositivo();
		todosDispositivos.removeAll(todosDispositivosNaoDisponiveis);
		todosDispositivos.sort((d1, d2) -> d1.getMarcaDispositivo().compareTo(d2.getMarcaDispositivo()));
		mv.addObject("dispositivos", todosDispositivos);

		List<Linha> todasLinhas = cadastroLinhaService.getIdLinha();
		List<Linha> todasLinhasNaoDisponiveis = cadastroLinhaService.listarLinhaDisponivel();
		todasLinhas.removeAll(todasLinhasNaoDisponiveis);
		todasLinhas.sort((l1, l2) -> l1.getNumeroLinha().compareTo(l2.getNumeroLinha()));
		mv.addObject("linhas", todasLinhas);

		List<Chip> todosChips = cadastroChipService.getIdChip();
		List<Chip> todosChipsNaoDisponiveis = cadastroChipService.listarChipDisponivel();
		todosChips.removeAll(todosChipsNaoDisponiveis);
		todosChips.sort((c1, c2) -> c1.getNumeroSerieChip().compareTo(c2.getNumeroSerieChip()));
		mv.addObject("chips", todosChips);

		List<Categoria> todasCategorias = cadastroCategoriaService.getIdCategoria();
		todasCategorias.sort((cat1, cat2) -> cat1.getDescricaoCategoria().compareTo(cat2.getDescricaoCategoria()));
		mv.addObject("categorias", todasCategorias);
		ldap.usuarioInfos(mv);
		return mv;
	}
	
	
	@RequestMapping("/lista-alocacoes")
	public ModelAndView listar(@ModelAttribute("filtro") FiltroPesquisa filtro) throws RemoteException{
		ModelAndView mv = new ModelAndView("AlocacaoListar");
		List<Alocacao> lista = alocacaoService.getIdAlocacao();
		List<Usuario> usuarioErrorSei = new ArrayList<br.gov.cultura.DitelAdm.model.Usuario>();

		for (Alocacao alocacao : lista) {
			
			List<DocumentoSei> documento = alocacaoService.getDocumentoSeiListaAlocacao(alocacao);
			documento.forEach(doc -> {
				try {
					sei.consultarAssinatura(doc);
				} catch (RemoteException | ParseException e) {

					System.out.println(e + "Erro de tempo de espera da conexão da verificação de documentos!!!");
					e.printStackTrace();
				} catch (InterruptedException e) {
					System.out.println(e + " Erro de interrupção de conexão da verificação de documentos");
					e.printStackTrace();
				}
			});
			
			if (alocacao.getDtDevolucao() == null) {
				br.gov.cultura.DitelAdm.wsdl.Usuario usuarioSei = sei.ValidaUsuarioUnidade(alocacao);
				if (usuarioSei == null) {
					if (!usuarioErrorSei.contains(alocacao.getUsuario())) {

						usuarioErrorSei.add(alocacao.getUsuario());
					}
				}
			}
		}
		
		List<Alocacao> todosUsuario = alocacaoService.filtroPesquisa(filtro);
		List<Alocacao> possivelDevolver = new ArrayList<Alocacao>();
		
		for(Alocacao aloca : todosUsuario){
			if(aloca.getAutorizar()!=null){
				possivelDevolver.add(aloca);
			}
		}
		
		if(possivelDevolver.size()>1){
		possivelDevolver.sort((ul1, ul2) -> ul1.getUsuario().getNomeUsuario().compareTo(ul2.getUsuario().getNomeUsuario()));
		}
		
		mv.addObject("alocacaoAtiva", possivelDevolver);
		mv.addObject("alocacaoTodas",todosUsuario);
		ldap.usuarioInfos(mv);
		
		return mv;

	}
	
	@RequestMapping("/lista-alocacoes/{id}")
	public String listarDocumentos(@PathVariable("id") String id, ModelMap model){
		Alocacao alocacao = alocacaoService.getAlocacao(Integer.parseInt(id));
		model.addAttribute("alocacao", alocacao);
		model.addAttribute("documentos", alocacaoService.getDocumentoSeiListaAlocacao(alocacao));
		return "InfoDocumentos::modalConteudo";
		
	}
	
	@RequestMapping("/devolver")
	public @ResponseBody @Context ModelAndView devolver(@ModelAttribute("filtro") FiltroPesquisa filtro,
			HttpSession session) {
		ModelAndView mv = new ModelAndView("AlocacaoDevolver");
		List<Alocacao> todosUsuario = alocacaoService.getIdAlocacao();
		List<Alocacao> possivelDevolver = new ArrayList<Alocacao>();
		
		for(Alocacao aloca : todosUsuario){
			if(aloca.getAutorizar()!=null){
				possivelDevolver.add(aloca);
			}
		}
		
		if(possivelDevolver.size()>1){
		possivelDevolver.sort((ul1, ul2) -> ul1.getUsuario().getNomeUsuario().compareTo(ul2.getUsuario().getNomeUsuario()));
		}
		
		mv.addObject("usuariosDispositivos", possivelDevolver);
		ldap.usuarioInfos(mv);
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(Alocacao alocacao, Usuario usuario, Errors errors, RedirectAttributes attributes,
			HttpServletRequest servletRequest, HttpServletResponse servletResponse)
			throws ParseException, RemoteException {

		/* Inicio da Devolução de itens */

		if (servletRequest.getParameter("idAlocacao") != null) {
			Integer idAlocacaoUsuarioLinha = Integer.parseInt(servletRequest.getParameter("idAlocacao"));
			alocacao = alocacaoService.getAlocacao(idAlocacaoUsuarioLinha);
			Date dtDevolucao = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
					.parse(servletRequest.getParameter("dtDevolucao"));
			Date dtRecebimentoReplicador = alocacao.getDtRecebido();
			Linha idReciver = alocacao.getLinha();
			List<Alocacao> linhaDispo = alocacaoService.getIdAlocacao();
			Alocacao dispo = linhaDispo.stream().filter(ld -> ld != null && ld.getLinha().equals(idReciver)
					&& ld.getDtRecebido().equals(dtRecebimentoReplicador)).findFirst().orElse(null);

			if (dispo == null) {
				System.out.println("Error acquiring Info Linha-Dispositivo!");
				attributes.addFlashAttribute("mensagem", "Devolução CANCELADA!");
				return "redirect:/alocacoes/devolver";
			} else if (dispo != null) {
				dispo.setDtDevolucao(dtDevolucao);
				alocacaoService.salvar(dispo);
			}

			List<Alocacao> linhaChip = alocacaoService.getIdAlocacao();
			Alocacao chip = linhaChip.stream().filter(ld -> ld != null && ld.getLinha().equals(idReciver)
					&& ld.getDtRecebido().equals(dtRecebimentoReplicador)).findFirst().orElse(null);
			if (chip == null) {
				System.out.println("Error acquiring Info Linha-Dispositivo!");
				attributes.addFlashAttribute("mensagem", "Devolução CANCELADA!");
				return "redirect:/alocacoes/devolver";
			} else if (chip != null) {
				chip.setDtDevolucao(dtDevolucao);
				alocacaoService.salvar(chip);
			}

			List<Alocacao> linhaCat = alocacaoService.getIdAlocacao();
			Alocacao cat = linhaCat.stream().filter(ld -> ld != null && ld.getLinha().equals(idReciver)
					&& ld.getDtRecebido().equals(dtRecebimentoReplicador)).findFirst().orElse(null);
			if (cat == null) {
				System.out.println("Error acquiring Info Linha-Dispositivo!");
				attributes.addFlashAttribute("mensagem", "Devolução CANCELADA!");
				return "redirect:/alocacoes/devolver";
			} else if (cat != null) {
				cat.setDtDevolucao(dtDevolucao);
				alocacaoService.salvar(cat);
			}

			alocacao.setDtDevolucao(dtDevolucao);
			alocacaoService.salvar(alocacao);
			attributes.addFlashAttribute("mensagem", "Registrada devolução de dispositivo do usuario!");
			return "redirect:/alocacoes/devolver";

			/* Fim da Devolução de itens */

		}

		/* Inicio da Disponibilização de itens (Codigo Abaixo) */
		else if (servletRequest.getParameter("dtRecebido") != null) {
			String cpf = servletRequest.getParameter("nomeUsuario");
			Usuario usuarioCad = cadastroUsuarioService.getByCpf(cpf);
			if (usuarioCad == null) {
				UsuarioLdap usuarioLdap = ldap.findOne(cpf);
				usuario.setCpfUsuario(usuarioLdap.getCpf());
				usuario.setNomeUsuario(usuarioLdap.getFullName());
				usuario.setPrimeiroNomeUsuario(usuarioLdap.getFirstName());
				usuario.setSobrenomeUsuario(usuarioLdap.getLastName());
				usuario.setEmailUsuario(usuarioLdap.getEmail());
				usuario.setCargoUsuario(servletRequest.getParameter("cargoUsuario"));
				usuario.setLimiteAtesto(limiteAtestoService
						.getLimiteAtestoId(Integer.parseInt(servletRequest.getParameter("limiteDasAtesto"))));
				List<Unidade> uni = Arrays.asList(sei.listarUnidades());
				for (Unidade item : uni) {
					if (item.getIdUnidade().equals(servletRequest.getParameter("unidadeSei"))) {
						usuario.setLotacaoUsuario(item.getSigla());
						usuario.setLotacaoIdUsuario(item.getIdUnidade());
						usuario.setDescricaoUsuario(item.getDescricao());
					}
				}
				cadastroUsuarioService.salvar(usuario);
			} else if (usuarioCad != null) {
				usuario = usuarioCad;
				UsuarioLdap usuarioLdap = ldap.findOne(cpf);

				if (usuario.getEmailUsuario() == null) {
					usuario.setEmailUsuario(usuarioLdap.getEmail());
				}

				if (!usuario.getEmailUsuario().contains(usuarioLdap.getEmail())) {
					usuario.setEmailUsuario(usuarioLdap.getEmail());
				}

				if (usuario.getPrimeiroNomeUsuario() == null) {
					usuario.setPrimeiroNomeUsuario(usuarioCad.getPrimeiroNomeUsuario());
				}
				if (!usuario.getPrimeiroNomeUsuario().contains(usuarioLdap.getFirstName())) {
					usuario.setPrimeiroNomeUsuario(usuarioLdap.getFirstName());
				}
				if (usuario.getSobrenomeUsuario() == null) {
					usuario.setSobrenomeUsuario(usuarioCad.getSobrenomeUsuario());
				}
				if (!usuario.getSobrenomeUsuario().contains(usuarioLdap.getLastName())) {
					usuario.setSobrenomeUsuario(usuarioLdap.getLastName());
				}
				usuario.setCargoUsuario(servletRequest.getParameter("cargoUsuario"));
				usuario.setLimiteAtesto(limiteAtestoService
						.getLimiteAtestoId(Integer.parseInt(servletRequest.getParameter("limiteDasAtesto"))));
				List<Unidade> uni = Arrays.asList(sei.listarUnidades());
				for (Unidade item : uni) {
					if (item.getIdUnidade().equals(servletRequest.getParameter("unidadeSei"))) {
						usuario.setLotacaoUsuario(item.getSigla());
						usuario.setLotacaoIdUsuario(item.getIdUnidade());
						usuario.setDescricaoUsuario(item.getDescricao());

					}

				}

				cadastroUsuarioService.salvar(usuario);
			}

			alocacao.setUsuario(usuario);

			List<Alocacao> listaConferencia = alocacaoService.getIdAlocacao();
			listaConferencia.remove(alocacao);

			int i = 0;
			while (i == 0) {

				Alocacao alocacaoVerificaProcesso = listaConferencia.stream()
						.filter(lc -> lc != null && lc.getUsuario().getCpfUsuario().equals(cpf)
								&& lc.getAlocacaoSei() != null)
						.findFirst().orElse(null);

				if (alocacaoVerificaProcesso != null) {

					RetornoConsultaProcedimento processo = sei.consutaProcessoSei(
							alocacaoVerificaProcesso.getAlocacaoSei().getNumeroExternoProcessoSei());

					if (processo != null) {

						if (processo.getAndamentoConclusao() == null) {
							alocacao.setAlocacaoSei(alocacaoVerificaProcesso.getAlocacaoSei());
							alocacaoService.salvar(alocacao);
							attributes.addFlashAttribute("mensagem", "Registrado vinculo!");
							i = 1;
						}

						else if (processo.getAndamentoConclusao() != null) {
							SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
							alocacaoVerificaProcesso.getAlocacaoSei().setDtEncerramentoProcesso(
									sdf.parse(processo.getAndamentoConclusao().getDataHora()));
							alocacaoService.salvar(alocacaoVerificaProcesso);
						}

					}

				} else if (alocacaoVerificaProcesso == null) {
					alocacaoService.salvar(alocacao);
					attributes.addFlashAttribute("mensagem", "Registrado vinculo!");
					i = 1;
				}
				/* FIM da disponibilização de itens(Codigo Acima) */
			}
		}
		return "redirect:/alocacoes/disponibilizar";

	}

	@RequestMapping(value = "/disponibilizar/{id}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Integer id, RedirectAttributes attributes) {
		alocacaoService.excluir(id);
		attributes.addFlashAttribute("mensagem", "Fornecimento CANCELADO com sucesso!");
		return "redirect:/pendencia";
	}
	
	@RequestMapping("/disponibilizar/{id}")
	public ModelAndView edicao(@PathVariable("id") Alocacao alocacao){
		ModelAndView mv = new ModelAndView("AlocacaoDisponibilizar");		
		
		List<Unidade> uni = Arrays.asList(sei.listarUnidades());
		uni.sort((u1, u2) -> u1.getDescricao().compareTo(u2.getDescricao()));
		mv.addObject("listaUnidadeSei", uni);

		List<UsuarioLdap> todosUsuarios = ldap.findAll();
		todosUsuarios.sort((user1, user2) -> user1.getFullName().compareTo(user2.getFullName()));
		mv.addObject("usuarios", todosUsuarios);

		List<LimiteAtesto> limiteAtesto = limiteAtestoService.getLimitesAtesto();
		mv.addObject("limiteAtesto", limiteAtesto);

		List<Dispositivo> todosDispositivos = dispositivos.findAll();
		List<Dispositivo> todosDispositivosNaoDisponiveis = dispositivos.findByNumeroSerieDispositivo();
		todosDispositivos.removeAll(todosDispositivosNaoDisponiveis);
		todosDispositivos.sort((d1, d2) -> d1.getMarcaDispositivo().compareTo(d2.getMarcaDispositivo()));
		mv.addObject("dispositivos", todosDispositivos);

		List<Linha> todasLinhas = cadastroLinhaService.getIdLinha();
		List<Linha> todasLinhasNaoDisponiveis = cadastroLinhaService.listarLinhaDisponivel();
		todasLinhas.removeAll(todasLinhasNaoDisponiveis);
		todasLinhas.sort((l1, l2) -> l1.getNumeroLinha().compareTo(l2.getNumeroLinha()));
		mv.addObject("linhas", todasLinhas);

		List<Chip> todosChips = cadastroChipService.getIdChip();
		List<Chip> todosChipsNaoDisponiveis = cadastroChipService.listarChipDisponivel();
		todosChips.removeAll(todosChipsNaoDisponiveis);
		todosChips.sort((c1, c2) -> c1.getNumeroSerieChip().compareTo(c2.getNumeroSerieChip()));
		mv.addObject("chips", todosChips);

		List<Categoria> todasCategorias = cadastroCategoriaService.getIdCategoria();
		todasCategorias.sort((cat1, cat2) -> cat1.getDescricaoCategoria().compareTo(cat2.getDescricaoCategoria()));
		mv.addObject("categorias", todasCategorias);
		 
		mv.addObject(alocacao);
		ldap.usuarioInfos(mv);
				return mv;
	}

}