package br.gov.cultura.DitelAdm.modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.gov.cultura.DitelAdm.modelo.Trailler;
import br.gov.cultura.DitelAdm.modelo.TraillerId;
import br.gov.cultura.DitelAdm.modelo.Notafiscal;
import br.gov.cultura.DitelAdm.modelo.NotafiscalId;
import br.gov.cultura.DitelAdm.modelo.Operadora;
import br.gov.cultura.DitelAdm.modelo.Planos;
import br.gov.cultura.DitelAdm.modelo.PlanosId;
import br.gov.cultura.DitelAdm.modelo.Ajustes;
import br.gov.cultura.DitelAdm.modelo.AjustesId;
import br.gov.cultura.DitelAdm.modelo.Chamadas;
import br.gov.cultura.DitelAdm.modelo.ChamadasId;
import br.gov.cultura.DitelAdm.modelo.Cliente;
import br.gov.cultura.DitelAdm.modelo.ClienteId;
import br.gov.cultura.DitelAdm.modelo.Descontos;
import br.gov.cultura.DitelAdm.modelo.DescontosId;
import br.gov.cultura.DitelAdm.modelo.Enderecos;
import br.gov.cultura.DitelAdm.modelo.EnderecosId;
import br.gov.cultura.DitelAdm.modelo.Fatura;
import br.gov.cultura.DitelAdm.modelo.FaturaId;
import br.gov.cultura.DitelAdm.modelo.ResumoId;
import br.gov.cultura.DitelAdm.modelo.Servicos;
import br.gov.cultura.DitelAdm.modelo.ServicosId;
import br.gov.cultura.DitelAdm.modelo.Resumo;

public class LeitorFebrabanV3 {

	private static SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
	private static SimpleDateFormat sdfh = new SimpleDateFormat("HHmmss"); 
	
	
	private static File file = new File(
			// "C:\\Users\\Administrador\\Desktop\\Projetos de
			// Programação\\Arquivos DITEL\\Faturas para o Projeto
			// ditel\\711725423_919441395_14_02_2016_FebrabanV3.txt");
			"C:\\Users\\72381817115\\Desktop\\Projetos de Programa��o\\"
			+ "Arquivos DITEL\\Faturas para o Projeto ditel\\"
			+ "711725423_919441395_14_02_2016_FebrabanV3.txt");

	private static String recuperaTextoCampo(String linha, PosicaoCamposEnum posicao){
		return linha.substring(posicao.getPosicaoInicial(), posicao.getPosicaoFinal());
	}
	
	private static Date recuperaDataCampo(String linha, PosicaoCamposEnum posicao) throws Exception{
		String textoDataEmissao = recuperaTextoCampo(linha, posicao);
		Date dataCampo = sdf.parse(textoDataEmissao);
		dataCampo = sdfh.parse(textoDataEmissao);
		
		return dataCampo;
	}
	

