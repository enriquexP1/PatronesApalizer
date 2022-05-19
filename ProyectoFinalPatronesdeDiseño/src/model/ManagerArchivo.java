/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDDocumentOutline;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineNode;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

/**
 *
 * @author enriq
 */
public class ManagerArchivo {

    ArrayList<String> seccionesPA = new ArrayList<String>();
    ArrayList<Seccion> secciones = new ArrayList<Seccion>();
    Formato formato = new Formato();
    ProductoAcademico PA = new ProductoAcademico();
    int id = 0;

    public ManagerArchivo() {
    }

    public String corregirRuta(String ruta) {
        String x = ruta.replaceAll("\\\\", "/");
      
        return x;
    }

    public String DiferenciarFormato(String archivo) {
        String extension = FilenameUtils.getExtension(archivo);
        return extension;
    }

    public Formato tomarFormatoPDF(String archivo) {
        try {
            PDDocument documentPDF = PDDocument.load(new File(archivo));
            PDDocumentOutline outline = documentPDF.getDocumentCatalog().
                    getDocumentOutline();
            formatoSecciones(documentPDF, outline);
            documentPDF.close();
            formato.setSecciones(secciones);
            return formato;
        } catch (IOException e) {
            System.err.println("Error al leer o escribir en el archivo con la ruta especificada");
        } catch (NullPointerException e) {
            System.err.println("Error al leer los bookmarks del archivo");
        }
        return null;
    }

    public void formatoSecciones(PDDocument document, PDOutlineNode bookmark) throws IOException {
        PDOutlineItem current = bookmark.getFirstChild();
        while (current != null) {
            ++id;
            Seccion seccion = new Seccion(id, current.getTitle());
            secciones.add(seccion);
            //System.out.println(id + " " + current.getTitle());
            formatoSecciones(document, current);
            current = current.getNextSibling();
        }
    }

    public Formato tomarFormatoWord(String archivo) /*throws FileNotFoundException, IOException */ {

        formato.setNombreArchivo(archivo);

        try {
            XWPFDocument documento = new XWPFDocument(new FileInputStream(archivo));
            XWPFWordExtractor xwpfWordExtractor = new XWPFWordExtractor(documento);
            List<XWPFParagraph> paragraphs = documento.getParagraphs();

            int idSeccion = 1;
            for (XWPFParagraph para : paragraphs) {
                if (para.getStyleID() != null && (para.getStyleID().compareTo("Ttulo") == 0
                        || para.getStyleID().compareTo("Ttulo1") == 0
                        || para.getStyleID().compareTo("Ttulo2") == 0
                        || para.getStyleID().compareTo("Ttulo3") == 0
                        || para.getStyleID().compareTo("Subttulo") == 0)) {
                    Seccion seccion = new Seccion(idSeccion, para.getText());
                    secciones.add(seccion);
                    ++idSeccion;
                }
            }
            formato.setSecciones(secciones);
            return formato;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ManagerArchivo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.err.println("Error al leer o escribir en el archivo con la ruta especificada");
        }
        return formato;

    }

