package ListasDinamicas;

import java.util.ArrayList;
import java.util.List;

public class ListaCircularSimple extends ListaDinamica {

    private static class Nodo {
        int info;
        Nodo liga;

        Nodo(int info) {
            this.info = info;
            this.liga = null;
        }
    }

    private Nodo P; // primer nodo
    private Nodo U; // ultimo nodo (U.liga siempre apunta a P)

    public ListaCircularSimple() {
        this.P = null;
        this.U = null;
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
            Q = Q.liga;
        } while (Q != P);
        return null;
    }

    @Override
    public boolean insertarAntes(int ref, int valor) {
        if (P == null) {
            return false;
        }
        Nodo T = U;                          // T = anterior de Q
        Nodo Q = P;
        do {
            if (Q.info == ref) {
                Nodo N = new Nodo(valor);
                N.liga = Q;
                T.liga = N;
                if (Q == P) {                // antes del primero: N pasa a ser el primero
                    P = N;
                }
                return true;
            }
            T = Q;
            Q = Q.liga;
        } while (Q != P);
        return false;
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
        if (Q == U) {                        // despues del ultimo: N pasa a ser el ultimo
            U = N;
        }
        return true;
    }

    @Override
    public Integer eliminarAntes(int ref) {
        Nodo Q = buscarNodo(ref);
        if (Q == null || P == U) {           // no existe, o solo hay un nodo
            return null;
        }
        Nodo X = Q;                          // X = nodo que esta 2 antes de Q
        while (X.liga.liga != Q) {
            X = X.liga;
        }
        Nodo A = X.liga;                     // A = el nodo que se elimina
        X.liga = Q;
        if (A == P) {
            P = Q;
        }
        if (A == U) {
            U = X;
        }
        return A.info;
    }

    @Override
    public Integer eliminarDespues(int ref) {
        Nodo Q = buscarNodo(ref);
        if (Q == null || P == U) {
            return null;
        }
        Nodo S = Q.liga;                     // el nodo que se elimina
        Q.liga = S.liga;
        if (S == P) {
            P = S.liga;
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
            Q.liga = Q;
            P = Q;
            U = Q;
            return;
        }
        Q.liga = P;
        U.liga = Q;
        P = Q;
    }

    @Override
    public void insertarFinal(int valor) {
        Nodo Q = new Nodo(valor);
        if (P == null) {
            Q.liga = Q;
            P = Q;
            U = Q;
            return;
        }
        Q.liga = P;
        U.liga = Q;
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
            P = P.liga;
            U.liga = P;
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
            return valor;
        }
        Nodo T = P;
        while (T.liga != U) {
            T = T.liga;
        }
        T.liga = P;
        U = T;
        return valor;
    }

    @Override
    public boolean eliminarValor(int valor) {
        if (P == null) {
            return false;
        }
        Nodo T = U;   // anterior de Q
        Nodo Q = P;
        do {
            if (Q.info == valor) {
                if (P == U) {          // era el unico nodo
                    P = null;
                    U = null;
                } else {
                    T.liga = Q.liga;
                    if (Q == P) {
                        P = Q.liga;
                    }
                    if (Q == U) {
                        U = T;
                    }
                }
                return true;
            }
            T = Q;
            Q = Q.liga;
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
            Q = Q.liga;
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
            Q = Q.liga;
        } while (Q != P);
        return res;
    }

    @Override
    public String descripcion() {
        return "Lista circular simple: cada nodo apunta al siguiente; el ultimo apunta de regreso al primero.";
    }
}