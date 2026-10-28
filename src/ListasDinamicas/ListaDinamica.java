package ListasDinamicas;

import java.util.List;

/**
 * ============================================================================
 * CLASE ABSTRACTA: ListaDinamica
 * ============================================================================
 * Clase base y modelo comun para los distintos tipos de Listas Dinamicas
 * Enlazadas implementadas en este proyecto:
 *
 * 1. Lista Ligada Simple (unidireccional con fin en null).
 * 2. Lista Doblemente Ligada (bidireccional con extremos en null).
 * 3. Lista Circular Simple (unidireccional cerrada en bucle).
 * 4. Lista Circular Doblemente Ligada (bidireccional cerrada en bucle).
 *
 * Facilita el polimorfismo en la interfaz grafica (PanelTipoLista) permitiendo
 * interactuar con cualquier estructura de lista usando una interfaz homogenea.
 *
 * @author eduardo-b
 * ============================================================================
 */
public abstract class ListaDinamica {

    /**
     * Inserta un nuevo elemento al principio de la lista.
     *
     * @param valorAInsertar Numero entero a colocar al inicio.
     */
    public abstract void insertarInicio(int valorAInsertar);

    /**
     * Inserta un nuevo elemento al final de la lista.
     *
     * @param valorAInsertar Numero entero a colocar al final.
     */
    public abstract void insertarFinal(int valorAInsertar);

    /**
     * Elimina y retorna el elemento ubicado al principio de la lista.
     *
     * @return El valor entero eliminado, o null si la lista esta vacia.
     */
    public abstract Integer eliminarInicio();

    /**
     * Elimina y retorna el elemento ubicado al final de la lista.
     *
     * @return El valor entero eliminado, o null si la lista esta vacia.
     */
    public abstract Integer eliminarFinal();

    /**
     * Busca y elimina la primera coincidencia del valor solicitado.
     *
     * @param valorAEliminar Numero entero a remover.
     * @return true si se encontro y elimino con exito, false si no existe o la lista esta vacia.
     */
    public abstract boolean eliminarValor(int valorAEliminar);

    /**
     * Inserta un nuevo elemento antes de un nodo de referencia especifico.
     *
     * @param valorReferencia Valor del nodo existente que sirve como punto de guia.
     * @param valorAInsertar  Nuevo numero entero a posicionar antes de la referencia.
     * @return true si la operacion fue exitosa, false si el valor de referencia no existe.
     */
    public abstract boolean insertarAntes(int valorReferencia, int valorAInsertar);

    /**
     * Inserta un nuevo elemento despues de un nodo de referencia especifico.
     *
     * @param valorReferencia Valor del nodo existente que sirve como punto de guia.
     * @param valorAInsertar  Nuevo numero entero a posicionar despues de la referencia.
     * @return true si la operacion fue exitosa, false si el valor de referencia no existe.
     */
    public abstract boolean insertarDespues(int valorReferencia, int valorAInsertar);

    /**
     * Elimina el nodo que se encuentra inmediatamente antes del nodo de referencia.
     *
     * @param valorReferencia Valor del nodo de guia.
     * @return El entero que fue eliminado, o null si la referencia no existe o no tiene nodo previo.
     */
    public abstract Integer eliminarAntes(int valorReferencia);

    /**
     * Elimina el nodo que se encuentra inmediatamente despues del nodo de referencia.
     *
     * @param valorReferencia Valor del nodo de guia.
     * @return El entero que fue eliminado, o null si la referencia no existe o no tiene nodo posterior.
     */
    public abstract Integer eliminarDespues(int valorReferencia);

    /**
     * Comprueba si un valor entero existe dentro de alguno de los nodos de la lista.
     *
     * @param valorABuscar Numero entero a localizar.
     * @return true si el valor existe en la lista, false en caso contrario.
     */
    public abstract boolean buscar(int valorABuscar);

    /**
     * Verifica si la lista carece de nodos enlazados.
     *
     * @return true si la lista esta vacia, false si contiene al menos un elemento.
     */
    public abstract boolean estaVacia();

    /**
     * Recorre secuencialmente todos los nodos y recopila sus datos en una lista de Java
     * para facilitar su representacion en los componentes graficos.
     *
     * @return Coleccion de enteros con el recorrido ordenado de la lista.
     */
    public abstract List<Integer> aLista();

    /**
     * Proporciona un texto didactico que describe como estan estructurados
     * los enlaces (ligas) en este tipo de lista.
     *
     * @return Descripcion explicativa de la estructura de datos.
     */
    public abstract String descripcion();
}