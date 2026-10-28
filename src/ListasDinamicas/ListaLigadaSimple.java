package ListasDinamicas;

import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================================
 * CLASE: ListaLigadaSimple
 * ============================================================================
 * Implementacion de una Lista Enlazada Simple (unidireccional).
 *
 * Caracteristicas didacticas:
 * - Cada nodo posee un campo de informacion (info) y un unico enlace (liga).
 * - El enlace siempre apunta hacia el nodo sucesor.
 * - El ultimo nodo de la cadena apunta a null, marcando el final de la estructura.
 * - El acceso se realiza a traves del nodo inicial 'primerNodo' (referencia P).
 *
 * @author eduardo-b
 * ============================================================================
 */
public class ListaLigadaSimple extends ListaDinamica {

    /**
     * Clase interna que define cada nodo de la lista simple.
     */
    private static class Nodo {
        int info;
        Nodo liga;

        Nodo(int valorDato) {
            this.info = valorDato;
            this.liga = null;
        }
    }

    /**
     * Apuntador al primer elemento de la lista (habitualmente llamado P).
     */
    private Nodo primerNodo;

    /**
     * Constructor: crea una lista ligada simple vacia.
     */
    public ListaLigadaSimple() {
        this.primerNodo = null;
    }

    /**
     * Metodo privado para buscar y retornar el nodo que contiene el valor especificado.
     *
     * @param valorABuscar Valor entero de referencia.
     * @return El nodo encontrado, o null si no existe en la lista.
     */
    private Nodo buscarNodo(int valorABuscar) {
        Nodo nodoActual = this.primerNodo;
        while (nodoActual != null && nodoActual.info != valorABuscar) {
            nodoActual = nodoActual.liga;
        }
        return nodoActual;
    }

    /**
     * Inserta un nuevo elemento antes de un nodo de referencia.
     */
    @Override
    public boolean insertarAntes(int valorReferencia, int valorAInsertar) {
        // Si la lista esta vacia, no existe la referencia
        if (this.primerNodo == null) {
            return false;
        }

        // Si la referencia es el primer nodo, equivale a insertar al inicio
        if (this.primerNodo.info == valorReferencia) {
            insertarInicio(valorAInsertar);
            return true;
        }

        // Recorremos con dos apuntadores para conocer el nodo anterior
        Nodo nodoAnterior = this.primerNodo;
        Nodo nodoActual = this.primerNodo.liga;

        while (nodoActual != null && nodoActual.info != valorReferencia) {
            nodoAnterior = nodoActual;
            nodoActual = nodoActual.liga;
        }

        // Si se llego al final sin encontrar la referencia
        if (nodoActual == null) {
            return false;
        }

        // Crear el nuevo nodo y enlazarlo entre nodoAnterior y nodoActual
        Nodo nuevoNodo = new Nodo(valorAInsertar);
        nuevoNodo.liga = nodoActual;
        nodoAnterior.liga = nuevoNodo;
        return true;
    }

    /**
     * Inserta un nuevo elemento despues de un nodo de referencia.
     */
    @Override
    public boolean insertarDespues(int valorReferencia, int valorAInsertar) {
        Nodo nodoEncontrado = buscarNodo(valorReferencia);
        if (nodoEncontrado == null) {
            return false;
        }

        // Crear nuevo nodo y enlazarlo despues de nodoEncontrado
        Nodo nuevoNodo = new Nodo(valorAInsertar);
        nuevoNodo.liga = nodoEncontrado.liga;
        nodoEncontrado.liga = nuevoNodo;
        return true;
    }

