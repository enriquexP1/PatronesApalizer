/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

/**
 *
 * @author enriq
 */
public class AbstractFormato {
    String nombreArchivo;
    ArrayList<Seccion> secciones;
    String Titulo;
    HashMap<Integer , ArrayList<String>> contenidoSeccion;
    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }
    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public ArrayList<Seccion> getSecciones() {
        return secciones;
    }

    public void setSecciones(ArrayList<Seccion> secciones) {
        this.secciones = secciones;
    }

    public HashMap<Integer, ArrayList<String>> getContenidoSeccion() {
        return contenidoSeccion;
    }

    public void setContenidoSeccion(HashMap<Integer, ArrayList<String>> contenidoSeccion) {
        this.contenidoSeccion = contenidoSeccion;
    }

    
     @Override
    public String toString()
    {
        return "El archivo es:" + getNombreArchivo() + "el titulo es: " + getTitulo();
    }
    
}
