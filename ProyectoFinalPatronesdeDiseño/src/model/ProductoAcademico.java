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
public class ProductoAcademico {
   String nombre;
   ArrayList<Seccion> SeccionesFormato;
   ArrayList<String> SeccionesPA;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Seccion> getSeccionesFormato() {
        return SeccionesFormato;
    }

    public void setSeccionesFormato(ArrayList<Seccion> SeccionesFormato) {
        this.SeccionesFormato = SeccionesFormato;
    }

    public ArrayList<String> getSeccionesPA() {
        return SeccionesPA;
    }

    public void setSeccionesPA(ArrayList<String> SeccionesPA) {
        this.SeccionesPA = SeccionesPA;
    }

    @Override
    public String toString()
    {
        return "El nombre del archivo es: " + nombre + "con secciones: \n" +SeccionesPA;
    }
    
  

  
    
}
