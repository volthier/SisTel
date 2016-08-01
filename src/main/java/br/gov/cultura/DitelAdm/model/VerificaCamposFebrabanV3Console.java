package br.gov.cultura.DitelAdm.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class VerificaCamposFebrabanV3Console {

	private static File file = new File(
			// "C:\\Users\\Administrador\\Desktop\\Projetos de
			// ProgramaÃ§Ã£o\\Arquivos DITEL\\Faturas para o Projeto
			// ditel\\711725423_919441395_14_02_2016_FebrabanV3.txt");
			"C:\\Users\\72381817115\\Desktop\\Projetos de Programação\\Arquivos DITEL\\Faturas para o Projeto ditel\\711725423_919441395_14_02_2016_FebrabanV3.txt");

	private static final void read(File file) throws IOException {
		FileReader fileReader = new FileReader(file);
		BufferedReader reader = new BufferedReader(fileReader);
		String data = null;
		
		while ((data = reader.readLine()) != null) {
			String TipoReg = data.substring(0, 2);
			switch (TipoReg)

			{
		/*	case "00":
					*//**
					 * 00_HEADER do guide Telecom padrÃ£o FEBRABAN-V3R0
					 * Identifica CabeÃ§alho da conta
					 *//*

					*//** Controle de sequencia de gravaÃ§Ã£o *//*
					String headerControlSeqGrav = data.substring(2, 14);

					*//** Identificador de Conta Unica ou Numero da conta *//*
					String headerIndConta = data.substring(14, 39);

					*//** Data da emissÃ£o da Fatura/conta *//*
					String headerDataEmiFatura = data.substring(39, 47);

					*//** MÃªs de ReferÃªncia da fatura(cobranÃ§a) *//*
					String headerMesRef = data.substring(47, 53);

					*//** Data de GeraÃ§Ã£o do Arquivo *//*
					String headerDataGeraArquivo = data.substring(53, 61);

					*//** Data de Vencimento da Fatura *//*
					String headerDataVencFatura = data.substring(61, 69);

					*//** Codigo da Operadora *//*
					String headerCodOpe = data.substring(69, 72);

					*//** Nome da Operadora *//*
					String headerNomeOpe = data.substring(72, 87);

					*//** CNPJ da Operadora *//*
					String headerCnpjOpe = data.substring(87, 102);

					*//** UF da Operadora *//*
					String headerUfOpe = data.substring(102, 104);

					*//** Codigo do Cliente *//*
					String headerCodCliente = data.substring(104, 119);

					*//** Nome do Cliente *//*
					String headerNomeCliente = data.substring(119, 149);

					*//** CNPJ do Cliente *//*
					String headerCnpjCliente = data.substring(149, 164);

					*//**
					 * VersÃ£o do Formato do Documento >>> (Tem que aparecer
					 * V3R0) <<<
					 *//*
					String headerVersaoFormato = data.substring(164, 168);

					*//** Numero da Fatura *//*
					String headerNumFatura = data.substring(168, 184);

					*//** Codigo de Barra *//*
					String headerCodBarra = data.substring(184, 234);

					*//**
					 * Codigo de CobranÃ§a ( Legenda do Valor) 01 - Debito
					 * automÃ¡tico; 02 - CrÃ©dito em conta; 03 - CobranÃ§a simples;
					 * 04 - CobranÃ§a registrada; 05 - CartÃ£o CrÃ©dito; 06 -
					 * Outros
					 *//*

					String headerCodCobranca = data.substring(234, 236);

					*//**
					 * DescriÃ§Ã£o(Ou Tipo) da CobranÃ§a (Legenda) 01 - Debito
					 * automÃ¡tico; 02 - CrÃ©dito em conta; 03 - CobranÃ§a simples;
					 * 04 - CobranÃ§a registrada; 05 - CartÃ£o CrÃ©dito; 06 -
					 * Outros
					 *//*

					String headerDescriCobranca = data.substring(236, 256);

					*//** Banco da CobranÃ§a *//*
					String headerBancoCobranca = data.substring(256, 260);

					*//** Agencia da CobranÃ§a *//*
					String headerAgenciaCobranca = data.substring(260, 264);

					*//** Conta Corrente da CobranÃ§a *//*
					String headerCcCobranca = data.substring(264, 274);

					*//** FISCO *//*
					String headerFisco = data.substring(274, 309);

					*//** Filler *//*
					String headerFiller = data.substring(309, 324);

					*//** Campo Livre Para Operadora *//*
					String headerCampoLivreOp = data.substring(324, 349);

					*//** MarcaÃ§Ã£o de FIM *//*
					String headerMarcaFim = data.substring(349, 350);

					System.out.println(TipoReg);
					System.out.println(headerControlSeqGrav);
					System.out.println(headerIndConta);
					System.out.println(headerDataEmiFatura);
					System.out.println(headerMesRef);
					System.out.println(headerDataGeraArquivo);
					System.out.println(headerDataVencFatura);
					System.out.println(headerCodOpe);
					System.out.println(headerNomeOpe);
					System.out.println(headerCnpjOpe);
					System.out.println(headerUfOpe);
					System.out.println(headerCodCliente);
					System.out.println(headerNomeCliente);
					System.out.println(headerCnpjCliente);
					System.out.println(headerVersaoFormato);
					System.out.println(headerNumFatura);
					System.out.println(headerCodBarra);
					System.out.println(headerCodCobranca);
					System.out.println(headerDescriCobranca);
					System.out.println(headerBancoCobranca);
					System.out.println(headerAgenciaCobranca);
					System.out.println(headerCcCobranca);
					System.out.println(headerFisco);
					System.out.println(headerFiller);
					System.out.println(headerCampoLivreOp);
					System.out.println(headerMarcaFim);
				

				break;

			case "10":
				*//**
				 * 10_RESUMO do guide Telecom padrÃ£o FEBRABAN-V3R0 SomatÃ³rio dos
				 * Valores por Recurso
				 *//*

				*//** Controle de sequencia de gravaÃ§Ã£o *//*
				String resumoControlSeqGrav = data.substring(2, 14);

				*//** Identificador de Conta Unica ou Numero da conta *//*
				String resumoIndConta = data.substring(14, 39);

				*//** Data da emissÃ£o da Fatura/conta *//*
				String resumoDataEmiFatura = data.substring(39, 47);

				*//** MÃªs de ReferÃªncia da fatura(cobranÃ§a) *//*
				String resumoMesRef = data.substring(47, 53);

				*//** Identificador Ãšnico do Recurso *//*
				String resumoIdUnicoRecurso = data.substring(53, 78);

				*//**
				 * CNL do Recurso (Legenda) (6) CÃ³digo Nacional de localidade:
				 * Fixo - definido pela ANATEL; MÃ³vel definido pela ABR Telecom
				 *//*
				String resumoCnlRecurso = data.substring(78, 83);

				*//** Numero do Recurso *//*
				String resumoNumRecurso = data.substring(83, 99);

				*//** Modalidade de ServiÃ§o do recurso *//*
				String resumoModServ = data.substring(99, 103);

				*//** Data da AtivaÃ§Ã£o do Recurso *//*
				String resumoDataAtivRecurso = data.substring(103, 111);

				*//** Data da DesativaÃ§Ã£o do Recurso *//*
				String resumoDataDesativRecurso = data.substring(111, 119);

				*//** Quantidade de Registro de Chamada *//*
				String resumoQuantRegChamada = data.substring(119, 128);

				*//** Valor Total dos Registros de Chamada com Impostos *//*
				String resumoValorTotalRegChamadaImp = data.substring(128, 141);
				
				*//** Quantidade de Registros de ServiÃ§os *//*
				String resumoQuantRegServ = data.substring(141, 150);

				*//** Valor Total dos Registros de ServiÃ§os com Impostos *//*
				String resumoValorTotalRegServImp = data.substring(150, 165);

				*//** Valor Total de Impostos *//*
				String resumoValorTotalImp = data.substring(165, 178);

				*//** Valor Total da Conta do Recurso Com Impostos *//*
				String resumoValorTotalContaRecursoImp = data.substring(178, 191);

				*//** Degrau do Recurso *//*
				String resumoDegrauRecurso = data.substring(191, 193);

				*//** Velocidade do Recurso *//*
				String resumoVeloRecurso = data.substring(193, 198);

				*//** Unidade da Velocidade do Recurso *//*
				String resumoUniVeloRecurso = data.substring(198, 202);

				*//** Data Vencimento *//*
				String resumoDataVenc = data.substring(202, 210);

				*//** Filler *//*
				String resumoFiller = data.substring(210, 324);

				*//** Campo Livre para Operadora *//*
				String resumoCampoLivreOp = data.substring(324, 349);

				*//** MarcaÃ§Ã£o de Fim *//*
				String resumoMarcaFim = data.substring(349, 350);

				System.out.println(TipoReg);
				System.out.println(resumoControlSeqGrav);
				System.out.println(resumoIndConta);
				System.out.println(resumoDataEmiFatura);
				System.out.println(resumoMesRef);
				System.out.println(resumoIdUnicoRecurso);
				System.out.println(resumoCnlRecurso);
				System.out.println(resumoNumRecurso);
				System.out.println(resumoModServ);
				System.out.println(resumoDataAtivRecurso);
				System.out.println(resumoDataDesativRecurso);
				System.out.println(resumoQuantRegChamada);
				System.out.println(resumoValorTotalRegChamadaImp);
				System.out.println(resumoQuantRegServ);
				System.out.println(resumoValorTotalRegServImp);
				System.out.println(resumoValorTotalImp);
				System.out.println(resumoValorTotalContaRecursoImp);
				System.out.println(resumoDegrauRecurso);
				System.out.println(resumoVeloRecurso);
				System.out.println(resumoUniVeloRecurso);
				System.out.println(resumoDataVenc);
				System.out.println(resumoFiller);
				System.out.println(resumoCampoLivreOp);
				System.out.println(resumoMarcaFim);

				break;
			case "20":

				*//**
				 * 20_ENDEREÃ‡OS do guia Telecom padrÃ£o FEBRABAN-V3R0
				 * IdentificaÃ§Ã£o dos endereÃ§Ãµs dos recursos cobrados na fatura
				 *//*

				*//** Controle de sequencia de gravaÃ§Ã£o *//*
				String endControlSeqGrav = data.substring(2, 14);

				*//** Identificador de Conta Unica ou Numero da conta *//*
				String endIndConta = data.substring(14, 39);

				*//** Data da emissÃ£o da Fatura/conta *//*
				String endDataEmiFatura = data.substring(39, 47);

				*//** MÃªs de ReferÃªncia da fatura(cobranÃ§a) *//*
				String endMesRef = data.substring(47, 53);

				*//** Identificador Ãšnico do Recurso *//*
				String endIdUnicoRecurso = data.substring(53, 78);

				*//** Numero do Recurso *//*
				String endNumRecurso = data.substring(78, 94);

				*//** CLN do Recurso EndereÃ§o Ponta A *//*
				String endClnRecEndPontaA = data.substring(94, 99);

				*//** Nome da Localidade do EndereÃ§o Ponta A *//*
				String endNomeLocalEndPontaA = data.substring(99, 114);

				*//** UF da Localidade Ponta A *//*
				String endUfLocalPontaA = data.substring(114, 116);

				*//** EndereÃ§o da Ponta A *//*
				String endEndPontaA = data.substring(116, 146);

				*//** Numero do EndereÃ§o da Ponta A *//*
				String endNumeroEndPontaA = data.substring(146, 151);

				*//** Complemento da Ponta A *//*
				String endComplementoPontaA = data.substring(151, 159);

				*//** Bairro da Ponta A *//*
				String endBairroPontaA = data.substring(159, 169);

				*//** CLN do Recurso EndereÃ§o Ponta B *//*
				String endClnRecEndPontaB = data.substring(169, 174);

				*//** Nome da Localidade do EndereÃ§o Ponta B *//*
				String endNomeLocalEndPontaB = data.substring(174, 189);

				*//** UF da Localidade Ponta B *//*
				String endUfLocalPontaB = data.substring(189, 191);

				*//** EndereÃ§o da Ponta B *//*
				String endEndPontaB = data.substring(191, 221);
				
				*//** Numero do EndereÃ§o da Ponta B *//*
				String endNumeroEndPontaB = data.substring(221, 226);

				*//** Complemento da Ponta B *//*
				String endComplementoPontaB = data.substring(226, 234);
				
				*//** Bairro da Ponta B *//*
				String endBairroPontaB = data.substring(234, 244);

				*//** CLN do Recurso EndereÃ§o Ponta C *//*
				String endClnRecEndPontaC = data.substring(244, 249);

				*//** Nome da Localidade do EndereÃ§o Ponta C *//*
				String endNomeLocalEndPontaC = data.substring(249, 264);

				*//** UF da Localidade Ponta C *//*
				String endUfLocalPontaC = data.substring(264, 266);
				
				*//** EndereÃ§o da Ponta C *//*
				String endEndPontaC = data.substring(266, 296);

				*//** Numero do EndereÃ§o da Ponta C *//*
				String endNumeroEndPontaC = data.substring(296, 301);

				*//** Complemento da Ponta A *//*
				String endComplementoPontaC = data.substring(301, 309);

				*//** Bairro da Ponta C *//*
				String endBairroPontaC = data.substring(309, 319);

				*//** Filler *//*
				String endFiller = data.substring(319, 324);

				*//** Campo Livre para Operadora *//*
				String endCampoLivreOp = data.substring(324, 349);

				*//** MarcaÃ§Ã£o de Fim *//*
				String endMarcaFim = data.substring(349, 350);

				System.out.println(TipoReg);
				System.out.println(endControlSeqGrav);
				System.out.println(endIndConta);
				System.out.println(endDataEmiFatura);
				System.out.println(endMesRef);
				System.out.println(endIdUnicoRecurso);
				System.out.println(endNumRecurso);
				System.out.println(endClnRecEndPontaA);
				System.out.println(endNomeLocalEndPontaA);
				System.out.println(endUfLocalPontaA);
				System.out.println(endEndPontaA);
				System.out.println(endNumeroEndPontaA);
				System.out.println(endComplementoPontaA);
				System.out.println(endBairroPontaA);
				System.out.println(endClnRecEndPontaB);
				System.out.println(endNomeLocalEndPontaB);
				System.out.println(endUfLocalPontaB);
				System.out.println(endEndPontaB);
				System.out.println(endNumeroEndPontaB);
				System.out.println(endComplementoPontaB);
				System.out.println(endBairroPontaB);
				System.out.println(endClnRecEndPontaC);
				System.out.println(endNomeLocalEndPontaC);
				System.out.println(endUfLocalPontaC);
				System.out.println(endEndPontaC);
				System.out.println(endNumeroEndPontaC);
				System.out.println(endComplementoPontaC);
				System.out.println(endBairroPontaC);
				System.out.println(endFiller);
				System.out.println(endCampoLivreOp);
				System.out.println(endMarcaFim);
				break;*/

			case "30":
				/**
				 * 30_CHAMADAS do guia Telecom padrÃ£o FEBRABAN-V3R0 Detalhamento
				 * de cham,adas de VOZ cobradas na fatura
				 */

				/** Controle de sequencia de gravaÃ§Ã£o */
				String chamaControlSeqGrav = data.substring(2, 14);

				/** Identificador de Conta Unica ou Numero da conta */
				String chamaIndConta = data.substring(14, 39);

				/** Data da emissÃ£o da Fatura/conta */
				String chamaDataEmiFatura = data.substring(39, 47);

				/** MÃªs de ReferÃªncia da fatura(cobranÃ§a) */
				String chamaMesRef = data.substring(47, 53);

				/** Identificador Ãšnico do Recurso */
				String chamaIdUnicoRecurso = data.substring(53, 78);

				/**
				 * CNL da Ã�rea local onde o terminal estava em uso durante a
				 * chamada **** CÃ³digo Nacional de localidade: Fixo - definido
				 * pela ANATEL; MÃ³vel definido pela ABR Telecom
				 */
				String chamaClnAreaLocalUsochamada = data.substring(78, 83);

				/** Numero do recurso */
				String chamaNumRecurso = data.substring(83, 99);

				/** Data da ligaÃ§Ã£o */
				String chamaDataLigacao = data.substring(99, 107);

				/**
				 * CNL da localidade de Destino da Chamada **** CÃ³digo Nacional
				 * de localidade: Fixo - definido pela ANATEL; MÃ³vel definido
				 * pela ABR Telecom
				 */
				String chamaCnlLocalDestinoChamada = data.substring(107, 112);

				/** Nome da Localidade de Destino da Chamada */
				String chamaNomeLocalDestinoChamada = data.substring(112, 137);

				/** UF do Telefone de Destino da Chamada */
				String chamaUfTelDestinoChamada = data.substring(137, 139);

				/** CÃ³digo Nacional/Internacional */
				String chamaCodNacInt = data.substring(139, 141);

				/**
				 * CÃ³digo de SeleÃ§Ã£o da Prestadora - CSP **** Preenchimento
				 * obrigatÃ³rio para chamadas de longa distÃ¢ncia.
				 */
				String chamaCsp = data.substring(141, 143);

				/**
				 * Nome Operadora CSP **** Preenchimento obrigatÃ³rio para
				 * chamadas de longa distÃ¢ncia.
				 */
				String chamaNomeOpCsp = data.substring(143, 163);

				/**
				 * NÃºmerpo do Telefone Chamado **** Para ligaÃ§Ãµes nacionais
				 * obedecer o formato: YYNNNNNNNN, onde: "YY" - CÃ³digo de area e
				 * "NNNNNNNN" - numero chamado. Para chamadas internacionais
				 * preencher o cÃ³digo do paÃ­s de destino e nÃºmero chamado
				 */
				String chamaNumTelefoneChamada = data.substring(163, 180);

				/**
				 * CÃ³digo da Operadora de Roaming **** Preencher com o cÃ³digo da
				 * rede mÃ³vel utilizada em roaming. MCC+MNC (MCC - Mobile
				 * Country Code e MNC - Mobile Network Code.) OBS: Preenchimento
				 * obrigatÃ³rio para chamadas/serviÃ§os originadas de telefones
				 * mÃ³veis, quando em roaming.
				 */
				String chamaCodOpRoaming = data.substring(180, 185);

				/**
				 * Operadora a Qual o Terminal de Destino estÃ¡
				 * Vinculado(portabilidade)**** NÃºmero EOT (Empresa Operadora de
				 * TelecomunicaÃ§Ãµes) junto a ABR Telecom
				 * 
				 * ObrigatÃ³rio para Chamadas Nacionais - Conforme condiÃ§Ãµes
				 * contratuais pactuadas entre operadoras e clientes.
				 * Â´http://www.abr.net.br/grupos/grupos_cadastro.htm
				 */
				String chamaOpTerminalDestVincPortabilidade = data.substring(185, 188);

				/** DuraÃ§Ã£o LigaÃ§Ã£o**** */
				String chamaDuracaoLigacao = data.substring(188, 195);

				/** CÃ³digo da Categoria Chamada**** */
				String chamaCodCatChamada = data.substring(195, 198);

				/** Sigla da Categoria Chamada */
				String chamaSigCatChamada = data.substring(198, 201);

				/** DescriÃ§Ã£o da Categoria Chamada */
				String chamaDesCatChamada = data.substring(201, 226);

				/** HorÃ¡rio da ligaÃ§Ã£o */
				String chamaHorLig = data.substring(226, 232);

				/** AlÃ­quota ICMS */
				String chamaAliIcms = data.substring(232, 237);

				/** Valor da ligaÃ§Ã£o com imposto */
				String chamaValLigImp = data.substring(237, 250);

				/** Valor da LigaÃ§Ã£o sem Imposto */
				String chamaValLigSemImp = data.substring(250, 265);

				/** Tipo NF */
				String chamaTipoNf = data.substring(265, 266);

				/** Numero da Nota Fiscal */
				String chamaNumNf = data.substring(266, 278);

				/** Tipo de Chamada (TC) */
				String chamaTipoChamada = data.substring(278, 279);

				/** Grupo HÃ³rario TarifÃ¡rio */
				String chamaGrupoHoraTarif = data.substring(279, 280);

				/** DescriÃ§Ã£o do HorÃ¡rio TarifÃ¡rio */
				String chamaDesHoraTarif = data.substring(280, 295);

				/** Degrau da LigaÃ§Ã£o */
				String chamaDegrauLig = data.substring(295, 297);

				/** Filler */
				String chamaFiller = data.substring(297, 324);

				/** Campo livre para Operadora */
				String chamaCampoLivreOp = data.substring(324, 349);

				/** MarcaÃ§Ã£o de Fim */
				String chamaMarcaFim = data.substring(349, 350);

				System.out.println(TipoReg);
				System.out.println(chamaControlSeqGrav);
				System.out.println(chamaIndConta);
				System.out.println(chamaDataEmiFatura);
				System.out.println(chamaMesRef);
				System.out.println(chamaIdUnicoRecurso);
				System.out.println(chamaClnAreaLocalUsochamada);
				System.out.println(chamaNumRecurso);
				System.out.println(chamaDataLigacao);
				System.out.println(chamaCnlLocalDestinoChamada);
				System.out.println(chamaNomeLocalDestinoChamada);
				System.out.println(chamaUfTelDestinoChamada);
				System.out.println(chamaCodNacInt);
				System.out.println(chamaCsp);
				System.out.println(chamaNomeOpCsp);
				System.out.println(chamaNumTelefoneChamada);
				System.out.println(chamaCodOpRoaming);
				System.out.println(chamaOpTerminalDestVincPortabilidade);
				System.out.println(chamaDuracaoLigacao);
				System.out.println(chamaCodCatChamada);
				System.out.println(chamaSigCatChamada);
				System.out.println(chamaDesCatChamada);
				System.out.println(chamaHorLig);
				System.out.println(chamaAliIcms);
				System.out.println(chamaValLigImp);
				System.out.println(chamaValLigSemImp);
				System.out.println(chamaTipoNf);
				System.out.println(chamaNumNf);
				System.out.println(chamaTipoChamada);
				System.out.println(chamaGrupoHoraTarif);
				System.out.println(chamaDesHoraTarif);
				System.out.println(chamaDegrauLig);
				System.out.println(chamaFiller);
				System.out.println(chamaCampoLivreOp);
				System.out.println(chamaMarcaFim);

				break;

			case "40":

			/*	*//**
				 * 40_SERVIÃ‡OS do guia Telecom padrÃ£o FEBRABAN-V3R0 Detalhamento
				 * dos serviÃ§os faturados
				 *//*

				*//** Controle de sequencia de gravaÃ§Ã£o *//*
				String servControlSeqGrav = data.substring(2, 14);

				*//** Identificador de Conta Unica ou Numero da conta *//*
				String servIndConta = data.substring(14, 39);

				*//** Data da emissÃ£o da Fatura/conta *//*
				String servDataEmiFatura = data.substring(39, 47);

				*//** MÃªs de ReferÃªncia da fatura(cobranÃ§a) *//*
				String servMesRef = data.substring(47, 53);

				*//** Identificador Ãšnico do Recurso *//*
				String servIdUnicoRecurso = data.substring(53, 78);

				*//**
				 * CNL da Ã�rea local onde o terminal estava em uso durante a
				 * chamada **** CÃ³digo Nacional de localidade: Fixo - definido
				 * pela ANATEL; MÃ³vel definido pela ABR Telecom
				 *//*
				String servClnAreaLocalUsochamada = data.substring(78, 83);

				*//** Numero do recurso *//*
				String servNumRecurso = data.substring(83, 99);

				*//** Data do ServiÃ§o *//*
				String servDataServico = data.substring(99, 107);

				*//**
				 * Codigo Nacional / Internacional
				 * 
				 *//*
				String servCodNacInt = data.substring(107, 109);

				*//**
				 * NÃºmero Telefone Destino
				 * 
				 *//*
				String servNumTelefoneDestino = data.substring(109, 126);

				*//**
				 * Codigo operadora de Roaming
				 * 
				 *//*
				String servCodOpRoaming = data.substring(126, 131);

				*//**
				 * Operadora a qual o terminal de destino esta
				 * vinculado(portabilidade)
				 * 
				 *//*
				String servOpTerminalDestVincPortabilidade = data.substring(131, 134);

				*//**
				 * Quantidade Utilizada
				 * 
				 *//*
				String servQuantUtil = data.substring(134, 140);

				*//**
				 * Unidade do ServiÃ§o
				 * 
				 *//*
				String servUniServico = data.substring(140, 142);

				*//**
				 * Horario do ServiÃ§o
				 * 
				 *//*
				String servHorServico = data.substring(142, 148);

				*//**
				 * Codigo da Categoria do ServiÃ§o
				 * 
				 *//*
				String servCodCatServico = data.substring(148, 151);

				*//**
				 * Sigla da Categoria do ServiÃ§o
				 * 
				 *//*
				String servSigCatServico = data.substring(151, 154);

				*//**
				 * DecriÃ§Ã£o da Categoria ServiÃ§o
				 * 
				 *//*
				String servDesCatServico = data.substring(154, 179);

				*//**
				 * Valor do ServiÃ§o com Impostos
				 * 
				 *//*
				String servValServImp = data.substring(179, 192);

				*//**
				 * Valor do ServiÃ§o Sem Impostos
				 * 
				 *//*
				String servValServSemImp = data.substring(192, 207);

				*//**
				 * Tipo Nota Fiscal NF
				 * 
				 *//*
				String servTipoNf = data.substring(207, 208);

				*//**
				 * Numero da nota Fiscal
				 * 
				 *//*
				String servNumNf = data.substring(208, 220);

				*//** Filler *//*
				String servFiller = data.substring(220, 324);

				*//** Campo livre para Operadora *//*
				String servCampoLivreOp = data.substring(324, 349);

				*//** MarcaÃ§Ã£o de Fim *//*
				String servMarcaFim = data.substring(349, 350);

				System.out.println(TipoReg);
				System.out.println(servControlSeqGrav);
				System.out.println(servIndConta);
				System.out.println(servDataEmiFatura);
				System.out.println(servMesRef);
				System.out.println(servIdUnicoRecurso);
				System.out.println(servClnAreaLocalUsochamada);
				System.out.println(servNumRecurso);
				System.out.println(servDataServico);
				System.out.println(servCodNacInt);
				System.out.println(servNumTelefoneDestino);
				System.out.println(servCodOpRoaming);
				System.out.println(servOpTerminalDestVincPortabilidade);
				System.out.println(servQuantUtil);
				System.out.println(servUniServico);
				System.out.println(servHorServico);
				System.out.println(servCodCatServico);
				System.out.println(servSigCatServico);
				System.out.println(servDesCatServico);
				System.out.println(servValServImp);
				System.out.println(servValServSemImp);
				System.out.println(servTipoNf);
				System.out.println(servNumNf);
				System.out.println(servFiller);
				System.out.println(servCampoLivreOp);
				System.out.println(servMarcaFim);

				break;

			case "50":
				*//**
				 * 50_DESCONTOS do guia Telecom padrÃ£o FEBRABAN-V3R0
				 * Detalhamento dos descontos concedidos
				 *//*

				*//** Controle de sequencia de gravaÃ§Ã£o *//*
				String descControlSeqGrav = data.substring(2, 14);

				*//** Identificador de Conta Unica ou Numero da conta *//*
				String descIndConta = data.substring(14, 39);

				*//** Data da emissÃ£o da Fatura/conta *//*
				String descDataEmiFatura = data.substring(39, 47);

				*//** MÃªs de ReferÃªncia da fatura(cobranÃ§a) *//*
				String descMesRef = data.substring(47, 53);

				*//** Identificador Ãšnico do Recurso *//*
				String descIdUnicoRecurso = data.substring(53, 78);

				*//** Numero do Telefone *//*
				String descNumTelefone = data.substring(78, 94);

				*//**
				 * Tipo do Desconto
				 * 
				 *//*
				String descTipoDesconto = data.substring(94, 95);

				*//**
				 * Codigo da Categoria Descontos
				 * 
				 *//*
				String descCodCatDescontos = data.substring(95, 98);

				*//**
				 * Sigla da Categoria Descontos
				 * 
				 *//*
				String descSigCatDescontos = data.substring(98, 101);

				*//**
				 * DescriÃ§Ã£o da Categhoria Desconto
				 * 
				 *//*
				String descDescricaoCatDesconto = data.substring(101, 126);

				*//**
				 * Base de Calculo Desconto
				 * 
				 *//*
				String descBaseCalDesconto = data.substring(126, 139);

				*//**
				 * Tipo de Nota Fiscal NF
				 * 
				 *//*
				String descTipoNf = data.substring(139, 140);

				*//**
				 * Numero da Nota Fiscal
				 * 
				 *//*
				String descNumNf = data.substring(140, 152);

				*//**
				 * Percentual de Desconto
				 * 
				 *//*
				String descPercDesconto = data.substring(152, 157);

				*//**
				 * Sinal do Desconto
				 * 
				 *//*
				String descSinalDesconto = data.substring(157, 158);

				*//**
				 * Valor do Desconto
				 * 
				 *//*
				String descValorDesconto = data.substring(158, 171);

				*//**
				 * Data Inicio do Desconto
				 * 
				 *//*
				String descDataInicDesconto = data.substring(171, 179);

				*//**
				 * Hora inicio do Desconto
				 * 
				 *//*
				String descHoraInicDesconto = data.substring(179, 185);

				*//**
				 * Data Fim do Desconto
				 * 
				 *//*
				String descDataFimDesconto = data.substring(185, 193);

				*//**
				 * Hora Fim do Desconto
				 * 
				 *//*
				String descHoraFimDesconto = data.substring(193, 199);

				*//** Filler *//*
				String descFiller = data.substring(199, 324);

				*//** Campo livre para Operadora *//*
				String descCampoLivreOp = data.substring(324, 349);

				*//** MarcaÃ§Ã£o de Fim *//*
				String descMarcaFim = data.substring(349, 350);

				System.out.println(TipoReg);
				System.out.println(descControlSeqGrav);
				System.out.println(descIndConta);
				System.out.println(descDataEmiFatura);
				System.out.println(descMesRef);
				System.out.println(descIdUnicoRecurso);
				System.out.println(descNumTelefone);
				System.out.println(descTipoDesconto);
				System.out.println(descCodCatDescontos);
				System.out.println(descSigCatDescontos);
				System.out.println(descDescricaoCatDesconto);
				System.out.println(descBaseCalDesconto);
				System.out.println(descTipoNf);
				System.out.println(descNumNf);
				System.out.println(descPercDesconto);
				System.out.println(descSinalDesconto);
				System.out.println(descValorDesconto);
				System.out.println(descDataInicDesconto);
				System.out.println(descHoraInicDesconto);
				System.out.println(descDataFimDesconto);
				System.out.println(descHoraFimDesconto);
				System.out.println(descFiller);
				System.out.println(descCampoLivreOp);
				System.out.println(descMarcaFim);

				break;

			case "60":
				*//**
				 * 60_PLANOS do guia Telecom padrÃ£o FEBRABAN-V3R0 Detalhamento
				 * dos planos faturados
				 *//*

				*//** Controle de sequencia de gravaÃ§Ã£o *//*
				String planosControlSeqGrav = data.substring(2, 14);

				*//** Identificador de Conta Unica ou Numero da conta *//*
				String planosIndConta = data.substring(14, 39);

				*//** Data da emissÃ£o da Fatura/conta *//*
				String planosDataEmiFatura = data.substring(39, 47);

				*//** MÃªs de ReferÃªncia da fatura(cobranÃ§a) *//*
				String planosMesRef = data.substring(47, 53);

				*//** Identificador Ãšnico do Recurso *//*
				String planoIdUnicoRecurso = data.substring(53, 78);

				*//** Numero do Telefone *//*
				String planoNumTelefone = data.substring(78, 94);

				*//**
				 * Tipo do Plano
				 * 
				 *//*
				String planoTipo = data.substring(94, 95);

				*//**
				 * Data inicio do Ciclo do Plano
				 * 
				 *//*
				String planoDataIniCiclo = data.substring(95, 103);

				*//**
				 * Data Fim do Ciclo do Plano
				 * 
				 *//*
				String planoDataFimCiclo = data.substring(103, 111);

				*//**
				 * Codigo da Operadora
				 * 
				 *//*
				String planoCodOp = data.substring(111, 114);

				*//**
				 * Nome da Operadora
				 * 
				 *//*
				String planoNomeOp = data.substring(114, 129);

				*//**
				 * Consumo Medido
				 * 
				 *//*
				String planoConsumoMedido = data.substring(129, 141);

				*//**
				 * Consumo Franqueado
				 * 
				 *//*
				String planoConsumoFranqueado = data.substring(141, 153);

				*//**
				 * Unidade de Medida
				 * 
				 *//*
				String planoUnidadeMedida = data.substring(153, 155);

				*//**
				 * Codigo da Categoria do Plano
				 * 
				 *//*
				String planoCodCat = data.substring(155, 158);

				*//**
				 * Sigla da Categoria do Plano
				 * 
				 *//*
				String planoSigCat = data.substring(158, 161);

				*//**
				 * DescriÃ§Ã£o da Categoria do Plano
				 * 
				 *//*
				String planoDescCat = data.substring(161, 186);

				*//**
				 * Codigo do Plano
				 * 
				 *//*
				String planoCod = data.substring(186, 191);

				*//**
				 * DescriÃ§Ã£o do plano
				 * 
				 *//*
				String planoDescricao = data.substring(191, 216);

				*//**
				 * Valor do Plano com Imposto
				 * 
				 *//*
				String planoValComImp = data.substring(216, 229);

				*//**
				 * Valor do Plano sem Imposto
				 * 
				 *//*
				String planoValSemImp = data.substring(229, 244);

				*//**
				 * Tipo Nota Fiscal NF
				 * 
				 *//*
				String planoTipoNf = data.substring(244, 245);

				*//**
				 * Numero da Nota Fiscal NF
				 * 
				 *//*
				String planoNumNf = data.substring(245, 257);

				*//** Filler *//*
				String planoFiller = data.substring(257, 324);

				*//** Campo livre para Operadora *//*
				String planoCampoLivreOp = data.substring(324, 349);

				*//** MarcaÃ§Ã£o de Fim *//*
				String planoMarcaFim = data.substring(349, 350);

				System.out.println(TipoReg);
				System.out.println(planosControlSeqGrav);
				System.out.println(planosIndConta);
				System.out.println(planosDataEmiFatura);
				System.out.println(planosMesRef);
				System.out.println(planoIdUnicoRecurso);
				System.out.println(planoNumTelefone);
				System.out.println(planoTipo);
				System.out.println(planoDataIniCiclo);
				System.out.println(planoDataFimCiclo);
				System.out.println(planoCodOp);
				System.out.println(planoNomeOp);
				System.out.println(planoConsumoMedido);
				System.out.println(planoConsumoFranqueado);
				System.out.println(planoUnidadeMedida);
				System.out.println(planoCodCat);
				System.out.println(planoSigCat);
				System.out.println(planoDescCat);
				System.out.println(planoCod);
				System.out.println(planoDescricao);
				System.out.println(planoValComImp);
				System.out.println(planoValSemImp);
				System.out.println(planoTipoNf);
				System.out.println(planoNumNf);
				System.out.println(planoFiller);
				System.out.println(planoCampoLivreOp);
				System.out.println(planoMarcaFim);

				break;

			case "70":
				*//**
				 * 70_AJUSTES do guia Telecom padrÃ£o FEBRABAN-V3R0 Detalhamento
				 * dos ajustes financeiros de movimentos anteriores
				 *//*

				/** Controle de sequencia de gravaÃ§Ã£o 
				String ajustesControlSeqGrav = data.substring(2, 14);

				*//** Identificador de Conta Unica ou Numero da conta *//*
				String ajustesIndConta = data.substring(14, 39);

				*//** Data da emissÃ£o da Fatura/conta *//*
				String ajustesDataEmiFatura = data.substring(39, 47);

				*//** MÃªs de ReferÃªncia da fatura(cobranÃ§a) *//*
				String ajustesMesRef = data.substring(47, 53);

				*//** Identificador Ãšnico do Recurso *//*
				String ajustesIdUnicoRecurso = data.substring(53, 78);

				*//**
				 * Numero do Telefone
				 * 
				 *//*
				String ajustesNumTelefone = data.substring(78, 94);

				*//**
				 * Tipo do Plano
				 * 
				 *//*
				String ajustesTipo = data.substring(94, 95);

				*//**
				 * Codigo da Categoria dos Ajustes
				 *
				 *//*
				String ajustesCodCat = data.substring(95, 98);

				*//**
				 * Sigla da Categoria dos Ajustes
				 * 
				 *//*
				String ajustesSigCat = data.substring(98, 101);

				*//**
				 * DescriÃ§Ã£o da Categoria dos Ajustes
				 * 
				 *//*
				String ajustesDescCat = data.substring(101, 141);

				*//**
				 * Base de CÃ¡lculo dos Ajustes
				 * 
				 *//*
				String ajustesBaseCalc = data.substring(141, 154);

				*//**
				 * Percentual dos Ajustes
				 * 
				 *//*
				String ajustesPercentual = data.substring(154, 159);

				*//**
				 * Sinal de Ajuste
				 * 
				 *//*
				String ajustesSinal = data.substring(159, 160);

				*//**
				 * Valor do Ajuste
				 * 
				 *//*
				String ajustesValor = data.substring(160, 173);

				*//**
				 * Data Inicio do Acerto
				 * 
				 *//*
				String ajustesDataInicioAcerto = data.substring(173, 181);

				*//**
				 * Hora Inicio do Acerto
				 * 
				 *//*
				String ajustesHoraInicioAcerto = data.substring(181, 187);

				*//**
				 * Data Fim do Acerto
				 * 
				 *//*
				String ajustesDataFimAcerto = data.substring(187, 195);

				*//**
				 * Hora Fim do Acerto
				 * 
				 *//*
				String ajustesHoraFimAcerto = data.substring(195, 201);

				*//** Filler *//*
				String ajustesFiller = data.substring(201, 324);

				*//** Campo livre para Operadora *//*
				String ajustesCampoLivreOp = data.substring(324, 349);

				*//** MarcaÃ§Ã£o de Fim *//*
				String ajustesMarcaFim = data.substring(349, 350);

				System.out.println(TipoReg);
				System.out.println(ajustesControlSeqGrav);
				System.out.println(ajustesIndConta);
				System.out.println(ajustesDataEmiFatura);
				System.out.println(ajustesMesRef);
				System.out.println(ajustesIdUnicoRecurso);
				System.out.println(ajustesNumTelefone);
				System.out.println(ajustesTipo);
				System.out.println(ajustesCodCat);
				System.out.println(ajustesSigCat);
				System.out.println(ajustesDescCat);
				System.out.println(ajustesBaseCalc);
				System.out.println(ajustesPercentual);
				System.out.println(ajustesSinal);
				System.out.println(ajustesValor);
				System.out.println(ajustesDataInicioAcerto);
				System.out.println(ajustesHoraInicioAcerto);
				System.out.println(ajustesDataFimAcerto);
				System.out.println(ajustesHoraFimAcerto);
				System.out.println(ajustesFiller);
				System.out.println(ajustesCampoLivreOp);
				System.out.println(ajustesMarcaFim);

				break;

			case "80":
				*//**
				 * 80_NF do guia Telecom padrÃ£o FEBRABAN-V3R0 Totalizador por
				 * nota fiscal apresentada
				 *//*

				/** Controle de sequencia de gravaÃ§Ã£o 
				String nfControlSeqGrav = data.substring(2, 14);

				*//** Identificador de Conta Unica ou Numero da conta *//*
				String nfIndConta = data.substring(14, 39);

				*//** Data da emissÃ£o da Fatura/conta *//*
				String nfDataEmiFatura = data.substring(39, 47);

				*//** MÃªs de ReferÃªncia da fatura(cobranÃ§a) *//*
				String nfMesRef = data.substring(47, 53);

				*//**
				 * Data de Vencimento da Nota Fiscal NF
				 * 
				 *//*
				String nfDataVenc = data.substring(53, 61);

				*//**
				 * Codigo da Operadora
				 *
				 *//*
				String nfCodOp = data.substring(61, 64);

				*//**
				 * Nome da Operadora
				 * 
				 *//*
				String nfNomeOp = data.substring(64, 79);

				*//**
				 * CNPJ Operadora
				 * 
				 *//*
				String nfCnpjOp = data.substring(79, 94);

				*//**
				 * Valor da Nota Fiscal NF com Impostos
				 * 
				 *//*
				String nfValComImp = data.substring(94, 107);

				*//**
				 * Tipo de Nota Fiscal NF
				 * 
				 *//*
				String nfTipo = data.substring(107, 108);

				*//**
				 * Numero da Nota Fiscal NF
				 * 
				 *//*
				String nfNum = data.substring(108, 120);

				*//** Filler *//*
				String nfFiller = data.substring(201, 324);

				*//** Campo livre para Operadora *//*
				String nfCampoLivreOp = data.substring(324, 349);

				*//** MarcaÃ§Ã£o de Fim *//*
				String nfMarcaFim = data.substring(349, 350);

				System.out.println(TipoReg);
				System.out.println(nfControlSeqGrav);
				System.out.println(nfIndConta);
				System.out.println(nfDataEmiFatura);
				System.out.println(nfMesRef);
				System.out.println(nfDataVenc);
				System.out.println(nfCodOp);
				System.out.println(nfNomeOp);
				System.out.println(nfCnpjOp);
				System.out.println(nfValComImp);
				System.out.println(nfTipo);
				System.out.println(nfNum);
				System.out.println(nfFiller);
				System.out.println(nfCampoLivreOp);
				System.out.println(nfMarcaFim);

				break;

			case "90":
				*//**
				 * 90_INFORMATIVOS do guia Telecom padrÃ£o FEBRABAN-V3R0
				 * Informativo gerencial, valores nÃ£o contemplados na fatura
				 *//*

				*//** Controle de sequencia de gravaÃ§Ã£o *//*
				String infControlSeqGrav = data.substring(2, 14);

				*//** Identificador de Conta Unica ou Numero da conta *//*
				String infIndConta = data.substring(14, 39);

				*//** Data da emissÃ£o da Fatura/conta *//*
				String infDataEmiFatura = data.substring(39, 47);

				*//** MÃªs de ReferÃªncia da fatura(cobranÃ§a) *//*
				String infMesRef = data.substring(47, 53);

				*//** Identificador Ãšnico do Recurso *//*
				String infIdUnicoRecurso = data.substring(53, 78);

				*//**
				 * CNL do Recurso
				 * 
				 *//*
				String infCnlRecurso = data.substring(78, 83);

				*//**
				 * Numero do Recurso
				 * 
				 *//*
				String infNumRecurso = data.substring(83, 99);

				*//**
				 * Codigo da Categoria do Informativo
				 * 
				 *//*
				String infCodCatInformativo = data.substring(99, 102);

				*//**
				 * Texto Informativo
				 * 
				 *//*
				String infTexto = data.substring(102, 302);

				*//**
				 * Sinal do Valor
				 * 
				 *//*
				String infSinVal = data.substring(302, 303);

				*//**
				 * Valor
				 * 
				 *//*
				String infValor = data.substring(303, 316);

				*//** Filler *//*
				String infFiller = data.substring(201, 324);

				*//** Campo livre para Operadora *//*
				String infCampoLivreOp = data.substring(324, 349);

				*//** MarcaÃ§Ã£o de Fim *//*
				String infMarcaFim = data.substring(349, 350);

				System.out.println(TipoReg);
				System.out.println(infControlSeqGrav);
				System.out.println(infIndConta);
				System.out.println(infDataEmiFatura);
				System.out.println(infMesRef);
				System.out.println(infIdUnicoRecurso);
				System.out.println(infCnlRecurso);
				System.out.println(infNumRecurso);
				System.out.println(infCodCatInformativo);
				System.out.println(infTexto);
				System.out.println(infSinVal);
				System.out.println(infValor);
				System.out.println(infFiller);
				System.out.println(infCampoLivreOp);
				System.out.println(infMarcaFim);

				break;

			case "99":
				*//**
				 * 99_TRAILLER do guia Telecom padrÃ£o FEBRABAN-V3R0 ConsolidaÃ§Ã£o
				 * de valores da conta faturada
				 *//*

				*//** Controle de sequencia de gravaÃ§Ã£o *//*
				String traillerControlSeqGrav = data.substring(2, 14);

				*//** Identificador de Conta Unica ou Numero da conta *//*
				String traillerIndConta = data.substring(14, 39);

				*//** Data da emissÃ£o da Fatura/conta *//*
				String traillerDataEmiFatura = data.substring(39, 47);

				*//** MÃªs de ReferÃªncia da fatura(cobranÃ§a) *//*
				String traillerMesRef = data.substring(47, 53);

				*//**
				 * Data de Vencimento
				 * 
				 *//*
				String traillerDataVenc = data.substring(53, 61);

				*//**
				 * Codigo do Cliente
				 * 
				 *//*
				String traillerCodCliente = data.substring(61, 76);

				*//**
				 * Valor Total
				 * 
				 *//*
				String traillerValTotal = data.substring(76, 89);

				*//**
				 * Quantidade de Total de Registros
				 * 
				 *//*
				String traillerQuanTotalReg = data.substring(89, 101);

				*//**
				 * Valor Total Registro 10
				 * 
				 *//*
				String traillerValTotal10 = data.substring(101, 114);

				*//**
				 * Quantidade de Registros 10
				 * 
				 *//*
				String traillerQuanReg10 = data.substring(114, 123);

				*//**
				 * Quantidade de Registros 20
				 * 
				 *//*
				String traillerQuanReg20 = data.substring(123, 132);

				*//**
				 * Valor Total Registro 30
				 * 
				 *//*
				String traillerValTotal30 = data.substring(132, 145);

				*//**
				 * Quantidade de Registros 30
				 * 
				 *//*
				String traillerQuanReg30 = data.substring(145, 154);

				*//**
				 * Valor Total Registro 40
				 * 
				 *//*
				String traillerValTotal40 = data.substring(154, 167);

				*//**
				 * Quantidade de Registros 40
				 * 
				 *//*
				String traillerQuanReg40 = data.substring(167, 176);

				*//**
				 * Sinal Total Registro 50
				 * 
				 *//*
				String traillerSinTotalReg50 = data.substring(176, 177);

				*//**
				 * Valor Total Registro 50
				 * 
				 *//*
				String traillerValTotal50 = data.substring(177, 190);

				*//**
				 * Quantidade de Registros 50
				 * 
				 *//*
				String traillerQuanReg50 = data.substring(191, 199);

				*//**
				 * Valor Total Registro 60
				 * 
				 *//*
				String traillerValTotal60 = data.substring(199, 212);
				*//**
				 * Quantidade de Registros 60
				 * 
				 *//*
				String traillerQuanReg60 = data.substring(212, 221);

				*//**
				 * Sinal Total Registro 70
				 * 
				 *//*
				String traillerSinTotalReg70 = data.substring(221, 222);

				*//**
				 * Valor Total Registro 70
				 * 
				 *//*
				String traillerValTotal70 = data.substring(222, 235);

				*//**
				 * Quantidade de Registros 70
				 * 
				 *//*
				String traillerQuanReg70 = data.substring(235, 244);

				*//**
				 * Valor Total Registro 80
				 * 
				 *//*
				String traillerValTotal80 = data.substring(244, 257);

				*//**
				 * Quantidade de Registros 80
				 * 
				 *//*
				String traillerQuanReg80 = data.substring(257, 266);

				*//** Filler *//*
				String traillerFiller = data.substring(201, 324);

				*//** Campo livre para Operadora *//*
				String traillerCampoLivreOp = data.substring(324, 349);

				*//** MarcaÃ§Ã£o de Fim *//*
				String traillerMarcaFim = data.substring(349, 350);

				System.out.println(TipoReg);
				System.out.println(traillerControlSeqGrav);
				System.out.println(traillerIndConta);
				System.out.println(traillerDataEmiFatura);
				System.out.println(traillerMesRef);
				System.out.println(traillerDataVenc);
				System.out.println(traillerCodCliente);
				System.out.println(traillerValTotal);
				System.out.println(traillerQuanTotalReg);
				System.out.println(traillerValTotal10);
				System.out.println(traillerQuanReg10);
				System.out.println(traillerQuanReg20);
				System.out.println(traillerValTotal30);
				System.out.println(traillerQuanReg30);
				System.out.println(traillerValTotal40);
				System.out.println(traillerQuanReg40);
				System.out.println(traillerSinTotalReg50);
				System.out.println(traillerValTotal50);
				System.out.println(traillerQuanReg50);
				System.out.println(traillerValTotal60);
				System.out.println(traillerQuanReg60);
				System.out.println(traillerSinTotalReg70);
				System.out.println(traillerValTotal70);
				System.out.println(traillerQuanReg70);
				System.out.println(traillerValTotal80);
				System.out.println(traillerQuanReg80);
				System.out.println(traillerFiller);
				System.out.println(traillerCampoLivreOp);
				System.out.println(traillerMarcaFim);*/

				break;
			

			}
		}
		fileReader.close();
		reader.close();
	}

	public static void main(String[] args) {
		try {
			VerificaCamposFebrabanV3Console.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
