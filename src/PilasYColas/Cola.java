package PilasYColas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Clase Cola Dinamica (FIFO - First In, First Out).
 * Hereda de EstructuraLineal.
 *
 * El primer elemento en entrar es el primero en salir.
 * Se encola por un extremo y se desencola por el otro.
 *
 * @author eduardo-b
 */
public class Cola extends EstructuraLineal {

    public Cola() {
        super();
    }

    /**
     * Operacion ENCOLAR: Inserta el nuevo elemento a la estructura.
     */
    @Override
    public void insertar(int valor) {
        Nodo nuevo = new Nodo(valor);
        nuevo.liga = this.P;
        this.P = nuevo;
    }

    /**
     * Operacion DESENCOLAR: Quita el elemento mas antiguo (el que entro primero).
     * Retorna null si la cola se encuentra vacia.
     */
    @Override
    public Integer eliminar() {
        if (estaVacia()) {
            return null;
        }

        // Si solo hay un nodo
        if (this.P.liga == null) {
            int valor = this.P.info;
            this.P = null;
            return valor;
        }

        // Recorremos hasta el ultimo nodo
        Nodo Q = this.P;
        Nodo T = null;
        while (Q.liga != null) {
            T = Q;
            Q = Q.liga;
        }
        int valor = Q.info;
        T.liga = null; // desconectamos el ultimo
        return valor;
    }

    /**
     * Devuelve los elementos en orden desde el FRENTE (proximo a salir) hacia el FINAL.
     */
    @Override
    public List<Integer> aLista() {
        List<Integer> lista = new ArrayList<>();
        Nodo temp = this.P;
        while (temp != null) {
            lista.add(temp.info);
            temp = temp.liga;
        }
        // Invertimos para que el Frente quede al inicio de la lista visual
        Collections.reverse(lista);
        return lista;
    }

    // Metodos auxiliares con los nombres originales por si se ocupan
    public void insertarInicio(int valor) {
        insertar(valor);
    }

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
