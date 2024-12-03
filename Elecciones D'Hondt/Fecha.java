/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicioelecciones;

import java.io.Serializable;
import java.util.Calendar;

/**
 *
 * @author JMRivera
 */
public class Fecha implements Comparable<Fecha>, Serializable {

    private int dia;
    private int mes;
    private int año;

    public Fecha(int dia, int mes, int año) {
        this.dia = dia;
        this.mes = mes;
        this.año = año;
    }

    public Fecha() {
        this.dia = 1;
        this.mes = 1;
        this.año = 2000;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (dia < 10) {
            sb.append('0');
        }
        sb.append(dia);
        sb.append('/');
        if (mes < 10) {
            sb.append('0');
        }
        sb.append(mes);
        sb.append('/');
        sb.append(año);

        return sb.toString();
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    /**
     * Método que comprueba si la fecha guardada en el objeto es válida
     *
     * @return true, si es válida y false, si no lo es
     */
    public boolean comprobarFecha() {
        boolean diaValido, mesValido, añoValido;
        añoValido = año > 0;
        mesValido = mes >= 1 && mes <= 12;
        switch (mes) {
            case 2:
                if (esBisiesto()) {
                    diaValido = dia >= 1 && dia <= 29;
                } else {
                    diaValido = dia >= 1 && dia <= 28;
                }
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                diaValido = dia >= 1 && dia <= 30;
                break;
            default:
                diaValido = dia >= 1 && dia <= 31;
        }
        return diaValido && mesValido && añoValido;
    }

    /**
     * Método que comprueba si el año guardado en el objeto es bisiesto
     *
     * @return true, si es bisiesto y false, si no lo es
     */
    private boolean esBisiesto() {
        return (año % 4 == 0 && año % 100 != 0 || año % 400 == 0);
    }

    /**
     * Método que cambia la fecha a la del día siguiente
     */
    public void diaSiguiente() {
        dia++;
        if (!comprobarFecha()) {
            dia = 1;
            mes++;
            if (!comprobarFecha()) {
                mes = 1;
                año++;
            }
        }
    }

    /**
     * Método que cambia la fecha a la de hoy
     */
    public void setHoy() {
        Calendar hoy = Calendar.getInstance();

        dia = hoy.get(Calendar.DAY_OF_MONTH);
        mes = hoy.get(Calendar.MONTH) + 1;
        año = hoy.get(Calendar.YEAR);
    }

    @Override
    public int compareTo(Fecha otraFecha) {
        int compara;
        // En esta comparación nos va a el devolver 1 en caso de que los
        // números sean más chicos, lo que significa que la fecha es anterior
        // y por lo tanto ha pasado más tiempo desde esa fecha hasta la actaul,
        // por eso se le considera mayor
        if (this.getAño() == otraFecha.getAño()) {
            if (this.getMes() == otraFecha.getMes()) {
                if (this.getDia() == otraFecha.getDia()) {
                    compara = 0;
                } else if (this.getDia() < this.getDia()) {
                    compara = 1;
                } else {
                    compara = -1;
                }
            } else if (this.getMes() < this.getMes()) {
                compara = 1;
            } else {
                compara = -1;
            }
        } else if (this.getAño() < otraFecha.getAño()) {
            compara = 1;
        } else {
            compara = -1;
        }
        return compara;
    }
    
    

}
