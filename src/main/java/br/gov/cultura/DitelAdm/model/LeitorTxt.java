package br.gov.cultura.DitelAdm.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LeitorTxt {

	private static File file = new File(
		//	"C:\\Users\\Administrador\\Desktop\\Projetos de Programação\\Arquivos DITEL\\Faturas para o Projeto ditel\\711725423_919441395_14_02_2016_FebrabanV3.txt");
          "C:\\Users\\72381817115\\Desktop\\Projetos de Programação\\Arquivos DITEL\\Faturas para o Projeto ditel\\711725423_919441395_14_02_2016_FebrabanV3.txt");
	
	private static final void read(File file) throws IOException{
		FileReader fileReader = new FileReader(file);
		BufferedReader reader = new BufferedReader(fileReader);
		String data = null;
		int i=0;
		while((data = reader.readLine()) != null){
			 ++i;
			 System.out.println(i);
			String TipoReg = data.substring(0, 2);
			switch (TipoReg)
			{
				case "00":
				//if (i == 1){
					/** 00_HEADER do guide Telecom padrão FEBRABAN-V3R0
					 *Identifica Cabeçalho da conta */
					
					/** Controle de sequencia de gravação */
					String headerControlSeqGrav = data.substring(2, 14);
					
					/**Identificador de Conta Unica ou Numero da conta */
					String headerIndConta = data.substring(14, 39);
					
					/**Data da emissão da Fatura/conta */
					String headerDataEmiFatura = data.substring(39, 47);
					
					/** Mês de Referência da fatura(cobrança)*/
					String headerMesRef = data.substring(47, 53);
					
					/** Data de Geração do Arquivo*/
					String headerDataGeraArquivo = data.substring(53, 61);
					
					/** Data de Vencimento da Fatura*/
					String headerDataVencFatura = data.substring(61, 69);
					
					/** Codigo da Operadora*/
					String headerCodOpe = data.substring(69, 72);
					
					/** Nome da Operadora*/
					String headerNomeOpe = data.substring(72, 87);
					
					/** CNPJ da Operadora*/
					String headerCnpjOpe = data.substring(87, 102);
					
					/** UF da Operadora*/
					String headerUfOpe = data.substring(102, 104);
					
					/** Codigo do Cliente*/
					String headerCodCliente = data.substring(104, 119);
					
					/** Nome do Cliente*/
					String headerNomeCliente = data.substring(119, 149);
					
					/** CNPJ do Cliente*/
					String headerCnpjCliente = data.substring(149, 164);
					
					/** Versão do Formato do Documento >>> (Tem que aparecer V3R0) <<<*/
					String headerVersaoFormato = data.substring(164, 168);
					
					/** Numero da Fatura*/
					String headerNumFatura = data.substring(168, 184);
					
					/** Codigo de Barra*/
					String headerCodBarra = data.substring(184, 234);
					
					/** Codigo de Cobrança ( Legenda do Valor)
					 *  01 - Debito automático;
					 *  02 - Crédito em conta; 
					 *  03 - Cobrança simples; 
					 *  04 - Cobrança registrada; 
					 *  05 - Cartão Crédito; 
					 *  06 - Outros  */
					
					String headerCodCobranca = data.substring(234, 236);
					
					/** Descrição(Ou Tipo) da Cobrança (Legenda)
					 *  01 - Debito automático;
					 *  02 - Crédito em conta; 
					 *  03 - Cobrança simples; 
					 *  04 - Cobrança registrada; 
					 *  05 - Cartão Crédito; 
					 *  06 - Outros  */
					
					String headerDescriCobranca = data.substring(236, 256);
					
					/** Banco da Cobrança*/
					String headerBancoCobranca = data.substring(256, 260);
					
					/** Agencia da Cobrança*/
					String headerAgenciaCobranca = data.substring(260, 264);
					
					/** Conta Corrente da Cobrança*/
					String headerCcCobranca = data.substring(264, 274);
					
					/** FISCO */
					String headerFisco = data.substring(274, 309);
					
					/** Filler */
					String headerFiller = data.substring(309, 324);
					
					/** Campo Livre Para Operadora*/
					String headerCampoLivreOp = data.substring(324, 349);
					
					/** Marcação de FIM*/
					String headerMarcaFim = data.substring(349, 350);
					
					
					System.out.println(TipoReg);
					System.out.println("*" + headerControlSeqGrav + " ");
					System.out.println("*"+ headerIndConta);
					System.out.println("*" + headerDataEmiFatura);
					System.out.println("*" + headerMesRef);
					System.out.println("*" + headerDataGeraArquivo);
					System.out.println("*" + headerDataVencFatura);
					System.out.println("*" + headerCodOpe);
					System.out.println("*" + headerNomeOpe);
					System.out.println("*" + headerCnpjOpe);
					System.out.println("*" + headerUfOpe);
					System.out.println("*" + headerCodCliente);
					System.out.println("*" + headerNomeCliente);
					System.out.println("*" + headerCnpjCliente);
					System.out.println("*" + headerVersaoFormato);
					System.out.println("*" + headerNumFatura);
					System.out.println("*" + headerCodBarra);
					System.out.println("*" + headerCodCobranca);
					System.out.println("*" + headerDescriCobranca);
					System.out.println("*" + headerBancoCobranca);
					System.out.println("*" + headerAgenciaCobranca);
					System.out.println("*" + headerCcCobranca);
					System.out.println("*" + headerFisco);
					System.out.println("*" + headerFiller);
					System.out.println("*" + headerCampoLivreOp);
					System.out.println("*" + headerMarcaFim);
					
					//}else if(i==2){
						
						
					//}
					break;
					
				case "10":
					/** 10_RESUMO do guide Telecom padrão FEBRABAN-V3R0
					 *Somatório dos Valores por Recurso */
					
					/** Controle de sequencia de gravação */
					String resumoControlSeqGrav = data.substring(2, 14);
					
					/**Identificador de Conta Unica ou Numero da conta */
					String resumoIndConta = data.substring(14, 39);
					
					/**Data da emissão da Fatura/conta */
					String resumoDataEmiFatura = data.substring(39, 47);
					
					/** Mês de Referência da fatura(cobrança)*/
					String resumoMesRef = data.substring(47, 53);
					
					/** Identificador Único do Recurso*/
					String resumoIdUnicoRecurso = data.substring(53, 78);
					
					/** CNL do Recurso (Legenda)
					 * (6) Código Nacional de localidade:
					 *  Fixo - definido pela ANATEL; 
					 *  Móvel definido pela ABR Telecom */
					
					String resumoCnlRecurso = data.substring(78, 83);
					
					/** Numero do Recurso*/
					String resumoIdNumRecurso = data.substring(83, 99);
					
					System.out.println(TipoReg);
					System.out.println(">" + resumoControlSeqGrav + " ");
					System.out.println(">"+ resumoIndConta);
					System.out.println("" + resumoDataEmiFatura);
					System.out.println("*" + resumoMesRef);
					System.out.println("*" + resumoIdUnicoRecurso);
					System.out.println("*" + resumoCnlRecurso);
					System.out.println("*" + resumoIdNumRecurso);
					
					//break;
				case "20":
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
			
			break;
			//System.out.println(data);
		}
		fileReader.close();
		reader.close();
	}

	public static void main(String[] args) {
		try {
			LeitorTxt.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
