/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package garajeficheros;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


/**
 *
 * @author JRM
 */
public class Garaje implements Serializable{
    
  private Vehiculo[] vehiculos;
  private int numPlazas;
  private int plazaLibre;

  public Garaje(int numPlazas) {
    this.numPlazas = numPlazas;
    this.vehiculos = new Vehiculo[numPlazas];
    this.plazaLibre = 0;
  }

  public int getPlazaLibre() {
     return plazaLibre;
  }
  
  public boolean hayPlazas(){
    return plazaLibre < numPlazas;
  }
  
  public boolean añadirVehiculo(Vehiculo nuevoVehiculo){
    boolean estado = false;

    if(hayPlazas() && nuevoVehiculo != null){
        vehiculos[plazaLibre] = nuevoVehiculo;
        plazaLibre++;
        estado = true;
    }
    return estado;
  }

  public Vehiculo pedirDatos(){
    Vehiculo miVehiculo = null;

    char tipoVehiculo = Entrada.leerCaracter("Tipo de vehículo (coche/moto): ");
    if(tipoVehiculo == 'm' || tipoVehiculo == 'c'){
      String matricula = Entrada.leerCadena("Matrícula: ");
      float potencia = Entrada.leerFloat("Potencia: ");
      switch(tipoVehiculo){
        case 'c':
            int plazas = Entrada.leerEntero("Número de plazas: ");
            miVehiculo = new Coche(potencia, matricula, plazas);
            break;
        case 'm':
            miVehiculo = new Moto(potencia, matricula);
            break;
        default:
            // no hacer nada
      }
    }
    return miVehiculo;
  }
  
  public Vehiculo devuelveVehiculo(int posicion){
      if(posicion < plazaLibre)
          return vehiculos[posicion];
      else
          return null;
  }

  public double cuotaMes(int posicion){
    Vehiculo miVehiculo = devuelveVehiculo(posicion);
    if (posicion >= 0 && posicion < plazaLibre){
        if(miVehiculo instanceof Coche){
            return miVehiculo.getPotencia() * ((Coche) miVehiculo).getNumPlazas() ;
        }else if(miVehiculo instanceof Moto) {
            return miVehiculo.getPotencia()*2;
        }
    }
    return 0.0;
  }
  
  public void guardarDatosFichero(){
    FileOutputStream ficheroCoches = null;
    FileOutputStream ficheroMotos = null;
    ObjectOutputStream salidaCoches = null;
    ObjectOutputStream salidaMotos = null;
    try{
        ficheroCoches = new FileOutputStream("Coches.dat");
        ficheroMotos = new FileOutputStream("Motos.dat");
        salidaCoches = new ObjectOutputStream(ficheroCoches);
        salidaMotos = new ObjectOutputStream(ficheroMotos);
        for (int i = 0; i < this.plazaLibre; i++){
          if(vehiculos[i] instanceof Coche)
            salidaCoches.writeObject(this.vehiculos[i]);
          if(vehiculos[i] instanceof Moto)
            salidaMotos.writeObject(this.vehiculos[i]);
        }
    }catch(Exception e){
        System.out.println(e.getMessage());
    }finally{
      try{
        ficheroCoches.close();
        ficheroMotos.close();
        salidaCoches.close();
        salidaMotos.close();
      }catch(IOException e){}
    }
  }

  public void leerCochesFichero(){
    FileInputStream fis = null;
    ObjectInputStream entrada = null;
    Coche miVehiculo;
    try{
        fis = new FileInputStream("Coches.dat");
        entrada = new ObjectInputStream(fis);
        miVehiculo = (Coche) entrada.readObject();
        while(miVehiculo != null){
            añadirVehiculo(miVehiculo);
            miVehiculo = (Coche) entrada.readObject();
        }
    }catch(FileNotFoundException  e){
        System.out.println(e.getMessage());
    }catch(IOException | ClassNotFoundException   e){
      //
    }
    finally{
        try{
            if (fis != null)
                fis.close();
            if (entrada != null)
                entrada.close();
        }catch(IOException  e){
        }
    }
  }

  public void leerMotosFichero(){
    FileInputStream fis = null;
    ObjectInputStream entrada = null;
    Moto miVehiculo;
    try{
        fis = new FileInputStream("Motos.dat");
        entrada = new ObjectInputStream(fis);
        miVehiculo = (Moto) entrada.readObject();
        while(miVehiculo != null){
            añadirVehiculo(miVehiculo);
            miVehiculo = (Moto) entrada.readObject();
        }
    }catch(FileNotFoundException  e){
        System.out.println(e.getMessage());
    }catch(IOException | ClassNotFoundException   e){
      //
    }
    finally{
        try{
            if (fis != null)
                fis.close();
            if (entrada != null)
                entrada.close();
        }catch(IOException  e){
        }
    }
  }
 
}
