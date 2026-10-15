package PilasYColas;

/**
 * Clase Nodo para estructuras lineales (Pilas y Colas).
 * Representa un elemento individual enlazado.
 *
 * @author eduardo-b
 */
public class Nodo {

    // Dato almacenado en el nodo
    public int info;

    // Apuntador/referencia hacia el siguiente nodo
    public Nodo liga;

    // Constructor que recibe el valor inicial
    public Nodo(int info) {
        this.info = info;
        this.liga = null;
    }
}
