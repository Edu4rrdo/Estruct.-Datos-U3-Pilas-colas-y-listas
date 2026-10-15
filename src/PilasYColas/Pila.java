package PilasYColas;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase Pila Dinamica (LIFO - Last In, First Out).
 * Hereda de EstructuraLineal.
 *
 * El ultimo elemento en entrar es el primero en salir.
 * Tanto la insercion (Push) como la eliminacion (Pop) ocurren en el TOPE.
 *
 * @author eduardo-b
 */
public class Pila extends EstructuraLineal {

    public Pila() {
        super();
    }

    /**
     * Operacion PUSH: Inserta un nuevo elemento en el tope de la pila.
     */
    @Override
    public void insertar(int valor) {
        Nodo Q = new Nodo(valor);
        Q.liga = this.P;
        this.P = Q;
    }

    /**
     * Operacion POP: Elimina y devuelve el elemento ubicado en el tope.
     * Retorna null si la pila se encuentra vacia.
     */
    @Override
    public Integer eliminar() {
        if (estaVacia()) {
            return null;
        }
        int valor = this.P.info;
        this.P = this.P.liga;
        return valor;
    }

    /**
     * Devuelve los elementos desde el TOPE hacia la BASE.
     */
    @Override
    public List<Integer> aLista() {
        List<Integer> lista = new ArrayList<>();
        Nodo temp = this.P;
        while (temp != null) {
            lista.add(temp.info);
            temp = temp.liga;
        }
        return lista;
    }

    // Metodos auxiliares con los nombres originales por si se ocupan
    public void insertarInicio(int valor) {
        insertar(valor);
    }

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
