/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examenabril24;

/**
 *
 * @author JRM
 */
public class Velero extends Barco {

    // Atributos de la Clase
    private int numeroMastiles;
    private float superficieVela;

    // Constructor
    public Velero(int numeroMastiles, float superficieVela, String matricula, float eslora, float manga, boolean conPatron) {
        super(matricula, eslora, manga, conPatron);
        this.numeroMastiles = numeroMastiles;
        this.superficieVela = superficieVela;
    }

    // Getters de la clase
    public int getNumeroMastiles() {
        return numeroMastiles;
    }

    public float getSuperficieVela() {
        return superficieVela;
    }

    @Override
    public float precioAlquiler() {
        return this.getEslora() * numeroMastiles + superficieVela + (this.isConPatron() ? 120 : 0);
    }

    @Override
    public void verDatos() {
        System.out.print("\nVelero ");
        super.verDatos();
        System.out.println(numeroMastiles + " mástiles y " + superficieVela + " mts2 de vela");
    }
    
    @Override
    public String atributosXML() {
        StringBuilder sb = new StringBuilder();
        sb.append((super.atributosXML()));
        sb.append("\t\t<numeromastiles>").append(numeroMastiles).append("</numeromastiles>\n");
        sb.append("\t\t<superficievela>").append(superficieVela).append("</superficievela>\n");
        return sb.toString();
    }

    @Override
    public String etiquetaXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t<velero>\n");
        sb.append(atributosXML());
        sb.append("\t</velero>\n");
        return sb.toString();
    }
    
        @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Velero\n");
        sb.append(super.toString());
        sb.append(numeroMastiles).append("\n");
        sb.append(superficieVela).append("\n");
        return sb.toString();
    }
}
