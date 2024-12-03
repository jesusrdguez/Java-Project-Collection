/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gestionarligaarraylist;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;

/**
 *
 * @author JRM
 */
public class GestionarLigaArrayList {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Liga miLiga = new Liga();
        int opcion;
        String nombreFichero;
        do {
            opcion = menu();
            switch (opcion) {
                case 1:
                    guardarLigaEnFichero(miLiga);
                    System.out.println("Guardados datos en Liga.dat");
                    break;
                case 2:
                    miLiga = leerLigaDeFichero();
                    System.out.println("Cargados datos desde Liga.dat");
                    break;
                case 3:
                    Collections.sort(miLiga.getEquipos());
                    miLiga.verEquipos();
                    break;
                case 4:
                    nombreFichero = Entrada.leerCadena("Nombre del fichero que contiene los equipos.");
                    miLiga.cargaEquipos(nombreFichero);
                    miLiga.verEquipos();
                    break;
                case 5:
                    nombreFichero = Entrada.leerCadena("Nombre del fichero que contiene los partidos de la jornada.");
                    miLiga.cargaJornada(nombreFichero);
                    miLiga.verPartidos();
                    break;
                case 6:
                    miLiga.procesaJornada();
                    break;
                case 7:
                    miLiga.verPartidos();
                    break;
                case 8:
                    for (Equipo eq : miLiga.getEquipos()) {
                        eq.reset();
                    }
                    System.out.println("Liga reiniciada.");
                    break;
            }
        } while (opcion != 0);
    }

    public static int menu() {
        int op;
        System.out.println("\nMenu");
        System.out.println("");
        System.out.println("1.- Guardar los datos de la liga en un fichero binario.");
        System.out.println("2.- Recuperar los datos de la liga de un fichero binario.");
        System.out.println("3.- Mostrar clasificación: mostrará los equipos ordenados por puntos.");
        System.out.println("4.- Cargar equipos desde un fichero de texto (inicializa la liga).");
        System.out.println("5.- Cargar jornada desde archivo de texto.");
        System.out.println("6.- Procesar jornada.");
        System.out.println("7.- Ver los resultados de los partidos de la jornada actual.");
        System.out.println("8.- Reiniciar la liga: mantiene los equipos y pone todos los datos a cero.");
        System.out.println("0.- Salir.");
        op = Entrada.leerEntero("\nElija opción:");

        return op;
    }

    public static void guardarLigaEnFichero(Liga liga) {
        try {
            FileOutputStream fichero = new FileOutputStream("Liga.dat");
            ObjectOutputStream salida = new ObjectOutputStream(fichero);
            salida.writeObject(liga);
            fichero.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static Liga leerLigaDeFichero() {
        Liga liga = null;
        try {
            FileInputStream fichero = new FileInputStream("Liga.dat");
            ObjectInputStream entrada = new ObjectInputStream(fichero);
            liga = (Liga) entrada.readObject();
            fichero.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            //
        }
        return liga;
    }

}
