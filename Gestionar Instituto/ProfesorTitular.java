/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionarinstituto;

/**
 *
 * @author JRM
 */
public class ProfesorTitular extends Profesor {

    private boolean conDestino;

    public ProfesorTitular(boolean conDestino, String especialidad, String puesto, String nrp, String nombre, Fecha fechaIngreso, Fecha fechaNacimiento, int telefono) {
        super(especialidad, puesto, nrp, nombre, fechaIngreso, fechaNacimiento, telefono);
        this.conDestino = conDestino;
    }

    public boolean isConDestino() {
        return conDestino;
    }

    public void setConDestino(boolean conDestino) {
        this.conDestino = conDestino;
    }

    @Override
    public double complemento() {
        Fecha hoy = new Fecha();
        hoy.setHoy();
        double totalComplemento = this.getFechaIngreso().trienios(hoy);
        totalComplemento += (conDestino ? 300 : 0);
        totalComplemento += super.complemento();
        return totalComplemento;
    }

    @Override
    public double salario() {
        return super.salario() + 300;
    }

    @Override
    public void verDatos() {
        System.out.print("\nProfesor titular " + (conDestino ? "con destino " : " "));
        super.verDatos();
    }

}
