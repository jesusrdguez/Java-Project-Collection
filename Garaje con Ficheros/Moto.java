/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package garajeficheros;

/**
 *
 * @author JRM
 */
public class Moto extends Vehiculo{

  public Moto(float potencia, String matricula) {
    super(potencia, matricula);
  }

    @Override
    public void verDatos() {
        System.out.print("\nMoto: ");
        super.verDatos(); 
    }


}
