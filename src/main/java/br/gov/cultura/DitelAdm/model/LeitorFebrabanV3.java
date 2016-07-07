package br.gov.cultura.DitelAdm.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.gov.cultura.DitelAdm.model.dtos.FaturaArquivoDTO;
import br.gov.cultura.DitelAdm.modelo.Cliente;
import br.gov.cultura.DitelAdm.modelo.ClienteId;
import br.gov.cultura.DitelAdm.modelo.Fatura;
import br.gov.cultura.DitelAdm.modelo.FaturaId;
import br.gov.cultura.DitelAdm.modelo.Operadora;

public class LeitorFebrabanV3 {

	private SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
	private SimpleDateFormat sdfh = new SimpleDateFormat("HHmmss");

	private String recuperaTextoCampo(String linha, PosicaoCamposEnum posicao) {
		return linha.substring(posicao.getPosicaoInicial(), posicao.getPosicaoFinal());
	}

	private Date recuperaDataCampo(String linha, PosicaoCamposEnum posicao) throws Exception {
		String textoDataEmissao = recuperaTextoCampo(linha, posicao);
		Date dataCampo = sdf.parse(textoDataEmissao);
		dataCampo = sdfh.parse(textoDataEmissao);

		return dataCampo;
	}

	public FaturaArquivoDTO read(File file) throws IOException {
		FaturaArquivoDTO faturaArquivoDTO = new FaturaArquivoDTO();
		FileReader fileReader = new FileReader(file);
		BufferedReader reader = new BufferedReader(fileReader);
		String data = null;
		while ((data = reader.readLine()) != null) {
			String TipoReg = data.substring(0, 2);
			switch (TipoReg)

			{
			case "00":
				/**
				 * 00_HEADER do guide Telecom padrão FEBRABAN-V3R0 Identifica
				 * Cabeçalho da conta
				 */

				Fatura fatura = new Fatura();
				FaturaId faturaId = new FaturaId();
				Cliente cliente = new Cliente();
				ClienteId clienteId = new ClienteId();
				Operadora operadora = new Operadora();

				/**
				 * Controle de sequencia de gravação String headerControlSeqGrav
				 * = data.substring(2, 14);
				 */

				/** Identificador de Conta Unica ou Numero da conta */
				// fatura.setIndConta(data.substring(14, 39));
				fatura.setIndConta(recuperaTextoCampo(data, PosicaoCamposEnum.CAMPO_HEADER_FATURA_INDCONTA));

				/** Data da emissão da Fatura/conta */
				// faturaId.setDataEmissao(sdf.parse(data.substring(39, 47)));

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
				cliente.setId(clienteId);
				
				/** Nome do Cliente */
				cliente.setNome(data.substring(119, 149));

				/** CNPJ do Cliente */
				cliente.setCnpj(data.substring(149, 164));
				
				
				operadora.configuraCliente(cliente);
				faturaArquivoDTO.setOperadora(operadora);

				return faturaArquivoDTO;
				

			case "10":

				break;
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

		}
		fileReader.close();
		reader.close();

		return faturaArquivoDTO;
	}

}
