package ListasDinamicas;

import java.util.ArrayList;
import java.util.List;

public class ListaLigadaSimple extends ListaDinamica {

    private static class Nodo {
        int info;
        Nodo liga;

        Nodo(int info) {
            this.info = info;
            this.liga = null;
        }
    }

    private Nodo P; // primer nodo

    public ListaLigadaSimple() {
        this.P = null;
    }

    @Override
    public void insertarInicio(int valor) {
        Nodo Q = new Nodo(valor);
        Q.liga = P;
        P = Q;
    }

    @Override
    public void insertarFinal(int valor) {
        Nodo Q = new Nodo(valor);
        if (P == null) {
            P = Q;
            return;
        }
        Nodo T = P;
        while (T.liga != null) {
            T = T.liga;
        }
        T.liga = Q;
    }

    @Override
    public Integer eliminarInicio() {
        if (P == null) {
            return null;
        }
        int valor = P.info;
        P = P.liga;
        return valor;
    }

    @Override
    public Integer eliminarFinal() {
        if (P == null) {
            return null;
        }
        if (P.liga == null) {
            int valor = P.info;
            P = null;
            return valor;
        }
        Nodo Q = P;
        Nodo T = null;
        while (Q.liga != null) {
            T = Q;
            Q = Q.liga;
        }
        T.liga = null;
        return Q.info;
    }

    @Override
    public boolean eliminarValor(int valor) {
        if (P == null) {
            return false;
        }
        if (P.info == valor) {
            P = P.liga;
            return true;
        }
        Nodo Q = P.liga;
        Nodo T = P;
        while (Q != null) {
            if (Q.info == valor) {
                T.liga = Q.liga;
                return true;
            }
            T = Q;
            Q = Q.liga;
        }
        return false;
    }

    @Override
    public boolean buscar(int valor) {
        Nodo Q = P;
        while (Q != null) {
            if (Q.info == valor) {
                return true;
            }
            Q = Q.liga;
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
            Q = Q.liga;
        }
        return res;
    }

    @Override
    public String descripcion() {
        return "Lista ligada simple: cada nodo apunta solo al siguiente; el ultimo apunta a null.";
    }
}