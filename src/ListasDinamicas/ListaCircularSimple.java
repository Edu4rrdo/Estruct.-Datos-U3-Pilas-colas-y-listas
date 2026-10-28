package ListasDinamicas;

import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================================
 * CLASE: ListaCircularSimple
 * ============================================================================
 * Implementacion de una Lista Circular Simple.
 *
 * Caracteristicas didacticas:
 * - Cada nodo posee un solo enlace (liga) dirigido al nodo sucesor.
 * - A diferencia de la lista lineal, el ultimo nodo NO apunta a null, sino
 *   que su enlace apunta de regreso al primer nodo de la lista (formando un ciclo).
 * - No existen enlaces con valor null; la lista es un anillo cerrado.
 * - Se mantienen referencias tanto a 'primerNodo' como a 'ultimoNodo' para
 *   operaciones eficientes en ambos extremos.
 *
 * @author eduardo-b
 * ============================================================================
 */
public class ListaCircularSimple extends ListaDinamica {

    /**
     * Clase interna que define cada nodo de la lista circular simple.
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
     * Apuntador al primer elemento del anillo enlazado.
     */
    private Nodo primerNodo;

    /**
     * Apuntador al ultimo elemento del anillo (su liga siempre apunta a primerNodo).
     */
    private Nodo ultimoNodo;

    /**
     * Constructor: inicializa la lista circular simple vacia.
     */
    public ListaCircularSimple() {
        this.primerNodo = null;
        this.ultimoNodo = null;
    }

    /**
     * Busca en el ciclo el nodo que contiene el valor especificado.
     *
     * @param valorABuscar Valor entero de referencia.
     * @return El nodo encontrado, o null si no se encuentra.
     */
    private Nodo buscarNodo(int valorABuscar) {
        if (this.primerNodo == null) {
            return null;
        }

        Nodo nodoActual = this.primerNodo;
        do {
            if (nodoActual.info == valorABuscar) {
                return nodoActual;
            }
            nodoActual = nodoActual.liga;
        } while (nodoActual != this.primerNodo);

        return null;
    }

    /**
     * Inserta un nuevo elemento antes de un nodo de referencia.
     */
    @Override
    public boolean insertarAntes(int valorReferencia, int valorAInsertar) {
        if (this.primerNodo == null) {
            return false;
        }

        Nodo nodoAnterior = this.ultimoNodo;
        Nodo nodoActual = this.primerNodo;

        do {
            if (nodoActual.info == valorReferencia) {
                Nodo nuevoNodo = new Nodo(valorAInsertar);
                nuevoNodo.liga = nodoActual;
                nodoAnterior.liga = nuevoNodo;

                // Si se inserto antes del primer nodo, el nuevo pasa a ser la cabeza
                if (nodoActual == this.primerNodo) {
                    this.primerNodo = nuevoNodo;
                }
                return true;
            }
            nodoAnterior = nodoActual;
            nodoActual = nodoActual.liga;
        } while (nodoActual != this.primerNodo);

        return false;
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

        Nodo nuevoNodo = new Nodo(valorAInsertar);
        nuevoNodo.liga = nodoReferencia.liga;
        nodoReferencia.liga = nuevoNodo;

        // Si se inserto despues del ultimo nodo, el nuevo pasa a ser el ultimo
        if (nodoReferencia == this.ultimoNodo) {
            this.ultimoNodo = nuevoNodo;
        }
        return true;
    }

    /**
     * Elimina el nodo que se encuentra antes del nodo de referencia.
     */
    @Override
    public Integer eliminarAntes(int valorReferencia) {
        Nodo nodoReferencia = buscarNodo(valorReferencia);
        // Si no existe o solo hay un nodo en el ciclo, no se puede eliminar el previo
        if (nodoReferencia == null || this.primerNodo == this.ultimoNodo) {
            return null;
        }

        // Buscar el nodo que esta dos posiciones atras del nodo de referencia
        Nodo nodoDosPosicionesAtras = nodoReferencia;
        while (nodoDosPosicionesAtras.liga.liga != nodoReferencia) {
            nodoDosPosicionesAtras = nodoDosPosicionesAtras.liga;
        }

        Nodo nodoAEliminar = nodoDosPosicionesAtras.liga;
        nodoDosPosicionesAtras.liga = nodoReferencia;

        if (nodoAEliminar == this.primerNodo) {
            this.primerNodo = nodoReferencia;
        }
        if (nodoAEliminar == this.ultimoNodo) {
            this.ultimoNodo = nodoDosPosicionesAtras;
        }

        return nodoAEliminar.info;
    }

