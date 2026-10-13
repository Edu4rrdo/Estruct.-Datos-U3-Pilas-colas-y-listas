package ListasDinamicas;

import java.util.ArrayList;
import java.util.List;

public class ListaDoblementeLigada extends ListaDinamica {

    private static class Nodo {
        int info;
        Nodo izq; // anterior
        Nodo der; // siguiente

        Nodo(int info) {
            this.info = info;
            this.izq = null;
            this.der = null;
        }
    }

    private Nodo P; // primer nodo
    private Nodo U; // ultimo nodo

    public ListaDoblementeLigada() {
        this.P = null;
        this.U = null;
    }

    @Override
    public void insertarInicio(int valor) {
        Nodo Q = new Nodo(valor);
        if (P == null) {
            P = Q;
            U = Q;
            return;
        }
        Q.der = P;
        P.izq = Q;
        P = Q;
    }

    @Override
    public void insertarFinal(int valor) {
        Nodo Q = new Nodo(valor);
        if (P == null) {
            P = Q;
            U = Q;
            return;
        }
        Q.izq = U;
        U.der = Q;
        U = Q;
    }

    @Override
    public Integer eliminarInicio() {
        if (P == null) {
            return null;
        }
        int valor = P.info;
        if (P == U) {
            P = null;
            U = null;
        } else {
            P = P.der;
            P.izq = null;
        }
        return valor;
    }

    @Override
    public Integer eliminarFinal() {
        if (P == null) {
            return null;
        }
        int valor = U.info;
        if (P == U) {
            P = null;
            U = null;
        } else {
            U = U.izq;
            U.der = null;
        }
        return valor;
    }

    @Override
    public boolean eliminarValor(int valor) {
        Nodo Q = P;
        while (Q != null && Q.info != valor) {
            Q = Q.der;
        }
        if (Q == null) {
            return false;
        }
        if (Q == P) {
            eliminarInicio();
        } else if (Q == U) {
            eliminarFinal();
        } else {
            Q.izq.der = Q.der;
            Q.der.izq = Q.izq;
        }
        return true;
    }

    @Override
    public boolean buscar(int valor) {
        Nodo Q = P;
        while (Q != null) {
            if (Q.info == valor) {
                return true;
            }
            Q = Q.der;
        }
        return false;
    }

    @Override
    public boolean estaVacia() {
        return P == null;
    }

    @Override
    public List<Integer> aLista() {
        List<Integer> res = new ArrayList<>();
        Nodo Q = P;
        while (Q != null) {
            res.add(Q.info);
            Q = Q.der;
        }
        return res;
    }

    @Override
    public String descripcion() {
        return "Lista doblemente ligada: cada nodo apunta al anterior y al siguiente; los extremos apuntan a null.";
    }
}