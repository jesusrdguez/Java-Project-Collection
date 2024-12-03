/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejercicioelecciones;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author JRM
 */
public class EjercicioElecciones {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Elecciones misElecciones = leerEleccionesDeFichero();
        if (misElecciones == null) {
            misElecciones = new Elecciones();
        }
        Partido p;
        String dato, nombrePartido;
        int opcion = menu();
        while (opcion != 0) {
            switch (opcion) {
                case 1:
                    misElecciones.verDatos();
                    break;
                case 2:
                    dato = Entrada.leerCadena("Introduzca el fichero de texto desde que desea cargar los partidos: ");
                    misElecciones.cargarPartido(dato);
                    break;
                case 3:
                    dato = Entrada.leerCadena("Introduzca el nombre del partido del que desea ver los datos: ");
                    p = misElecciones.buscarPartidoNombre(dato);
                    if (p != null) {
                        p.verDatos();
                    } else {
                        System.out.println("No existe el partido a buscar.");
                    }
                    break;
                case 4:
                    nombrePartido = Entrada.leerCadena("Nombre del partido: ");
                    p = misElecciones.buscarPartidoNombre(nombrePartido);
                    if (p != null) {
                        p.verDatos();
                        dato = Entrada.leerCadena("\nNombre del fichero con los candidatos: ");
                        p.cargarFichero(dato);
                    } else {
                        System.out.println("No existe el partido introducido.");
                    }
                    break;
                case 5:
                    misElecciones.leyDhont();
                    break;
                case 6:
                    misElecciones.verDatosResultado();
                    break;
                default:
                    break;
            }
            Entrada.leerCadena("\nPulse INTRO para continuar.");
            opcion = menu();
        }
        System.out.println("\nFin de la ejecución.");
        guardarEleccionesEnFichero(misElecciones);
        System.out.println("Objeto guardado en elecciones.dat.");
    }

    /**
     * Método que muestra el menú
     *
     * @return la opción elegida por el usuario
     */
    public static int menu() {
        int opcion;
        System.out.println("Número de candidatos: " + Candidato.numeroCandidatos);
        System.out.println("Paraelecciones Europedos");
        System.out.println("-------------");
        System.out.println("1.- Listar partidos");
        System.out.println("2.- Cargar partidos desde un archivo de texto");
        System.out.println("3.- Ver datos de un partido");
        System.out.println("4.- Cargar los candidatos de un partido desde un archivo de texto");
        System.out.println("5.- Calcular el resultado de elecciones");
        System.out.println("6.- Mostrar el resultado de la elecciones");
        System.out.println("0. Salir");
        opcion = Entrada.leerEntero("\n¿Opción?: ");
        return opcion;
    }

    public static void guardarEleccionesEnFichero(Elecciones misElecciones) {
        try {
            FileOutputStream fichero = new FileOutputStream("elecciones.dat");
            ObjectOutputStream salida = new ObjectOutputStream(fichero);
            salida.writeObject(misElecciones);
            fichero.close();
        } catch (IOException e) {
            System.out.println("Error guardando fichero elecciones.dat");
        }
    }

    public static Elecciones leerEleccionesDeFichero() {
        Elecciones misElecciones = null;
        try {
            FileInputStream fichero = new FileInputStream("elecciones.dat");
            ObjectInputStream entrada = new ObjectInputStream(fichero);
            misElecciones = (Elecciones) entrada.readObject();
            fichero.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error leyendo fichero elecciones.dat");
        }
        return misElecciones;
    }
}
