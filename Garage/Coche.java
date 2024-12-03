/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package garajeficheros;

import garajeficheros.Vehiculo;

/**
 *
 * @author JRM
 */
public class Coche extends Vehiculo{
    
    private int numPlazas;

    public Coche(float potencia, String matricula, int numPlazas) {
        super(potencia, matricula);
        this.numPlazas = numPlazas;
    }

    public int getNumPlazas() {
        return numPlazas;
    }

    public void setNumPlazas(int numPlazas) {
        this.numPlazas = numPlazas;
    }

    @Override
    public void verDatos() {
        System.out.print("\nCoche: ");
        super.verDatos();
        System.out.print(" "+this.numPlazas+" plazas");
    }

    

}
