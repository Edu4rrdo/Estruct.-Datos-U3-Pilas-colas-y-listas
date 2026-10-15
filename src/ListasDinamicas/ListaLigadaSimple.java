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
    private Nodo buscarNodo(int ref){
         Nodo Q = P;
        while (Q != null && Q.info != ref) {
            Q = Q.liga;
        }
        return Q;
    }
    
     @Override
    public boolean insertarAntes(int ref, int valor) {
        if (P == null) {
            return false;
        }
        if (P.info == ref) {            // la referencia es el primero
            insertarInicio(valor);
            return true;
        }
        Nodo T = P;                      // T siempre va un nodo atras de Q
        Nodo Q = P.liga;
        while (Q != null && Q.info != ref) {
            T = Q;
            Q = Q.liga;
        }
        if (Q == null) {
            return false;
        }
        Nodo N = new Nodo(valor);
        N.liga = Q;
        T.liga = N;
        return true;
    }

    @Override
    public boolean insertarDespues(int ref, int valor) {
        Nodo Q = buscarNodo(ref);
        if (Q == null) {
            return false;
        }
        Nodo N = new Nodo(valor);
        N.liga = Q.liga;
        Q.liga = N;
        return true;
    }

    @Override
    public Integer eliminarAntes(int ref) {
        if (P == null || P.info == ref) {   // vacia o ref es el primero: no hay nada antes
            return null;
        }
        Nodo A = null;                       // A = nodo anterior a T
        Nodo T = P;                          // T = nodo anterior a Q (el que se elimina)
        Nodo Q = P.liga;
        while (Q != null && Q.info != ref) {
            A = T;
            T = Q;
            Q = Q.liga;
        }
        if (Q == null) {
            return null;
        }
        if (A == null) {                     // T es el primero
            P = Q;
        } else {
            A.liga = Q;
        }
        return T.info;
    }

    @Override
    public Integer eliminarDespues(int ref) {
        Nodo Q = buscarNodo(ref);
        if (Q == null || Q.liga == null) {
            return null;
        }
        Nodo S = Q.liga;                     // el nodo que se elimina
        Q.liga = S.liga;
        return S.info;
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