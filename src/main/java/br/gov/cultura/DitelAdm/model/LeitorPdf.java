package br.gov.cultura.DitelAdm.model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;


public class LeitorPdf {
	public static final String RESULT = "C:\\Users\\72381817115\\Desktop\\Este.txt";

	public static void main(String[] args) throws DocumentException, IOException {
		String filename = "C:\\Users\\72381817115\\Downloads\\919441395_02-02-2016-1-2016-14.pdf";
		PrintWriter writer = new PrintWriter(new FileOutputStream(RESULT));
		PdfReader reader = new PdfReader(filename);
		String texto = PdfTextExtractor.getTextFromPage(reader, 1);
		String[] linhas = texto.split("\n");
		System.out.println(linhas);
		writer.println(filename);
		writer.print("Numero de paginas: ");
		writer.println(reader.getNumberOfPages());
		Rectangle mediabox = reader.getPageSize(1);
		writer.print("Tamanho da pagina 1: [");
		writer.print(mediabox.getLeft());
		writer.print(',');
		writer.print(mediabox.getBottom());
		writer.print(',');
		writer.print(mediabox.getRight());
		writer.print(',');
		writer.print(mediabox.getTop());
		writer.println("]");
		writer.print("Rotacao da primeira pagina: ");
		writer.println(reader.getPageRotation(1));
		writer.print("Tamanho da rotacao (pagina 1): ");
		writer.println(reader.getPageSizeWithRotation(1));
		writer.print("Tamanho do arquivo: ");
		writer.println(reader.getFileLength());
		writer.print("Esta reprocessado? ");
		writer.println(reader.isRebuilt());
		writer.print("Esta encriptado? ");
		writer.println(reader.isEncrypted());
		writer.println();
		writer.flush();
		writer.close();
	}
}
