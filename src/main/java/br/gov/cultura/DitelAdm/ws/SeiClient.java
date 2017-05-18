package br.gov.cultura.DitelAdm.ws;

import java.io.IOException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.xml.rpc.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.gov.cultura.DitelAdm.model.Alocacao;
import br.gov.cultura.DitelAdm.model.DocumentoSei;
import br.gov.cultura.DitelAdm.service.AlocacaoService;
import br.gov.cultura.DitelAdm.wsdl.Assinatura;
import br.gov.cultura.DitelAdm.wsdl.Assunto;
import br.gov.cultura.DitelAdm.wsdl.Destinatario;
import br.gov.cultura.DitelAdm.wsdl.Documento;
import br.gov.cultura.DitelAdm.wsdl.Interessado;
import br.gov.cultura.DitelAdm.wsdl.Procedimento;
import br.gov.cultura.DitelAdm.wsdl.Remetente;
import br.gov.cultura.DitelAdm.wsdl.RetornoConsultaDocumento;
import br.gov.cultura.DitelAdm.wsdl.RetornoConsultaProcedimento;
import br.gov.cultura.DitelAdm.wsdl.RetornoGeracaoProcedimento;
import br.gov.cultura.DitelAdm.wsdl.RetornoInclusaoDocumento;
import br.gov.cultura.DitelAdm.wsdl.SeiPortType;
import br.gov.cultura.DitelAdm.wsdl.SeiServiceLocator;
import br.gov.cultura.DitelAdm.wsdl.Unidade;
import br.gov.cultura.DitelAdm.wsdl.Usuario;

