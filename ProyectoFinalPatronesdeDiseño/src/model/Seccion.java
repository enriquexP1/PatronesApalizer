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
public class Seccion {
 int id;
 String nombre;
 int numPalabras;
 String texto;
HashMap<Integer ,ArrayList<String>> contenidoSeccion;
 ArrayList<String> textin;

 public Seccion( )
 {
    
     
 }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumPalabras() {
        return numPalabras;
    }

    public void setNumPalabras(int numPalabras) {
        this.numPalabras = numPalabras;
    }

    public ArrayList<String> getTexto() {
        return textin;
    }

    public void setTexto(HashMap<Integer ,ArrayList<String>> contenidoSeccion) {
       textin = contenidoSeccion.get(id);
       
    }

   

   
 @Override
 public String toString()
 {
     return "\n----soy la seccion: " +getId()+ getNombre() + " \ntengo de palabras :" +getNumPalabras() 
             + "\ndigo: " + getTexto() + "\ny tengo: " ;
 }
}