    public ProductoAcademico tomarPAWord(String archivo) {
        PA.setSeccionesFormato(secciones);
        try {
            XWPFDocument documento = new XWPFDocument(new FileInputStream(archivo));
            XWPFWordExtractor xwpfWordExtractor = new XWPFWordExtractor(documento);
            List<XWPFParagraph> paragraphs = documento.getParagraphs();

            for (XWPFParagraph para : paragraphs) {
                if (para.getStyleID() != null && (para.getStyleID().compareTo("Ttulo") == 0
                        || para.getStyleID().compareTo("Ttulo1") == 0
                        || para.getStyleID().compareTo("Ttulo2") == 0
                        || para.getStyleID().compareTo("Ttulo3") == 0
                        || para.getStyleID().compareTo("Subttulo") == 0)) {
                    seccionesPA.add(para.getText());
                }
            }
            construirSeccionesWord(archivo);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ManagerArchivo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ManagerArchivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return PA;
    }

    public void construirSeccionesWord(String archivo) {
        try {
            XWPFDocument documento = new XWPFDocument(new FileInputStream(archivo));
            XWPFWordExtractor xwpfWordExtractor = new XWPFWordExtractor(documento);
            String contenido = xwpfWordExtractor.getText();
            StringTokenizer parrafos = new StringTokenizer(contenido, "\n");
            StringBuilder textoSeccion = new StringBuilder();
            Seccion seccion = null;
            int numPalabras = 0;
            while (parrafos.hasMoreTokens()) {
                String parrafo = parrafos.nextToken().trim();
                StringTokenizer palabras = new StringTokenizer(parrafo, " ");
                if (seccionesPA.contains(parrafo)) {
                    if (seccion != null) {
                        seccion.setNumPalabras(numPalabras);
                        seccion.setTexto(textoSeccion.toString());
                        textoSeccion = new StringBuilder();
                    }
                    seccion = findSeccion(parrafo);
                    numPalabras = 0;
                    seccionesPA.remove(parrafo);
                } else if (seccion != null) {
                    while (palabras.hasMoreTokens()) {
                        String palabra = palabras.nextToken();
                        textoSeccion.append(palabra).append(" ");
                        numPalabras++;
                    }
                    textoSeccion.append("\n");
                }
            }
            if (!textoSeccion.toString().isEmpty() && seccion != null) {
                seccion.setNumPalabras(numPalabras);
                seccion.setTexto(textoSeccion.toString());
            }
        } catch (IOException ex) {
            Logger.getLogger(ManagerArchivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Seccion findSeccion(String name) {
        for (Seccion seccion
                : secciones) {
            if (name.equals(seccion.getNombre())) {
                seccion.setCumplido(true);
                return seccion;
            }
        }
        return null;
    }

    public ProductoAcademico tomarPAPDF(String archivo) {
        try {
            PDDocument documentPDF = PDDocument.load(new File(archivo));
            PDDocumentOutline outline = documentPDF.getDocumentCatalog().
                    getDocumentOutline();
            productoAcSecciones(documentPDF, outline);
            documentPDF.close();
            PA.setSeccionesFormato(secciones);
            construirSeccionesPDF(archivo);
        } catch (IOException e) {
            System.err.println("Error al leer o escribir en el archivo con la ruta especificada");
        } catch (NullPointerException e) {
            System.err.println("Error al leer los bookmarks del archivo");
        }
        return PA;
    }

    private void productoAcSecciones(PDDocument document, PDOutlineNode bookmark) {
        PDOutlineItem current = bookmark.getFirstChild();
        while (current != null) {
            seccionesPA.add(current.getTitle());
            productoAcSecciones(document, current);
            current = current.getNextSibling();
        }
    }
    
     public void construirSeccionesPDF(String archivo) {
       try {
         File file = new File(archivo);
         PDDocument document = PDDocument.load(file);
         
         PDFTextStripper pdfTextStripper = new PDFTextStripper();
         String text = pdfTextStripper.getText(document);
         StringTokenizer parrafos = new StringTokenizer(text, "\n");
         StringBuilder textoSeccion = new StringBuilder();
         Seccion seccion = null;
         int numPalabras = 0;
         while (parrafos.hasMoreTokens()) {
            String parrafo = parrafos.nextToken().trim();
            StringTokenizer palabras = new StringTokenizer(parrafo, " ");
            if (seccionesPA.contains(parrafo)) {
               if (seccion != null) {
                  seccion.setNumPalabras(numPalabras);
                  seccion.setTexto(textoSeccion.toString());
                  textoSeccion = new StringBuilder();
               }
               seccion = findSeccion(parrafo);
               numPalabras = 0;
               seccionesPA.remove(parrafo);
            } else if (seccion != null) {
               while (palabras.hasMoreTokens()) {
                  String palabra = palabras.nextToken();
                  textoSeccion.append(palabra).append(" ");
                  numPalabras++;
               }
               textoSeccion.append("\n");
            }
         }
         if (!textoSeccion.toString().isEmpty() && seccion != null) {
            seccion.setNumPalabras(numPalabras);
            seccion.setTexto(textoSeccion.toString());
         }
      } catch (IOException ex) {
         System.err.println("ProductoPDF : "
                 + "Error al leer o escribir en el archivo con la ruta especificada");
      }
    }
}
