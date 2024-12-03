/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionarligaarraylist;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author JRM
 */
public class Equipo implements Serializable, Comparable<Equipo> {

    private String nombre;
    private int partidosGanados;
    private int partidosEmpatados;
    private int partidosPerdidos;
    private int golesAFavor;
    private int golesEnContra;

    public Equipo(String nombre) {
        this.nombre = nombre;
        this.partidosGanados = 0;
        this.partidosEmpatados = 0;
        this.partidosPerdidos = 0;
        this.golesAFavor = 0;
        this.golesEnContra = 0;
    }

    public int getGolesEnContra() {
        return golesEnContra;
    }

    public void setGolesEnContra(int golesEnContra) {
        this.golesEnContra = golesEnContra;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != "") {
            this.nombre = nombre;
        }
    }

    public int getPartidosGanados() {
        return partidosGanados;
    }

    public void setPartidosGanados(int partidosGanados) {
        this.partidosGanados = partidosGanados;
    }

    public int getPartidosEmpatados() {
        return partidosEmpatados;
    }

    public void setPartidosEmpatados(int partidosEmpatados) {
        this.partidosEmpatados = partidosEmpatados;
    }

    public int getPartidosPerdidos() {
        return partidosPerdidos;
    }

    public void setPartidosPerdidos(int partidosPerdidos) {
        this.partidosPerdidos = partidosPerdidos;
    }

    public int getGolesAFavor() {
        return golesAFavor;
    }

    public void setGolesAFavor(int golesAFavor) {
        this.golesAFavor = golesAFavor;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Equipo other = (Equipo) obj;
        return Objects.equals(this.nombre, other.nombre);
    }

    public int puntos() {
        return partidosGanados * 3 + partidosEmpatados;
    }

    public int diferenciaDeGoles() {
        return golesAFavor - golesEnContra;
    }

    public int partidosJugados() {
        return partidosGanados + partidosEmpatados + partidosPerdidos;
    }

    public void reset() {
        this.partidosGanados = 0;
        this.partidosEmpatados = 0;
        this.partidosPerdidos = 0;
        this.golesAFavor = 0;
        this.golesEnContra = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(nombre);
        sb.append("\t").append(puntos());
        sb.append("\t").append(partidosJugados());
        sb.append("\t").append(partidosGanados);
        sb.append("\t").append(partidosEmpatados);
        sb.append("\t").append(partidosPerdidos);
        sb.append("\t").append(golesAFavor);
        sb.append("\t").append(golesEnContra);
        sb.append("\t").append(diferenciaDeGoles()).append("\n");
        return sb.toString();
    }

    @Override
    public int compareTo(Equipo otro) {
        if (this.puntos() == otro.puntos()) {
            return 0;
        } else if (this.puntos() < otro.puntos()) {
            return 1;
        } else {
            return -1;
        }
    }
}
