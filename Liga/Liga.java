/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionarligaarraylist;

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
public class Liga implements Serializable {

    private ArrayList<Equipo> equipos;
    private ArrayList<Partido> partidos;

    public Liga() {
        this.equipos = new ArrayList<>();
        this.partidos = new ArrayList<>();
    }

    public ArrayList<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(ArrayList<Equipo> equipos) {
        this.equipos = equipos;
    }

    public ArrayList<Partido> getPartidos() {
        return partidos;
    }

    public void setPartidos(ArrayList<Partido> partidos) {
        this.partidos = partidos;
    }

    public void verEquipos() {
        if (!equipos.isEmpty()) {
            System.out.println("Equipo\t\tP\tPJ\tPG\tPE\tPP\tGF\tGC\tD");
            for (Equipo eq : equipos) {
                System.out.print(eq.toString());
            }
        } else {
            System.out.println("No hay equipos cargados.");
        }
    }

    public void verPartidos() {
        if (!partidos.isEmpty()) {
            System.out.println("Partidos jornada");
            for (Partido partido : partidos) {
                System.out.print(partido.toString());
            }
        } else {
            System.out.println("No hay partidos cargados.");
        }
    }

    public void cargaEquipos(String nombreFichero) {
        try {
            FileReader fichero = new FileReader(nombreFichero);
            BufferedReader entrada = new BufferedReader(fichero);
            Equipo nuevoEquipo;
            equipos.clear();
            String nombre = entrada.readLine(); // lectura de una línea del fichero
            while (nombre != null) {
                nuevoEquipo = new Equipo(nombre);
                equipos.add(nuevoEquipo);
                nombre = entrada.readLine();
            }
            entrada.close();
        } catch (IOException e) {
            System.out.println("Error en el formato del fichero: " + nombreFichero);
        }
    }

    public void cargaJornada(String nombreFichero) {
        try {
            FileReader fichero = new FileReader(nombreFichero);
            BufferedReader entrada = new BufferedReader(fichero);
            String eLocal, eVisitante;
            int gLocal, gVisitante;
            Partido nuevoPartido;
            String linea = entrada.readLine(); // lectura de una línea del fichero
            while (linea != null) {
                Scanner datos = new Scanner(linea).useDelimiter(";");
                eLocal = datos.next();
                gLocal = datos.nextInt();
                eVisitante = datos.next();
                gVisitante = datos.nextInt();
                nuevoPartido = new Partido(eLocal, gLocal, eVisitante, gVisitante);
                partidos.add(nuevoPartido);
                linea = entrada.readLine();
            }
            entrada.close();
        } catch (IOException e) {
            System.out.println("Error en el formato del fichero: " + nombreFichero);
        }
    }

    public boolean gestionarPartido(Partido miPartido) {
        Equipo eqLocal = new Equipo(miPartido.getNombreLocal());
        Equipo eqVisitante = new Equipo(miPartido.getNombreVisitante());
        int golesLocal, golesVisitante, posLocal, posVisitante;
        // comprueba que existan los equipos
        if (equipos.contains(eqLocal) && equipos.contains(eqVisitante)) {
            // Busca los índices de cada uno
            posLocal = equipos.indexOf(eqLocal);
            posVisitante = equipos.indexOf(eqVisitante);
            // 
            eqLocal = equipos.get(posLocal);
            eqVisitante = equipos.get(posVisitante);
            golesLocal = miPartido.getGolesLocal();
            golesVisitante = miPartido.getGolesVisitante();
            // Procesar goles
            eqLocal.setGolesAFavor(eqLocal.getGolesAFavor() + golesLocal);
            eqLocal.setGolesEnContra(eqLocal.getGolesEnContra() + golesVisitante);
            eqVisitante.setGolesAFavor(eqVisitante.getGolesAFavor() + golesVisitante);
            eqVisitante.setGolesEnContra(eqVisitante.getGolesEnContra() + golesLocal);
            // Procesar partidos
            if (miPartido.ganalocal()) {
                eqLocal.setPartidosGanados(eqLocal.getPartidosGanados() + 1);
                eqVisitante.setPartidosPerdidos(eqVisitante.getPartidosPerdidos() + 1);
            } else if (miPartido.ganaVisitante()) {
                eqLocal.setPartidosPerdidos(eqLocal.getPartidosPerdidos() + 1);
                eqVisitante.setPartidosGanados(eqVisitante.getPartidosGanados() + 1);
            } else {
                eqLocal.setPartidosEmpatados(eqLocal.getPartidosEmpatados() + 1);
                eqVisitante.setPartidosEmpatados(eqVisitante.getPartidosEmpatados() + 1);
            }
            return true;
        }
        return false;
    }

    public void procesaJornada() {
        if (!partidos.isEmpty() && !equipos.isEmpty()) {
            for (Partido p : partidos) {
                gestionarPartido(p);
            }
            partidos.clear();
            System.out.println("Jornada procesada.");
        } else {
            System.out.println("Error procesando jornada: No hay datos cargados.");
        }
    }
}
