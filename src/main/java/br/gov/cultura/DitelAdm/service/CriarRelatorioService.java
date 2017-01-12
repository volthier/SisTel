package br.gov.cultura.DitelAdm.service;

import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class CriarRelatorioService {

	Document documentoPDF = new Document();
	
	{

	try{ 
		// cria uma instancia do documento e da o nome do pdf
		PdfWriter.getInstance(documentoPDF, new FileOutputStream("C:\\Users\\72381817115\\Documents\\ArquivoTeste.pdf"));
		
		// abrir o documento
		documentoPDF.open();
		
		// setar o tamanho da pagina
		documentoPDF.setPageSize(PageSize.A4);
		
		// adicionando primeiro paragrafo
		documentoPDF.add(new Paragraph("Teste de gerar PDF"));
		
		// nova pagina
		documentoPDF.newPage();
		
		// paragrafo da segunda pagina
		documentoPDF.add(new Paragraph("Teste segunda pagina"));
		
		// imagem do relatorio
		//Image imagem = Image.getInstance("C:\\Users\\72381817115\\Desktop\\OLHAISSOAQUI.png");
		
		//setando o tamanho da imagem (tamanho abaixo esta em pixel)
		//imagem.scaleToFit(400,200);
		
		//adicionando a imagam ao pdf
		//documentoPDF.add(imagem);
		
	}catch(DocumentException de){
		de.printStackTrace();
	}catch(IOException ioe){
		ioe.printStackTrace();
	}finally{
		documentoPDF.close();
	}
}
};
