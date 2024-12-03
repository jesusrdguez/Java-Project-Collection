/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package garajeficheros;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author JRM
 */
public class GarajeFicheros {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Garaje miGaraje = leerGarajeFichero();
        /*Garaje miGaraje = new Garaje(20);
        miGaraje.leerMotosFichero();
        miGaraje.leerCochesFichero();
         */
        Vehiculo nuevoVehiculo;

        char seguir = Entrada.leerCaracter("¿Quiere aparcar un vehiculo? (s/n)");
        while (seguir != 'n' && miGaraje.hayPlazas()) {
            nuevoVehiculo = miGaraje.pedirDatos();
            miGaraje.añadirVehiculo(nuevoVehiculo);
            seguir = Entrada.leerCaracter("¿Quiere aparcar un vehiculo? (s/n)");
        }

        for (int i = 0; i < miGaraje.getPlazaLibre(); i++) {
            miGaraje.devuelveVehiculo(i).verDatos();
        }
        System.out.println("");
        //miGaraje.guardarDatosFichero();
        guardarGarajeFichero(miGaraje);
    }

    public static void guardarGarajeFichero(Garaje miGaraje) {
        FileOutputStream fichero = null;
        ObjectOutputStream salida = null;
        try {
            fichero = new FileOutputStream("Garaje.dat");
            salida = new ObjectOutputStream(fichero);
            salida.writeObject(miGaraje);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                fichero.close();
                salida.close();
            } catch (IOException e) {
            }
        }
    }

    public static Garaje leerGarajeFichero() {
        FileInputStream fichero = null;
        ObjectInputStream entrada = null;
        Garaje miGaraje = null;
        try {
            fichero = new FileInputStream("Garaje.dat");
            entrada = new ObjectInputStream(fichero);
            miGaraje = (Garaje) entrada.readObject();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            //
        } finally {
            try {
                if (fichero != null) {
                    fichero.close();
                }
                if (entrada != null) {
                    entrada.close();
                }
            } catch (IOException e) {
            }
        }
        return miGaraje;
    }

}
