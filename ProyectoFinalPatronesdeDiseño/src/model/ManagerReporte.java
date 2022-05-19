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
    Formato formato;
    ProductoAcademico PA;
    ManagerArchivo managerArch;
    ArrayList<Seccion> seccionesFormato = null;
    ArrayList<String> seccionesPA = null;
    String ruta;
    String extension;

    public ManagerReporte() {
        this.managerArch = new ManagerArchivo();
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
}
