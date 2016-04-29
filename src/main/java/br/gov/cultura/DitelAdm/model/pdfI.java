package br.gov.cultura.DitelAdm.model;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

public class pdfI {

    private static String INPUTFILE = "C:\\Users\\Administrador\\Desktop\\Projetos de Programação\\Arquivos DITEL\\Faturas para o Projeto ditel\\711725423_919441395_14_02_2016_FebrabanV3.txt";
    private static String OUTPUTFILE = "c:\\Users\\Administrador\\Desktop\\new3.pdf";

    public static void main(String[] args) throws DocumentException, IOException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(OUTPUTFILE));
        document.open();

        PdfReader reader = new PdfReader(INPUTFILE);
        int n = reader.getNumberOfPages();

        PdfImportedPage page;

        // Go through all pages
        for (int i = 1; i <= n; i++) {
            page = writer.getImportedPage(reader, i);
            Image instance = Image.getInstance(page);
            document.add(instance);
        }

        document.close();

        PdfReader readerN = new PdfReader(OUTPUTFILE);
        for (int i = 1; i <= n; i++) {
            String myLine = PdfTextExtractor.getTextFromPage(readerN,i);
            System.out.println(myLine);

            try {             
                FileWriter fw = new FileWriter("c:\\Users\\72381817115\\Desktop\\new3.txt",true);
                fw.write(myLine);
                fw.close();
            }catch (IOException ioe) {ioe.printStackTrace(); }
    }
}
}