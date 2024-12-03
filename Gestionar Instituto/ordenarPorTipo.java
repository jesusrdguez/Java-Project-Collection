/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionarinstituto;

import java.util.Comparator;

/**
 *
 * @author JRM
 */
public class ordenarPorTipo implements Comparator<Personal>{

    @Override
    public int compare(Personal o1, Personal o2) {
        if (o1.getClass() == o2.getClass()){
            return o1.getNombre().compareTo(o2.getNombre());
        }else{
            if (o1 instanceof Laboral){
                return -1;
            }else{
                return 1;
            }
        }
    }

}
