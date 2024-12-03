/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicioelecciones;

import java.util.Comparator;

/**
 *
 * @author JRM
 */
public class OrdenarPorVoto implements Comparator<Partido> {

    @Override
    public int compare(Partido p1, Partido p2) {
        if (p1.getVotos() == p2.getVotos()) {
            return 0;
        } else if (p1.getVotos() < p2.getVotos()) {
            return 1;
        } else {
            return -1;
        }
    }

}