    /**
     * Elimina el nodo que se encuentra antes del nodo de referencia.
     */
    @Override
    public Integer eliminarAntes(int valorReferencia) {
        // Si esta vacia o la referencia es el primer elemento, no hay nada antes
        if (this.primerNodo == null || this.primerNodo.info == valorReferencia) {
            return null;
        }

        Nodo nodoPredecesorDelEliminado = null;
        Nodo nodoAEliminar = this.primerNodo;
        Nodo nodoActual = this.primerNodo.liga;

        while (nodoActual != null && nodoActual.info != valorReferencia) {
            nodoPredecesorDelEliminado = nodoAEliminar;
            nodoAEliminar = nodoActual;
            nodoActual = nodoActual.liga;
        }

        // Si no se localizo la referencia en la lista
        if (nodoActual == null) {
            return null;
        }

        // Si el nodo a eliminar era el primero de la lista
        if (nodoPredecesorDelEliminado == null) {
            this.primerNodo = nodoActual;
        } else {
            nodoPredecesorDelEliminado.liga = nodoActual;
        }

        return nodoAEliminar.info;
    }

    /**
     * Elimina el nodo ubicado inmediatamente despues de la referencia.
     */
    @Override
    public Integer eliminarDespues(int valorReferencia) {
        Nodo nodoReferencia = buscarNodo(valorReferencia);
        // Si no existe la referencia o no tiene sucesor
        if (nodoReferencia == null || nodoReferencia.liga == null) {
            return null;
        }

        Nodo nodoAEliminar = nodoReferencia.liga;
        nodoReferencia.liga = nodoAEliminar.liga;
        return nodoAEliminar.info;
    }

    /**
     * Inserta un nuevo elemento al principio de la lista.
     */
    @Override
    public void insertarInicio(int valorAInsertar) {
        Nodo nuevoNodo = new Nodo(valorAInsertar);
        nuevoNodo.liga = this.primerNodo;
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
            return;
        }

        Nodo nodoUltimo = this.primerNodo;
        while (nodoUltimo.liga != null) {
            nodoUltimo = nodoUltimo.liga;
        }
        nodoUltimo.liga = nuevoNodo;
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
        this.primerNodo = this.primerNodo.liga;
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

        // Si unicamente hay un nodo en la lista
        if (this.primerNodo.liga == null) {
            int valorEliminado = this.primerNodo.info;
            this.primerNodo = null;
            return valorEliminado;
        }

        Nodo nodoActual = this.primerNodo;
        Nodo nodoAnterior = null;

        while (nodoActual.liga != null) {
            nodoAnterior = nodoActual;
            nodoActual = nodoActual.liga;
        }

        // Desconectar el ultimo nodo
        nodoAnterior.liga = null;
        return nodoActual.info;
    }

    /**
     * Elimina la primera coincidencia del valor solicitado.
     */
    @Override
    public boolean eliminarValor(int valorAEliminar) {
        if (this.primerNodo == null) {
            return false;
        }

        // Si el valor a eliminar esta en el primer nodo
        if (this.primerNodo.info == valorAEliminar) {
            this.primerNodo = this.primerNodo.liga;
            return true;
        }

        Nodo nodoAnterior = this.primerNodo;
        Nodo nodoActual = this.primerNodo.liga;

        while (nodoActual != null) {
            if (nodoActual.info == valorAEliminar) {
                nodoAnterior.liga = nodoActual.liga;
                return true;
            }
            nodoAnterior = nodoActual;
            nodoActual = nodoActual.liga;
        }

        return false;
    }

    /**
     * Comprueba si un valor se encuentra en la lista.
     */
    @Override
    public boolean buscar(int valorABuscar) {
        Nodo nodoActual = this.primerNodo;
        while (nodoActual != null) {
            if (nodoActual.info == valorABuscar) {
                return true;
            }
            nodoActual = nodoActual.liga;
        }
        return false;
    }

    /**
     * Comprueba si la lista no contiene elementos.
     */
    @Override
    public boolean estaVacia() {
        return this.primerNodo == null;
    }

    /**
     * Convierte y retorna todos los valores enlazados en una lista secuencial.
     */
    @Override
    public List<Integer> aLista() {
        List<Integer> listaValores = new ArrayList<>();
        Nodo nodoActual = this.primerNodo;
        while (nodoActual != null) {
            listaValores.add(nodoActual.info);
            nodoActual = nodoActual.liga;
        }
        return listaValores;
    }

    @Override
    public String descripcion() {
        return "Lista ligada simple: cada nodo apunta solo al siguiente; el ultimo apunta a null.";
    }
}