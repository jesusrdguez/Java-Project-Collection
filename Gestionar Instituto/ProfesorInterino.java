/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionarinstituto;

/**
 *
 * @author JRM
 */
public class ProfesorInterino extends Profesor{
    private boolean sustituto;

    public ProfesorInterino(boolean sustituto, String especialidad, String puesto, String nrp, String nombre, Fecha fechaIngreso, Fecha fechaNacimiento, int telefono) {
        super(especialidad, puesto, nrp, nombre, fechaIngreso, fechaNacimiento, telefono);
        this.sustituto = sustituto;
    }

    public boolean isSustituto() {
        return sustituto;
    }

    public void setSustituto(boolean sustituto) {
        this.sustituto = sustituto;
    }

    @Override
    public void verDatos() {
        System.out.print("\nProfesor interino " + (sustituto ? "sustituto " : " "));
        super.verDatos();
    }

}
