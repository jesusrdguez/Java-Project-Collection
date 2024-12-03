/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionarinstituto;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author JRM
 */
public class Instituto implements Serializable{

    private String codigo;
    private String nombre;
    private String direccion;
    private String ciudad;
    private ArrayList<Personal> plantilla;

    public Instituto(String codigo, String nombre, String direccion, String ciudad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.direccion = direccion;
        this.ciudad = ciudad;
        plantilla = new ArrayList<>();
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public ArrayList<Personal> getPlantilla() {
        return plantilla;
    }

    public void setPlantilla(ArrayList<Personal> plantilla) {
        this.plantilla = plantilla;
    }

    public Personal buscarEmpleado(String nrp) {
        Personal p = new Laboral(null, null, nrp, null, null, null, 0);
        int indice = plantilla.indexOf(p);
        if (indice >= 0) {
            return plantilla.get(indice);
        }
        return null;
    }

    public Personal buscarEmpleado(int telefono) {
        for (Personal empleado : plantilla) {
            if (empleado.getTelefono() == telefono) {
                return empleado;
            }
        }
        return null;
    }

    public Personal director() {
        for (Personal empleado : plantilla) {
            if (empleado instanceof Profesor) {
                if (((Profesor) empleado).getPuesto().compareTo("Director") == 0) {
                    return empleado;
                }
            }
        }
        return null;
    }

    public void verDatos() {
        StringBuilder sb = new StringBuilder();
        sb.append(codigo);
        sb.append(" ").append(nombre);
        sb.append("\n").append(direccion);
        sb.append(" ").append(ciudad);
        System.out.println(sb.toString());
    }

    public boolean altaEmpleado(Personal p) {
        return this.plantilla.add(p);
    }

    public boolean bajaEmpleado(Personal p) {
        return this.plantilla.remove(p);
    }

    public void verLaborales() {
        System.out.println("Laborales:");
        for (Personal empleado : plantilla) {
            if (empleado instanceof Laboral) {
                empleado.verDatos();
            }
        }
    }

    public void verProfesores() {
        System.out.println("Profesores:");
        for (Personal empleado : plantilla) {
            if (empleado instanceof Profesor) {
                empleado.verDatos();
            }
        }
    }

    public void verPersonal() {
        System.out.println("Listado personal");
        // Usando un objeto Iterator
        Iterator<Personal> empleados = plantilla.iterator();
        while (empleados.hasNext()) {
            empleados.next().verDatos();
        }
    }

    public void guardarLaborales() {
        try {
            FileOutputStream fichero = new FileOutputStream("Laborales.dat");
            ObjectOutputStream salida = new ObjectOutputStream(fichero);
            for (Personal empleado : plantilla) {
                if (empleado instanceof Laboral) {
                    salida.writeObject(empleado);
                }
            }
            fichero.close();
        } catch (IOException e) {
            System.out.println("Error creando fichero Laborales.dat");
        }
    }

    public void guardarInterinos() {
        try {
            FileOutputStream fichero = new FileOutputStream("Interinos.dat");
            ObjectOutputStream salida = new ObjectOutputStream(fichero);
            for (Personal empleado : plantilla) {
                if (empleado instanceof ProfesorInterino) {
                    salida.writeObject(empleado);
                }
            }
            fichero.close();
        } catch (IOException e) {
            System.out.println("Error creando fichero Interinos.dat");
        }
    }

    public void guardarTitulares() {
        try {
            FileOutputStream fichero = new FileOutputStream("Titulares.dat");
            ObjectOutputStream salida = new ObjectOutputStream(fichero);
            for (Personal empleado : plantilla) {
                if (empleado instanceof ProfesorTitular) {
                    salida.writeObject(empleado);
                }
            }
            fichero.close();
        } catch (IOException e) {
            System.out.println("Error creando fichero Titulares.dat");
        }
    }

    public void leerTitulares() {
        try {
            FileInputStream fichero = new FileInputStream("Titulares.dat");
            ObjectInputStream entrada = new ObjectInputStream(fichero);
            Personal p = (Personal) entrada.readObject();
            while (p != null) {
                plantilla.add(p);
                p = (Personal) entrada.readObject();
            }
            fichero.close();
        } catch (EOFException e) {
            // Se ha llegado al final del fichero
            System.out.println("Cargados todos los objetos del fichero Titulares.dat");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error leyendo en el fichero: Titulares.dat");
        }
    }

    public void leerInterinos() {
        try {
            FileInputStream fichero = new FileInputStream("Interinos.dat");
            ObjectInputStream entrada = new ObjectInputStream(fichero);
            Personal p = (Personal) entrada.readObject();
            while (p != null) {
                plantilla.add(p);
                p = (Personal) entrada.readObject();
            }
            fichero.close();
        } catch (EOFException e) {
            // Se ha llegado al final del fichero
            System.out.println("Cargados todos los objetos del fichero Interinos.dat");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error leyendo en el fichero: Interinos.dat");
        }
    }

    public void leerLaborales() {
        try {
            FileInputStream fichero = new FileInputStream("Laborales.dat");
            ObjectInputStream entrada = new ObjectInputStream(fichero);
            Personal p = (Personal) entrada.readObject();
            while (p != null) {
                plantilla.add(p);
                p = (Personal) entrada.readObject();
            }
            fichero.close();
        } catch (EOFException e) {
            // Se ha llegado al final del fichero
            System.out.println("Cargados todos los objetos del fichero Laborales.dat");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error leyendo en el fichero: Laborales.dat");
        }
    }

    public void cargaEmpleados(String nombreFichero) {
        try {
            FileReader fichero = new FileReader(nombreFichero);
            BufferedReader entrada = new BufferedReader(fichero);
            Personal nuevoEmpleado;
            // Borra el ArrayList
            plantilla.clear();
            String linea = entrada.readLine(); // lectura de una linea del fichero
            while (linea != null) {
                // Creo el objeto Scanner separando los datos de cada atributo
                Scanner datos = new Scanner(linea).useDelimiter(";");
                String tipo = datos.next();
                String nrp = datos.next();
                String nombreEmp = datos.next();
                String f1 = datos.next();
                // Creo el objeto Scanner separando la fecha en dia, mes y año
                Scanner fecha1 = new Scanner(f1).useDelimiter("/");
                Fecha fechaN = new Fecha(fecha1.nextInt(), fecha1.nextInt(), fecha1.nextInt());
                String f2 = datos.next();
                // Creo el objeto Scanner separando la fecha en dia, mes y año
                Scanner fecha2 = new Scanner(f2).useDelimiter("/");
                Fecha fechaI = new Fecha(fecha2.nextInt(), fecha2.nextInt(), fecha2.nextInt());
                int telefono = datos.nextInt();
                String especialidad, puesto, categoria, turno;
                boolean destino, sustituto;

                switch (tipo.charAt(0)) {
                    case 'T':
                        especialidad = datos.next();
                        puesto = datos.next();
                        destino = datos.nextBoolean();
                        nuevoEmpleado = new ProfesorTitular(destino, especialidad, puesto, nrp, nombreEmp, fechaN, fechaI, telefono);
                        break;
                    case 'I':
                        especialidad = datos.next();
                        puesto = datos.next();
                        sustituto = datos.nextBoolean();
                        nuevoEmpleado = new ProfesorInterino(sustituto, especialidad, puesto, nrp, nombreEmp, fechaN, fechaI, telefono);
                        break;
                    case 'L':
                        categoria = datos.next();
                        turno = datos.next();
                        nuevoEmpleado = new Laboral(categoria, turno, nrp, nombreEmp, fechaI, fechaI, telefono);
                        break;
                    default:
                        nuevoEmpleado = null;
                }

                if (nuevoEmpleado != null) {
                    plantilla.add(nuevoEmpleado);
                }
                linea = entrada.readLine();
            }
            entrada.close();
        } catch (IOException e) {
            System.out.println("Error en el formato del fichero: " + nombreFichero);
        }
    }

    public Personal pedirDatosEmpleado() {
        Personal p = null;
        int op = Entrada.leerEntero(" 1.- Laboral\n 2.- Profesor Titular\n 3.- Profesor Interino");
        switch (op) {
            case 1:
                p = pedirDatosLaboral();
                break;
            case 2:
                p = pedirDatosTitular();
                break;
            case 3:
                p = pedirDatosInterino();
                break;
            default:
        }
        return p;
    }

    public Personal pedirDatosLaboral() {
        Personal p;
        String categoria = Entrada.leerCadena("Categoria (Administrativo/Ordenanza/Limpieza):");
        String turno = Entrada.leerCadena("Turno (Mañana/Tarde):");
        String nrp = Entrada.leerCadena("NRP:");
        String nombreEmp = Entrada.leerCadena("Nombre");
        System.out.println("Fecha de ingreso:");
        Fecha fechaIngreso = pedirFecha();
        System.out.println("Fecha de nacimiento:");
        Fecha fechaNacimiento = pedirFecha();
        int telefono = Entrada.leerEntero("Telefono: ");
        p = new Laboral(categoria, turno, nrp, nombreEmp, fechaIngreso, fechaNacimiento, telefono);
        return p;
    }

    public Personal pedirDatosTitular() {
        Personal p;
        boolean conDestino = Entrada.leerBoolean("¿Tiene destino en el centro?");
        String especialidad = Entrada.leerCadena("Especialidad:");
        String puesto = Entrada.leerCadena("Puesto:");
        String nrp = Entrada.leerCadena("NRP:");
        String nombreEmp = Entrada.leerCadena("Nombre");
        System.out.println("Fecha de ingreso:");
        Fecha fechaIngreso = pedirFecha();
        System.out.println("Fecha de nacimiento:");
        Fecha fechaNacimiento = pedirFecha();
        int telefono = Entrada.leerEntero("Telefono: ");
        p = new ProfesorTitular(conDestino, especialidad, puesto, nrp, nombreEmp, fechaIngreso, fechaNacimiento, telefono);
        return p;
    }

    public Personal pedirDatosInterino() {
        Personal p;
        boolean sustituto = Entrada.leerBoolean("¿Es sustito?");
        String especialidad = Entrada.leerCadena("Especialidad:");
        String puesto = Entrada.leerCadena("Puesto:");
        String nrp = Entrada.leerCadena("NRP:");
        String nombreEmp = Entrada.leerCadena("Nombre");
        System.out.println("Fecha de ingreso:");
        Fecha fechaIngreso = pedirFecha();
        System.out.println("Fecha de nacimiento:");
        Fecha fechaNacimiento = pedirFecha();
        int telefono = Entrada.leerEntero("Telefono: ");
        p = new ProfesorInterino(sustituto, especialidad, puesto, nrp, nombreEmp, fechaIngreso, fechaNacimiento, telefono);
        return p;
    }

    public Fecha pedirFecha() {
        Fecha nuevaFecha;
        int d, m, a;
        d = Entrada.leerEntero("Día:");
        m = Entrada.leerEntero("Mes:");
        a = Entrada.leerEntero("Año:");
        nuevaFecha = new Fecha(d, m, a);
        return nuevaFecha;
    }
}
