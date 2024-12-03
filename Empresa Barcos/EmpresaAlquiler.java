/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examenabril24;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author JRM
 */
public class EmpresaAlquiler {

    private String nombre;
    private String propietario;
    private String cif;
    private ArrayList<Barco> embarcaciones;
    private Cuenta cuenta;
    private int numeroEmbarcaciones;

    // Constructor con parametros
    public EmpresaAlquiler(String nombre, String propietario, String cif, double saldo) {
        this.nombre = nombre;
        this.propietario = propietario;
        this.cif = cif;
        this.embarcaciones = new ArrayList<>();
        this.cuenta = new Cuenta(propietario, cif, saldo);
        this.numeroEmbarcaciones = 0;
    }

    // Getters de la clase
    public String getPropietario() {
        return propietario;
    }

    public String getCif() {
        return cif;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public int getNumeroEmbarcaciones() {
        return numeroEmbarcaciones;
    }

    // Setters de la clase
    public void setPropietario(String propietario) {
        if (propietario.compareTo("") != 0) {
            this.propietario = propietario;
        }
    }

    /**
     *
     * @param nuevoBarco
     * @return verdadero o falso segun el reultado de la operacion
     */
    public boolean insertarEmbarcacion(Barco nuevoBarco) {
        return embarcaciones.add(nuevoBarco);
    }

    /**
     *
     * @param posicion
     * @return verdadero o falso segun el resultado de la operacion
     */
    public boolean borrarEmbarcacion(int posicion) {
        /*if (posicion >= 0 && posicion < embarcaciones.size()) {
            embarcaciones.remove(posicion);
            return true;
        }
        return false;*/
        try {
            embarcaciones.remove(posicion);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    /**
     *
     * @param matricula
     * @return la posicion en la que se encuentra la embarcacion con esa matri­cula o -1 si no existe
     */
    public int buscarEmbarcacion(String matricula) {
        Barco b = new Lancha(0, matricula, 0, 0, true);
        return embarcaciones.indexOf(b);
    }

    /**
     * Devuelve la embarcacion que tiene la matri­cula pasada por parametro
     *
     * @param posicion
     * @return la embarcacion de esa posicion o null
     */
    public Barco buscarEmbarcacion(int posicion) {
        return embarcaciones.get(posicion);
    }

    /**
     * Muestra un listado con todos los barcos
     */
    public void listarBarcos() {
        for (Barco barco : embarcaciones) {
            if (barco != null) {
                barco.verDatos();
            }
        }
    }

    /**
     * Muestra un listado con todos los veleros
     */
    public void listarVeleros() {
        for (Barco barco : embarcaciones) {
            if (barco != null && barco instanceof Velero) {
                barco.verDatos();
            }
        }
    }

    /**
     * Muestra un listado con todos las lanchas
     */
    public void listarLanchas() {
        for (Barco barco : embarcaciones) {
            if (barco != null && barco instanceof Lancha) {
                barco.verDatos();
            }
        }
    }

    /**
     * Metodo que muestra los datos de la empresa
     */
    public void verDatos() {
        System.out.println(nombre + " propiedad de " + propietario);
        System.out.println("Numero de embarcaciones: " + numeroEmbarcaciones);
        System.out.println("Saldo: " + cuenta.getSaldo());
    }

    /**
     * Pide los datos de una nueva embarcacion devolviendo el correspondiente objeto
     *
     * @return el objeto creado o null si no se ha podido crear
     */
    public Barco pedirDatosEmbarcacion() {
        Barco miBarco = null;
        int opcion = Entrada.leerEntero("\n1.- Veleros.\n2.- Lanchas\nElija opcion: ");
        if (opcion == 1 || opcion == 2) {
            String matricula = Entrada.leerCadena("Matricula: ");
            float eslora = Entrada.leerFloat("Eslora: ");
            float manga = Entrada.leerFloat("Manga: ");
            boolean conPatron = Entrada.leerBoolean("¿Con patron?(s|n)");
            switch (opcion) {
                case 1:
                    int numeroMastiles = Entrada.leerEntero("Numero de mastiles: ");
                    float superficieVela = Entrada.leerFloat("Superficie de las velas: ");
                    miBarco = new Velero(numeroMastiles, superficieVela, matricula, eslora, manga, conPatron);
                    break;
                case 2:
                    float potencia = Entrada.leerFloat("Potencia: ");
                    miBarco = new Lancha(potencia, matricula, eslora, manga, conPatron);
                    break;
            }
        }
        return miBarco;
    }

    public void ordenar() {
        Collections.sort(embarcaciones);
    }

    public void crearXML() {
        try {
            FileWriter fichero = new FileWriter("embarcaciones.xml");
            BufferedWriter bw = new BufferedWriter(fichero);
            bw.write("<?xml version=\"1.0\"?>\n");
            bw.write("<!DOCTYPE barcos SYSTEM \"barcos.dtd\">\n");
            bw.write("<barcos>\n");
            for (Barco barco : embarcaciones) {
                bw.write(barco.etiquetaXML());
            }
            bw.write("</barcos>\n");
            bw.close();
        } catch (IOException e) {
            System.out.println("No se ha podido escribir en el fichero");
        }

    }

    public void crearBarcos() {
        try {
            FileWriter fichero = new FileWriter("barcos.txt");
            BufferedWriter bw = new BufferedWriter(fichero);
            for (Barco barco : embarcaciones) {
                bw.write(barco.toString());
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("No se ha podido escribir en el fichero");
        }

    }

    public void crearVeleros() {
        try {
            FileWriter fichero = new FileWriter("velero.txt");
            BufferedWriter bw = new BufferedWriter(fichero);
            for (Barco barco : embarcaciones) {
                if (barco instanceof Velero) {
                    bw.write(barco.toString());
                }
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("No se ha podido escribir en el fichero");
        }

    }

    public void crearLanchas() {
        try {
            FileWriter fichero = new FileWriter("lancha.txt");
            BufferedWriter bw = new BufferedWriter(fichero);
            for (Barco barco : embarcaciones) {
                if (barco instanceof Lancha) {
                    bw.write(barco.toString());
                }
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("No se ha podido escribir en el fichero");
        }
    }

    public void leerBarcos() {
        try {
            FileReader fichero = new FileReader("barcos.txt");
            BufferedReader br = new BufferedReader(fichero);
            String linea = br.readLine(); // lectura de una línea del fichero
            String tipo = linea;
            String matricula;
            float eslora, manga, potencia, superficieVela;
            boolean conPatron, alquilado;
            int numeroMastiles;
            Barco nuevoBarco = null;
            while (linea != null) {
                linea = br.readLine(); // lectura de una línea del fichero
                matricula = linea;
                linea = br.readLine(); // lectura de una línea del fichero
                eslora = Float.parseFloat(linea);
                linea = br.readLine(); // lectura de una línea del fichero
                manga = Float.parseFloat(linea);
                linea = br.readLine(); // lectura de una línea del fichero
                conPatron = Boolean.parseBoolean(linea);
                linea = br.readLine(); // lectura de una línea del fichero
                alquilado = Boolean.parseBoolean(linea);
                if (tipo.compareTo("Velero") == 0) {
                    linea = br.readLine(); // lectura de una línea del fichero
                    numeroMastiles = Integer.parseInt(linea);
                    linea = br.readLine(); // lectura de una línea del fichero
                    superficieVela = Float.parseFloat(linea);
                    nuevoBarco = new Velero(numeroMastiles, superficieVela, matricula, eslora, manga, conPatron);
                }
                if (tipo.compareTo("Lancha") == 0) {
                    linea = br.readLine(); // lectura de una línea del fichero
                    potencia = Float.parseFloat(linea);
                    nuevoBarco = new Lancha(potencia, matricula, eslora, manga, conPatron);
                }
                insertarEmbarcacion(nuevoBarco);
                linea = br.readLine();
                tipo = linea;
            }

            br.close();
        } catch (Exception e) {
            System.out.println("Error en el formato del fichero.");
        }

    }

    public ArrayList<Barco> getEmbarcaciones() {
        return embarcaciones;
    }

    public void crearFicheroBinarioVeleros() {
        FileOutputStream fos = null;
        ObjectOutputStream salida = null;

        try {
            fos = new FileOutputStream("Veleros.dat");
            salida = new ObjectOutputStream(fos); // usado para introducir los datos de objetos
            /**
             * for each para los objetos que se encuentran dentro del arraylist embarcaciones. 
             * Luego filtramos con el instanceof para que solamente añadamos los veleros al fichero binario.
             */
            for (Barco barco : embarcaciones) {
                if (barco instanceof Velero) {
                    salida.writeObject(barco);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (salida != null) {
                    salida.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void crearFicheroBinarioLanchas() {
        FileOutputStream fos = null;
        ObjectOutputStream salida = null;

        try {
            fos = new FileOutputStream("Lanchas.dat");
            salida = new ObjectOutputStream(fos);
            for (Barco barco : embarcaciones) {
                if (barco instanceof Lancha) {
                    salida.writeObject(barco);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (salida != null) {
                    salida.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void leerVelerosFichero() {
        FileInputStream fis = null;
        ObjectInputStream entrada = null;
        Barco miBarco;
        try {
            fis = new FileInputStream("Veleros.dat"); // ruta que usamos para coger los datos de los objetos
            entrada = new ObjectInputStream(fis); 
            miBarco = (Velero) entrada.readObject();
            while (miBarco != null) {
                insertarEmbarcacion(miBarco);
                miBarco = (Velero) entrada.readObject();
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            //
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (entrada != null) {
                    entrada.close();
                }
            } catch (IOException e) {
            }
        }
    }

        public void leerLanchasFichero() {
        FileInputStream fis = null;
        ObjectInputStream entrada = null;
        Barco miBarco;
        try {
            fis = new FileInputStream("Lanchas.dat");
            entrada = new ObjectInputStream(fis);
            miBarco = (Lancha) entrada.readObject();
            while (miBarco != null) {
                insertarEmbarcacion(miBarco);
                miBarco = (Lancha) entrada.readObject();
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            //
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (entrada != null) {
                    entrada.close();
                }
            } catch (IOException e) {
            }
        }
    }
    
}
