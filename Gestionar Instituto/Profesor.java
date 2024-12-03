/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionarinstituto;

/**
 *
 * @author JRM
 */
public abstract class Profesor extends Personal {

    private String especialidad;
    private String puesto;

    public Profesor(String especialidad, String puesto, String nrp, String nombre, Fecha fechaIngreso, Fecha fechaNacimiento, int telefono) {
        super(nrp, nombre, fechaIngreso, fechaNacimiento, telefono);
        this.especialidad = especialidad;
        this.puesto = puesto;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    @Override
    public void verDatos() {
        super.verDatos();
        StringBuilder sb = new StringBuilder();
        sb.append("Especialidad: ").append(especialidad);
        sb.append(" Puesto: ").append(puesto);
        System.out.println(sb.toString());
    }

    @Override
    public double salario() {
        return 1800;
    }

    @Override
    public double complemento() {
        if (puesto.compareTo("Director") == 0) {
            return 800;
        }
        if (puesto.compareTo("Jefe de Estudios") == 0) {
            return 400;
        }
        if (puesto.compareTo("Vicedirector") == 0) {
            return 400;
        }
        if (puesto.compareTo("Secretario") == 0) {
            return 400;
        }
        if (puesto.compareTo("Jefe de Departamento") == 0) {
            return 50;
        }
        if (puesto.compareTo("Tutor") == 0) {
            return 25;
        } else {
            return 0;
        }
    }
}
