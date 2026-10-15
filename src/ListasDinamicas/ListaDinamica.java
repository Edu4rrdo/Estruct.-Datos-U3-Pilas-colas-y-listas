package ListasDinamicas;

import java.util.List;

/**
 * Clase Padre Abstracta para todas las Listas Dinamicas.
 *
 * Sirve como base comun para:
 * 1. Lista Ligada Simple
 * 2. Lista Doblemente Ligada
 * 3. Lista Circular Simple
 * 4. Lista Circular Doblemente Ligada
 *
 * Permite que la interfaz grafica (PanelTipoLista) trabaje con cualquier
 * tipo de lista mediante polimorfismo.
 *
 * @author eduardo-b
 */
public abstract class ListaDinamica {

    // Metodos abstractos que cada lista enlazada implementa segun sus ligas
    public abstract void insertarInicio(int valor);

    public abstract void insertarFinal(int valor);

    public abstract Integer eliminarInicio();   // regresa null si la lista esta vacia

    public abstract Integer eliminarFinal();    // regresa null si la lista esta vacia

    public abstract boolean eliminarValor(int valor);   // elimina la primera coincidencia
    
    public abstract boolean insertarAntes(int ref, int valor); // false si ref no existe
    
    public abstract boolean insertarDespues(int ref, int valor); // false si ref no existe
    
    public abstract Integer eliminarAntes(int ref);     // null si ref no existe o no tiene nodo antes
    
    public abstract Integer eliminarDespues(int ref);
    
    public abstract boolean buscar(int valor);

    public abstract boolean estaVacia();

    public abstract List<Integer> aLista();     // recorre la lista y regresa sus valores en orden

    public abstract String descripcion();       // texto que explica como esta enlazada la lista
}