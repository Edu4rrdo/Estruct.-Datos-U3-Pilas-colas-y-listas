package ListasDinamicas;

import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================================
 * CLASE: ListaDoblementeLigada
 * ============================================================================
 * Implementacion de una Lista Enlazada Doble (bidireccional).
 *
 * Caracteristicas didacticas:
 * - Cada nodo posee dos apuntadores:
 *     1. izq: Referencia hacia el nodo anterior.
 *     2. der: Referencia hacia el nodo siguiente.
 * - Permite recorrer la lista tanto hacia adelante como hacia atras.
 * - Los extremos apuntan a null:
 *     - El enlace izquierdo del primer nodo es null.
 *     - El enlace derecho del ultimo nodo es null.
 *
 * @author eduardo-b
 * ============================================================================
 */
public class ListaDoblementeLigada extends ListaDinamica {

    /**
     * Clase interna que define cada nodo de la lista doblemente ligada.
     */
    private static class Nodo {
        int info;
        Nodo izq; // Apuntador al nodo anterior
        Nodo der; // Apuntador al nodo siguiente

        Nodo(int valorDato) {
            this.info = valorDato;
            this.izq = null;
            this.der = null;
        }
    }

    /**
     * Apuntador al primer elemento de la lista.
     */
    private Nodo primerNodo;

    /**
     * Apuntador al ultimo elemento de la lista.
     */
    private Nodo ultimoNodo;

    /**
     * Constructor: inicializa la lista doblemente ligada vacia.
     */
    public ListaDoblementeLigada() {
        this.primerNodo = null;
        this.ultimoNodo = null;
    }

    /**
     * Busca y retorna el nodo que almacena el valor solicitado.
     *
     * @param valorABuscar Valor entero de referencia.
     * @return El nodo encontrado, o null si no se localiza.
     */
    private Nodo buscarNodo(int valorABuscar) {
        Nodo nodoActual = this.primerNodo;
        while (nodoActual != null && nodoActual.info != valorABuscar) {
            nodoActual = nodoActual.der;
        }
        return nodoActual;
    }

    /**
     * Inserta un nuevo elemento antes de un nodo de referencia.
     */
    @Override
    public boolean insertarAntes(int valorReferencia, int valorAInsertar) {
        Nodo nodoReferencia = buscarNodo(valorReferencia);
        if (nodoReferencia == null) {
            return false;
        }

        // Si la referencia es el primer elemento
        if (nodoReferencia == this.primerNodo) {
            insertarInicio(valorAInsertar);
            return true;
        }

        // Crear el nuevo nodo y ajustar sus ligas dobles
        Nodo nuevoNodo = new Nodo(valorAInsertar);
        nuevoNodo.izq = nodoReferencia.izq;
        nuevoNodo.der = nodoReferencia;
        nodoReferencia.izq.der = nuevoNodo;
        nodoReferencia.izq = nuevoNodo;
        return true;
    }

    /**
     * Inserta un nuevo elemento despues de un nodo de referencia.
     */
    @Override
    public boolean insertarDespues(int valorReferencia, int valorAInsertar) {
        Nodo nodoReferencia = buscarNodo(valorReferencia);
        if (nodoReferencia == null) {
            return false;
        }

        // Si la referencia es el ultimo elemento
        if (nodoReferencia == this.ultimoNodo) {
            insertarFinal(valorAInsertar);
            return true;
        }

        // Crear el nuevo nodo y ajustar sus ligas dobles
        Nodo nuevoNodo = new Nodo(valorAInsertar);
        nuevoNodo.izq = nodoReferencia;
        nuevoNodo.der = nodoReferencia.der;
        nodoReferencia.der.izq = nuevoNodo;
        nodoReferencia.der = nuevoNodo;
        return true;
    }

    /**
     * Elimina el nodo que se encuentra antes del nodo de referencia.
     */
    @Override
    public Integer eliminarAntes(int valorReferencia) {
        Nodo nodoReferencia = buscarNodo(valorReferencia);
        // Si no existe la referencia o es el primer nodo (no tiene nada antes)
        if (nodoReferencia == null || nodoReferencia == this.primerNodo) {
            return null;
        }

        Nodo nodoAEliminar = nodoReferencia.izq;
        int valorEliminado = nodoAEliminar.info;

        if (nodoAEliminar == this.primerNodo) {
            eliminarInicio();
        } else {
            nodoAEliminar.izq.der = nodoReferencia;
            nodoReferencia.izq = nodoAEliminar.izq;
        }

        return valorEliminado;
    }

