/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examenabril24;

/**
 *
 * @author JRM
 */
public class Lancha extends Barco {
    // Atributos de la clase

    private float potencia;

    // Constructor de la clase
    public Lancha(float potencia, String matricula, float eslora, float manga, boolean conPatron) {
        super(matricula, eslora, manga, conPatron);
        this.potencia = potencia;
    }

    // Getter de la clase
    public float getPotencia() {
        return potencia;
    }

    @Override
    public float precioAlquiler() {
        return this.potencia + this.getEslora() + (this.isConPatron() ? 50 : 0);
    }

    @Override
    public void verDatos() {
        System.out.print("\nLancha ");
        super.verDatos();
        System.out.println("Potencia " + potencia + " CV");
    }

    @Override
    public String atributosXML() {
        StringBuilder sb = new StringBuilder();
        sb.append((super.atributosXML()));
        sb.append("\t\t<potencia>").append(potencia).append("</potencia>\n");
        return sb.toString();  
    }
    

    @Override
    public String etiquetaXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t<lancha>\n");
        sb.append(atributosXML());
        sb.append("\t</lancha>\n");
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Lancha\n");
        sb.append(super.toString());
        sb.append(potencia).append("\n");
        return sb.toString();
    }
    
    
}
