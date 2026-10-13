package ListasDinamicas;

import java.util.List;

// Interfaz comun para los 4 tipos de lista.
// Asi un solo panel grafico puede trabajar con cualquiera de ellas.
public interface ListaDinamica {

    void insertarInicio(int valor);

    void insertarFinal(int valor);

    Integer eliminarInicio();   // regresa null si la lista esta vacia

    Integer eliminarFinal();    // regresa null si la lista esta vacia

    boolean eliminarValor(int valor);   // elimina la primera coincidencia

    boolean buscar(int valor);

    boolean estaVacia();

    List<Integer> aLista();     // recorre la lista y regresa sus valores en orden

    String descripcion();       // texto que explica como esta enlazada la lista
}