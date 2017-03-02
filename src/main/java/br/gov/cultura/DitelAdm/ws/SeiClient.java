package br.gov.cultura.DitelAdm.ws;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Locale;

import javax.xml.rpc.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import br.gov.cultura.DitelAdm.wsdl.Assunto;
import br.gov.cultura.DitelAdm.wsdl.Destinatario;
import br.gov.cultura.DitelAdm.wsdl.Documento;
import br.gov.cultura.DitelAdm.wsdl.Interessado;
import br.gov.cultura.DitelAdm.wsdl.Procedimento;
import br.gov.cultura.DitelAdm.wsdl.Remetente;
import br.gov.cultura.DitelAdm.wsdl.RetornoGeracaoProcedimento;
import br.gov.cultura.DitelAdm.wsdl.RetornoInclusaoDocumento;
import br.gov.cultura.DitelAdm.wsdl.SeiPortType;
import br.gov.cultura.DitelAdm.wsdl.SeiServiceLocator;
import br.gov.cultura.DitelAdm.wsdl.Unidade;

@Component
public class SeiClient {

	private SeiPortType seiWs;

	@Value("${sei.sistema}")
	private String siglaSistema;

	@Value("${sei.servico}")
	private String idServico;
	
	@Autowired
	private ViewResolver viewResolver;

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
	
	public RetornoGeracaoProcedimento gerarProcedimentoAlocacao() throws RemoteException{
		Procedimento p = new Procedimento();
		p.setIdTipoProcedimento("100000513");
		p.setAssuntos(new Assunto[0]);
		p.setInteressados(new Interessado[0]);
		p.setObservacao("");
		p.setNivelAcesso("");
		
		//Unidade 110000073 -- DITEL -- Divisão de Telefonia e Serviços
		//Unidade 110000069 -- COSIN -- Coordenação de Sistemas de Informação
		RetornoGeracaoProcedimento retorno = seiWs.gerarProcedimento(siglaSistema, idServico, "110000073", p, new Documento[0], new String[0], new String[0], null, null, null, null, null);
		return retorno;
		
	}

	public RetornoInclusaoDocumento enviarFatura(String processo, byte[] fatura) throws IOException {
		byte[] encoded = Base64.getEncoder().encode(fatura);
		String encodedFile = new String(encoded, "ISO-8859-1");
		
		Documento doc = new Documento();
		doc.setTipo("R");
		doc.setIdProcedimento(processo);
		doc.setIdSerie("84");
		doc.setNumero("");
		doc.setData(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		doc.setDescricao("Fatura telefônica");
		doc.setRemetente(new Remetente("SISTEL","Sistema de Telefonia"));
		doc.setInteressados(new Interessado[0]);
		doc.setDestinatarios(new Destinatario[0]);
		doc.setObservacao("");
		doc.setNomeArquivo("fatura.pdf");
		doc.setConteudo(encodedFile);
		doc.setNivelAcesso("0");

		RetornoInclusaoDocumento response = seiWs.incluirDocumento(siglaSistema, idServico, "110000073", doc);
		return response;
	}
	
	public RetornoInclusaoDocumento enviarMemorando(String processo, byte[] memorando) throws IOException {
		byte[] encoded = Base64.getEncoder().encode(memorando);
		String encodedFile = new String(encoded, "ISO-8859-1");
		
		Documento doc = new Documento();
		doc.setTipo("G");
		doc.setIdProcedimento(processo);
		doc.setIdSerie("12");
		doc.setNumero("");
		doc.setData("");
		doc.setDescricao("");
		doc.setRemetente(new Remetente("",""));
		doc.setInteressados(new Interessado[0]);
		doc.setDestinatarios(new Destinatario[0]);
		doc.setObservacao("");
		doc.setNomeArquivo("");
		doc.setConteudo(encodedFile);
		doc.setNivelAcesso("0");
		
		RetornoInclusaoDocumento response = seiWs.incluirDocumento(siglaSistema, idServico, "110000073", doc);
		return response;
	}

	public SeiPortType getSeiWs() {
		return seiWs;
	}

	public void setSeiWs(SeiPortType seiWs) {
		this.seiWs = seiWs;
	}
}