    /**
     * Elimina el nodo que se encuentra despues del nodo de referencia.
     */
    @Override
    public Integer eliminarDespues(int valorReferencia) {
        Nodo nodoReferencia = buscarNodo(valorReferencia);
        if (nodoReferencia == null || this.primerNodo == this.ultimoNodo) {
            return null;
        }

        Nodo nodoAEliminar = nodoReferencia.liga;
        nodoReferencia.liga = nodoAEliminar.liga;

        if (nodoAEliminar == this.primerNodo) {
            this.primerNodo = nodoAEliminar.liga;
        }
        if (nodoAEliminar == this.ultimoNodo) {
            this.ultimoNodo = nodoReferencia;
        }

        return nodoAEliminar.info;
    }

    /**
     * Inserta un nuevo elemento al inicio del ciclo.
     */
    @Override
    public void insertarInicio(int valorAInsertar) {
        Nodo nuevoNodo = new Nodo(valorAInsertar);
        if (this.primerNodo == null) {
            nuevoNodo.liga = nuevoNodo;
            this.primerNodo = nuevoNodo;
            this.ultimoNodo = nuevoNodo;
            return;
        }

        nuevoNodo.liga = this.primerNodo;
        this.ultimoNodo.liga = nuevoNodo;
        this.primerNodo = nuevoNodo;
    }

    /**
     * Inserta un nuevo elemento al final del ciclo.
     */
    @Override
    public void insertarFinal(int valorAInsertar) {
        Nodo nuevoNodo = new Nodo(valorAInsertar);
        if (this.primerNodo == null) {
            nuevoNodo.liga = nuevoNodo;
            this.primerNodo = nuevoNodo;
            this.ultimoNodo = nuevoNodo;
            return;
        }

        nuevoNodo.liga = this.primerNodo;
        this.ultimoNodo.liga = nuevoNodo;
        this.ultimoNodo = nuevoNodo;
    }

    /**
     * Elimina el primer elemento del ciclo.
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
            this.primerNodo = this.primerNodo.liga;
            this.ultimoNodo.liga = this.primerNodo;
        }

        return valorEliminado;
    }

    /**
     * Elimina el ultimo elemento del ciclo.
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
            return valorEliminado;
        }

        // Buscar el penultimo nodo
        Nodo nodoActual = this.primerNodo;
        while (nodoActual.liga != this.ultimoNodo) {
            nodoActual = nodoActual.liga;
        }

        nodoActual.liga = this.primerNodo;
        this.ultimoNodo = nodoActual;
        return valorEliminado;
    }

    /**
     * Busca y elimina la primera coincidencia del valor solicitado en el ciclo.
     */
    @Override
    public boolean eliminarValor(int valorAEliminar) {
        if (this.primerNodo == null) {
            return false;
        }

        Nodo nodoAnterior = this.ultimoNodo;
        Nodo nodoActual = this.primerNodo;

        do {
            if (nodoActual.info == valorAEliminar) {
                if (this.primerNodo == this.ultimoNodo) {
                    this.primerNodo = null;
                    this.ultimoNodo = null;
                } else {
                    nodoAnterior.liga = nodoActual.liga;
                    if (nodoActual == this.primerNodo) {
                        this.primerNodo = nodoActual.liga;
                    }
                    if (nodoActual == this.ultimoNodo) {
                        this.ultimoNodo = nodoAnterior;
                    }
                }
                return true;
            }
            nodoAnterior = nodoActual;
            nodoActual = nodoActual.liga;
        } while (nodoActual != this.primerNodo);

        return false;
    }

    /**
     * Comprueba si un valor entero existe dentro del ciclo.
     */
    @Override
    public boolean buscar(int valorABuscar) {
        if (this.primerNodo == null) {
            return false;
        }

        Nodo nodoActual = this.primerNodo;
        do {
            if (nodoActual.info == valorABuscar) {
                return true;
            }
            nodoActual = nodoActual.liga;
        } while (nodoActual != this.primerNodo);

        return false;
    }

    /**
     * Comprueba si la lista circular esta vacia.
     */
    @Override
    public boolean estaVacia() {
        return this.primerNodo == null;
    }

    /**
     * Recorre todo el anillo de elementos una vez y los reune en una lista secuencial.
     */
    @Override
    public List<Integer> aLista() {
        List<Integer> listaValores = new ArrayList<>();
        if (this.primerNodo == null) {
            return listaValores;
        }

        Nodo nodoActual = this.primerNodo;
        do {
            listaValores.add(nodoActual.info);
            nodoActual = nodoActual.liga;
        } while (nodoActual != this.primerNodo);

        return listaValores;
    }

    @Override
    public String descripcion() {
        return "Lista circular simple: cada nodo apunta al siguiente; el ultimo apunta de regreso al primero.";
    }
}