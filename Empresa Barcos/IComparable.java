/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package examenabril24;

/**
 *
 * @author JRM
 */
public interface IComparable {

    /**
     *
     * @param otroObjeto objeto con el que se compara
     * @param opcion criterio de comparación
     * @return Devolverá true cuando el objeto que llama al método es menor que
     * el pasado por parámetro y false, en otro caso.
     */
    boolean menorQue(IComparable otroObjeto, int opcion);

    /**
     *
     * @param otroObjeto objeto con el que se compara
     * @param opcion criterio de comparación
     * @return Devolverá true cuando el objeto que llama al método es mayor que
     * el pasado por parámetro y false, en otro caso.
     */
    boolean mayorQue(IComparable otroObjeto, int opcion);

    /**
     *
     * @param otroObjeto objeto con el que se compara
     * @param opcion criterio de comparación
     * @return Devolverá true cuando el objeto que llama al método es igual que
     * el pasado por parámetro y false, en otro caso.
     */
    boolean igualQue(IComparable otroObjeto, int opcion);
}
