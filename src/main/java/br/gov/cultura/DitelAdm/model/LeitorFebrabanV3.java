package br.gov.cultura.DitelAdm.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;

import br.gov.cultura.DitelAdm.model.dtos.FaturaArquivoDTO;
import br.gov.cultura.DitelAdm.modelo.Cliente;
import br.gov.cultura.DitelAdm.modelo.ClienteId;
import br.gov.cultura.DitelAdm.modelo.Enderecos;
import br.gov.cultura.DitelAdm.modelo.EnderecosId;
import br.gov.cultura.DitelAdm.modelo.Fatura;
import br.gov.cultura.DitelAdm.modelo.FaturaId;
import br.gov.cultura.DitelAdm.modelo.Operadora;
import br.gov.cultura.DitelAdm.modelo.Resumo;
import br.gov.cultura.DitelAdm.modelo.ResumoId;

public class LeitorFebrabanV3 {

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	private SimpleDateFormat sdfh = new SimpleDateFormat("HHmmss");
	private SimpleDateFormat sdfNormal = new SimpleDateFormat("ddMMyyyy");

/*	private String recuperaTextoCampo(String linha, PosicaoCamposEnum posicao) {
		return linha.substring(posicao.getPosicaoInicial(), posicao.getPosicaoFinal());
	}*/