	private static final void read(File file) throws IOException {
		FileReader fileReader = new FileReader(file);
		BufferedReader reader = new BufferedReader(fileReader);
		String data = null;
		while ((data = reader.readLine()) != null) {
			String TipoReg = data.substring(0, 2);
			switch (TipoReg)

			{
			case "00":
					/**
					 * 00_HEADER do guide Telecom padrão FEBRABAN-V3R0
					 * Identifica Cabeçalho da conta
					 */
					Fatura fatura = new Fatura();
					FaturaId faturaId = new FaturaId();
					Cliente cliente = new Cliente();
					ClienteId clienteId = new ClienteId();
					Operadora operadora = new Operadora();					
					
					/** Controle de sequencia de gravação 
					String headerControlSeqGrav = data.substring(2, 14);*/

					/** Identificador de Conta Unica ou Numero da conta */
				//	fatura.setIndConta(data.substring(14, 39));
					fatura.setIndConta(recuperaTextoCampo(data, PosicaoCamposEnum.CAMPO_HEADER_FATURA_INDCONTA));

					
					/** Data da emissão da Fatura/conta */
		//			faturaId.setDataEmissao(sdf.parse(data.substring(39, 47)));
					
					try {
						faturaId.setDataEmissao(recuperaDataCampo(data, PosicaoCamposEnum.CAMPO_HEADER_FATURA_DATAEMISSAO));
					} catch (Exception e1) {
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

					/** Nome do Cliente */
					cliente.setNome(data.substring(119, 149));

					/** CNPJ do Cliente */
					cliente.setCnpj(data.substring(149, 164));

					/**
					 * Versão do Formato do Documento >>> (Tem que aparecer
					 * V3R0) <<<
					 */
					fatura.setVersaoFormato(data.substring(164, 168));

					/** Numero da Fatura */
					faturaId.setNumFatura(Integer.parseInt(data.substring(168, 184)));

					/** Codigo de Barra */
					fatura.setCodBarra(data.substring(184, 234));

					/**
					 * Codigo de Cobrança ( Legenda do Valor) 01 - Debito
					 * automático; 02 - Crédito em conta; 03 - Cobrança simples;
					 * 04 - Cobrança registrada; 05 - Cartão Crédito; 06 -
					 * Outros
					 */

					fatura.setCodCobranca(data.substring(234, 236));

					/**
					 * Descrição(Ou Tipo) da Cobrança (Legenda) 01 - Debito
					 * automático; 02 - Crédito em conta; 03 - Cobrança simples;
					 * 04 - Cobrança registrada; 05 - Cartão Crédito; 06 -
					 * Outros
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

					/** Filler 
					String headerFiller(data.substring(309, 324);*/

					/** Campo Livre Para Operadora */
					fatura.setCampoLivreOp(data.substring(324, 349));

					/** Marcação de FIM 
					String headerMarcaFim(data.substring(349, 350);*/


				break;

			case "10":
				/**
				 * 10_RESUMO do guide Telecom padrão FEBRABAN-V3R0 Somatório dos
				 * Valores por Recurso
				 * 
				 * Controle de sequencia de gravação String resumoControlSeqGrav
				 * = data.substring(2, 14);
				 */
				Resumo resumo = new Resumo();
				ResumoId resumoId = new ResumoId();
				
				/** Identificador de Conta Unica ou Numero da conta */
				resumo.setIdUnico(data.substring(14, 39));

				/** Data da emissão da Fatura/conta */
				try {
					resumoId.setFaturaDataEmissao(sdf.parse(data.substring(39, 47)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				/** Mês de Referência da fatura(cobrança) 
				String resumoMesRef(data.substring(47, 53));*/

				/** Identificador Único do Recurso */
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

				/** Data da Ativação do Recurso VERIFICAR O PARSE DATE */
				try {
					resumo.setDataAtiv(sdf.parse(data.substring(103, 111)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				/** Data da Desativação do Recurso */
				try {
					resumo.setDataDesativ(sdf.parse(data.substring(111, 119)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^


				/** Quantidade de Registro de Chamada 
				String resumoQuantRegChamada(data.substring(119, 128);*/

				/** Valor Total dos Registros de Chamada com Impostos */
				resumo.setValorTotalRegChamadaImp(Float.parseFloat(data.substring(128, 141)));
				
				/** Quantidade de Registros de Serviços */
				resumo.setQuantRegServ(Integer.parseInt(data.substring(141, 150)));

				/** Valor Total dos Registros de Serviços com Impostos]
				String resumoValorTotalRegServImp(data.substring(150, 165)); */

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

				/** Filler 
				String resumoFiller(data.substring(210, 324);*/

				/** Campo Livre para Operadora 
				String resumoCampoLivreOp(data.substring(324, 349);*/

				/** Marcação de Fim 
				String resumoMarcaFim(data.substring(349, 350);*/
				
				break;
			case "20":

				/**
				 * 20_ENDEREÇOS do guia Telecom padrão FEBRABAN-V3R0
				 * Identificação dos endereçõs dos recursos cobrados na fatura
				 */
				Enderecos enderecos = new Enderecos();
				EnderecosId enderecosId = new EnderecosId();
				
				/** Controle de sequencia de gravação 
				String endControlSeqGrav(data.substring(2, 14));*/

				/** Identificador de Conta Unica ou Numero da conta 
				String endIndConta(data.substring(14, 39));*/

				/** Data da emissão da Fatura/conta 
				String endDataEmiFatura(data.substring(39, 47));*/

				/** Mês de Referência da fatura(cobrança) 
				String endMesRef(data.substring(47, 53));*/

				/** Identificador Único do Recurso 
				String endIdUnicoRecurso(data.substring(53, 78));*/

				/** Numero do Recurso 
				String endNumRecurso(data.substring(78, 94));*/

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
				
				break;

			case "30":
				/**
				 * 30_CHAMADAS do guia Telecom padrão FEBRABAN-V3R0 Detalhamento
				 * de chamadas de VOZ cobradas na fatura
				 */
				Chamadas chamadas = new Chamadas();
				ChamadasId chamadasId = new ChamadasId();

				/** Controle de sequencia de gravação 
				String chamaControlSeqGrav(data.substring(2, 14);*/

				/** Identificador de Conta Unica ou Numero da conta 
				String chamaIndConta(data.substring(14, 39);*/

				/** Data da emissão da Fatura/conta 
				String chamaDataEmiFatura(data.substring(39, 47);*/

				/** Mês de Referência da fatura(cobrança) 
				String chamaMesRef(data.substring(47, 53);*/

				/** Identificador Único do Recurso 
				String chamaIdUnicoRecurso(data.substring(53, 78);*/

				/**
				 * CNL da �?rea local onde o terminal estava em uso durante a
				 * chamada **** Código Nacional de localidade: Fixo - definido
				 * pela ANATEL; Móvel definido pela ABR Telecom
				 */
				chamadas.setCnlAreaLocalUso(Integer.parseInt(data.substring(78, 83)));

				/** Numero do recurso 
				String chamaNumRecurso(data.substring(83, 99));

				/** Data da ligação */
				try {
					chamadasId.setDataLigacao(sdf.parse(data.substring(99, 107)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				/**
				 * CNL da localidade de Destino da Chamada **** Código Nacional
				 * de localidade: Fixo - definido pela ANATEL; Móvel definido
				 * pela ABR Telecom
				 */
				chamadas.setCnlLocalDestino(Integer.parseInt(data.substring(107, 112)));

				/** Nome da Localidade de Destino da Chamada */
				chamadas.setNomeLocalDestino(data.substring(112, 137));

				/** UF do Telefone de Destino da Chamada */
				chamadas.setUfTelDestino(data.substring(137, 139));

				/** Código Nacional/Internacional */
				chamadas.setCodNacInt(data.substring(139, 141));

				/**
				 * Código de Seleção da Prestadora - CSP **** Preenchimento
				 * obrigatório para chamadas de longa distância.
				 */
				chamadas.setCodCsp(data.substring(141, 143));

				/**
				 * Nome Operadora CSP **** Preenchimento obrigatório para
				 * chamadas de longa distância.
				 */
				chamadas.setNomeOpCsp(data.substring(143, 163));

				/**
				 * Númerpo do Telefone Chamado **** Para ligações nacionais
				 * obedecer o formato: YYNNNNNNNN, onde: "YY" - Código de area e
				 * "NNNNNNNN" - numero chamado. Para chamadas internacionais
				 * preencher o código do país de destino e número chamado
				 
		>>>>		String chamaNumTelefoneChamada(data.substring(163, 180)); <<<<<

				/**
				 * Código da Operadora de Roaming **** Preencher com o código da
				 * rede móvel utilizada em roaming. MCC+MNC (MCC - Mobile
				 * Country Code e MNC - Mobile Network Code.) OBS: Preenchimento
				 * obrigatório para chamadas/serviços originadas de telefones
				 * móveis, quando em roaming.
				 */
				chamadas.setCodOpRoaming(Integer.parseInt(data.substring(180, 185)));

				/**
				 * Operadora a Qual o Terminal de Destino está
				 * Vinculado(portabilidade)**** Número EOT (Empresa Operadora de
				 * Telecomunicações) junto a ABR Telecom
				 * 
				 * Obrigatório para Chamadas Nacionais - Conforme condições
				 * contratuais pactuadas entre operadoras e clientes.
				 * ´http://www.abr.net.br/grupos/grupos_cadastro.htm
				 */
				chamadas.setOpTerminalVincDestino(data.substring(185, 188));

				/** Duração Ligação**** */
				try {
					chamadas.setDuracaoLigacao(sdfh.parse(data.substring(188, 195)));
				} catch (ParseException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				/** Código da Categoria Chamada**** */
				chamadasId.setCategoriaChamadaCodCatChamada(Integer.parseInt(data.substring(195, 198)));

				/** Sigla da Categoria Chamada 
				String chamaSigCatChamada(data.substring(198, 201));*/

				/** Descrição da Categoria Chamada 
				String chamaDesCatChamada(data.substring(201, 226));*/

				/** Horário da ligação */
				try {
					chamadasId.setHoraLigacao(sdfh.parse(data.substring(226, 232)));
				} catch (ParseException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				/** Alíquota ICMS */
				chamadas.setIcms(Integer.parseInt(data.substring(232, 237)));

				/** Valor da ligação com imposto */
				chamadas.setValLigImp(Float.parseFloat(data.substring(237, 250)));

				/** Valor da Ligação sem Imposto */
				chamadas.setValLigSemImp(Float.parseFloat(data.substring(250, 265)));

				/** Tipo NF */
				chamadas.setTipoNf(Integer.parseInt(data.substring(265, 266)));

				/** Numero da Nota Fiscal */
				chamadas.setNumNf(data.substring(266, 278));

				/** Tipo de Chamada (TC) */
				chamadas.setTipoChamada(data.substring(278, 279));

				/** Grupo Hórario Tarifário */
				chamadas.setGrupoHoraTarif(data.substring(279, 280));

				/** Descrição do Horário Tarifário */
				chamadas.setDesHoraTarif(data.substring(280, 295));

				/** Degrau da Ligação */
				chamadas.setDegrauLigacao(Integer.parseInt(data.substring(295, 297)));

				/** Filler 
				String chamaFiller(data.substring(297, 324);*/

				/** Campo livre para Operadora */
				chamadas.setCampoLivreOp(data.substring(324, 349));

				/** Marcação de Fim 
				String chamaMarcaFim(data.substring(349, 350);*/
				
				break;

			case "40":

				/**
				 * 40_SERVIÇOS do guia Telecom padrão FEBRABAN-V3R0 Detalhamento
				 * dos serviços faturados
				 */
				Servicos servicos = new Servicos();
				ServicosId servicosId = new ServicosId();
				
				/** Controle de sequencia de gravação 
				String servControlSeqGrav(data.substring(2, 14); */

				/** Identificador de Conta Unica ou Numero da conta 
				String servIndConta(data.substring(14, 39); */

				/** Data da emissão da Fatura/conta 
				String servDataEmiFatura(data.substring(39, 47); */

				/** Mês de Referência da fatura(cobrança) 
				String servMesRef(data.substring(47, 53); */

				/** Identificador Único do Recurso 
				String servIdUnicoRecurso(data.substring(53, 78); */

				/**
				 * CNL da �?rea local onde o terminal estava em uso durante a
				 * chamada **** Código Nacional de localidade: Fixo - definido
				 * pela ANATEL; Móvel definido pela ABR Telecom
				 */
				servicos.setCnlAreaLocalUso(Integer.parseInt(data.substring(78, 83)));

				/**
				 *  Numero do recurso 
				
				String servNumRecurso(data.substring(83, 99); */

				/**
				 *  Data do Serviço 
				 */
				try {
					servicosId.setDataServico(sdf.parse(data.substring(99, 107)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				/**
				 * Codigo Nacional / Internacional
				 */
				servicos.setCodNacInt(data.substring(107, 109));

				/**
				 * Número Telefone Destino
				 */
				servicos.setNumTelDestino(data.substring(109, 126));

				/**
				 * Codigo operadora de Roaming
				 */
				servicos.setCodOpRoaming(Integer.parseInt(data.substring(126, 131)));

				/**
				 * Operadora a qual o terminal de destino esta
				 * vinculado(portabilidade)
				 */
				servicos.setOpTerminalVincDestino(data.substring(131, 134));

				/**
				 * Quantidade Utilizada
				 */
				servicos.setQuantUtil(Integer.parseInt(data.substring(134, 140)));

				/**
				 * Unidade do Serviço
				 */
				servicos.setUnidadeServico(data.substring(140, 142));

				/**
				 * Horario do Serviço
				 */
				try {
					servicosId.setHoraServico(sdfh.parse(data.substring(142, 148)));
				} catch (ParseException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				

				/**
				 * Codigo da Categoria do Serviço
				 */
				servicosId.setCategoriaServicoCodCatServico(Integer.parseInt(data.substring(148, 151)));

				/**
				 * Sigla da Categoria do Serviço
				 * String servSigCatServico(data.substring(151, 154); */

				/**
				 * Decrição da Categoria Serviço */
			//	 servicos.setCategoriaservico(data.substring(154, 179));

				/**
				 * Valor do Serviço com Impostos
				 */
				servicos.setValServImp(Float.parseFloat(data.substring(179, 192)));

				/**
				 * Valor do Serviço Sem Impostos
				 */
				servicos.setValServ(Float.parseFloat(data.substring(192, 207)));

				/**
				 * Tipo Nota Fiscal NF
				 */
				servicos.setTipoNf(Integer.parseInt(data.substring(207, 208)));

				/**
				 * Numero da nota Fiscal
				 */
				servicos.setNumNf(data.substring(208, 220));

				/** Filler 
				String servFiller(data.substring(220, 324); */

				/** Campo livre para Operadora */
				servicos.setCampoLivreOp(data.substring(324, 349));

				/** Marcação de Fim 
				String servMarcaFim(data.substring(349, 350);*/
				
				break;

			case "50":
				/**
				 * 50_DESCONTOS do guia Telecom padrão FEBRABAN-V3R0
				 * Detalhamento dos descontos concedidos
				 */

				Descontos descontos = new Descontos();
				DescontosId descontosId = new DescontosId();
				
				/** Controle de sequencia de gravação 
				String descControlSeqGrav(data.substring(2, 14); */

				/** Identificador de Conta Unica ou Numero da conta 
				String descIndConta(data.substring(14, 39);*/

				/** Data da emissão da Fatura/conta 
				String descDataEmiFatura(data.substring(39, 47); */

				/** Mês de Referência da fatura(cobrança) 
				String descMesRef(data.substring(47, 53); */

				/** Identificador Único do Recurso 
				String descIdUnicoRecurso(data.substring(53, 78); */

				/** Numero do Telefone 
				String descNumTelefone(data.substring(78, 94);*/

				/**
				 * Tipo do Desconto
				 * 
				 */
				descontos.setTipo(data.substring(94, 95));

				/**
				 * Codigo da Categoria Descontos
				 * 
				 */
				//descontos.setCategoriadesconto(data.substring(95, 98));

				/**
				 * Sigla da Categoria Descontos
				 * String descSigCatDescontos(data.substring(98, 101); 
				 */
				

				/**
				 * Descrição da Categhoria Desconto
				 * String descDescricaoCatDesconto(data.substring(101, 126); 
				 */
				

				/**
				 * Base de Calculo Desconto
				 * 
				 */
				descontos.setBaseCal(Float.parseFloat(data.substring(126, 139)));

				/**
				 * Tipo de Nota Fiscal NF
				 * 
				 */
				descontos.setTipoNf(Integer.parseInt(data.substring(139, 140)));

				/**
				 * Numero da Nota Fiscal
				 * 
				 */
				descontos.setNumNf(data.substring(140, 152));

				/**
				 * Percentual de Desconto
				 * 
				 */
				descontos.setPercentual(Float.parseFloat(data.substring(152, 157)));

				/**
				 * Sinal do Desconto
				 * 
				 */
				descontos.setSinal(data.substring(157, 158));

				/**
				 * Valor do Desconto
				 * 
				 */
				descontos.setValor(Float.parseFloat(data.substring(158, 171)));

				/**
				 * Data Inicio do Desconto
				 * 
				 */
				try {
					descontosId.setDataInicio(sdf.parse(data.substring(171, 179)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				/**
				 * Hora inicio do Desconto
				 * 
				 */
				try {
					descontosId.setHoraInicio(sdfh.parse(data.substring(179, 185)));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				/**
				 * Data Fim do Desconto
				 * 
				 */
				try {
					descontos.setDataFim(sdf.parse(data.substring(185, 193)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				/**
				 * Hora Fim do Desconto
				 * 
				 */
				try {
					descontos.setHoraFim(sdfh.parse(data.substring(193, 199)));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				/** Filler 
				String descFiller(data.substring(199, 324);*/

				/** Campo livre para Operadora */
				descontos.setCampoLivreOp(data.substring(324, 349));

				/** Marcação de Fim 
				String descMarcaFim(data.substring(349, 350); */
				
				break;

			case "60":
				/**
				 * 60_PLANOS do guia Telecom padrão FEBRABAN-V3R0 Detalhamento
				 * dos planos faturados
				 */
				PlanosId planosId = new PlanosId();
				Planos planos = new Planos();
				
				/** Controle de sequencia de gravação 
				String planosControlSeqGrav(data.substring(2, 14);

				/** Identificador de Conta Unica ou Numero da conta 
				String planosIndConta(data.substring(14, 39);*/

				/** Data da emissão da Fatura/conta */
				try {
					planosId.setResumoFaturaDataEmissao(sdf.parse(data.substring(39, 47)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

				/** Mês de Referência da fatura(cobrança)
				String planosMesRef(data.substring(47, 53); */

				/** Identificador Único do Recurso */
				planosId.setResumoNumRecurso(data.substring(53, 78));

				/** Numero do Telefone 
				String planoNumTelefone(data.substring(78, 94);*/

				/**
				 * Tipo do Plano
				 */
				planos.setTipo(data.substring(94, 95));

				/**
				 * Data inicio do Ciclo do Plano
				 * 
				 */
				try {
					planosId.setDataIniCiclo(sdf.parse(data.substring(95, 103)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				/**
				 * Data Fim do Ciclo do Plano
				 * 
				 */
				try {
					planos.setDataFimCiclo(sdf.parse(data.substring(103, 111)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				/**
				 * Codigo da Operadora
				 * 
				 */
				planosId.setResumoFaturaClienteOperadoraCodOperadora(Integer.parseInt(data.substring(111, 114)));

				/**
				 * Nome da Operadora
				 * String planoNomeOp(data.substring(114, 129); 
				 */
				

				/**
				 * Consumo Medido
				 * 
				 */
				planos.setConsumoMedido(Float.parseFloat(data.substring(129, 141)));

				/**
				 * Consumo Franqueado
				 * 
				 */
				planos.setConsumoFranqueado(Float.parseFloat(data.substring(141, 153)));

				/**
				 * Unidade de Medida
				 * String planoUnidadeMedida(data.substring(153, 155); 
				 */
				

				/**
				 * Codigo da Categoria do Plano
				 * String planoCodCat(data.substring(155, 158); 
				 */
				

				/**
				 * Sigla da Categoria do Plano
				 * String planoSigCat(data.substring(158, 161);
				 */
				

				/**
				 * Descrição da Categoria do Plano
				 * String planoDescCat(data.substring(161, 186); 
				 */
				

				/**
				 * Codigo do Plano
				 * 
				 */
				planos.setCodPlano(data.substring(186, 191));

				/**
				 * Descrição do plano
				 * 
				 */
				planos.setDescricaoPlano(data.substring(191, 216));

				/**
				 * Valor do Plano com Imposto
				 * 
				 */
				planos.setValComImp(Float.parseFloat(data.substring(216, 229)));

				/**
				 * Valor do Plano sem Imposto
				 * 
				 */
				planos.setValSemImp(Float.parseFloat(data.substring(229, 244)));

				/**
				 * Tipo Nota Fiscal NF
				 * 
				 */
				planos.setTipoNf(Integer.parseInt(data.substring(244, 245)));

				/**
				 * Numero da Nota Fiscal NF
				 * 
				 */
				planos.setNumNf(data.substring(245, 257));

				/** Filler 
				String planoFiller(data.substring(257, 324); */

				/** Campo livre para Operadora */
				planos.setCampoLivreOp(data.substring(324, 349));

				/** Marcação de Fim 
				String planoMarcaFim(data.substring(349, 350);*/
				
				break;

			case "70":
				/**
				 * 70_AJUSTES do guia Telecom padrão FEBRABAN-V3R0 Detalhamento
				 * dos ajustes financeiros de movimentos anteriores
				 *
				 * Controle de sequencia de gravação String
				 * ajustesControlSeqGrav(data.substring(2, 14);
				 * 
				 * Identificador de Conta Unica ou Numero da conta String
				 * ajustesIndConta(data.substring(14, 39);
				 * 
				 * Data da emissão da Fatura/conta String ajustesDataEmiFatura =
				 * data.substring(39, 47);
				 * 
				 * Mês de Referência da fatura(cobrança) String ajustesMesRef =
				 * data.substring(47, 53);
				 *
				 * Identificador Único do Recurso String ajustesIdUnicoRecurso =
				 * data.substring(53, 78);
				 *
				 * Numero do Telefone String ajustesNumTelefone =
				 * data.substring(78, 94);
				 */
				Ajustes ajustes = new Ajustes();
				AjustesId ajustesId = new AjustesId();

				/** Tipo do Plano */
				ajustes.setTipo(data.substring(94, 95));

				/** Codigo da Categoria dos Ajustes */
				//ajustesId.setAjustesCodCat(Integer.parseInt(data.substring(95, 98)));

				/** Sigla da Categoria dos Ajustes */
			//	ajustesId.setCategoriaAjusteCodCatAjuste(Integer.parseInt(data.substring(98, 101)));

				/**
				 * Descrição da Categoria dos Ajustes String ajustesDescCat =
				 * data.substring(101, 141);
				 */

				/** Base de Cálculo dos Ajustes */
				ajustes.setBaseCalculo(Float.parseFloat(data.substring(141, 154)));

				/** Percentual dos Ajustes */
				ajustes.setPercentual(Float.parseFloat(data.substring(154, 159)));

				/** Sinal de Ajuste */
				ajustes.setSinal(data.substring(159, 160));

				/** Valor do Ajuste */
				ajustes.setValor(Float.parseFloat(data.substring(160, 173)));

				/** Data Inicio do Acerto */
				try {
					ajustesId.setDataInicio(sdf.parse(data.substring(173, 181)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				/** Hora Inicio do Acerto */
				try {
					ajustesId.setHoraInicio(sdfh.parse(data.substring(181, 187)));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				/** Data Fim do Acerto */
				try {
					ajustes.setDataFim(sdf.parse(data.substring(187, 195)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				/** Hora Fim do Acerto */
				try {
					ajustes.setHoraFim(sdfh.parse(data.substring(195, 201)));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				/**
				 * Filler String ajustesFiller(data.substring(201, 324);
				 */

				/** Campo livre para Operadora */
				ajustes.setCampoLivreOp(data.substring(324, 349));

				/**	 Marcação de Fim 
				 String ajustesMarcaFim(data.substring(349,350); */
				
				break;

			case "80":
				/**
				 * 80_NF do guia Telecom padrão FEBRABAN-V3R0 Totalizador por
				 * nota fiscal apresentada
				 *
				 *
				 * Controle de sequencia de gravação String nfControlSeqGrav =
				 * data.substring(2, 14);
				 *
				 * Identificador de Conta Unica ou Numero da conta String
				 * nfIndConta(data.substring(14, 39);
				 *
				 * Data da emissão da Fatura/conta String nfDataEmiFatura =
				 * data.substring(39, 47);
				 *
				 * Mês de Referência da fatura(cobrança) String nfMesRef =
				 * data.substring(47, 53);
				 *
				 *
				 * Data de Vencimento da Nota Fiscal NF String nfDataVenc =
				 * data.substring(53, 61);
				 *
				 * Codigo da Operadora String nfCodOp(data.substring(61, 64);
				 *
				 * Nome da Operadora String nfNomeOp(data.substring(64, 79);
				 *
				 * CNPJ Operadora String nfCnpjOp(data.substring(79, 94);
				 */
				Notafiscal notaFiscal = new Notafiscal();
				NotafiscalId notaFiscalId = new NotafiscalId();
				/** Valor da Nota Fiscal NF com Impostos */
				notaFiscal.setValorNfimp(Float.parseFloat(data.substring(94, 107)));

				/** Tipo de Nota Fiscal NF */
				notaFiscal.setTipoNf(data.substring(107, 108));

				/** Numero da Nota Fiscal NF */
				notaFiscalId.setNumNf(data.substring(108, 120));

				/**
				 * Filler String nfFiller(data.substring(201, 324);
				 */

				/** Campo livre para Operadora */
				notaFiscal.setCampoLivreOp(data.substring(324, 349));

				/**	 Marcação de Fim 
				 String nfMarcaFim(data.substring(349, 350); */
				
				break;

			case "90":
				/**
				 * 90_INFORMATIVOS do guia Telecom padrão FEBRABAN-V3R0
				 * Informativo gerencial, valores não contemplados na fatura
				 *
				 *
				 * Controle de sequencia de gravação String infControlSeqGrav =
				 * data.substring(2, 14);
				 *
				 * Identificador de Conta Unica ou Numero da conta String
				 * infIndConta(data.substring(14, 39);
				 * 
				 * Data da emissão da Fatura/conta String infDataEmiFatura =
				 * data.substring(39, 47);
				 *
				 * Mês de Referência da fatura(cobrança) String infMesRef =
				 * data.substring(47, 53);
				 *
				 * Identificador Único do Recurso String infIdUnicoRecurso =
				 * data.substring(53, 78);
				 *
				 * CNL do Recurso String infCnlRecurso(data.substring(78, 83);
				 *
				 * Numero do Recurso String infNumRecurso(data.substring(83,
				 * 99);
				 *
				 * Codigo da Categoria do Informativo String
				 * infCodCatInformativo(data.substring(99, 102);
				 *
				 * Texto Informativo String infTexto(data.substring(102, 302);
				 *
				 * Sinal do Valor String infSinVal(data.substring(302, 303);
				 *
				 * Valor String infValor(data.substring(303, 316);
				 *
				 * Filler String infFiller(data.substring(201, 324);
				 *
				 * Campo livre para Operadora String infCampoLivreOp =
				 * data.substring(324, 349);
				 *
				 * Marcação de Fim String infMarcaFim(data.substring(349,
				 * 350);
				 * break;
				 */

			case "99":
				/**
				 * 99_TRAILLER do guia Telecom padrão FEBRABAN-V3R0 Consolidação
				 * de valores da conta faturada
				 * 
				 * >>> Codigo de leitura comentado para preservação de trabalho
				 * <<<
				 */

				TraillerId traillerId = new TraillerId();
				Trailler trailler = new Trailler();
				/**
				 * Controle de sequencia de gravação String
				 * traillerControlSeqGrav(data.substring(2, 14);
				 * 
				 * /** Identificador de Conta Unica ou Numero da conta String
				 * traillerIndConta(data.substring(14, 39);
				 */

				/** Data da emissão da Fatura/conta */

				try {
					traillerId.setFaturaDataEmissao(sdf.parse(data.substring(39, 47)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				/**
				 * Mês de Referência da fatura(cobrança) String traillerMesRef =
				 * data.substring(47, 53);
				 * 
				/** Data de Vencimento 
				 * String traillerDataVenc(data.substring(53, 61); 
				 */
				 
				
				 /** Codigo do Cliente */
				traillerId.setFaturaClienteCodCliente(data.substring(61, 76));

				/** 
				 * Valor Total 
				 */
				trailler.setValTotal(Float.parseFloat(data.substring(76, 89)));

				/**
				 * Quantidade de Total de Registros
				 */
				trailler.setQuanTotalReg(Integer.parseInt(data.substring(89, 101)));

				/**
				 * Valor Total Registro 10
				 */
				trailler.setValTotal10(Float.parseFloat(data.substring(101, 114)));

				/**
				 * Quantidade de Registros 10
				 */
				trailler.setQuanReg10(Integer.parseInt(data.substring(114, 123)));

				/**
				 * Quantidade de Registros 20
				 */
				trailler.setQuanReg20(Integer.parseInt(data.substring(123, 132)));

				/**
				 * Valor Total Registro 30
				 */
				trailler.setValTotal30(Float.parseFloat(data.substring(132, 145)));

				/**
				 * Quantidade de Registros 30
				 */
				trailler.setQuanReg30(Integer.parseInt(data.substring(145, 154)));

				/**
				 * Valor Total Registro 40
				 */
				trailler.setValTotal40(Float.parseFloat(data.substring(154, 167)));

				/**
				 * Quantidade de Registros 40
				 */
				trailler.setQuanReg40(Integer.parseInt(data.substring(167, 176)));

				/**
				 * Sinal Total Registro 50
				 */
				trailler.setSinTotalReg50(data.substring(176, 177).charAt(0));

				/**
				 * Valor Total Registro 50
				 */
				trailler.setValTotal50(Float.parseFloat(data.substring(177, 190)));

				/**
				 * Quantidade de Registros 50
				 */
				trailler.setQuanReg50(Integer.parseInt(data.substring(191, 199)));

				/**
				 * Valor Total Registro 60
				 */
				trailler.setValTotal60(Float.parseFloat(data.substring(199, 212)));

				/**
				 * Quantidade de Registros 60
				 */
				trailler.setQuanReg60(Integer.parseInt(data.substring(212, 221)));

				/**
				 * Sinal Total Registro 70
				 */
				trailler.setSinTotalReg70(data.substring(221, 222).charAt(0));

				/**
				 * Valor Total Registro 70
				 */
				trailler.setValTotal70(Float.parseFloat(data.substring(222, 235)));

				/**
				 * Quantidade de Registros 70
				 */
				trailler.setQuanReg70(Integer.parseInt(data.substring(235, 244)));

				/**
				 * Valor Total Registro 80
				 */
				trailler.setValTotal80(Float.parseFloat(data.substring(244, 257)));

				/**
				 * Quantidade de Registros 80
				 */
				trailler.setQuanReg80(Integer.parseInt(data.substring(257, 266)));

				/**
				 * Filler String traillerFiller(data.substring(201, 324);
				 *
				 ** Campo livre para Operadora
				 */
				trailler.setCampoLivreOp(data.substring(324, 349));

				/** Marcação de Fim
				 	 String traillerMarcaFim(data.substring(349, 350); */
				
				break;
			}

		}
		fileReader.close();
		reader.close();
	}

	public static void main(String[] args) {
		try {
			LeitorFebrabanV3.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
