/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicioelecciones;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author JRM
 */
public class Partido implements Serializable {

    private String siglas;
    private String nombre;
    private int votos;
    private int escaniosConseguidos;
    private ArrayList<Candidato> candidatos;

    public Partido(String siglas, String nombre, int votos, int escaniosConseguidos) {
        this.siglas = siglas;
        this.nombre = nombre;
        this.votos = votos;
        this.escaniosConseguidos = escaniosConseguidos;
        this.candidatos = new ArrayList();
    }

    public Partido(String nombre, int votos) {
        this.nombre= nombre;
        this.votos = votos;
    }

    public String getSiglas() {
        return siglas;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVotos() {
        return votos;
    }

    public void setVotos(int votos) {
        this.votos = votos;
    }

    public int getEscaniosConseguidos() {
        return escaniosConseguidos;
    }

    public void setEscaniosConseguidos(int escaniosConseguidos) {
        this.escaniosConseguidos = escaniosConseguidos;
    }

    public ArrayList<Candidato> getCandidatos() {
        return candidatos;
    }

    public void setCandidatos(ArrayList<Candidato> candidatos) {
        this.candidatos = candidatos;
    }

    public void cargarFichero(String nombreFichero) {
        try {
            FileReader fichero = new FileReader(nombreFichero);
            BufferedReader entrada = new BufferedReader(fichero);
            String linea = entrada.readLine(); // lectura de una linea del fichero
            Candidato nuevoCandidato;
            while (linea != null) {
                // Creo el objeto Scanner separando los datos de cada atributo
                Scanner datos = new Scanner(linea).useDelimiter(";");
                String nombre = datos.next();
                String f1 = datos.next();
                Scanner fecha = new Scanner(f1).useDelimiter("/");
                int dia = fecha.nextInt();
                int mes = fecha.nextInt();
                int anio = fecha.nextInt();
                Fecha nuevaFecha = new Fecha(dia, mes, anio);
                String siglasPartido = datos.next();
                String ciudad = datos.next();
                nuevoCandidato = new Candidato(nombre, nuevaFecha, siglasPartido, ciudad);
                candidatos.add(nuevoCandidato);
                linea = entrada.readLine();
            }
            entrada.close();
        } catch (IOException e) {
            System.out.println("Error en el formato del fichero: " + nombreFichero);
        }
    }

    public float porcentaje(int totalVotos) {
        if (totalVotos == 0) {
            return 0;
        }
        return ((float) this.getVotos() / totalVotos) * 100;
    }

    public void verDatos() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nPartido: " + this.nombre);
        sb.append(" Siglas: ").append(siglas);
        sb.append(" Votos: ").append(votos);
        sb.append(" Escanios conseguidos: ").append(escaniosConseguidos);
        System.out.println(sb.toString());
        for (Candidato miCandidato : candidatos) {
            miCandidato.verDatos();
        }
    }

    public void verDatosConEscanios() {
        StringBuilder sb = new StringBuilder();
        sb.append(" Nombre: ").append(nombre);
        sb.append(" Votos: ").append(votos);
        System.out.println(sb.toString());

    }

}
