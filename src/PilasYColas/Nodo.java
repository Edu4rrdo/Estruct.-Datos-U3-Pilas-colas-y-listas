package PilasYColas;

/**
 * ============================================================================
 * CLASE: Nodo
 * ============================================================================
 * Representa el elemento fundamental (nodo) de una estructura lineal enlazada,
 * como una Pila o una Cola Dinamica.
 *
 * Cada nodo consta de dos partes principales:
 * 1. info: El valor o dato entero almacenado.
 * 2. liga: La referencia o apuntador que indica cual es el siguiente nodo en memoria.
 *
 * @author eduardo-b
 * ============================================================================
 */
public class Nodo {

    /**
     * Dato o informacion entera almacenada dentro de este nodo.
     */
    public int info;

    /**
     * Enlace, referencia o apuntador que conecta con el siguiente nodo de la estructura.
     * Si es el ultimo nodo o no apunta a nadie, su valor sera null.
     */
    public Nodo liga;

    /**
     * Constructor para instanciar un nuevo nodo con un valor inicial.
     * Al crearse, su enlace (liga) inicia apuntando a null por seguridad.
     *
     * @param valorDatoInicial Valor entero que guardara este nodo.
     */
    public Nodo(int valorDatoInicial) {
        this.info = valorDatoInicial;
        this.liga = null;
    }
}
