package PilasYColas;

import java.util.List;

/**
 * Clase Padre Abstracta para Pilas y Colas Dinamicas.
 *
 * Reune los atributos y metodos comunes para estructuras lineales enlazadas:
 * - El apuntador inicial P
 * - Verificacion de estructura vacia
 * - Operaciones base de insertar y eliminar
 * - Textos informativos para la interfaz grafica (PanelBase)
 *
 * @author eduardo-b
 */
public abstract class EstructuraLineal {

    // Apuntador al primer nodo (Tope en Pila / Primer elemento en Cola)
    public Nodo P;

    // Constructor: inicializa la estructura vacia
    public EstructuraLineal() {
        this.P = null;
    }

    // Metodo comun: verifica si la estructura no contiene elementos
    public boolean estaVacia() {
        return this.P == null;
    }

    // Metodo comun: vacia la estructura
    public void vaciar() {
        this.P = null;
    }

    // Metodos abstractos que cada estructura hija implementa con su propia regla:
    // Pila: LIFO (Last In, First Out)
    // Cola: FIFO (First In, First Out)
    public abstract void insertar(int valor);

    public abstract Integer eliminar();

    // Devuelve los elementos ordenados en una lista para facilitar el dibujo en la GUI
    public abstract List<Integer> aLista();

    // Metodos que definen los textos en la interfaz grafica PanelBase
    public abstract String getTitulo();

    public abstract String getTextoInsertar();

    public abstract String getTextoEliminar();

    public abstract String getIndicadorArriba();

    public abstract String getIndicadorAbajo();
}
