package PilasYColas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ============================================================================
 * CLASE: Cola (Cola Dinamica Enlazada)
 * ============================================================================
 * Estructura de datos lineal que opera bajo el principio FIFO:
 * "First In, First Out" (El primero en entrar es el primero en salir).
 *
 * Caracteristicas principales:
 * - Los elementos se ingresan por un extremo (FINAL de la cola - Encolar).
 * - Los elementos se retiran por el extremo opuesto (FRENTE de la cola - Desencolar).
 * - Modela el comportamiento de una fila en la vida real (atencion por turno de llegada).
 *
 * @author eduardo-b
 * ============================================================================
 */
public class Cola extends EstructuraLineal {

    /**
     * Constructor: crea una cola dinamica vacia.
     */
    public Cola() {
        super();
    }

    /**
     * Operacion ENCOLAR (Insertar):
     * Agrega un nuevo elemento a la estructura enlazada.
     * En esta implementacion por nodos enlazados, el nuevo nodo se enlaza
     * al inicio de la cadena interna y se convertira en el elemento mas reciente (FINAL).
     *
     * @param valorAInsertar Numero entero a encolar.
     */
    @Override
    public void insertar(int valorAInsertar) {
        // 1. Crear el nuevo nodo que contendra el dato
        Nodo nuevoNodo = new Nodo(valorAInsertar);

        // 2. Conectar la liga del nuevo nodo a la cadena existente
        nuevoNodo.liga = this.P;

        // 3. Actualizar la referencia principal para que apunte al nuevo nodo
        this.P = nuevoNodo;
    }

    /**
     * Operacion DESENCOLAR (Eliminar):
     * Extrae y devuelve el elemento que entro primero (el mas antiguo, FRENTE).
     * Recorre la lista de nodos hasta el extremo opuesto para retirar dicho elemento.
     *
     * @return El valor entero desencolado, o null si la cola esta vacia.
     */
    @Override
    public Integer eliminar() {
        // Caso 1: La cola no contiene elementos
        if (estaVacia()) {
            return null;
        }

        // Caso 2: Solo hay un elemento en la cola
        if (this.P.liga == null) {
            int valorEliminado = this.P.info;
            this.P = null; // La cola queda vacia
            return valorEliminado;
        }

        // Caso 3: Hay dos o mas elementos.
        // Se recorre la cadena con dos apuntadores hasta ubicar el ultimo nodo (el primero que entro)
        Nodo nodoActual = this.P;
        Nodo nodoAnterior = null;

        while (nodoActual.liga != null) {
            nodoAnterior = nodoActual;
            nodoActual = nodoActual.liga;
        }

        // El nodoActual es el frente de la cola (el mas antiguo)
        int valorEliminado = nodoActual.info;

        // Se desconecta el ultimo nodo desenlazando la liga de su predecesor
        nodoAnterior.liga = null;

        return valorEliminado;
    }

    /**
     * Devuelve los elementos ordenados desde el FRENTE (proximo en salir)
     * hacia el FINAL (el recien llegado).
     *
     * @return Lista con los elementos en orden cronologico de salida.
     */
    @Override
    public List<Integer> aLista() {
        List<Integer> listaElementos = new ArrayList<>();
        Nodo nodoActual = this.P;

        // Recorrer la cadena de nodos y acumular los valores
        while (nodoActual != null) {
            listaElementos.add(nodoActual.info);
            nodoActual = nodoActual.liga;
        }

        // Invertimos la coleccion para que visualmente el FRENTE quede
        // a la izquierda (primero) y el FINAL a la derecha
        Collections.reverse(listaElementos);
        return listaElementos;
    }

    /**
     * Metodo auxiliar para insertar un dato (equivalente a encolar).
     *
     * @param valorAInsertar Valor a agregar.
     */
    public void insertarInicio(int valorAInsertar) {
        insertar(valorAInsertar);
    }

    /**
     * Metodo auxiliar para extraer el elemento al frente (equivalente a desencolar).
     *
     * @return El valor extraido.
     */
    public Integer quitarFinal() {
        return eliminar();
    }

    @Override
    public String getTitulo() {
        return "Cola Dinamica (FIFO)";
    }

    @Override
    public String getTextoInsertar() {
        return "Insertar (Encolar)";
    }

    @Override
    public String getTextoEliminar() {
        return "Eliminar (Desencolar)";
    }

    @Override
    public String getIndicadorArriba() {
        return "▼ FRENTE (Proximo a salir - Desencolar) ▼";
    }

    @Override
    public String getIndicadorAbajo() {
        return "▲ FINAL (Ultimo en entrar - Encolado) ▲";
    }
}
