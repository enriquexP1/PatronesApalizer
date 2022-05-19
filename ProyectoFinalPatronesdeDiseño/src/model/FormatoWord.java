/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author enriq
 */
public class FormatoWord extends AbstractFormato{
    ArrayList<Seccion> secciones;
    String nombreArchivo;
    String Titulo;
    HashMap<Integer ,ArrayList<String>> contenidoSeccion;
    public FormatoWord(String nombreArchivo , String Titulo  )
    {
        
        this.Titulo=Titulo;
        this.nombreArchivo=nombreArchivo;
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
    public String getNombreArchivo() {
        return nombreArchivo;
    }

    @Override
    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
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
    public HashMap<Integer, ArrayList<String>> getContenidoSeccion() {
        return contenidoSeccion;
    }

    @Override
    public void setContenidoSeccion(HashMap<Integer, ArrayList<String>> contenidoSeccion) {
        this.contenidoSeccion = contenidoSeccion;
    }

   
    @Override
    public String toString()
    {
        return "El archivo es:" + getNombreArchivo() + "el titulo es: " + getTitulo() + "  " + contenidoSeccion.get(4) ;
    }
    
    
    
}