	public FaturaArquivoDTO read(File file) throws IOException {
		FaturaArquivoDTO faturaArquivoDTO = new FaturaArquivoDTO();
		FileReader fileReader = new FileReader(file);
		BufferedReader reader = new BufferedReader(fileReader);
		String data = null;

		Fatura fatura = new Fatura();
		FaturaId faturaId = new FaturaId();
		Cliente cliente = new Cliente();
		ClienteId clienteId = new ClienteId();
		Operadora operadora = new Operadora();
		//Instaciar List Resumo Reforçar 
		List<Resumo> resumoLista = new ArrayList<Resumo>();
		Resumo resumo = new Resumo();
		ResumoId resumoId = new ResumoId();
		List<Enderecos> enderecosLista = new ArrayList<Enderecos>();
		Enderecos enderecos = new Enderecos();
		EnderecosId enderecosId = new EnderecosId();
		

		while ((data = reader.readLine()) != null) {
			String TipoReg = data.substring(0, 2);
			switch (TipoReg)

			{
			case "00":
				/**
				 * 00_HEADER do guide Telecom padrão FEBRABAN-V3R0 Identifica
				 * Cabeçalho da conta
				 */

				/**
				 * Controle de sequencia de gravação String headerControlSeqGrav
				 * = data.substring(2, 14);
				 */

				/** Identificador de Conta Unica ou Numero da conta */
				 fatura.setIndConta(data.substring(14, 39));
				//fatura.setIndConta(recuperaTextoCampo(data, PosicaoCamposEnum.CAMPO_HEADER_FATURA_INDCONTA));

				/** Data da emissão da Fatura/conta */
				try {
					faturaId.setDataEmissao(sdf.parse(data.substring(39, 47)));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				/** Mês de Referência da fatura(cobrança) */
				fatura.setMesRef(data.substring(47, 53));

				/** Data de Geração do Arquivo */
				try {
					fatura.setDataGeraArquivo(sdf.parse(data.substring(53, 61)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				/** Data de Vencimento da Fatura */
				try {
					fatura.setDataVenc(sdf.parse(data.substring(61, 69)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				/** Codigo da Operadora */
				operadora.setCodOperadora(Integer.parseInt(data.substring(69, 72)));

				/** Nome da Operadora */
				operadora.setNome(data.substring(72, 87));

				/** CNPJ da Operadora */
				operadora.setCnpj(data.substring(87, 102));

				/** UF da Operadora */
				operadora.setUf(data.substring(102, 104));

				/** Codigo do Cliente */
				clienteId.setCodCliente(data.substring(104, 119));
				clienteId.setOperadoraCodOperadora(operadora.getCodOperadora());
				faturaId.setClienteOperadoraCodOperadora(operadora.getCodOperadora());
				faturaId.setClienteCodCliente(clienteId.getCodCliente());
				cliente.setId(clienteId);

				/** Nome do Cliente */
				cliente.setNome(data.substring(119, 149));

				/** CNPJ do Cliente */
				cliente.setCnpj(data.substring(149, 164));

				/**
				 * Versão do Formato do Documento >>> (Tem que aparecer V3R0)
				 * <<<
				 */
				fatura.setVersaoFormato(data.substring(164, 168));

				/** Numero da Fatura */
				faturaId.setNumFatura(Integer.parseInt(data.substring(168, 184)));
				fatura.setId(faturaId);

				/** Codigo de Barra */
				fatura.setCodBarra(data.substring(184, 234));

				/**
				 * Codigo de Cobrança ( Legenda do Valor) 01 - Debito
				 * automático; 02 - Crédito em conta; 03 - Cobrança simples; 04
				 * - Cobrança registrada; 05 - Cartão Crédito; 06 - Outros
				 */

				fatura.setCodCobranca(data.substring(234, 236));

				/**
				 * Descrição(Ou Tipo) da Cobrança (Legenda) 01 - Debito
				 * automático; 02 - Crédito em conta; 03 - Cobrança simples; 04
				 * - Cobrança registrada; 05 - Cartão Crédito; 06 - Outros
				 */

				fatura.setDescriCobranca(data.substring(236, 256));

				/** Banco da Cobrança */
				fatura.setBancoCobranca(data.substring(256, 260));

				/** Agencia da Cobrança */
				fatura.setAgenciaCobranca(data.substring(260, 264));

				/** Conta Corrente da Cobrança */
				fatura.setCcCobranca(data.substring(264, 274));

				/** FISCO */
				fatura.setFisco(data.substring(274, 309));

				/**
				 * Filler String headerFiller(data.substring(309, 324);
				 */

				/** Campo Livre Para Operadora */
				fatura.setCampoLivreOp(data.substring(324, 349));

				/**
				 * Marcação de FIM String headerMarcaFim(data.substring(349,
				 * 350);
				 */

				faturaArquivoDTO.setOperadora(operadora);
				faturaArquivoDTO.setCliente(cliente);
				faturaArquivoDTO.setFatura(fatura);

				break;

			case "10":
				/**
				 * 10_RESUMO do guide Telecom padrão FEBRABAN-V3R0 Somatório dos
				 * Valores por Recurso
				 * 
				 * Controle de sequencia de gravação String resumoControlSeqGrav
				 * = data.substring(2, 14);
				 */

				/** Identificador de Conta Unica ou Numero da conta */
				// resumo.setIdUnico(data.substring(14, 39));

				/** Data da emissão da Fatura/conta */
				resumoId = new ResumoId();
				try {
					resumoId.setFaturaDataEmissao(sdf.parse(data.substring(39, 47)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				/**
				 * Mês de Referência da fatura(cobrança) String
				 * resumoMesRef(data.substring(47, 53));
				 */

				/** Identificador Único do Recurso */
				resumo = new Resumo();
				resumo.setIdUnico(data.substring(53, 78));

				/**
				 * CNL do Recurso (Legenda) (6) Código Nacional de localidade:
				 * Fixo - definido pela ANATEL; Móvel definido pela ABR Telecom
				 */
				resumo.setCnl(Integer.parseInt(data.substring(78, 83)));

				/** Numero do Recurso */
				resumoId.setNumRecurso(data.substring(83, 99));

				/** Modalidade de Serviço do recurso */
				resumo.setModServ(Integer.parseInt(data.substring(99, 103)));

				/** Data da Ativação do Recurso */
				try {
					resumo.setDataAtiv(sdf.parse(data.substring(103, 111)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String i ="";
				i=data.substring(111, 119);
				/** Data da Desativação do Recurso */
				if (i.equals("00000000")) {
					resumo.setDataDesativ(null);
				}else{
						try {
							resumo.setDataDesativ(sdf.parse(data.substring(111, 119)));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}				
				}
				i="";
				/**
				 * Quantidade de Registro de Chamada String
				 * resumoQuantRegChamada(data.substring(119, 128);
				 */

				/** Valor Total dos Registros de Chamada com Impostos */
				resumo.setValorTotalRegChamadaImp(Float.parseFloat(data.substring(128, 141)));

				/** Quantidade de Registros de Serviços */
				resumo.setQuantRegServ(Integer.parseInt(data.substring(141, 150)));

				/**
				 * Valor Total dos Registros de Serviços com Impostos] String
				 * resumoValorTotalRegServImp(data.substring(150, 165));
				 */

				/** Valor Total de Impostos */
				resumo.setValorTotalImp(Float.parseFloat(data.substring(165, 178)));

				/** Valor Total da Conta do Recurso Com Impostos */
				resumo.setValorTotalContaRecursoImp(Float.parseFloat(data.substring(178, 191)));

				/** Degrau do Recurso */
				resumo.setDegrau(data.substring(191, 193));

				/** Velocidade do Recurso */
				resumo.setVelocidade(data.substring(193, 198));

				/** Unidade da Velocidade do Recurso */
				resumo.setUniVelocidade(data.substring(198, 202));

				/** Data Vencimento */
				try {
					resumo.setDataVenc(sdf.parse(data.substring(202, 210)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				/**
				 * Filler String resumoFiller(data.substring(210, 324);
				 */

				/**
				 * Campo Livre para Operadora String
				 * resumoCampoLivreOp(data.substring(324, 349);
				 */

				/**
				 * Marcação de Fim String resumoMarcaFim(data.substring(349,
				 * 350);
				 */
				resumoId.setFaturaClienteCodCliente(faturaId.getClienteCodCliente());
				resumoId.setFaturaClienteOperadoraCodOperadora(faturaId.getClienteOperadoraCodOperadora());
				resumoId.setFaturaNumFatura(faturaId.getNumFatura());
				resumoId.setFaturaDataEmissao(faturaId.getDataEmissao());
				resumo.setId(resumoId);
				resumo.setFatura(fatura);
				resumoLista.add(resumo);
				faturaArquivoDTO.setResumo(resumoLista);

				break;

			case "20":

				/**
				 * 20_ENDEREÇOS do guia Telecom padrão FEBRABAN-V3R0
				 * Identificação dos endereçõs dos recursos cobrados na fatura
				 */
				enderecos = new Enderecos();
				enderecosId = new EnderecosId();
				
				/** Controle de sequencia de gravação 
				String endControlSeqGrav(data.substring(2, 14));*/

				/** Identificador de Conta Unica ou Numero da conta 
				String endIndConta(data.substring(14, 39));*/

				/** Data da emissão da Fatura/conta */
				try {
					enderecosId.setFaturaDataEmissao(sdf.parse(data.substring(39, 47)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				/** Mês de Referência da fatura(cobrança) 
				String endMesRef(data.substring(47, 53));*/

				/** Identificador Único do Recurso 
				String endIdUnicoRecurso(data.substring(53, 78));*/

				/** Numero do Recurso */
				enderecosId.setIdEnderecos(data.substring(78, 94));

				/** CLN do Recurso Endereço Ponta A */
				enderecos.setCnlRecEnd(data.substring(94, 99));

				/** Nome da Localidade do Endereço Ponta A */
				enderecos.setNomeLocalEnd(data.substring(99, 114));

				/** UF da Localidade Ponta A */
				enderecos.setUfLocal(data.substring(114, 116));

				/** Endereço da Ponta A */
				enderecos.setEndereco(data.substring(116, 146));

				/** Numero do Endereço da Ponta A */
				enderecos.setNumero(data.substring(146, 151));

				/** Complemento da Ponta A */
				enderecos.setComplemento(data.substring(151, 159));

				/** Bairro da Ponta A */
				enderecos.setBairro(data.substring(159, 169));

				/** CLN do Recurso Endereço Ponta B 
				String endClnRecEndPontaB(data.substring(169, 174));

				/** Nome da Localidade do Endereço Ponta B 
				String endNomeLocalEndPontaB(data.substring(174, 189));

				/** UF da Localidade Ponta B 
				String endUfLocalPontaB(data.substring(189, 191));

				/** Endereço da Ponta B 
				String endEndPontaB(data.substring(191, 221));

				/** Numero do Endereço da Ponta B 
				String endNumeroEndPontaB(data.substring(221, 226));

				/** Complemento da Ponta B 
				String endComplementoPontaB(data.substring(226, 234));

				/** Bairro da Ponta B 
				String endBairroPontaB(data.substring(234, 244));

				/** CLN do Recurso Endereço Ponta C 
				String endClnRecEndPontaC(data.substring(244, 249));

				/** Nome da Localidade do Endereço Ponta C 
				String endNomeLocalEndPontaC(data.substring(249, 264));

				/** UF da Localidade Ponta C 
				String endUfLocalPontaC(data.substring(264, 266));

				/** Endereço da Ponta C 
				String endEndPontaC(data.substring(266, 296));

				/** Numero do Endereço da Ponta C 
				String endNumeroEndPontaC(data.substring(296, 301));

				/** Complemento da Ponta C 
				String endComplementoPontaC(data.substring(301, 309));

				/** Bairro da Ponta C 
				String endBairroPontaC(data.substring(309, 319));

				/** Filler 
				String endFiller(data.substring(319, 324);*/

				/** Campo Livre para Operadora */
				enderecos.setCampoLivreOp(data.substring(324, 349));

				/** Marcação de Fim
				String endMarcaFim(data.substring(349, 350);*/
				enderecosId.setFaturaClienteCodCliente(faturaId.getClienteCodCliente());
				enderecosId.setFaturaClienteOperadoraCodOperadora(faturaId.getClienteOperadoraCodOperadora());
				enderecosId.setFaturaNumFatura(faturaId.getNumFatura());
				enderecos.setId(enderecosId);
				enderecosLista.add(enderecos);
				faturaArquivoDTO.setEnderecos(enderecosLista);
				
				break;

			case "30":
				break;

			case "40":
				break;

			case "50":

				break;

			case "60":

				break;

			case "70":

				break;

			case "80":

				break;

			case "90":
				break;

			case "99":

				break;
			}

		}
		fileReader.close();
		reader.close();
		return faturaArquivoDTO;
	}

}
