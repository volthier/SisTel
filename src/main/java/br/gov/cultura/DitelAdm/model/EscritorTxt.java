package br.gov.cultura.DitelAdm.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EscritorTxt {
	
		private static final String path = "C:\\Users\\72381817115\\Desktop\\ola.pdf"/* "Caminho do arquivo" */;
		public static void main(String[] args) throws IOException {
			File file = new File(path);
			long begin = System.currentTimeMillis();
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write("Arquivo gravado em : " + new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date()));
			writer.newLine();
			writer.write("Caminho da gravação: " + path);
			writer.newLine();
			long end = System.currentTimeMillis();
			writer.write("Tempo de gravação: " + (end - begin) + "ms.");
			//Criando o conteúdo do arquivo
			writer.flush();
			//Fechando conexão e escrita do arquivo.
			writer.close();
			System.out.println("Arquivo gravado em: " + path);
		}
	}
