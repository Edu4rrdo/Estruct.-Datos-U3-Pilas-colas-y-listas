package PilasYColas;

import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================================
 * CLASE: Pila (Pila Dinamica Enlazada)
 * ============================================================================
 * Estructura de datos lineal que opera bajo el principio LIFO:
 * "Last In, First Out" (El ultimo en entrar es el primero en salir).
 *
 * Caracteristicas principales:
 * - Solo existe un punto de acceso denominado "TOPE" o "CIMA".
 * - La operacion de insercion se conoce como PUSH (entra por el tope).
 * - La operacion de eliminacion se conoce como POP (sale por el tope).
 * - En memoria se implementa mediante nodos enlazados donde cada nuevo nodo
 *   apunta al nodo que anteriormente era el tope.
 *
 * @author eduardo-b
 * ============================================================================
 */
public class Pila extends EstructuraLineal {

    /**
     * Constructor: crea una pila dinamica vacia.
     */
    public Pila() {
        super();
    }

    /**
     * Operacion PUSH (Apilar / Insertar):
     * Crea un nuevo nodo con el valor proporcionado y lo coloca en la cima de la pila.
     * El nuevo nodo apuntara hacia donde antes apuntaba el tope (P), y luego el tope (P)
     * se actualiza para senalar a este nuevo nodo.
     *
     * @param valorAInsertar Numero entero a apilar.
     */
    @Override
    public void insertar(int valorAInsertar) {
        // 1. Crear el nuevo nodo con la informacion recibida
        Nodo nuevoNodo = new Nodo(valorAInsertar);

        // 2. Conectar la liga del nuevo nodo al actual nodo del tope (P)
        nuevoNodo.liga = this.P;

        // 3. Mover el apuntador del tope (P) para que ahora sea el nuevo nodo
        this.P = nuevoNodo;
    }

    /**
     * Operacion POP (Desapilar / Eliminar):
     * Extrae y devuelve el elemento ubicado en el tope de la pila.
     * Desplaza el apuntador del tope hacia el siguiente nodo de la cadena.
     *
     * @return El valor entero que estaba en el tope, o null si la pila esta vacia.
     */
    @Override
    public Integer eliminar() {
        // Si la pila no contiene elementos, no es posible eliminar nada
        if (estaVacia()) {
            return null;
        }

        // 1. Guardar el valor del nodo que actualmente esta en el tope
        int valorEliminado = this.P.info;

        // 2. Desplazar el tope hacia el siguiente nodo enlazado
        this.P = this.P.liga;

        // 3. Retornar el valor del elemento extraido
        return valorEliminado;
    }

    /**
     * Recorre todos los nodos de la pila desde el TOPE hacia la BASE
     * y los reune en una lista para facilitar su dibujo en pantalla.
     *
     * @return Lista con los valores ordenados desde el tope hasta la base.
     */
    @Override
    public List<Integer> aLista() {
        List<Integer> listaElementos = new ArrayList<>();
        Nodo nodoActual = this.P;

        // Recorrer nodo por nodo hasta llegar a null (la base de la pila)
        while (nodoActual != null) {
            listaElementos.add(nodoActual.info);
            nodoActual = nodoActual.liga;
        }

        return listaElementos;
    }

    /**
     * Metodo auxiliar para insertar al inicio (equivalente a PUSH).
     *
     * @param valorAInsertar Valor a colocar en el tope.
     */
    public void insertarInicio(int valorAInsertar) {
        insertar(valorAInsertar);
    }

    /**
     * Metodo auxiliar para eliminar al inicio (equivalente a POP).
     *
     * @return El valor eliminado del tope.
     */
    public Integer eliminarInicio() {
        return eliminar();
    }

    @Override
    public String getTitulo() {
        return "Pila Dinamica (LIFO)";
    }

    @Override
    public String getTextoInsertar() {
        return "Insertar (Push)";
    }

    @Override
    public String getTextoEliminar() {
        return "Eliminar (Pop)";
    }

    @Override
    public String getIndicadorArriba() {
        return "▼ TOPE (Ultimo en entrar, primero en salir) ▼";
    }

    @Override
    public String getIndicadorAbajo() {
        return "▲ BASE DE LA PILA ▲";
    }
}
