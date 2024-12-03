/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionarinstituto;

/**
 *
 * @author JRM
 */
public class Laboral extends Personal {

    private String categoria;
    private String turno;

    public Laboral(String categoria, String turno, String nrp, String nombre, Fecha fechaIngreso, Fecha fechaNacimiento, int telefono) {
        super(nrp, nombre, fechaIngreso, fechaNacimiento, telefono);
        this.categoria = categoria;
        this.turno = turno;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public double complemento() {
        if (categoria.compareTo("Administrativo") == 0) {
            return 300;
        }
        if (categoria.compareTo("Ordenanza") == 0) {
            return 200;
        }
        if (categoria.compareTo("Limpieza") == 0) {
            return 100;
        } else {
            return 0;
        }
    }

    @Override
    public double salario() {
        return 1000;
    }

    @Override
    public void verDatos() {
        System.out.println("\n" + categoria + " turno de " + turno);
        super.verDatos();
    }

}
