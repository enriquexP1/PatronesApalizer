/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FilenameUtils;



/**
 *
 * @author enriq
 */
public class ManagerReporte {

    String nombre;
    Formato formato;
    ProductoAcademico PA;
    ManagerArchivo managerArch;
    ArrayList<Seccion> seccionesFormato = null;
    ArrayList<String> seccionesPA = null;
    String ruta;
    String extension;
    String rutaDestino;
    public ManagerReporte() {
        this.managerArch = new ManagerArchivo();
    }
    public void generarProceso(String Formatin , String ProductA , String rutaDestino)
    {
        leerFormato(Formatin);
        leerPA(ProductA);
        Comparar();
        this.rutaDestino=rutaDestino;
        
        generarReporte(rutaDestino);
    }
    public Formato leerFormato(String archivo) {
        ruta = managerArch.corregirRuta(archivo);
        extension = managerArch.DiferenciarFormato(archivo);

        formato = null;

        if (extension.compareTo("pdf") == 0) {
            //Aqui necesito que mande a llamar a un formato del manager Archivo
            System.out.println("aqui vamos a leer el pdf");
            formato = managerArch.tomarFormatoPDF(archivo);
            System.out.println(formato.toString());
            seccionesFormato = formato.getSecciones();
            for (int i = 0; i < seccionesFormato.size(); i++) {
                System.out.println(seccionesFormato.get(i));
            }

        } else if (extension.compareTo("docx") == 0) {
            formato = managerArch.tomarFormatoWord(archivo);
            System.out.println(formato.toString());
            seccionesFormato = formato.getSecciones();
            for (int i = 0; i < seccionesFormato.size(); i++) {
                System.out.println(seccionesFormato.get(i));
            }
        } else {
            System.out.println("Aún no soportamos este tipo de archivo");
        }
        return formato;
    }

    public ProductoAcademico leerPA(String archivo) {

        ruta = managerArch.corregirRuta(archivo);
        extension = managerArch.DiferenciarFormato(archivo);

        PA = null;

        if (extension.compareTo("pdf") == 0) {
            //Aqui necesito que mande a llamar a un formato del manager Archivo
            System.out.println("aqui vamos a leer el pdf");

            PA = managerArch.tomarPAPDF(archivo);
            System.out.println(PA.toString());
            seccionesFormato = PA.getSeccionesFormato();
            seccionesPA = PA.getSeccionesPA();
            for (int i = 0; i < seccionesFormato.size(); i++) {
                System.out.println(seccionesFormato.get(i));
            }

        } else if (extension.compareTo("docx") == 0) {

            PA = managerArch.tomarPAWord(archivo);
            System.out.println(PA.toString());
            seccionesPA = PA.getSeccionesPA();
            seccionesFormato = PA.getSeccionesFormato();
            for (int i = 0; i < seccionesFormato.size(); i++) {
                System.out.println(seccionesFormato.get(i));
            }
        } else {
            System.out.println("Aún no soportamos este tipo de archivo");
        }
        return PA;
    }
    
    public void Comparar(){
      for(Seccion seccion : PA.getSeccionesFormato()){
         if(seccion.isCumplido() && seccion.getNumPalabras() == 0){
            seccion.setCumplido(false);
         }
      }
   }
    
    public void generarReporte(String rutaDestino)
    {
       
        
        try {
            ReportePdf reporte = new ReportePdf();
            reporte.setRutaDestino(rutaDestino);
            
            reporte.setNombrePA(PA.getNombre());
            
            reporte.setSeccionPA(PA.getSeccionesFormato());
        
        String rutaDestino2= managerArch.corregirRuta(reporte.getRutaDestino()) + "/";
        rutaDestino2 +=  "reporteFinal.pdf";
        
            
        
           PdfDocument pdfDoc = new PdfDocument(new PdfWriter(rutaDestino2));
             //2. Creacion del docuemnto como objeto
         Document doc = new Document(pdfDoc);
         
         //3. Creando tabla
         // Table(2) -> Indica que la tabla tendra dos columnas
         Table table = new Table(2);
         
         //4. Añadimos las celas a la tabla
         table.addCell("Formato");
         table.addCell("Cumplido");
         for (Seccion seccion : reporte.getSeccionPA()) {
            table.addCell(seccion.getNombre());
            if (seccion.isCumplido()) {
               table.addCell("Si");
            } else {
               table.addCell("No");
            }
         }
         
         //6. Añadimos la tabla al docuemento
         doc.add(table);
         
         //7. Cerramos el documento
         doc.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ManagerReporte.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        
        
    }
}
