package ColasDinamicas;

public class Nodo {

    // Atributos
    public int info;
    public Nodo liga;

    // metodo constructor
    public Nodo(int info) {
        this.info = info;
        this.liga = null;
    }
}
