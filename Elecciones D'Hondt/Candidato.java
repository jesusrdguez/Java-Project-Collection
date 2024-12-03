/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicioelecciones;

import java.io.Serializable;

/**
 *
 * @author JRM
 */
public class Candidato implements Serializable {
    
    private String nombre;
    private Fecha fechaNacimiento;
    private String siglasPartido;
    private String ciudad;
    static int numeroCandidatos;

    public Candidato(String nombre, Fecha fechaNacimiento, String siglasPartido, String ciudad) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.siglasPartido = siglasPartido;
        this.ciudad = ciudad;
        numeroCandidatos++;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Fecha getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Fecha fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSiglasPartido() {
        return siglasPartido;
    }

    public void setSiglasPartido(String siglasPartido) {
        this.siglasPartido = siglasPartido;
    }

    public void verDatos() {
        StringBuilder sb = new StringBuilder();
        sb.append(" Candidato");
        sb.append(" Nombre: ").append(nombre);
        sb.append(" Fecha de nacimiento: ").append(fechaNacimiento);
        sb.append(" Siglas del partido: ").append(siglasPartido);
        sb.append(" Ciudad de nacimiento: ").append(ciudad);
        System.out.println(sb.toString());
    }
    
}
