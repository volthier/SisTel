package br.gov.cultura.DitelAdm.ws;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.springframework.beans.factory.annotation.Value;

import br.gov.cultura.DitelAdm.wsdl.Documento;
import br.gov.cultura.DitelAdm.wsdl.Procedimento;
import br.gov.cultura.DitelAdm.wsdl.RetornoGeracaoProcedimento;
import br.gov.cultura.DitelAdm.wsdl.SeiPortType;
import br.gov.cultura.DitelAdm.wsdl.SeiServiceLocator;
import br.gov.cultura.DitelAdm.wsdl.Unidade;

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

	public RetornoGeracaoProcedimento enviarFaturaSei(Procedimento proc, Documento doc) {
		RetornoGeracaoProcedimento response;
		try {
			response = seiWs.gerarProcedimento(siglaSistema, idServico, null, proc, new Documento[] { doc }, null, null,
					null, null, null, null, null);
		} catch (RemoteException e) {
			e.printStackTrace();
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
