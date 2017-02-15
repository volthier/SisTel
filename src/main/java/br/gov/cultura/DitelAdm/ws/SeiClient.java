package br.gov.cultura.DitelAdm.ws;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.util.Base64;

import javax.xml.rpc.ServiceException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.gov.cultura.DitelAdm.wsdl.Assunto;
import br.gov.cultura.DitelAdm.wsdl.Destinatario;
import br.gov.cultura.DitelAdm.wsdl.Documento;
import br.gov.cultura.DitelAdm.wsdl.Interessado;
import br.gov.cultura.DitelAdm.wsdl.Procedimento;
import br.gov.cultura.DitelAdm.wsdl.Remetente;
import br.gov.cultura.DitelAdm.wsdl.RetornoGeracaoProcedimento;
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

	public RetornoGeracaoProcedimento enviarFaturaSei(Procedimento proc, Documento doc) throws IOException {
		RetornoGeracaoProcedimento response;
		try {
			//Tipo de processo = 100000513 -- Projetos de TI
			//Unidade = 110000073 -- DITEL -- Divisão de Telefonia e Serviços
			//110000069 -- COSIN -- Coordenação de Sistemas de Informação
			proc.setIdTipoProcedimento("100000513");
			proc.setEspecificacao("Teste");
			proc.setAssuntos(new Assunto[0]);
			proc.setInteressados(new Interessado[0]);
			proc.setObservacao("");
			proc.setNivelAcesso("0");

			Path memo = Paths.get("src/main/resources/templates/MemorandoFaturaTelefonica.html");
			byte[] encoded = Base64.getEncoder().encode(Files.readAllBytes(memo));
			String encodedFile = new String(encoded, "ISO-8859-1");
			
			doc.setTipo("G");
			doc.setIdProcedimento("");
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
			response = seiWs.gerarProcedimento(siglaSistema, idServico, "110000069", proc, new Documento[] { doc }, new String[0], new String[0],
					"S", "N", null, null, "N");
		} catch (RemoteException e) {
//			e.printStackTrace();
			return null;
		}
		return response;
	}

	public SeiPortType getSeiWs() {
		return seiWs;
	}

	public void setSeiWs(SeiPortType seiWs) {
		this.seiWs = seiWs;
	}
}
