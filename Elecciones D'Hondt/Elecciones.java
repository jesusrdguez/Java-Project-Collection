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
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 *
 * @author JRM
 *
 * Hacer una tabla con los votos de cada partido. Si hay un total de 5 escaños, los votos de cada partido se irán dividiendo de forma creciente hasta 5. Por ejemplo, si el partido A tiene 25000, primero tendrá 25000, luego 12500, después 8333,33, 6500 sería l a próxima cifra y la última sería 5000. Hay que tener en cuenta que habrá más partidos que tendrán un número mayor o menor de votos.
 *
 * Una vez tenemos los votos ya dividos, los metemos en un ArrayList que contendrá por ejemplo el nombre del partido y su cantidad correspondiente según el orden en donde se encuentra.
 */
public class Elecciones implements Serializable {

    private int escanios;
    private ArrayList<Partido> partidos;
    private ArrayList<Candidato> resultados;

    public Elecciones() {
        this.escanios = 5;
        this.partidos = new ArrayList();
        this.resultados = new ArrayList();
    }

    public int getEscanios() {
        return escanios;
    }

    public void setEscanios(int escanios) {
        this.escanios = escanios;
    }

    public ArrayList<Partido> getPartidos() {
        return partidos;
    }

    public void setPartidos(ArrayList<Partido> partidos) {
        this.partidos = partidos;
    }

    public ArrayList<Candidato> getResultados() {
        return resultados;
    }

    public void setResultados(ArrayList<Candidato> resultados) {
        this.resultados = resultados;
    }

    public void leyDhont() {
        /**
         * Limpiamos el ArrayList para que no se vayan acumulando todos candidatos
         */
        resultados.clear();
        resetearEscanios();
        cambiarEscanios();
        Partido p;
        int repartoVotos = 0, repartoEscanios = this.getEscanios(), restaEscanios = this.getEscanios();
        ArrayList<Partido> partidoConEscanios = new ArrayList();
        for (Partido miPartido : partidos) {
            if (miPartido.porcentaje(this.totalVotos()) >= 5) {
                int i = 1;
                for (int j = 0; j < this.getEscanios(); j++) {
                    repartoVotos = miPartido.getVotos() / i;
                    i++;
                    Partido partidoEscaniano = new Partido(miPartido.getNombre(), repartoVotos);
                    partidoConEscanios.add(partidoEscaniano);
                }
            }
        }

        /**
         * Se ordenan todos los partidos que he añadido en el nuevo ArrayList: partidoConEscanio
         */
        Collections.sort(partidoConEscanios, new OrdenarPorVoto());

        /**
         * Se asginan los escanios a sus respectivos partidos
         */
        for (Partido miPartido : partidoConEscanios) {
            p = this.buscarPartidoNombre(miPartido.getNombre());
            if (repartoEscanios > 0) {
                p.setEscaniosConseguidos(p.getEscaniosConseguidos() + 1);
                repartoEscanios--;
            }
        }

        ArrayList<Partido> escanios = new ArrayList();
        for (Partido miPartido : partidos) {
            String nuevoNombre = miPartido.getNombre();
            int nuevosEscanios = miPartido.getEscaniosConseguidos();
            Partido nuevoPartido = new Partido(miPartido.getNombre(), nuevosEscanios);
            escanios.add(nuevoPartido);
        }

        /**
         * Se quiere insertar en el ArrayList resultados los candidatos de cada partido según el orden establecido por el número de votos.
         *
         * Tengo los partidos fraccionados y ordenados según sus votos correspondientes. Se va a recorrer el ArrayList de partidoConEscanios y con el uso del método buscarPartidoNombre se usará el objeto Partido correspondiente.
         *
         * Una vez tengo el objeto que quiero, miro los escanios que tiene el partido, con estos hago un bucle que insertará los candidatos del ArrayList un número de veces correspondiente al número de escanios del partido.
         */
        for (Partido miPartido : partidoConEscanios) {
            p = this.buscarPartidoNombre(miPartido.getNombre());
            for (Partido escanio : escanios) {
                if (escanio.getNombre().equalsIgnoreCase(p.getNombre())) {
                    int escaniosPropios = escanio.getVotos();
                    if (restaEscanios > 0) {
                        ArrayList<Candidato> candidatos = p.getCandidatos();
                        for (Candidato miCandidato : candidatos) {
                            if (escaniosPropios > 0 && restaEscanios > 0) {
                                resultados.add(miCandidato);
                                escaniosPropios--;
                                restaEscanios--;
                            }
                        }
                        escanio.setVotos(0);
                    }
                }
            }
        }
    }

    public void resetearEscanios() {
        for (Partido miPartido : partidos) {
            miPartido.setEscaniosConseguidos(0);
        }
    }

    public void cambiarEscanios() {
        int dato = Entrada.leerEntero("Deseas cambiar el número de escanios asignables a cada partido "
                + "\n\t1. Sí \n\t2. No\n");
        switch (dato) {
            case 1:
                System.out.println("Introduzca en número de escanios: ");
                int nuevosEscanios = Entrada.leerEntero();
                this.setEscanios(nuevosEscanios);
                break;
            case 2:
                System.out.print("Adio");
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }

    /**
     * Se necesita que el ArrayList partidoConEscnaios tenga un ArrayList de candidatos, entonces, se irán insertando según los escanios conseguidos sus correpondientes candidatos.
     *
     * @return
     */
    public Candidato accederDatosCandidatosDeTodosLosPartidos() {
        for (Partido partido : partidos) {
            ArrayList<Candidato> candidatos = partido.getCandidatos(); // Obtener la lista de candidatos del partido

            // Iterar sobre la lista de candidatos e imprimir sus datos (o hacer cualquier otra operación)
            for (Candidato miCandidato : candidatos) {
                return miCandidato;
            }
        }
        return null;
    }

    public int totalVotos() {
        int suma = 0;
        for (Partido miPartido : partidos) {
            suma += miPartido.getVotos();
        }
        return suma;
    }

    public void cargarPartido(String nombreFichero) {
        try {
            FileReader fichero = new FileReader(nombreFichero);
            BufferedReader entrada = new BufferedReader(fichero);
            String linea = entrada.readLine();
            Partido nuevoPartido;
            while (linea != null) {
                Scanner datos = new Scanner(linea).useDelimiter(";");
                String siglas = datos.next();
                String nombre = datos.next();
                int votos = datos.nextInt();
                int escaniosConseguidos = datos.nextInt();
                nuevoPartido = new Partido(siglas, nombre, votos, escaniosConseguidos);
                partidos.add(nuevoPartido);
                linea = entrada.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error en el formato de fichero: " + nombreFichero);
        }
    }

    public void verDatos() {
        for (Partido miPartido : partidos) {
            miPartido.verDatos();
        }
    }

    public Partido buscarPartidoNombre(String nombre) {
        for (Partido miPartido : partidos) {
            if (miPartido.getNombre().equalsIgnoreCase(nombre)) {
                return miPartido;
            }
        }
        return null;
    }

    public void verDatosResultado() {
        for (Candidato miCandidato : resultados) {
            miCandidato.verDatos();
        }
    }

}
