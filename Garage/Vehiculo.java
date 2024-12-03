/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package garajeficheros;

import java.io.Serializable;

/**
 *
 * @author JRM
 */
public class Vehiculo implements Serializable{

  private float potencia;
  private String matricula;

  public Vehiculo(float potencia, String matricula) {
    this.potencia = potencia;
    this.matricula = matricula;
  }

  public float getPotencia() {
    return this.potencia;
  }

  public void setPotencia(float potencia) {
    this.potencia = potencia;
  }

  public String getMatricula() {
    return matricula;
  }

  public void setMatricula(String matricula) {
    this.matricula = matricula;
  }

  public void verDatos(){
    System.out.print(this.matricula);
    System.out.print(" ("+this.potencia+" CV)");
  }
  
  
}

