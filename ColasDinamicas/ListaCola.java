package ColasDinamicas;

public class ListaCola {
    public Nodo P;

    // Constructor
    public ListaCola() {
        this.P = null;

    }

    // Metodos

    public void insertarInicio(int var1) {
        Nodo var2 = new Nodo(var1);
        var2.liga = this.P;
        this.P = var2;
    }

    public Integer quitarFinal() {
        if (this.P == null) {
            return null;
        }

        Nodo Q = this.P;
        Nodo T = null;
        if (this.P.liga == null) {
            int valor = this.P.info;
            this.P = null;
            return valor;
        } else {
            while (Q.liga != null) {
                T = Q;
                Q = Q.liga;
            }
            int valor = Q.info;
            T.liga = null;
            return valor;
        }
    }
}
