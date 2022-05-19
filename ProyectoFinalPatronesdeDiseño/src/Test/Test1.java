/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;

import java.io.IOException;
import model.ManagerArchivo;
import model.ManagerReporte;

/**
 *
 * @author enriq
 */
public class Test1 {
    public static void main(String[] args) throws IOException {
        ManagerReporte man = new ManagerReporte();
        String formato = "C:\\Users\\enriq\\OneDrive - Universidad Autónoma Metropolitana Unidad Azcapotzalco\\Escuela\\Trimestre 22i\\Patrones de diseño\\pruebas proyectoi\\PatronesDSub1-main\\PatronesDSub1-main\\holin2.docx";
        String PA = "C:\\Users\\enriq\\OneDrive - Universidad Autónoma Metropolitana Unidad Azcapotzalco\\Escuela\\Trimestre 22i\\Patrones de diseño\\pruebas proyectoi\\PatronesDSub1-main\\PatronesDSub1-main\\holin2.pdf";
        String rutaDest = "‪‪C:\\Users\\enriq\\Downloads\\";
        man.generarProceso(formato,PA,rutaDest);
      
    }
}
