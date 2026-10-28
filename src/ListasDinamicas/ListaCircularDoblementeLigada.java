package ListasDinamicas;

import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================================
 * CLASE: ListaCircularDoblementeLigada
 * ============================================================================
 * Implementacion de una Lista Circular Doblemente Ligada.
 *
 * Caracteristicas didacticas:
 * - Cada nodo posee dos apuntadores:
 *     1. izq: Referencia al nodo anterior en el anillo.
 *     2. der: Referencia al nodo siguiente en el anillo.
 * - Es una estructura cerrada y simetrica:
 *     - La liga derecha del 'ultimoNodo' apunta a 'primerNodo'.
 *     - La liga izquierda del 'primerNodo' apunta a 'ultimoNodo'.
 * - No existen punteros en null cuando la lista contiene al menos un nodo.
 * - Permite navegacion continua en cualquier sentido (horario o antihorario).
 *
 * @author eduardo-b
 * ============================================================================
 */
public class ListaCircularDoblementeLigada extends ListaDinamica {

    /**
     * Clase interna que define cada nodo de la lista circular doble.
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
     * Apuntador al primer elemento del anillo.
     */
    private Nodo primerNodo;

    /**
     * Apuntador al ultimo elemento del anillo.
     */
    private Nodo ultimoNodo;

    /**
     * Constructor: inicializa la lista circular doblemente ligada vacia.
     */
    public ListaCircularDoblementeLigada() {
        this.primerNodo = null;
        this.ultimoNodo = null;
    }

    /**
     * Metodo auxiliar para inicializar el primer y unico nodo de la lista.
     * Al ser el unico, sus ligas izquierda y derecha apuntan a si mismo.
     *
     * @param nodoUnico Nodo recien creado a enlazar.
     */
    private void configurarPrimerNodo(Nodo nodoUnico) {
        nodoUnico.izq = nodoUnico;
        nodoUnico.der = nodoUnico;
        this.primerNodo = nodoUnico;
        this.ultimoNodo = nodoUnico;
    }

    /**
     * Busca en el anillo el nodo que almacena el valor especificado.
     *
     * @param valorABuscar Valor entero de referencia.
     * @return El nodo encontrado, o null si no existe en la lista.
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
            nodoActual = nodoActual.der;
        } while (nodoActual != this.primerNodo);

        return null;
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

        Nodo nuevoNodo = new Nodo(valorAInsertar);
        nuevoNodo.izq = nodoReferencia.izq;
        nuevoNodo.der = nodoReferencia;
        nodoReferencia.izq.der = nuevoNodo;
        nodoReferencia.izq = nuevoNodo;

        // Si se inserto antes del primer nodo, el nuevo pasa a ser la cabeza
        if (nodoReferencia == this.primerNodo) {
            this.primerNodo = nuevoNodo;
        }
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

        Nodo nuevoNodo = new Nodo(valorAInsertar);
        nuevoNodo.izq = nodoReferencia;
        nuevoNodo.der = nodoReferencia.der;
        nodoReferencia.der.izq = nuevoNodo;
        nodoReferencia.der = nuevoNodo;

        // Si se inserto despues del ultimo nodo, el nuevo pasa a ser la cola
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
        // Si no existe o solo hay un nodo en el anillo
        if (nodoReferencia == null || this.primerNodo == this.ultimoNodo) {
            return null;
        }

        Nodo nodoAEliminar = nodoReferencia.izq;
        Nodo nodoAnteriorAlEliminado = nodoAEliminar.izq;

        nodoAnteriorAlEliminado.der = nodoReferencia;
        nodoReferencia.izq = nodoAnteriorAlEliminado;

        if (nodoAEliminar == this.primerNodo) {
            this.primerNodo = nodoReferencia;
        }
        if (nodoAEliminar == this.ultimoNodo) {
            this.ultimoNodo = nodoAnteriorAlEliminado;
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

        Nodo nodoAEliminar = nodoReferencia.der;
        Nodo nodoSiguienteAlEliminado = nodoAEliminar.der;

        nodoReferencia.der = nodoSiguienteAlEliminado;
        nodoSiguienteAlEliminado.izq = nodoReferencia;

        if (nodoAEliminar == this.primerNodo) {
            this.primerNodo = nodoSiguienteAlEliminado;
        }
        if (nodoAEliminar == this.ultimoNodo) {
            this.ultimoNodo = nodoReferencia;
        }

        return nodoAEliminar.info;
    }

    /**
     * Inserta un nuevo elemento al inicio del anillo.
     */
    @Override
    public void insertarInicio(int valorAInsertar) {
        Nodo nuevoNodo = new Nodo(valorAInsertar);
        if (this.primerNodo == null) {
            configurarPrimerNodo(nuevoNodo);
            return;
        }

        nuevoNodo.der = this.primerNodo;
        nuevoNodo.izq = this.ultimoNodo;
        this.primerNodo.izq = nuevoNodo;
        this.ultimoNodo.der = nuevoNodo;
        this.primerNodo = nuevoNodo;
    }

    /**
     * Inserta un nuevo elemento al final del anillo.
     */
    @Override
    public void insertarFinal(int valorAInsertar) {
        Nodo nuevoNodo = new Nodo(valorAInsertar);
        if (this.primerNodo == null) {
            configurarPrimerNodo(nuevoNodo);
            return;
        }

        nuevoNodo.izq = this.ultimoNodo;
        nuevoNodo.der = this.primerNodo;
        this.ultimoNodo.der = nuevoNodo;
        this.primerNodo.izq = nuevoNodo;
        this.ultimoNodo = nuevoNodo;
    }

    /**
     * Elimina el primer elemento del anillo.
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
            this.primerNodo.izq = this.ultimoNodo;
            this.ultimoNodo.der = this.primerNodo;
        }

        return valorEliminado;
    }

    /**
     * Elimina el ultimo elemento del anillo.
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
            this.ultimoNodo.der = this.primerNodo;
            this.primerNodo.izq = this.ultimoNodo;
        }

        return valorEliminado;
    }

    /**
     * Busca y elimina la primera coincidencia del valor solicitado.
     */
    @Override
    public boolean eliminarValor(int valorAEliminar) {
        if (this.primerNodo == null) {
            return false;
        }

        Nodo nodoActual = this.primerNodo;
        do {
            if (nodoActual.info == valorAEliminar) {
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
            nodoActual = nodoActual.der;
        } while (nodoActual != this.primerNodo);

        return false;
    }

    /**
     * Comprueba si un valor entero existe dentro de la lista.
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
            nodoActual = nodoActual.der;
        } while (nodoActual != this.primerNodo);

        return false;
    }

    /**
     * Comprueba si la lista circular doble esta vacia.
     */
    @Override
    public boolean estaVacia() {
        return this.primerNodo == null;
    }

    /**
     * Recorre todo el anillo de elementos una vez y los retorna en una lista secuencial.
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
            nodoActual = nodoActual.der;
        } while (nodoActual != this.primerNodo);

        return listaValores;
    }

    @Override
    public String descripcion() {
        return "Lista circular doblemente ligada: cada nodo apunta al anterior y al siguiente; el ultimo y el primero se enlazan entre si.";
    }
}