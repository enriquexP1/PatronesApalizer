/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author enriq
 */
public class ReportePdf {
   private ArrayList<Seccion> seccionPA;
   private String nombrePA;
   private String rutaDestino;

    public ReportePdf() {
    }

    public ArrayList<Seccion> getSeccionPA() {
        return seccionPA;
    }

    public void setSeccionPA(ArrayList<Seccion> seccionPA) {
        this.seccionPA = seccionPA;
    }

    public String getNombrePA() {
        return nombrePA;
    }

    public void setNombrePA(String nombrePA) {
        this.nombrePA = nombrePA;
    }

    public String getRutaDestino() {
        return rutaDestino;
    }

    public void setRutaDestino(String rutaDestino) {
        this.rutaDestino = rutaDestino;
    }
   
   @Override
   public String toString()
   {
       return "El nombre del archivo es: " + nombrePA + "\nCon la ruta: " + rutaDestino;
   }
}