@Component
public class SeiClient {

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
				new Documento[0], new String[0], new String[0], null, null, null, null, null);
		return retorno;

	}

	public RetornoConsultaProcedimento consutaProcessoSei(String protocoloProcedimento) throws RemoteException {

		String sin, nin;
		sin = "S";
		nin = "N";
		return seiWs.consultarProcedimento(siglaSistema, idServico, "110000073", protocoloProcedimento, sin, nin, sin,
				sin, sin, nin, nin, nin, nin);
	}

	public RetornoInclusaoDocumento enviarFatura(Integer idAlocacao, String processo, byte[] fatura)
			throws IOException, ParseException {
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
		DocumentoSei documento = new DocumentoSei();
		documento.setDocumentosLink(response.getLinkAcesso());
		documento.setDocumentoIdSei(response.getIdDocumento());
		documento.setDocumentosNumero(response.getDocumentoFormatado());
		documento.setDocumentosTipo("Fatura");
		documento.setAlocacaoSei(alocacao.getAlocacaoSei());
		documento.setAlocacao(alocacao);
		String sin, nin;
		sin = "S";
		nin = "N";

		RetornoConsultaDocumento consultarDocumento = seiWs.consultarDocumento(siglaSistema, idServico, "110000073",
				response.getDocumentoFormatado(), sin, nin, nin);
		documento.setDocumentosDataGerado(sdf.parse(consultarDocumento.getAndamentoGeracao().getDataHora()));

		alocacaoService.salvar(documento);

		return response;
	}

	public Usuario ValidaUsuarioUnidade(Alocacao alocacao) throws RemoteException {
		List<Usuario> usuarioSeiLista = Arrays.asList(
				seiWs.listarUsuarios(siglaSistema, idServico, alocacao.getUsuario().getLotacaoIdUsuario(), null));
		Usuario usuarioSei = usuarioSeiLista.stream()
				.filter(u -> u.getNome().equalsIgnoreCase(alocacao.getUsuario().getNomeUsuario())).findFirst()
				.orElse(null);
		return usuarioSei;
	}

	public RetornoInclusaoDocumento enviarMemorando(Alocacao alocacao, byte[] memorando)
			throws IOException, ParseException {
		byte[] encoded = Base64.getEncoder().encode(memorando);
		String encodedFile = new String(encoded, "ISO-8859-1");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		Documento doc = new Documento();
		doc.setTipo("G");
		doc.setIdProcedimento(alocacao.getAlocacaoSei().getNumeroProcessoSei());
		doc.setIdSerie("12");
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

		DocumentoSei documento = new DocumentoSei();
		documento.setDocumentosLink(response.getLinkAcesso());
		documento.setDocumentoIdSei(response.getIdDocumento());
		documento.setDocumentosNumero(response.getDocumentoFormatado());
		documento.setDocumentosTipo("Memorando de Atesto");
		documento.setAlocacaoSei(alocacao.getAlocacaoSei());
		documento.setAlocacao(alocacao);
		String sin, nin;
		sin = "S";
		nin = "N";

		RetornoConsultaDocumento consultarDocumento = seiWs.consultarDocumento(siglaSistema, idServico, "110000073",
				response.getDocumentoFormatado(), sin, nin, nin);
		documento.setDocumentosDataGerado(sdf.parse(consultarDocumento.getAndamentoGeracao().getDataHora()));

		alocacaoService.salvar(documento);
		return response;
	}

	public RetornoInclusaoDocumento enviarTermoResponsabilidade(Alocacao alocacao, byte[] termo)
			throws IOException, ParseException {
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

		String[] uni = new String[1];
		uni[0] = alocacao.getUsuario().getLotacaoIdUsuario();

		descricaoBloco = alocacao.getUsuario().getNomeUsuario();

		RetornoInclusaoDocumento response = seiWs.incluirDocumento(siglaSistema, idServico, "110000073", doc);

		DocumentoSei documento = new DocumentoSei();
		documento.setDocumentosLink(response.getLinkAcesso());
		documento.setDocumentoIdSei(response.getIdDocumento());
		documento.setDocumentosNumero(response.getDocumentoFormatado());
		documento.setDocumentosTipo("Termo Responsabilidade");
		documento.setAlocacaoSei(alocacao.getAlocacaoSei());
		documento.setAlocacao(alocacao);

		String[] dc = new String[1];
		dc[0] = documento.getDocumentosNumero();
		RetornoConsultaDocumento consultarDocumento = seiWs.consultarDocumento(siglaSistema, idServico, "110000073",
				response.getDocumentoFormatado(), sin, nin, nin);

		documento.setDocumentosDataGerado(sdf.parse(consultarDocumento.getAndamentoGeracao().getDataHora()));

		if (!alocacao.getUsuario().getLotacaoIdUsuario().equals("110000073")) {
			String response1 = seiWs.gerarBloco(siglaSistema, idServico, "110000073", assinatura, descricaoBloco, uni,
					dc, sin);
			documento.setBlocoId(response1);
		} else {
			documento.setBlocoId("interno");
		}
		alocacaoService.salvar(documento);
		return response;
	}

	public void consultarAssinatura(DocumentoSei documento) throws RemoteException, ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		String sin, nin;
		sin = "S";
		nin = "N";

		RetornoConsultaDocumento consultarDocumento = seiWs.consultarDocumento(siglaSistema, idServico, "110000073",
				documento.getDocumentosNumero(), nin, sin, nin);

		consultarDocumento.getAssinaturas();

		if (consultarDocumento.getAssinaturas() != null) {
			for (Assinatura a : consultarDocumento.getAssinaturas()) {
				if (consultarDocumento.getAssinaturas().length == 1) {
					if (a.getNome().equalsIgnoreCase(documento.getAlocacao().getUsuario().getNomeUsuario())) {
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
					if (a.getNome().equalsIgnoreCase(documento.getAlocacao().getUsuario().getNomeUsuario())) {
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
			alocacaoService.salvar(documento);
			if (!documento.getBlocoId().equals("interno")) {
				if (documento.getBlocoId() != null) {
					String fecharBloco = seiWs.excluirBloco(siglaSistema, idServico, "110000073",
							documento.getBlocoId());
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
