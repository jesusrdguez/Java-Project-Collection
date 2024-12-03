/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examenabril24;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author JRM
 */
public abstract class Barco implements Comparable<Barco>, Serializable{

    // Atributos de la clase
    private String matricula;
    private float eslora;
    private float manga;
    private boolean conPatron;
    private boolean alquilado;

    // Constructor con parámetros
    public Barco(String matricula, float eslora, float manga, boolean conPatron) {
        this.matricula = matricula;
        this.eslora = eslora;
        this.manga = manga;
        this.conPatron = conPatron;
        this.alquilado = false;
    }

    /**
     * Método abstracto que devuelve el precio del alquiler
     *
     * @return precio del alquiler
     */
    public abstract float precioAlquiler();

    /**
     * Método que muestra los datos del Barco
     */
    public void verDatos() {
        System.out.println(matricula + " E: " + eslora + " mts M: " + manga + " mts");
        System.out.println("Precio: " + precioAlquiler() + " Alquilado: " + (alquilado ? "Sí" : "No"));
    }

    // Getters de la clase Barco
    public String getMatricula() {
        return matricula;
    }

    public float getEslora() {
        return eslora;
    }

    public float getManga() {
        return manga;
    }

    public boolean isAlquilado() {
        return alquilado;
    }

    public boolean isConPatron() {
        return conPatron;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.matricula);
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
        final Barco other = (Barco) obj;
        return Objects.equals(this.matricula, other.matricula);
    }

    @Override
    public int compareTo(Barco o) {
        if(this.eslora == o.eslora){
            return 0;
        } if (this.eslora < o.eslora){
            return -1;
        }else{
            return 1;
        }
    }

    public abstract String etiquetaXML();
    
    public String atributosXML(){
        StringBuilder sb = new StringBuilder();
        sb.append("\t\t<matricula>").append(matricula).append("</matricula>\n");
        sb.append("\t\t<eslora>").append(eslora).append("</eslora>\n");
        sb.append("\t\t<manga>").append(manga).append("</manga>\n");
        sb.append("\t\t<conpatron>").append(conPatron?"SI":"NO").append("</conpatron>\n");
        sb.append("\t\t<alquilado>").append(alquilado?"SI":"NO").append("</alquilado>\n");
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(matricula).append("\n");
        sb.append(eslora).append("\n");
        sb.append(manga).append("\n");
        sb.append(conPatron).append("\n");
        sb.append(alquilado).append("\n");
        return sb.toString();
    }
    
    
}
