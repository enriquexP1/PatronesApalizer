/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

/**
 *
 * @author enriq
 */
public class ManagerArchivo {

    public ManagerArchivo() {
    }
    public AbstractFormato tomarFormatoPdf(String archivo)
    {
        return null;
    }
    public AbstractFormato tomarFormatoWord(String archivo) throws FileNotFoundException, IOException {
        AbstractFormato formato = new FormatoWord(archivo, archivo);
        try {
            ArrayList<Seccion> secciones = new ArrayList<Seccion>();
            HashMap<Integer, ArrayList<String>> content = new HashMap<Integer, ArrayList<String>>();
            
            String nombreArchivo = archivo;
            //Le pasamos el archivo que queremos leer
            XWPFDocument documento = new XWPFDocument(new FileInputStream(nombreArchivo));

            //Le pasamos el archivo para que extraega el contenido
            XWPFWordExtractor xwpfWordExtractor = new XWPFWordExtractor(documento);
            //Extraermos el contenido del archivo, solo lee el texto, no las tablas ni imagenes, etc.
            String contenido = xwpfWordExtractor.getText();

            List<XWPFParagraph> paragraphs = documento.getParagraphs();

            int i = 0;

            for (XWPFParagraph para : paragraphs) {
                ArrayList<String> parrafo = new ArrayList<String>();
                Seccion seccion = null;
                

                
                if (para.getStyleID() == null) {
                    System.out.println("Contenido: " + i + "    " + para.getParagraphText());
                    parrafo.add(para.getText());
                    content.get(i).add(para.getText());
                    
                    
                } else if (para.getStyleID().compareTo("Ttulo") == 0
                        || para.getStyleID().compareTo("Ttulo1") == 0
                        || para.getStyleID().compareTo("Ttulo2") == 0
                        || para.getStyleID().compareTo("Ttulo3") == 0
                        || para.getStyleID().compareTo("Subttulo") == 0) {
                    //se recupera la seccion
                     i++;
                    seccion = new Seccion();
                    seccion.setId(i );
                    seccion.setNombre(para.getText());
                    seccion.setNumPalabras(0);
                    
                    System.out.println("Seccion:  " + para.getText());
                    //Añadimos la seccion al formato
                    secciones.add(seccion);
                    
                    parrafo.add(para.getText());
                    content.put(i, parrafo);
                   seccion.setTexto(content);
                    


                }

                
            }

            formato.setSecciones(secciones);
            formato.setContenidoSeccion(content);

        } catch (Exception e) {
        }

        return formato;

    }
}
