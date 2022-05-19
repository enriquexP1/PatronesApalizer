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
public class FormatoPdf extends AbstractFormato{
     String nombreArchivo;
    ArrayList<Seccion> secciones;
    String Titulo;

    public FormatoPdf(String nombreArchivo , String Titulo)
    {
        this.secciones = new ArrayList<Seccion>();
        this.Titulo=Titulo;
        this.nombreArchivo=nombreArchivo;
    }
     @Override
    public String getTitulo() {
        return Titulo;
    }

     @Override
    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }
     @Override
    public String getNombreArchivo() {
        return nombreArchivo;
    }

     @Override
    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

     @Override
    public ArrayList<Seccion> getSecciones() {
        return secciones;
    }

     @Override
    public void setSecciones(ArrayList<Seccion> secciones) {
        this.secciones = secciones;
    }
    
     @Override
    public String toString()
    {
        return "El archivo es:" + getNombreArchivo() + "el titulo es: " + getTitulo();
    }
}
