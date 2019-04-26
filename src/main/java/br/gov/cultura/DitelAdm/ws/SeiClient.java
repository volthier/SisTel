package br.gov.cultura.DitelAdm.ws;

import br.gov.cultura.DitelAdm.model.Alocacao;
import br.gov.cultura.DitelAdm.model.DocumentoSei;
import br.gov.cultura.DitelAdm.service.AlocacaoService;
import br.gov.cultura.DitelAdm.service.CadastroUsuarioService;
import br.gov.cultura.DitelAdm.wsdl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class SeiClient {

	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;

	private SeiPortType seiWs;

	@Value("${sei.sistema}")
	private String siglaSistema;

	@Value("${sei.servico}")
	private String idServico;

	@Autowired
	AlocacaoService alocacaoService;

	public SeiClient() {
		SeiServiceLocator locator = new SeiServiceLocator();
		try {
			this.seiWs = locator.getSeiPortService();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	public Unidade[] listarUnidades() {
		Unidade[] unidades;
		try {
			unidades = seiWs.listarUnidades(siglaSistema, idServico, null, null);
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
		return unidades;
	}

	public RetornoGeracaoProcedimento gerarProcedimentoAlocacao() throws RemoteException {
		Procedimento p = new Procedimento();
		p.setIdTipoProcedimento("100000513");
		p.setAssuntos(new Assunto[0]);
		p.setInteressados(new Interessado[0]);
		p.setObservacao("");
		p.setNivelAcesso("");

		// Unidade 110000073 -- DITEL -- Divisão de Telefonia e Serviços
		// Unidade 110000069 -- COSIN -- Coordenação de Sistemas de Informação
		RetornoGeracaoProcedimento retorno = seiWs.gerarProcedimento(siglaSistema, idServico, "110000073", p,
				new Documento[0], new String[0], new String[0], null, null,
				null, null, null, "", "");
		return retorno;
	}


	public RetornoConsultaProcedimento consutaProcessoSei(String protocoloProcedimento) throws RemoteException {

		String sin, nin;
		sin = "S";
		nin = "N";
		return seiWs.consultarProcedimento(siglaSistema, idServico, "110000073", protocoloProcedimento, sin, nin, sin,
				sin, sin, nin, nin, nin, nin);
	}

	public DocumentoSei enviarFatura(Integer idAlocacao, String processo, byte[] fatura)
			throws IOException, ParseException, InterruptedException {
		byte[] encoded = Base64.getEncoder().encode(fatura);
		String encodedFile = new String(encoded, "ISO-8859-1");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		Documento doc = new Documento();
		doc.setTipo("R");
		doc.setIdProcedimento(processo);
		doc.setIdSerie("84");
		doc.setNumero("");
		doc.setData(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		doc.setDescricao("");
		doc.setRemetente(new Remetente("SISTEL", "Sistema de Telefonia"));
		doc.setInteressados(new Interessado[0]);
		doc.setDestinatarios(new Destinatario[0]);
		doc.setObservacao("");
		doc.setNomeArquivo("fatura.pdf");
		doc.setConteudo(encodedFile);
		doc.setNivelAcesso("0");

		RetornoInclusaoDocumento response = seiWs.incluirDocumento(siglaSistema, idServico, "110000073", doc);
		Alocacao alocacao = alocacaoService.getAlocacao(idAlocacao);

		List<Alocacao> alocacaoLista = new ArrayList<Alocacao>();
				alocacaoLista.add(alocacao);

		DocumentoSei documento = new DocumentoSei();
		documento.setDocumentosLink(response.getLinkAcesso());
		documento.setDocumentoIdSei(response.getIdDocumento());
		documento.setDocumentosNumero(response.getDocumentoFormatado());
		documento.setDocumentosTipo("Fatura");
		documento.setAlocacaoSei(alocacao.getAlocacaoSei());
		documento.setAlocacao(new HashSet<Alocacao>(alocacaoLista));
		String sin, nin;
		sin = "S";
		nin = "N";

		RetornoConsultaDocumento consultarDocumento = seiWs.consultarDocumento(siglaSistema, idServico, "110000073",
				response.getDocumentoFormatado(), sin, nin, nin, "");
		documento.setDocumentosDataGerado(sdf.parse(consultarDocumento.getAndamentoGeracao().getDataHora()));

		alocacaoService.salvar(documento);

		return documento;
	}

	public DocumentoSei enviarFaturasCompostas(List<Alocacao> alocacaoLista, byte[] fatura)
			throws IOException, ParseException, InterruptedException {
		byte[] encoded = Base64.getEncoder().encode(fatura);
		String encodedFile = new String(encoded, "ISO-8859-1");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		int ponteiro = 0;
		for(Alocacao aloc : alocacaoLista){
			for(Alocacao aloca : alocacaoLista){
				if(aloc.getDtRecebido().compareTo(aloca.getDtRecebido())>0){
					ponteiro = alocacaoLista.indexOf(aloc);
				}else if(aloc.getDtRecebido().compareTo(aloca.getDtRecebido())<0){
					ponteiro = alocacaoLista.indexOf(aloca);
				}
			}
		}

		Documento doc = new Documento();
		doc.setTipo("R");
		doc.setIdProcedimento(alocacaoLista.get(ponteiro).getAlocacaoSei().getNumeroProcessoSei());
		doc.setIdSerie("84");
		doc.setNumero("");
		doc.setData(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		doc.setDescricao("");
		doc.setRemetente(new Remetente("SISTEL", "Sistema de Telefonia"));
		doc.setInteressados(new Interessado[0]);
		doc.setDestinatarios(new Destinatario[0]);
		doc.setObservacao("");
		doc.setNomeArquivo("fatura.pdf");
		doc.setConteudo(encodedFile);
		doc.setNivelAcesso("0");

		RetornoInclusaoDocumento response = seiWs.incluirDocumento(siglaSistema, idServico, "110000073", doc);
		DocumentoSei documento = new DocumentoSei();
		documento.setDocumentosLink(response.getLinkAcesso());
		documento.setDocumentoIdSei(response.getIdDocumento());
		documento.setDocumentosNumero(response.getDocumentoFormatado());
		documento.setDocumentosTipo("Fatura");
		documento.setAlocacaoSei(alocacaoLista.get(ponteiro).getAlocacaoSei());
		documento.setAlocacao(new HashSet<Alocacao>(alocacaoLista));
		String sin, nin;
		sin = "S";
		nin = "N";

		RetornoConsultaDocumento consultarDocumento = seiWs.consultarDocumento(siglaSistema, idServico, "110000073",
				response.getDocumentoFormatado(), sin, nin, nin, "");
		documento.setDocumentosDataGerado(sdf.parse(consultarDocumento.getAndamentoGeracao().getDataHora()));

		alocacaoService.salvar(documento);

		return documento;
	}

	@Async
	public Usuario ValidaUsuarioUnidade(Alocacao alocacao) throws RemoteException {
		List<Usuario> usuarioSeiLista = Arrays.asList(
				seiWs.listarUsuarios(siglaSistema, idServico, alocacao.getUsuario().getLotacaoIdUsuario(), null));
		Usuario usuarioSei = usuarioSeiLista.stream()
				.filter(u -> u.getNome().equalsIgnoreCase(alocacao.getUsuario().getNomeUsuario())).findFirst()
				.orElse(null);
		if(usuarioSei!=null){
			br.gov.cultura.DitelAdm.model.Usuario us = cadastroUsuarioService.getByNome(usuarioSei.getNome());
			if(us!=null){
				us.setUsuarioSigla(usuarioSei.getSigla());
				cadastroUsuarioService.salvar(us);
			}
		}
		return usuarioSei;
	}

	public RetornoInclusaoDocumento enviarMemorando(List<Alocacao> alocacaoLista, byte[] memorando)
			throws IOException, ParseException, InterruptedException {
		byte[] encoded = Base64.getEncoder().encode(memorando);
		String encodedFile = new String(encoded, "ISO-8859-1");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		if(alocacaoLista.isEmpty()){

		}

		int ponteiro = 0;
		for(Alocacao aloc : alocacaoLista){
			for(Alocacao aloca : alocacaoLista){
				if(aloc.getDtRecebido().compareTo(aloca.getDtRecebido())>0){
					ponteiro = alocacaoLista.indexOf(aloc);
				}else if(aloc.getDtRecebido().compareTo(aloca.getDtRecebido())<0){
					ponteiro = alocacaoLista.indexOf(aloca);
				}
			}
		}

		Destinatario[] destinatario = new Destinatario[1];
		Destinatario dest = new Destinatario();
		dest.setNome(alocacaoLista.get(ponteiro).getUsuario().getNomeUsuario());
		dest.setSigla(alocacaoLista.get(ponteiro).getUsuario().getCpfUsuario());
		destinatario[0] = dest;

		Documento doc = new Documento();
		doc.setTipo("G");
		doc.setIdProcedimento(alocacaoLista.get(ponteiro).getAlocacaoSei().getNumeroProcessoSei());
		doc.setIdSerie("270");
		doc.setNumero("");
		doc.setData("");
		doc.setDescricao("");
		doc.setRemetente(new Remetente("", ""));
		doc.setInteressados(new Interessado[0]);
		doc.setDestinatarios(destinatario);
		doc.setObservacao("");
		doc.setNomeArquivo("");
		doc.setConteudo(encodedFile);
		doc.setNivelAcesso("0");

		RetornoInclusaoDocumento response = seiWs.incluirDocumento(siglaSistema, idServico, "110000073", doc);

		DocumentoSei documento = new DocumentoSei();
		documento.setDocumentosLink(response.getLinkAcesso());
		documento.setDocumentoIdSei(response.getIdDocumento());
		documento.setDocumentosNumero(response.getDocumentoFormatado());
		documento.setDocumentosTipo("Memorando de Atesto");
		documento.setAlocacaoSei(alocacaoLista.get(ponteiro).getAlocacaoSei());
		documento.setAlocacao(new HashSet<Alocacao>(alocacaoLista));
		String sin, nin;
		sin = "S";
		nin = "N";

		RetornoConsultaDocumento consultarDocumento = seiWs.consultarDocumento(siglaSistema, idServico, "110000073",
				response.getDocumentoFormatado(), sin, nin, nin, "");
		documento.setDocumentosDataGerado(sdf.parse(consultarDocumento.getAndamentoGeracao().getDataHora()));

		alocacaoService.salvar(documento);
		return response;
	}

	public RetornoInclusaoDocumento enviarMemorandoRessarcimento(List<Alocacao> alocacaoLista, byte[] memorando)
			throws IOException, ParseException, InterruptedException {
		byte[] encoded = Base64.getEncoder().encode(memorando);
		String encodedFile = new String(encoded, "ISO-8859-1");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		if(alocacaoLista.isEmpty()){
		}

		int ponteiro = 0;
		for(Alocacao aloc : alocacaoLista){
			for(Alocacao aloca : alocacaoLista){
				if(aloc.getDtRecebido().compareTo(aloca.getDtRecebido())>0){
					ponteiro = alocacaoLista.indexOf(aloc);
				}else if(aloc.getDtRecebido().compareTo(aloca.getDtRecebido())<0){
					ponteiro = alocacaoLista.indexOf(aloca);
				}
			}
		}

		Destinatario[] destinatario = new Destinatario[1];
		Destinatario dest = new Destinatario();
		dest.setNome(alocacaoLista.get(ponteiro).getUsuario().getNomeUsuario());
		dest.setSigla(alocacaoLista.get(ponteiro).getUsuario().getCpfUsuario());
		destinatario[0] = dest;

		Documento doc = new Documento();
		doc.setTipo("G");
		doc.setIdProcedimento(alocacaoLista.get(ponteiro).getAlocacaoSei().getNumeroProcessoSei());
		doc.setIdSerie("271");
		doc.setNumero("");
		doc.setData("");
		doc.setDescricao("");
		doc.setRemetente(new Remetente("", ""));
		doc.setInteressados(new Interessado[0]);
		doc.setDestinatarios(destinatario);
		doc.setObservacao("");
		doc.setNomeArquivo("");
		doc.setConteudo(encodedFile);
		doc.setNivelAcesso("0");

		RetornoInclusaoDocumento response = seiWs.incluirDocumento(siglaSistema, idServico, "110000073", doc);

		DocumentoSei documento = new DocumentoSei();
		documento.setDocumentosLink(response.getLinkAcesso());
		documento.setDocumentoIdSei(response.getIdDocumento());
		documento.setDocumentosNumero(response.getDocumentoFormatado());
		documento.setDocumentosTipo("Memorando de Atesto");
		documento.setAlocacaoSei(alocacaoLista.get(ponteiro).getAlocacaoSei());
		documento.setAlocacao(new HashSet<Alocacao>(alocacaoLista));
		String sin, nin;
		sin = "S";
		nin = "N";

		RetornoConsultaDocumento consultarDocumento = seiWs.consultarDocumento(siglaSistema, idServico, "110000073",
				response.getDocumentoFormatado(), sin, nin, nin, "");
		documento.setDocumentosDataGerado(sdf.parse(consultarDocumento.getAndamentoGeracao().getDataHora()));

		alocacaoService.salvar(documento);
		return response;
	}

	public DocumentoSei enviarTermoResponsabilidade(Alocacao alocacao, byte[] termo)
			throws IOException, ParseException, InterruptedException {
		byte[] encoded = Base64.getEncoder().encode(termo);
		String encodedFile = new String(encoded, "ISO-8859-1");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		String sin, nin, assinatura, descricaoBloco;
		sin = "S";
		nin = "N";
		assinatura = "A";

		Documento doc = new Documento();
		doc.setTipo("G");
		doc.setIdProcedimento(alocacao.getAlocacaoSei().getNumeroProcessoSei());
		doc.setIdSerie("266");
		doc.setNumero("");
		doc.setData("");
		doc.setDescricao("");
		doc.setRemetente(new Remetente("", ""));
		doc.setInteressados(new Interessado[0]);
		doc.setDestinatarios(new Destinatario[0]);
		doc.setObservacao("");
		doc.setNomeArquivo("");
		doc.setConteudo(encodedFile);
		doc.setNivelAcesso("0");

		RetornoInclusaoDocumento response = seiWs.incluirDocumento(siglaSistema, idServico, "110000073", doc);

		List<Alocacao> alocacaoLista = new ArrayList<Alocacao>();
		alocacaoLista.add(alocacao);

		DocumentoSei documento = new DocumentoSei();
		documento.setDocumentosLink(response.getLinkAcesso());
		documento.setDocumentoIdSei(response.getIdDocumento());
		documento.setDocumentosNumero(response.getDocumentoFormatado());
		documento.setDocumentosTipo("Termo de Responsabilidade");
		documento.setAlocacaoSei(alocacao.getAlocacaoSei());
		documento.setAlocacao(new HashSet<Alocacao>(alocacaoLista));

		descricaoBloco = "Termo de responsabilidade para assinatura de "+alocacao.getUsuario().getNomeUsuario();

		String[] dc = new String[1];
		dc[0] = documento.getDocumentosNumero();

		String[] uni = new String[1];
		uni[0] = alocacao.getUsuario().getLotacaoIdUsuario();

		RetornoConsultaDocumento consultarDocumento = seiWs.consultarDocumento(siglaSistema, idServico, "110000073",
				response.getDocumentoFormatado(), sin, nin, nin, "");

		documento.setDocumentosDataGerado(sdf.parse(consultarDocumento.getAndamentoGeracao().getDataHora()));

		if (!alocacao.getUsuario().getLotacaoIdUsuario().equals("110000073")) {
			String response1 = seiWs.gerarBloco(siglaSistema, idServico, "110000073", assinatura, descricaoBloco, uni,
					dc, sin);
			documento.setBlocoId(response1);
			documento.setBlocoDisponibilizado(true);
		} else {
			documento.setBlocoId("interno");
		}
		alocacaoService.salvar(documento);
		return documento;
	}

	@Async
	public void consultarAssinatura(DocumentoSei documento)
			throws RemoteException, ParseException, InterruptedException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		String sin, nin;
		sin = "S";
		nin = "N";

		RetornoConsultaDocumento consultarDocumento = seiWs.consultarDocumento(siglaSistema, idServico, "110000073",
				documento.getDocumentosNumero(), nin, sin, nin, "");

		consultarDocumento.getAssinaturas();

		br.gov.cultura.DitelAdm.model.Usuario usuario = new br.gov.cultura.DitelAdm.model.Usuario();

		for(Alocacao alocacao :documento.getAlocacao()){
		 usuario = alocacao.getUsuario();
		 break;
		 }

		if (consultarDocumento.getAssinaturas() != null) {
			for (Assinatura a : consultarDocumento.getAssinaturas()) {
				if (consultarDocumento.getAssinaturas().length == 1) {
					if (a.getNome().equalsIgnoreCase(usuario.getNomeUsuario())) {
						documento.setAssinaturaValida(true);
						documento.setAssinaturaNome(a.getNome());
						documento.setAssinaturaHora(sdf.parse(a.getDataHora()));
						documento.setAssinaturaCargo(a.getCargoFuncao());
					} else {
						documento.setAssinaturaNome(a.getNome());
						documento.setAssinaturaHora(sdf.parse(a.getDataHora()));
						documento.setAssinaturaCargo(a.getCargoFuncao());
					}
				}
				if (consultarDocumento.getAssinaturas().length > 1) {
					if (a.getNome().equalsIgnoreCase(usuario.getCargoUsuario())) {
						documento.setAssinaturaNome(a.getNome());
						documento.setAssinaturaHora(sdf.parse(a.getDataHora()));
						documento.setAssinaturaCargo(a.getCargoFuncao());
						break;
					} else {
						documento.setAssinaturaNome(a.getNome());
						documento.setAssinaturaHora(sdf.parse(a.getDataHora()));
						documento.setAssinaturaCargo(a.getCargoFuncao());
					}
				}
			}
		}

		if (documento.getAssinaturaHora() != null) {
			String cancelar, remover, fechar;
			cancelar = null;
			remover = null;
			alocacaoService.salvar(documento);
			if (!documento.getBlocoId().equals("interno")) {

				if (documento.getBlocoId() != null) {
					if (documento.isBlocoDisponibilizado() == true) {
						cancelar = seiWs.cancelarDisponibilizacaoBloco(siglaSistema, idServico, "110000073",
								documento.getBlocoId());

						if (cancelar.equalsIgnoreCase("1")) {
							documento.setBlocoDisponibilizado(false);
							alocacaoService.salvar(documento);

							remover = seiWs.retirarDocumentoBloco(siglaSistema, idServico, "110000073",
									documento.getBlocoId(), documento.getDocumentosNumero());
							cancelar = null;
							if (remover.equalsIgnoreCase("1")) {

								fechar = seiWs.excluirBloco(siglaSistema, idServico, "110000073",
										documento.getBlocoId());
								remover = null;
								documento.setBlocoFinalizado(true);
								alocacaoService.salvar(documento);
							}
						}
					}
					if (documento.isBlocoDisponibilizado() == false) {
						if (documento.isBlocoFinalizado() == false) {
							remover = seiWs.retirarDocumentoBloco(siglaSistema, idServico, "110000073",
									documento.getBlocoId(), documento.getDocumentosNumero());
							if (remover.equalsIgnoreCase("1")) {

								fechar = seiWs.excluirBloco(siglaSistema, idServico, "110000073",
										documento.getBlocoId());
								remover = null;
								documento.setBlocoFinalizado(true);
								alocacaoService.salvar(documento);
							}
						}
					}
				}
			}
		}
	}

	public SeiPortType getSeiWs() {
		return seiWs;
	}

	public void setSeiWs(SeiPortType seiWs) {
		this.seiWs = seiWs;
	}
}