    /**
     * Elimina el nodo que se encuentra despues del nodo de referencia.
     */
    @Override
    public Integer eliminarDespues(int valorReferencia) {
        Nodo nodoReferencia = buscarNodo(valorReferencia);
        // Si no existe la referencia o es el ultimo nodo (no tiene nada despues)
        if (nodoReferencia == null || nodoReferencia == this.ultimoNodo) {
            return null;
        }

        Nodo nodoAEliminar = nodoReferencia.der;
        int valorEliminado = nodoAEliminar.info;

        if (nodoAEliminar == this.ultimoNodo) {
            eliminarFinal();
        } else {
            nodoReferencia.der = nodoAEliminar.der;
            nodoAEliminar.der.izq = nodoReferencia;
        }

        return valorEliminado;
    }

    /**
     * Inserta un nuevo elemento al inicio de la lista.
     */
    @Override
    public void insertarInicio(int valorAInsertar) {
        Nodo nuevoNodo = new Nodo(valorAInsertar);
        if (this.primerNodo == null) {
            this.primerNodo = nuevoNodo;
            this.ultimoNodo = nuevoNodo;
            return;
        }

        nuevoNodo.der = this.primerNodo;
        this.primerNodo.izq = nuevoNodo;
        this.primerNodo = nuevoNodo;
    }

    /**
     * Inserta un nuevo elemento al final de la lista.
     */
    @Override
    public void insertarFinal(int valorAInsertar) {
        Nodo nuevoNodo = new Nodo(valorAInsertar);
        if (this.primerNodo == null) {
            this.primerNodo = nuevoNodo;
            this.ultimoNodo = nuevoNodo;
            return;
        }

        nuevoNodo.izq = this.ultimoNodo;
        this.ultimoNodo.der = nuevoNodo;
        this.ultimoNodo = nuevoNodo;
    }

    /**
     * Elimina el primer elemento de la lista.
     */
    @Override
    public Integer eliminarInicio() {
        if (this.primerNodo == null) {
            return null;
        }

        int valorEliminado = this.primerNodo.info;
        if (this.primerNodo == this.ultimoNodo) {
            this.primerNodo = null;
            this.ultimoNodo = null;
        } else {
            this.primerNodo = this.primerNodo.der;
            this.primerNodo.izq = null;
        }

        return valorEliminado;
    }

    /**
     * Elimina el ultimo elemento de la lista.
     */
    @Override
    public Integer eliminarFinal() {
        if (this.primerNodo == null) {
            return null;
        }

        int valorEliminado = this.ultimoNodo.info;
        if (this.primerNodo == this.ultimoNodo) {
            this.primerNodo = null;
            this.ultimoNodo = null;
        } else {
            this.ultimoNodo = this.ultimoNodo.izq;
            this.ultimoNodo.der = null;
        }

        return valorEliminado;
    }

    /**
     * Busca y elimina la primera coincidencia del valor solicitado.
     */
    @Override
    public boolean eliminarValor(int valorAEliminar) {
        Nodo nodoActual = this.primerNodo;
        while (nodoActual != null && nodoActual.info != valorAEliminar) {
            nodoActual = nodoActual.der;
        }

        if (nodoActual == null) {
            return false;
        }

        if (nodoActual == this.primerNodo) {
            eliminarInicio();
        } else if (nodoActual == this.ultimoNodo) {
            eliminarFinal();
        } else {
            nodoActual.izq.der = nodoActual.der;
            nodoActual.der.izq = nodoActual.izq;
        }

        return true;
    }

    /**
     * Comprueba si un valor entero existe dentro de la lista.
     */
    @Override
    public boolean buscar(int valorABuscar) {
        Nodo nodoActual = this.primerNodo;
        while (nodoActual != null) {
            if (nodoActual.info == valorABuscar) {
                return true;
            }
            nodoActual = nodoActual.der;
        }
        return false;
    }

    /**
     * Comprueba si la lista no contiene ningun nodo.
     */
    @Override
    public boolean estaVacia() {
        return this.primerNodo == null;
    }

    /**
     * Recopila todos los elementos desde el primero hasta el ultimo en una lista de Java.
     */
    @Override
    public List<Integer> aLista() {
        List<Integer> listaValores = new ArrayList<>();
        Nodo nodoActual = this.primerNodo;
        while (nodoActual != null) {
            listaValores.add(nodoActual.info);
            nodoActual = nodoActual.der;
        }
        return listaValores;
    }

    @Override
    public String descripcion() {
        return "Lista doblemente ligada: cada nodo apunta al anterior y al siguiente; los extremos apuntan a null.";
    }
}