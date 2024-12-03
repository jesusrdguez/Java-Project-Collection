/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examenabril24;

import java.util.Comparator;

/**
 *
 * @author JRM
 */
public class OrdenaBarcoPorAlquiler implements Comparator<Barco> {

    @Override
    public int compare(Barco o1, Barco o2) {
        if (o1.precioAlquiler() == o2.precioAlquiler()){
            return 0;
        } else if (o1.precioAlquiler() < o2.precioAlquiler()){
            return -1;
        } else {
            return 1;
        }
    }


    
}
