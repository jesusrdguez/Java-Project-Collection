/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionarligaarraylist;

/**
 *
 * @author JRM
 */
public class Partido {

    private String nombreLocal;
    private int golesLocal;
    private String nombreVisitante;
    private int golesVisitante;

    public Partido(String nombreLocal, int golesLocal, String nombreVisitante, int golesVisitante) {
        this.nombreLocal = nombreLocal;
        this.golesLocal = golesLocal;
        this.nombreVisitante = nombreVisitante;
        this.golesVisitante = golesVisitante;
    }

    public String getNombreLocal() {
        return nombreLocal;
    }

    public int getGolesLocal() {
        return golesLocal;
    }

    public String getNombreVisitante() {
        return nombreVisitante;
    }

    public int getGolesVisitante() {
        return golesVisitante;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(nombreLocal).append("\t");
        sb.append(golesLocal).append(" - ");;
        sb.append(golesVisitante).append("\t");
        sb.append(nombreVisitante).append("\n");;
        return sb.toString();
    }

    public boolean ganalocal(){
        return golesLocal > golesVisitante;
    }
    
    public boolean ganaVisitante(){
        return golesLocal < golesVisitante;
    }
    
}
