package ListasDinamicas;

import java.util.ArrayList;
import java.util.List;

public class ListaCircularDoblementeLigada extends ListaDinamica {

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
    
        private Nodo buscarNodo(int ref) {
        if (P == null) {
            return null;
        }
        Nodo Q = P;
        do {
            if (Q.info == ref) {
                return Q;
            }
            Q = Q.der;
        } while (Q != P);
        return null;
    }

    @Override
    public boolean insertarAntes(int ref, int valor) {
        Nodo Q = buscarNodo(ref);
        if (Q == null) {
            return false;
        }
        Nodo N = new Nodo(valor);
        N.izq = Q.izq;
        N.der = Q;
        Q.izq.der = N;
        Q.izq = N;
        if (Q == P) {                        // antes del primero: N pasa a ser el primero
            P = N;
        }
        return true;
    }

    @Override
    public boolean insertarDespues(int ref, int valor) {
        Nodo Q = buscarNodo(ref);
        if (Q == null) {
            return false;
        }
        Nodo N = new Nodo(valor);
        N.izq = Q;
        N.der = Q.der;
        Q.der.izq = N;
        Q.der = N;
        if (Q == U) {                        // despues del ultimo: N pasa a ser el ultimo
            U = N;
        }
        return true;
    }

    @Override
    public Integer eliminarAntes(int ref) {
        Nodo Q = buscarNodo(ref);
        if (Q == null || P == U) {
            return null;
        }
        Nodo A = Q.izq;                      // el nodo que se elimina
        Nodo B = A.izq;                      // el que esta antes de A
        B.der = Q;
        Q.izq = B;
        if (A == P) {
            P = Q;
        }
        if (A == U) {
            U = B;
        }
        return A.info;
    }

    @Override
    public Integer eliminarDespues(int ref) {
        Nodo Q = buscarNodo(ref);
        if (Q == null || P == U) {
            return null;
        }
        Nodo S = Q.der;                      // el nodo que se elimina
        Nodo C = S.der;                      // el que esta despues de S
        Q.der = C;
        C.izq = Q;
        if (S == P) {
            P = C;
        }
        if (S == U) {
            U = Q;
        }
        return S.info;
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