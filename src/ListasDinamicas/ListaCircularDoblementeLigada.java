package ListasDinamicas;

import java.util.ArrayList;
import java.util.List;

public class ListaCircularDoblementeLigada implements ListaDinamica {

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
    private Nodo U; // ultimo nodo (U.der = P  y  P.izq = U)

    public ListaCircularDoblementeLigada() {
        this.P = null;
        this.U = null;
    }

    private void primerNodo(Nodo Q) {
        Q.izq = Q;
        Q.der = Q;
        P = Q;
        U = Q;
    }

    @Override
    public void insertarInicio(int valor) {
        Nodo Q = new Nodo(valor);
        if (P == null) {
            primerNodo(Q);
            return;
        }
        Q.der = P;
        Q.izq = U;
        P.izq = Q;
        U.der = Q;
        P = Q;
    }

    @Override
    public void insertarFinal(int valor) {
        Nodo Q = new Nodo(valor);
        if (P == null) {
            primerNodo(Q);
            return;
        }
        Q.izq = U;
        Q.der = P;
        U.der = Q;
        P.izq = Q;
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
            P.izq = U;
            U.der = P;
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
            U.der = P;
            P.izq = U;
        }
        return valor;
    }

    @Override
    public boolean eliminarValor(int valor) {
        if (P == null) {
            return false;
        }
        Nodo Q = P;
        do {
            if (Q.info == valor) {
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
            Q = Q.der;
        } while (Q != P);
        return false;
    }

    @Override
    public boolean buscar(int valor) {
        if (P == null) {
            return false;
        }
        Nodo Q = P;
        do {
            if (Q.info == valor) {
                return true;
            }
            Q = Q.der;
        } while (Q != P);
        return false;
    }

    @Override
    public boolean estaVacia() {
        return P == null;
    }

    @Override
    public List<Integer> aLista() {
        List<Integer> res = new ArrayList<>();
        if (P == null) {
            return res;
        }
        Nodo Q = P;
        do {
            res.add(Q.info);
            Q = Q.der;
        } while (Q != P);
        return res;
    }

    @Override
    public String descripcion() {
        return "Lista circular doblemente ligada: cada nodo apunta al anterior y al siguiente; el ultimo y el primero se enlazan entre si.";
    }
}