/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.File;
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
    AbstractFormato formato;
    ManagerArchivo managerArch;
    ArrayList<Seccion> seccionesFormato= null;
    String ruta;
    String extension;
    public ManagerReporte()
    {
       this.managerArch = new ManagerArchivo();
    }
  
    public AbstractFormato leerFormato(String archivo  )
    {
        /*Metodo encargado de llamar al manager Archivo*/
        extension = DiferenciarFormato(archivo);
      
        AbstractFormato formato = null;
       
        if(extension.compareTo("pdf") == 0)
        {
            //Aqui necesito que mande a llamar a un formato del manager Archivo
            System.out.println("aqui vamos a leer el pdf");
        }
        else if (extension.compareTo("docx")== 0 )
        {
          
            try {
                formato=  managerArch.tomarFormatoWord(archivo );
                System.out.println(formato.toString());
                seccionesFormato = formato.getSecciones();
                for(int i=0 ; i< seccionesFormato.size() ; i++)
                {
                    System.out.println(seccionesFormato.get(i));
                }
            } catch (IOException ex) {
                Logger.getLogger(ManagerReporte.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        {
            System.out.println("Aún no soportamos este tipo de archivo");   
        }
        return formato;
    }
      public String DiferenciarFormato(String archivo)
    {
       extension = FilenameUtils.getExtension(archivo);
        return extension;
    }
}
